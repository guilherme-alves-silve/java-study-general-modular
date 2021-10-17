package br.com.guilhermealvessilve.certification.study.datastructure.tree.btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTree2 {

    private final int m;
    private final int minKeys;
    private int size;
    private Node2 root;

    public BTree2(int m) {
        this.m = m;
        this.minKeys = (int) Math.ceil(m / 2.0) - 1;
    }

    public Node2 getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "BTree{"
                + "root=" + root
                + '}';
    }

    public Entry2 searchEntry(int key) {
        return searchEntry(root, key);
    }

    private Entry2 searchEntry(Node2 node, int key) {
        if (node == null) {
            return null;
        }
        // 使用二分查找法定位下标
        int index = Collections.binarySearch(node.getEntrys(), new Entry2(key, null));
        if (index >= 0) {
            return node.getEntrys().get(index);
        } else {
            if (node.getChildNodes().size() == 0) {
                return null;
            }
            return searchEntry(node.getChildNodes().get(-index - 1), key);
        }
    }

    /**
     * 根据键搜索记录所在节点
     *
     * @param key
     * @return
     */
    public Node2 searchNode(int key) {
        return searchNode(root, key);
    }

    /**
     * 根据键搜索记录所在节点 - 递归
     *
     * @param node
     * @param key
     * @return
     */
    private Node2 searchNode(Node2 node, int key) {
        if (node == null) {
            return null;
        }
        // 使用二分查找法定位下标
        int index = Collections.binarySearch(node.getEntrys(), new Entry2(key, null));
        if (index >= 0) {
            return node;
        } else {
            if (node.getChildNodes().size() == 0) {
                return null;
            }
            return searchNode(node.getChildNodes().get(-index - 1), key);
        }
    }

    /**
     * 添加元素
     *
     * @param entry
     */
    public boolean add(Entry2 entry) {
        // root为空，直接创建
        if (root == null) {
            ++size;
            Node2 node = new Node2();
            node.add(entry);
            root = node;
        }
        add(root, entry);
        
        return true;
    }

    /**
     * 添加元素 - 递归
     *
     * @param node
     * @param entry
     */
    private void add(Node2 node, Entry2 entry) {

        // 当前节点为叶子节点
        if (node.getChildNodes().size() == 0) {

            ++size;
            // 如果当前节点元素未满，直接添加元素
            if (node.getEntrys().size() < m - 1) {
                node.add(entry);
                return;
            }
            // 如果当前节点元素已满，则分裂当前节点
            node.add(entry);
            split(node);
            return;
        }

        // 当前节点为内部节点，继续往下调用（新插入的节点，只能是叶子节点）
        // 使用二分查找法找到要插入的分支
        int index = Collections.binarySearch(node.getEntrys(), entry);
        if (index < 0) {
            add(node.getChildNodes().get(-index - 1), entry);
        }
    }

    /**
     * 分离当前节点
     *
     * @param node
     */
    private void split(Node2 node) {
        int mid = node.getEntrys().size() / 2;
        // 分隔值
        Entry2 separateEntry = node.getEntrys().get(mid);
        // 分离后的左节点
        Node2 leftNode = new Node2();
        leftNode.getEntrys().addAll(node.getEntrys().subList(0, mid));
        // 分离后的右节点
        Node2 rightNode = new Node2();
        rightNode.getEntrys().addAll(node.getEntrys().subList(mid + 1, node.getEntrys().size()));
        // 分离子节点
        if (node.getChildNodes().size() > 0) {
            List<Node2> leftChildNode = node.getChildNodes().subList(0, mid + 1);
            for (Node2 temp : leftChildNode) {
                temp.setParentNode(leftNode);
            }
            leftNode.getChildNodes().addAll(leftChildNode);

            List<Node2> rightChildNode = node.getChildNodes().subList(mid + 1, node.getEntrys().size() + 1);
            for (Node2 temp : rightChildNode) {
                temp.setParentNode(rightNode);
            }
            rightNode.getChildNodes().addAll(rightChildNode);
        }

        // 当前节点为根节点
        if (node.parentNode == null) {
            Node2 newRoot = new Node2();
            newRoot.add(separateEntry);
            root = newRoot;
            leftNode.parentNode = root;
            rightNode.parentNode = root;
            root.addChild(leftNode).addChild(rightNode);
        } else {
            node.parentNode.add(separateEntry);
            leftNode.parentNode = node.parentNode;
            rightNode.parentNode = node.parentNode;
            node.parentNode.addChild(leftNode).addChild(rightNode);
            node.parentNode.getChildNodes().remove(node);
            // 若其父节点溢出，继续分裂
            if (node.parentNode.getEntrys().size() > m - 1) {
                split(node.parentNode);
            }
        }
    }

    public boolean remove(int key) {
        Node2 node = searchNode(key);
        if (node == null) {
            return false;
        }

        --size;
        int keyIndex = Collections.binarySearch(node.getEntrys(), new Entry2(key, null));
        node.getEntrys().remove(keyIndex);
        if (node.getEntrys().size() < minKeys) {
            afterDeletingHandler(node, keyIndex);
        }

        return true;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * 删除后处理：当前节点元素数小于限定值，则根据不同场景选择进行合并、左旋转、右旋转
     *
     * @param node
     */
    private void afterDeletingHandler(Node2 node, int deletedKeyIndex) {

        // 该节点为内部节点
        if (node.getChildNodes().size() > 0) {
            // 获取删除元素的左右子节点
            Node2 leftChildNode = node.getChildNodes().get(deletedKeyIndex);
            Node2 rightChildNode = node.getChildNodes().get(deletedKeyIndex + 1);

            if (leftChildNode == null || rightChildNode == null) {
                System.out.println("");
            }
            
            /*
            var greatestLeft = getGreatestNode(leftChildNode);
            var smallestRight = getSmallestNode(rightChildNode);
            
            int greatestLeftSize = greatestLeft == null ? 0 : greatestLeft.entrys.size();
            int smallestRightSize = smallestRight == null ? 0 : smallestRight.entrys.size();
            Entry2 replaceValue = (greatestLeftSize > smallestRightSize)
                    ? removeGreatestValue(greatestLeft)
                    : removeSmallestValue(smallestRight);
            node.add(replaceValue);
            
            int maxSize = Math.max(greatestLeftSize, smallestRightSize);  
            */
            
            int leftEntrysSize = leftChildNode == null ? 0 : leftChildNode.entrys.size();
            int rightEntrysSize = rightChildNode == null ? 0 : rightChildNode.entrys.size();
            
            if (leftEntrysSize < minKeys || rightEntrysSize < minKeys) {
                merge(leftChildNode, rightChildNode);
                return;
            }
            
            int maxSize = Math.max(leftEntrysSize, rightEntrysSize);
            Entry2 entry;
            if (maxSize == leftEntrysSize) {
                entry = leftChildNode.getEntrys().get(leftChildNode.getEntrys().size() - 1);
                leftChildNode.getEntrys().remove(entry);
            } else {
                entry = rightChildNode.getEntrys().get(0);
                rightChildNode.getEntrys().remove(entry);
            }
            node.add(entry);
            return;
        }

        Node2 parentNode = node.parentNode;
        int nodeIndex = parentNode.getChildNodes().indexOf(node);
        Node2 leftNode = nodeIndex > 0 ? parentNode.getChildNodes().get(nodeIndex - 1) : null;
        Node2 rightNode = nodeIndex == parentNode.getChildNodes().size() - 1 ? null : parentNode.getChildNodes().get(nodeIndex + 1);

        int leftEntrysSize = leftNode == null ? 0 : leftNode.entrys.size();
        int rightEntrysSize = rightNode == null ? 0 : rightNode.entrys.size();
        int maxSize = Math.max(leftEntrysSize, rightEntrysSize);

        if (maxSize <= minKeys) {
            if (leftNode != null) {
                merge(node, leftNode, nodeIndex - 1);
            } else if (rightNode != null) {
                merge(node, rightNode, nodeIndex);
            }
            return;
        }

        if (maxSize == leftEntrysSize) {
            rightRotate(node, nodeIndex, leftNode);
        } else {
            leftRotate(node, nodeIndex, rightNode);
        }
    }
    
    private Node2 getGreatestNode(Node2 nodeToGet) {
        Node2 node = nodeToGet;
        while (node.childNodes.size() > 0) {
            node = node.childNodes.get(node.childNodes.size() - 1);
        }
        return node;
    }
    
    private Entry2 removeGreatestValue(Node2 node) {
        Entry2 entry = null;
        if (node.entrys.size() > 0) {
            entry = node.entrys.get(node.entrys.size() - 1);
            node.entrys.remove(node.entrys.size() - 1);
        }
        return entry;
    }
    
    private Node2 getSmallestNode(Node2 nodeToGet) {
        Node2 node = nodeToGet;
        while (node.childNodes.size() > 0) {
            node = node.childNodes.get(0);
        }
        return node;
    }
    
    private Entry2 removeSmallestValue(Node2 node) {
        Entry2 entry = null;
        if (node.entrys.size() > 0) {
            entry = node.entrys.get(0);
            node.entrys.remove(0);
        }
        return entry;
    }
    
    /**
     * 将当前节点与兄弟节点、中间值合并
     *
     * @param node 当前节点
     * @param brotherNode 兄弟节点
     * @param parentEntryIndex 中间值
     */
    private void merge(Node2 node, Node2 brotherNode, int parentEntryIndex) {
        // 创建新的节点
        Node2 parentNode = node.getParentNode();
        Node2 newNode = new Node2();
        newNode.getEntrys().addAll(node.getEntrys());
        newNode.getEntrys().addAll(brotherNode.getEntrys());
        newNode.add(parentNode.getEntrys().get(parentEntryIndex));
        // 删除原节点及关系
        parentNode.getEntrys().remove(parentEntryIndex);
        parentNode.getChildNodes().remove(node);
        parentNode.getChildNodes().remove(brotherNode);
        if (node.getChildNodes().size() > 0) {
            for (Node2 childNode : node.getChildNodes()) {
                newNode.addChild(childNode);
                childNode.setParentNode(newNode);
            }
        }
        if (brotherNode.getChildNodes().size() > 0) {
            for (Node2 childNode : brotherNode.getChildNodes()) {
                newNode.addChild(childNode);
                childNode.setParentNode(newNode);
            }
        }
        // 若原节点为根节点，则当前节点为新的根节点
        if (parentNode.getEntrys().size() == 0) {
            root = newNode;
            return;
        } else {
            parentNode.addChild(newNode);
            newNode.setParentNode(parentNode);
        }

        // 合并后，若父节点的元素小于限定值，则再次合并（原则上是与最少元素数节点合并）
        if (parentNode.getEntrys().size() < minKeys) {
            Node2 grandfatherNode = parentNode.getParentNode();
            if (grandfatherNode == null) {
                return;
            }
            int nodeIndex = Collections.binarySearch(grandfatherNode.getChildNodes(), parentNode);
            // 左兄弟节点
            Node2 leftNode = nodeIndex > 0 ? grandfatherNode.getChildNodes().get(nodeIndex - 1) : null;
            // 右兄弟节点
            Node2 rightNode = nodeIndex >= grandfatherNode.getChildNodes().size() - 1 ? null : grandfatherNode.getChildNodes().get(nodeIndex + 1);
            int leftEntrysSize = leftNode == null ? 0 : leftNode.entrys.size();
            int rightEntrysSize = rightNode == null ? 0 : rightNode.entrys.size();
            
            if ( leftNode == null || rightNode == null) {
                System.out.println("");
            }
                        
            int minSize = Math.min(leftEntrysSize, rightEntrysSize);
            if (minSize > 0) {
                if (leftEntrysSize == minSize) {
                    merge(parentNode, leftNode, nodeIndex - 1);
                } else {
                    merge(parentNode, rightNode, nodeIndex);
                }
            } else if (leftEntrysSize > 0) {
                merge(parentNode, leftNode, nodeIndex - 1);
            } else if (rightEntrysSize > 0) {
                merge(parentNode, rightNode, nodeIndex);
            }
        }

    }

    /**
     * 合并两个兄弟节点
     *
     * @param node 当前节点
     * @param brotherNode 兄弟节点
     */
    private void merge(Node2 node, Node2 brotherNode) {
        Node2 parentNode = node.getParentNode();
        Node2 newNode = new Node2();
        newNode.getEntrys().addAll(node.getEntrys());
        newNode.getEntrys().addAll(brotherNode.getEntrys());
        Collections.sort(newNode.getEntrys());

        newNode.setParentNode(parentNode);
        parentNode.getChildNodes().remove(node);
        parentNode.getChildNodes().remove(brotherNode);
        parentNode.addChild(newNode);
    }

    /**
     * 左旋转 （1）将父节点的中间值元素删除，并添加到当前节点中 （2）将右兄弟节点中最小元素删除，并添加到父节点中
     *
     * @param node 当前节点
     * @param nodeIndex 中间值索引
     * @param rightNode 右节点
     */
    private void leftRotate(Node2 node, int nodeIndex, Node2 rightNode) {
        Entry2 parentEntry = node.getParentNode().getEntrys().get(nodeIndex);
        node.add(parentEntry);
        node.getParentNode().getEntrys().remove(parentEntry);
        Entry2 rightEntry = rightNode.getEntrys().get(0);
        node.getParentNode().add(rightEntry);
        rightNode.getEntrys().remove(rightEntry);
    }

    /**
     * 右旋转 （1）将父节点的中间值元素删除，并添加到当前节点中 （2）将左兄弟节点中最大元素删除，并添加到父节点中
     *
     * @param node 当前节点
     * @param nodeIndex 中间值索引
     * @param leftNode 左节点
     */
    private void rightRotate(Node2 node, int nodeIndex, Node2 leftNode) {
        Entry2 parentEntry = node.getParentNode().getEntrys().get(nodeIndex - 1);
        node.add(parentEntry);
        node.getParentNode().getEntrys().remove(parentEntry);
        Entry2 leftEntry = leftNode.getEntrys().get(leftNode.getEntrys().size() - 1);
        node.getParentNode().add(leftEntry);
        leftNode.getEntrys().remove(leftEntry);
    }
    
    public List<Entry2> traverse(){
        var elements = new ArrayList<Entry2>(size);
        traverse(root, elements);
        return elements;
    }
    
    private void traverse(Node2 node, List<Entry2> elements){
        int i;
        for (i = 0; i < node.entrys.size(); i++){
            if (!node.childNodes.isEmpty())
                traverse(node.childNodes.get(i), elements);
            elements.add(node.entrys.get(i));
        }

        if (!node.childNodes.isEmpty()){
            traverse(node.childNodes.get(i), elements);
        }
    }
    
    public boolean validate() {
        if (root == null) return true;
        return validateNode(root);
    }
    
    private boolean validateNode(Node2 node) {
        
        int minChildrenSize = minKeys + 1; 
        int maxKeySize = 2 * minKeys; 
        int maxChildrenSize = maxKeySize + 1;
        
        int keySize = node.entrys.size();
        if (keySize > 1) {
            // Make sure the keys are sorted
            for (int i = 1; i < keySize; i++) {
                Entry2 p = node.entrys.get(i - 1);
                Entry2 n = node.entrys.get(i);
                if (p.compareTo(n) > 0)
                    return false;
            }
        }
        int childrenSize = node.childNodes.size();
        if (node.parentNode == null) {
            // root
            if (keySize > maxKeySize) {
                // check max key size. root does not have a min key size
                return false;
            } else if (childrenSize == 0) {
                // if root, no children, and keys are valid
                return true;
            } else if (childrenSize < 2) {
                // root should have zero or at least two children
                return false;
            } else if (childrenSize > maxChildrenSize) {
                return false;
            }
        } else {
            // non-root
            if (keySize < minKeys) {
                return false;
            } else if (keySize > maxKeySize) {
                return false;
            } else if (childrenSize == 0) {
                return true;
            } else if (keySize != (childrenSize - 1)) {
                // If there are chilren, there should be one more child then
                // keys
                return false;
            } else if (childrenSize < minChildrenSize) {
                return false;
            } else if (childrenSize > maxChildrenSize) {
                return false;
            }
        }
        
        Node2 first = node.childNodes.get(0);
        // The first child's last key should be less than the node's first key
        if (first.entrys.get(first.entrys.size()- 1).compareTo(node.entrys.get(0)) > 0)
            return false;

        Node2 last = node.childNodes.get(node.childNodes.size() - 1);
        // The last child's first key should be greater than the node's last key
        if (last.entrys.get(0).compareTo(node.entrys.get(node.entrys.size()- 1)) < 0)
            return false;

        // Check that each node's first and last key holds it's invariance
        for (int i = 1; i < node.entrys.size(); i++) {
            Entry2 p = node.entrys.get(i - 1);
            Entry2 n = node.entrys.get(i);
            Node2 c = node.childNodes.get(i);
            if (p.compareTo(c.entrys.get(0)) > 0)
                return false;
            if (n.compareTo(c.entrys.get(c.entrys.size()- 1)) < 0)
                return false;
        }

        for (int i = 0; i < node.childNodes.size(); i++) {
            Node2 c = node.childNodes.get(i);
            boolean valid = this.validateNode(c);
            if (!valid)
                return false;
        }

        return true;
    }

    private static class Node2 implements Comparable<Node2> {

        private final List<Entry2> entrys; // 当前节点的键值对
        private final List<Node2> childNodes; // 子节点，使用数组存储子节点
        private Node2 parentNode; // 父节点

        public Node2() {
            entrys = new ArrayList<>();
            childNodes = new ArrayList<>();
        }

        public List<Entry2> getEntrys() {
            return entrys;
        }

        public List<Node2> getChildNodes() {
            return childNodes;
        }

        public void setParentNode(Node2 parentNode) {
            this.parentNode = parentNode;
        }

        public Node2 getParentNode() {
            return parentNode;
        }

        public Node2 add(Entry2 entry) {
            entrys.add(entry);
            Collections.sort(entrys);
            return this;
        }

        public Node2 addChild(Node2 node) {
            childNodes.add(node);
            Collections.sort(childNodes);
            return this;
        }

        /**
         * 按照键值中第一个key进行排序
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Node2 o) {
            return Integer.compare(entrys.get(0).getKey(), o.getEntrys().get(0).getKey());
        }

        @Override
        public String toString() {
            return "Node{"
                    + "entrys=" + entrys
                    + ", childNodes=" + childNodes
                    + '}';
        }
    }

    /**
     * 定位一个键值对，其实现了 Map.Entry<K, V> 接口
     */
    public static class Entry2 implements Comparable<Entry2> {

        final int key;
        String value;

        public Entry2(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public String setValue(String newValue) {
            String oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        /**
         * 按照key进行排序
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Entry2 o) {
            return Integer.compare(key, o.getKey());
        }

        @Override
        public String toString() {
            return "{key = " + key + "}";
        }
    }
}
