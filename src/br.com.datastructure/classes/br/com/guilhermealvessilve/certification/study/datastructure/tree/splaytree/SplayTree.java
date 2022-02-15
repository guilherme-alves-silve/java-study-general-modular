package br.com.guilhermealvessilve.certification.study.datastructure.tree.splaytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class SplayTree<E extends Comparable<E>> {

    private int size;
    private Node<E> root;
    private boolean altered;
    
    public boolean insert(E data) {
        
        Objects.requireNonNull(data, "data cannot be null!");
        final int tempSize = size;
        this.root = insertNode(root, data);
        return size > tempSize;
    }
    
    private Node<E> insertNode(Node<E> node, E data) {
        
        if (null == node) {
            ++size;
            return new Node<>(data);
        }
        
        if (node.data.compareTo(data) > 0) {
            node.left = insertNode(node.left, data);
            node.left.parent = node;
        } else if (node.data.compareTo(data) < 0) {
            node.right = insertNode(node.right, data);
            node.right.parent = node;
        }

        return node;
    }
    
    public E search(E data) {
        
        Objects.requireNonNull(data, "data cannot be null!");
        if (null == root) {
            return null;
        }

        var node = searchNode(root, data);
        return getSplay(node);
    }
    
    private E getSplay(Node<E> node) {
        
        if (null == node) return null;
        
        while (node != root) {
            if (null == node.parent) {
                root = node;
            //zig situation
            } else if (node.parent != null && node.parent.parent == null) {
                if (node.isLeftChild()) {
                    rotateRight(node.parent);
                } else {
                    rotateLeft(node.parent);
                }
            
            } else if (node.parent != null && node.parent.parent != null) {
                
                //zig-zig situation
                if (node.isLeftChild() && node.parent.isLeftChild()) {
                    rotateRight(node.parent.parent);
                    rotateRight(node.parent);
                } else if (node.isRightChild() && node.parent.isRightChild()) {
                    rotateLeft(node.parent.parent);
                    rotateLeft(node.parent);
                //zig-zag situation
                } else if (node.isLeftChild() && node.parent.isRightChild()) {
                    rotateRight(node.parent);
                    rotateLeft(node.parent);
                } else if (node.isRightChild() && node.parent.isLeftChild()) {
                    rotateLeft(node.parent);
                    rotateRight(node.parent);
                }
            }
        }
        
        return node.data;
    }
    
    public E getRootValue() {
        if (null == root) return null;
        return root.data;
    }
    
    private Node<E> searchNode(Node<E> node, E data) {
        
        if (null == node) {
            return null;
        }
        
        if (node.data.compareTo(data) == 0) {
            return node;
        }
        
        if (node.data.compareTo(data) > 0) {
            return searchNode(node.left, data);
        }
        
        return searchNode(node.right, data);
    }
    
    public List<E> traverse() {
        if (0 == size) {
            return Collections.emptyList();
        }

        final List<E> elements = new ArrayList<>(size);
        inOrderTraverse(root, elements);
        return elements;
    }
    
    private void inOrderTraverse(Node<E> node, List<E> elements) {
        if (node.left != null) {
            inOrderTraverse(node.left, elements);
        }
        
        elements.add(node.data);
        
        if (node.right != null) {
            inOrderTraverse(node.right, elements);
        }
    }
    
    public boolean remove(E data) {
        Objects.requireNonNull(data, "data cannot be null!");
        
        int tempSize = size;
        this.root = removeValue(root, data);
        if (null == root) {
            System.out.println("HERE");
        }
        if (altered) --size;
        altered = false;
        return tempSize > size;
    }
    
    private Node<E> removeValue(Node<E> node, E data) {
        
        if (null == node) {
            return null;
        }

        if (node.data.compareTo(data) > 0) {
            node.left = removeValue(node.left, data);
            if (node.left != null) node.left.parent = node;
        } else if (node.data.compareTo(data) < 0) {
            node.right = removeValue(node.right, data);
            if (node.right != null) node.right.parent = node;
        } else {
            altered = true;
            if (null == node.left) {
                return node.right;
            } else if (node == node.right) {
                return node.left;
            } else {
                final var predecessor = searchPredecessor(node.left);
                node.data = predecessor.data;
                node.left = removeValue(node.left, predecessor.data);
            }
        }
        
        return node;
    }
    
    private Node<E> searchPredecessor(Node<E> node) {
        
        if (node.right != null) {
            return searchPredecessor(node.right);
        }
        
        return node;
    }

    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return null == root;
    }
    
    public E getMin() {
        if (null == root) return null;
        return getMin(root);
    }
    
    private E getMin(Node<E> node) {
        if (node.left != null) return getMin(node.left);
        return node.data;
    }
    
    public E getMax() {
        if (null == root) return null;
        return getMax(root);
    }
    
    private E getMax(Node<E> node) {
        if (node.right != null) return getMax(node.right);
        return node.data;
    }
    
    public void printNode() {
        printNode(root);
    }
    
    private void rotateRight(Node<E> node) {
        var parent = node.parent;
        var tempLeft = node.left;
        var grandChild = tempLeft.right;
        
        node.left = grandChild;
        tempLeft.right = node;
        
        node.parent = tempLeft;
        tempLeft.parent = parent;
        if (grandChild != null) grandChild.parent = node;
        
        replaceParentsChild(parent, node, tempLeft);
    }
    
    private void rotateLeft(Node<E> node) {
        var parent = node.parent;
        var tempRight = node.right;
        var grandChild = tempRight.left;
        
        node.right = grandChild;
        tempRight.left = node;
        
        node.parent = tempRight;
        tempRight.parent = parent;
        if (grandChild != null) grandChild.parent = node;
        
        replaceParentsChild(parent, node, tempRight);
    }
    
    private void replaceParentsChild(Node<E> parent, Node<E> oldChild, Node<E> newChild) {
        if (null == parent) root = newChild;
        else if (parent.left == oldChild) parent.left = newChild;
        else if (parent.right == oldChild) parent.right = newChild;
        else throw new IllegalStateException();
        
        if (newChild != null) newChild.parent = parent;
    }
    
    private static class Node<E> {
        E data;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
        
        boolean isLeftChild() {
            return this == parent.left;
        }
        
        boolean isRightChild() {
            return this == parent.right;
        }
        
        Node<E> getGrandParent() {
            return (parent != null)? parent.parent : null;
        }
        
        @Override
        public String toString() {
            return (data != null)? data.toString() : null;
        }
    }
    
    /**
     * Reference:
     *  https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
     */
    private static <T extends Comparable<?>> void printNode(Node<T> root) {
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        final var newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        return list.stream()
                .noneMatch(Objects::nonNull);
    }
}
