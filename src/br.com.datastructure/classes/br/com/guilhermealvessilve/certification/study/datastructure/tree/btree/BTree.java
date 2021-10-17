package br.com.guilhermealvessilve.certification.study.datastructure.tree.btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 *
 * Reference:
 *  https://www.fatalerrors.org/a/data-structure-and-algorithm-b-tree-graphic-explanation-including-complete-code.html
 *  https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/BTree.java
 *  https://github.com/cptanalatriste/Database-Algorithms/blob/master/src/pe/edu/pucp/database/algorithms/btree/BTree.java
 *  https://programmer.group/b-tree-java-implementation.html
 */
public class BTree<K extends Comparable<K>, V> {

    private static final Node NULL_NODE = new Node();

    private final int minKeys;
    private final int maxKeys;
    private int size;
    private Node root;

    public BTree(int m) {
        this.maxKeys = m - 1;
        this.minKeys = (int) Math.ceil(m / 2.0F) - 1;
    }

    public V search(K key) {
        requireNonNull(key);
        var entry = searchEntry(root, key);
        if (null == entry) {
            return null;
        }

        return (V) entry.value;
    }

    private Entry searchEntry(Node node, K key) {
        if (null == node) {
            return null;
        }

        int index = Collections.binarySearch(node.keys, new Entry(key));
        if (index >= 0) {
            return node.keys.get(index);
        }

        if (node.isLeaf()) {
            return null;
        }

        int searchIndex = -index - 1;
        return searchEntry(node.children.get(searchIndex), key);
    }
    
    private Node searchNode(K key) {
        return searchNode(root, new Entry(key));
    }

    private Node searchNode(Node node, Entry entry) {
        if (null == node) {
            return null;
        }

        int index = Collections.binarySearch(node.keys, entry);
        if (index >= 0) {
            return node;
        }

        if (node.isLeaf()) {
            return null;
        }

        int searchIndex = -index - 1;
        return searchNode(node.children.get(searchIndex), entry);
    }

    public boolean put(K key, V value) {
        requireNonNull(key);
        requireNonNull(value);
        var entry = new Entry(key, value);
        if (null == root) {
            ++size;
            root = new Node(entry);
            return true;
        }

        int tempSize = size;
        putNode(root, entry);
        return size > tempSize;
    }

    private void putNode(Node node, Entry entry) {
        if (node.isLeaf()) {
            ++size;
            if (node.keys.size() < maxKeys) {
                node.addKey(entry);
                return;
            }

            node.addKey(entry);
            split(node);
            return;
        }

        int index = Collections.binarySearch(node.keys, entry);
        if (index < 0) {
            int searchIndex = -index - 1;
            putNode(node.children.get(searchIndex), entry);
        }
    }

    private void split(Node node) {
        int mid = node.keys.size() / 2;
        var separateEntry = node.keys.get(mid);

        var left = new Node();
        left.keys.addAll(node.keys.subList(0, mid));

        var right = new Node();
        right.keys.addAll(node.keys.subList(mid + 1, node.keys.size()));

        if (node.isInternal()) {
            var leftChildren = node.children.subList(0, mid + 1);
            left.addChildren(leftChildren);

            var rightChildren = node.children.subList(mid + 1, node.children.size());
            right.addChildren(rightChildren);
        }

        if (null == node.parent) {
            var newRoot = new Node(separateEntry);
            newRoot.addChildren(left, right);
            root = newRoot;
            return;
        }

        var parent = node.parent;
        parent.addKey(separateEntry)
              .addChildren(left, right)
              .removeChild(node);

        if (parent.keys.size() > maxKeys) {
            split(parent);
        }
    }

    public boolean remove(K key) {
        requireNonNull(key);
        var node = searchNode(key);
        if (null == node) {
            return false;
        }

        --size;
        int keyIndex = Collections.binarySearch(node.keys, new Entry(key));
        node.keys.remove(keyIndex);
        
        if (node.parent == null && node.isLeaf()) {
            root = null;
            return true;
        }
        
        if (node.keys.size() < minKeys) {
            handleRemoveCases(node, keyIndex);
        }

        return true;
    }

    private void handleRemoveCases(Node node, int deletedKeyIndex) {
        if (node.isInternal()) {
            handleInternalNodeRemoveCase(node, deletedKeyIndex);
            if (!validate()) {
                System.out.println("handleInternalNodeRemoveCase is invalidating the tree!");
            }
            
            return;
        }

        handleLeafNodeRemoveCase(node.parent, node);
        if (!validate()) {
            System.out.println("handleLeafNodeRemoveCase is invalidating the tree!");
        }
    }

    private void handleInternalNodeRemoveCase(Node node, int deletedKeyIndex) {
        var left = node.getChildAt(deletedKeyIndex);
        var right = node.getChildAt(deletedKeyIndex + 1);

        int leftEntriesSize = left.keys.size();
        int rightEntriesSize = right.keys.size();

        int maxSize = Math.max(leftEntriesSize, rightEntriesSize);
        if (maxSize <= minKeys) {
            merge(node, left, right);
            return;
        }

        var entry = (maxSize == leftEntriesSize)
                ? left.pollGreatestEntry()
                : right.pollSmallestEntry();

        node.addKey(entry);
    }

    private void handleLeafNodeRemoveCase(Node parent, Node node) {

        int nodeIndex = parent.indexOfChild(node);

        var left = parent.getLeftBrother(nodeIndex);
        var right = parent.getRightBrother(nodeIndex);

        int leftEntriesSize = left.keys.size();
        int rightEntriesSize = right.keys.size();
        int maxSize = Math.max(leftEntriesSize, rightEntriesSize);

        if (maxSize <= minKeys) {
            if (left != NULL_NODE) {
                merge(parent, node, left, nodeIndex - 1);
            } else if (right != NULL_NODE) {
                merge(parent, node, right, nodeIndex); 
            }

            return;
        }

        if (maxSize == leftEntriesSize) {
            rightRotate(parent, node, left, nodeIndex - 1);
        } else {
            leftRotate(parent, node, right, nodeIndex);
        }
    }

    private void merge(Node parent, Node node, Node brother, int parentEntryIndex) {

        var newNode = new Node();
        newNode.keys.add(parent.pollEntryAt(parentEntryIndex));
        newNode.addAllKeys(node.keys, brother.keys);
        newNode.addAllChildren(node.children, brother.children);

        parent.removeChildren(node, brother);

        if (parent.keys.isEmpty()) {
            root = newNode;
            return;
        }

        parent.addChild(newNode);

        if (parent.keys.size() >= minKeys) {
            return;
        }
        
        var grandParent = parent.parent;
        if (null == grandParent) {
            return;
        }

        int nodeIndex = grandParent.indexOfChild(parent);
        var left = grandParent.getLeftBrother(nodeIndex);
        var right = grandParent.getRightBrother(nodeIndex);

        int leftEntriesSize = left.keys.size();
        int rightEntriesSize = right.keys.size();
        int minSize = Math.min(leftEntriesSize, rightEntriesSize);

        if (leftEntriesSize > 0 && leftEntriesSize == minSize) {
            merge(grandParent, parent, left, nodeIndex - 1);
        } else if (rightEntriesSize > 0) {
            merge(grandParent, parent, right, nodeIndex);
        }
    }
    
    private void merge(Node parent, Node left, Node right) {

        var newNode = new Node();
        newNode.addAllKeys(left.keys, right.keys);
        
        parent.removeChildren(left, right)
              .addChild(newNode);
    }

    private void rightRotate(Node parent, Node node, Node left, int nodeIndex) {
        var parentEntry = parent.pollEntryAt(nodeIndex);
        node.addKey(parentEntry);
        
        var leftEntry = left.pollGreatestEntry();
        parent.addKey(leftEntry);
    }

    private void leftRotate(Node parent, Node node, Node right, int nodeIndex) {
        
        var parentEntry = parent.pollEntryAt(nodeIndex);
        node.addKey(parentEntry);
        
        var rightEntry = right.pollSmallestEntry();
        parent.addKey(rightEntry);
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public List<K> traverse(){
        if (null == root) {
            return Collections.emptyList();
        }
        
        var elements = new ArrayList<K>(size);
        traverse(root, elements);
        return elements;
    }
    
    private void traverse(Node node, List<K> elements){
        int i;
        for (i = 0; i < node.keys.size(); i++){
            if (!node.children.isEmpty())
                traverse(node.children.get(i), elements);
            elements.add((K) node.keys.get(i).key);
        }

        if (!node.children.isEmpty()){
            traverse(node.children.get(i), elements);
        }
    }

    private static class Node implements Comparable<Node> {

        private Node parent;
        private final List<Entry> keys;
        private final List<Node> children;

        public Node() {
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
        }
        
        public Node(Entry entry) {
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
            this.keys.add(entry);
        }

        private boolean isLeaf() {
            return children.isEmpty();
        }

        private boolean isInternal() {
            return !children.isEmpty();
        }

        public Node addKey(Entry entry) {
            keys.add(entry);
            Collections.sort(keys);
            return this;
        }

        public Node addChild(Node node) {
            children.add(node);
            node.parent = this;
            Collections.sort(children);
            return this;
        }

        public Node addChildren(List<Node> nodes) {
            nodes.forEach(node -> node.parent = this);
            children.addAll(nodes);
            return this;
        }
        
        private Node addChildren(Node node1, Node node2) {
            addChild(node1);
            addChild(node2);
            return this;
        }
        
        private Node addAllKeys(List<Entry> keys1, List<Entry> keys2) {
            addKeys(keys1);
            addKeys(keys2);
            Collections.sort(this.keys);
            return this;
        }
        
        private Node addAllChildren(List<Node> children1, List<Node> children2) {
            addChildren(children1);
            addChildren(children2);
            Collections.sort(this.children);
            return this;
        }
        
        private Node addKeys(List<Entry> entries) {
            this.keys.addAll(entries);
            return this;
        }
        
        private Node removeChildren(Node node1, Node node2) {
            children.remove(node1);
            children.remove(node2);
            return this;
        }

        private Node removeChild(Node node) {
            children.remove(node);
            return this;
        }

        private Node getChildAt(int childIndex) {
            if (childIndex >= children.size()) {
                return NULL_NODE;
            }

            return children.get(childIndex);
        }

        private Entry pollEntryAt(int index) {
            var entry = keys.get(index);
            keys.remove(index);
            return entry;
        }

        private Entry pollGreatestEntry() {
            return pollEntryAt(keys.size() - 1);
        }

        private Entry pollSmallestEntry() {
            return pollEntryAt(0);
        }
        
        private Node getLeftBrother(int childIndex) {
            return childIndex == 0 ? NULL_NODE : children.get(childIndex - 1);
        }
        
        private Node getRightBrother(int childIndex) {
            return childIndex == children.size() - 1 ? NULL_NODE : children.get(childIndex + 1);
        }
        
        private int indexOfChild(Node child) {
            return children.indexOf(child);
        }

        @Override
        public int compareTo(final Node other) {
            return keys.get(0).compareTo(other.keys.get(0));
        }

        @Override
        public String toString() {
            return "Node{entries=" + keys + ", children=" + children + '}';
        }
    }

    private static class Entry implements Comparable<Entry> {

        private final Comparable key;
        private Object value;

        public Entry(Comparable key) {
            this.key = key;
        }

        public Entry(Comparable key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(final Entry other) {
            return key.compareTo(other.key);
        }

        @Override
        public String toString() {
            return "Entry{" + "key=" + key + ", value=" + value + '}';
        }
    }

    @Override
    public String toString() {
        return TreePrinter.getString(this);
    }

    public boolean validate() {
        if (root == null) return true;
        return validateNode(root);
    }
    
    private boolean validateNode(Node node) {
        
        int minChildrenSize = minKeys + 1; 
        int maxKeySize = 2 * minKeys; 
        int maxChildrenSize = maxKeySize + 1;
        
        int keySize = node.keys.size();
        if (keySize > 1) {
            // Make sure the keys are sorted
            for (int i = 1; i < keySize; i++) {
                Entry p = node.keys.get(i - 1);
                Entry n = node.keys.get(i);
                if (p.compareTo(n) > 0)
                    return false;
            }
        }
        int childrenSize = node.children.size();
        if (node.parent == null) {
            // root
            if (keySize > maxKeys) {
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

        Node first = node.children.get(0);
        // The first child's last key should be less than the node's first key
        if (first.keys.get(first.keys.size()- 1).compareTo(node.keys.get(0)) > 0)
            return false;

        Node last = node.children.get(node.children.size() - 1);
        // The last child's first key should be greater than the node's last key
        if (last.keys.get(0).compareTo(node.keys.get(node.keys.size()- 1)) < 0)
            return false;

        // Check that each node's first and last key holds it's invariance
        for (int i = 1; i < node.keys.size(); i++) {
            Entry p = node.keys.get(i - 1);
            Entry n = node.keys.get(i);
            Node c = node.children.get(i);
            if (p.compareTo(c.keys.get(0)) > 0)
                return false;
            if (n.compareTo(c.keys.get(c.keys.size()- 1)) < 0)
                return false;
        }

        for (int i = 0; i < node.children.size(); i++) {
            Node c = node.children.get(i);
            boolean valid = this.validateNode(c);
            if (!valid)
                return false;
        }

        return true;
    }

    private static class TreePrinter {

        public static <K extends Comparable<K>, V> String getString(BTree<K, V> tree) {
            if (tree.root == null) {
                return "Tree has no nodes.";
            }
            
            return getString(tree.root, "", true);
        }

        private static <K extends Comparable<K>, V> String getString(Node node, String prefix, boolean isTail) {
            StringBuilder builder = new StringBuilder();

            builder.append(prefix).append((isTail ? "└── " : "├── "));
            for (int i = 0; i < node.keys.size(); i++) {
                Entry value = node.keys.get(i);
                builder.append(value.value);
                if (i < node.keys.size() - 1) {
                    builder.append(", ");
                }
            }
            builder.append("\n");

            if (node.children != null) {
                for (int i = 0; i < node.children.size() - 1; i++) {
                    Node obj = node.children.get(i);
                    builder.append(getString(obj, prefix + (isTail ? "    " : "│   "), false));
                }
                
                if (node.children.size() >= 1) {
                    Node obj = node.children.get(node.children.size() - 1);
                    builder.append(getString(obj, prefix + (isTail ? "    " : "│   "), true));
                }
            }

            return builder.toString();
        }
    }
}
