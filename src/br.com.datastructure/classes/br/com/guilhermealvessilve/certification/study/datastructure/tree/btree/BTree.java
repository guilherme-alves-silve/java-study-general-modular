package br.com.guilhermealvessilve.certification.study.datastructure.tree.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 *
 * Reference:
 * https://www.fatalerrors.org/a/data-structure-and-algorithm-b-tree-graphic-explanation-including-complete-code.html
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

        int index = Collections.binarySearch(node.entries, new Entry(key));
        if (index >= 0) {
            return node.entries.get(index);
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

        int index = Collections.binarySearch(node.entries, entry);
        if (index > 0) {
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
            if (node.entries.size() < maxKeys) {
                node.addEntry(entry);
                return;
            }

            node.addEntry(entry);
            split(node);
            return;
        }

        int index = Collections.binarySearch(node.entries, entry);
        if (index < 0) {
            int searchIndex = -index - 1;
            putNode(node.children.get(searchIndex), entry);
        }
    }

    private void split(Node node) {
        int mid = node.entries.size() / 2;
        var separateEntry = node.entries.get(mid);

        var left = new Node();
        left.entries.addAll(node.entries.subList(0, mid - 1));

        var right = new Node();
        right.entries.addAll(node.entries.subList(mid + 1, node.entries.size()));

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
        parent.addEntry(separateEntry)
              .addChildren(left, right)
              .removeChild(node);

        if (parent.entries.size() > maxKeys) {
            split(parent);
        }
    }

    public boolean remove(K key) {
        requireNonNull(key);
        var node = searchNode(key);
        if (null == node) {
            return false;
        }

        int keyIndex = Collections.binarySearch(node.entries, new Entry(key));
        node.entries.remove(keyIndex);
        if (node.entries.size() < minKeys) {
            handleRemoveCases(node, keyIndex);
        }

        return true;
    }

    private void handleRemoveCases(Node node, int deletedKeyIndex) {
        if (node.isInternal()) {
            handleInternalNodeRemoveCase(node, deletedKeyIndex);
            return;
        }

        handleLeafNodeRemoveCase(node.parent, node);
    }

    private void handleInternalNodeRemoveCase(Node node, int deletedKeyIndex) {
        var left = node.getChildAt(deletedKeyIndex);
        var right = node.getChildAt(deletedKeyIndex + 1);

        int leftEntriesSize = left.entries.size();
        int rightEntriesSize = right.entries.size();

        int maxSize = Math.max(leftEntriesSize, rightEntriesSize);
        if (maxSize <= minKeys) {
            merge(node, left, right);
            return;
        }

        var entry = (maxSize == leftEntriesSize)
                ? left.pollGreatestEntry()
                : right.pollSmallestEntry();

        node.addEntry(entry);
    }

    private void handleLeafNodeRemoveCase(Node parent, Node node) {

        int nodeIndex = parent.indexOfChild(node);

        var left = parent.getLeftBrother(nodeIndex);
        var right = parent.getRightBrother(nodeIndex);

        int leftEntriesSize = left.entries.size();
        int rightEntriesSize = right.entries.size();
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
        newNode.entries.add(parent.pollEntryAt(parentEntryIndex));
        newNode.addAllEntries(node.entries, brother.entries);

        parent.removeChildren(node, brother);

        if (parent.entries.isEmpty()) {
            root = newNode;
            return;
        }

        parent.addChild(newNode);

        if (parent.entries.size() >= minKeys) {
            return;
        }
        
        var grandParent = parent.parent;
        if (null == grandParent) {
            return;
        }

        int nodeIndex = grandParent.indexOfChild(parent);
        var left = grandParent.getLeftBrother(nodeIndex);
        var right = grandParent.getRightBrother(nodeIndex);

        int leftEntriesSize = left.entries.size();
        int rightEntriesSize = right.entries.size();
        int minSize = Math.min(leftEntriesSize, rightEntriesSize);

        if (leftEntriesSize > 0 && leftEntriesSize == minSize) {
            merge(grandParent, parent, left, nodeIndex - 1);
        } else if (rightEntriesSize > 0) {
            merge(grandParent, parent, right, nodeIndex);
        }
    }
    
    private void merge(Node parent, Node left, Node right) {

        var newNode = new Node();
        newNode.addAllEntries(left.entries, right.entries);
        newNode.addAllChildren(left.children, right.children);
        
        parent.removeChildren(left, right)
              .addChild(newNode);
    }

    private void rightRotate(Node parent, Node node, Node left, int nodeIndex) {
        var parentEntry = parent.pollEntryAt(nodeIndex);
        node.addEntry(parentEntry);
        
        var leftEntry = left.pollGreatestEntry();
        parent.addEntry(leftEntry);
    }

    private void leftRotate(Node parent, Node node, Node right, int nodeIndex) {
        
        var parentEntry = parent.pollEntryAt(nodeIndex);
        node.addEntry(parentEntry);
        
        var rightEntry = right.pollSmallestEntry();
        parent.addEntry(rightEntry);
    }

    private static class Node implements Comparable<Node> {

        private Node parent;
        private final List<Entry> entries;
        private final List<Node> children;

        public Node() {
            this.entries = new ArrayList<>();
            this.children = new ArrayList<>();
        }
        
        public Node(Entry entry) {
            this.entries = new ArrayList<>();
            this.children = new ArrayList<>();
            this.entries.add(entry);
        }

        private boolean isLeaf() {
            return children.isEmpty();
        }

        private boolean isInternal() {
            return !children.isEmpty();
        }

        public Node addEntry(Entry entry) {
            entries.add(entry);
            Collections.sort(entries);
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
        
        private Node addAllEntries(List<Entry>... arrayOfEntries) {
            Arrays.stream(arrayOfEntries).forEach(this::addEntries);
            Collections.sort(this.entries);
            return this;
        }
        
        private Node addAllChildren(List<Node>... arrayOfChildren) {
            Arrays.stream(arrayOfChildren).forEach(this::addChildren);
            return this;
        }

        private Node addEntries(List<Entry> entries) {
            this.entries.addAll(entries);
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
            var entry = entries.get(index);
            entries.remove(index);
            return entry;
        }

        private Entry pollGreatestEntry() {
            return pollEntryAt(entries.size() - 1);
        }

        private Entry pollSmallestEntry() {
            return pollEntryAt(0);
        }
        
        private Node getLeftBrother(int childIndex) {
            return childIndex == 0 ? NULL_NODE : children.get(childIndex - 1);
        }
        
        private Node getRightBrother(int nodeIndex) {
            return nodeIndex == children.size() - 1 ? NULL_NODE : children.get(nodeIndex + 1);
        }
        
        private int indexOfChild(Node child) {
            return Collections.binarySearch(children, child);
        }

        @Override
        public int compareTo(final Node other) {
            return entries.get(0).key.compareTo(other.entries.get(0).key);
        }

        @Override
        public String toString() {
            return "Node{entries=" + entries + ", children=" + children + '}';
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
        return "BTree{minKeys=" + minKeys + ", maxKeys=" + maxKeys + ", size=" + size + ", root=" + root + '}';
    }
}
