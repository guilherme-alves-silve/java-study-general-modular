package br.com.guilhermealvessilve.certification.study.datastructure.tree.redblacktree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.Objects.requireNonNull;

/**
 * Based on some parts of the code below
 * Reference:
 *  https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/RedBlackTree.java
 *  https://www.happycoders.eu/algorithms/red-black-tree-java/#Left_Rotation
 *  https://github.com/geekific-official/geekific-youtube/blob/main/tree-redblack/src/main/java/com/youtube/geekific/RedBlackTree.java
 *  https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/RedBlackBST.java.html
 * @author Alves
 */
public class RedBlackTree<E extends Comparable<E>> {
    
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private int size;
    private Node<E> root;
    
    public E search(E data) {
        requireNonNull(data);
        var node = searchNode(root, data);
        return (node != null)? node.data : null;
    }
    
    private Node<E> searchNode(Node<E> node, E data) {
        if (null == node) return null;
        
        if (node.data.compareTo(data) > 0) {
            return searchNode(node.left, data);
        } 
        
        if (node.data.compareTo(data) < 0) {
            return searchNode(node.right, data);
        }
        
        return node;
    }
    
    public boolean insert(E data) {
        var newNode = new Node<E>(requireNonNull(data));
        int tempSize = size;
        
        root = insertNode(root, newNode);
        fixBalance(newNode);
        return size > tempSize;
    }
    
    private Node<E> insertNode(Node<E> node, Node<E> newNode) {
        if (null == node) {
            ++size;
            return newNode;
        }
        
        if (node.data.compareTo(newNode.data) > 0) {
            node.left = insertNode(node.left, newNode);
            node.left.parent = node;
        } else if (node.data.compareTo(newNode.data) < 0) {
            node.right = insertNode(node.right, newNode);
            node.right.parent = node;
        }
        
        return node;
    }
    
    private void fixBalance(Node<E> node) {
        
        var parent = node.parent;
        if (node != root && isRed(parent)) {
            
            var uncle = getSiblingOf(parent);
            var grandParent = parent.parent;
            if (isRed(uncle)) {
                fixColor(parent, uncle, grandParent);
            } else if (parent.isLeftChild()) {
                fixLeftCases(node, parent, grandParent);
            } else if (parent.isRightChild()) {
                fixRightCases(node, parent, grandParent);
            }
        }
        
        root.color = BLACK;
    }
    
    private void fixColor(Node<E> parent, Node<E> uncle, Node<E> grandParent) {
        parent.color = BLACK;
        uncle.color = BLACK;
        grandParent.color = RED;
        fixBalance(grandParent);
    }
    
    private void fixLeftCases(Node<E> node, Node<E> parent, Node<E> grandParent) {

        if (node.isRightChild()) {
            rotateLeft(parent);
            parent = node;
        }
        
        rotateRight(grandParent);
        parent.color = BLACK;
        grandParent.color = RED;
    }
    
    private void fixRightCases(Node<E> node, Node<E> parent, Node<E> grandParent) {
        
        if (node.isLeftChild()) {
            rotateRight(parent);
            parent = node;
        }
        
        rotateLeft(grandParent);
        parent.color = BLACK;
        grandParent.color = RED;
    }
    
    private Node<E> rotateRight(Node<E> node) {
        var parent = node.parent;
        var tempLeft = node.left;
        var grandChild = tempLeft.right;
        
        node.left = grandChild;
        tempLeft.right = node;
        
        node.parent = tempLeft;
        tempLeft.parent = parent;
        if (grandChild != null) grandChild.parent = node;
        
        replaceParentsChild(parent, node, tempLeft);
        return tempLeft;
    }
    
    private Node<E> rotateLeft(Node<E> node) {
        var parent = node.parent;
        var tempRight = node.right;
        var grandChild = tempRight.left;
        
        node.right = grandChild;
        tempRight.left = node;
        
        node.parent = tempRight;
        tempRight.parent = parent;
        if (grandChild != null) grandChild.parent = node;
        
        replaceParentsChild(parent, node, tempRight);
        return tempRight;
    }
    
    private void replaceParentsChild(Node<E> parent, Node<E> oldChild, Node<E> newChild) {
        if (null == parent) root = newChild;
        else if (parent.left == oldChild) parent.left = newChild;
        else if (parent.right == oldChild) parent.right = newChild;
        else throw new IllegalStateException();
        
        if (newChild != null) newChild.parent = parent;
    }
    
    private Node<E> getSiblingOf(Node<E> node) {
        var parent = node.parent;
        if (null == parent) return null;
        if (parent.left == node) return parent.right;
        if (parent.right == node) return parent.left;
        throw new IllegalStateException();
    }
    
    private boolean isRed(Node<E> node) {
        if (null == node) return false;
        return RED == node.color; 
    }
    
    private boolean isBlack(Node<E> node) {
        if (null == node) return true;
        return BLACK == node.color;
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
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return 0 == size;
    }
    
    private static class Node<E> {
        E data;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        boolean color;

        Node(E data) {
            this.data = data;
            this.color = RED;
        }
        
        boolean isLeftChild() {
            return this == parent.left;
        }
        
        boolean isRightChild() {
            return this == parent.right;
        }
        
        String getFormatted() {
            var strColor = RED == color? "R" : "B";
            return strColor + "" + data;
        }

        @Override
        public String toString() {
            return getFormatted();
        }
    }
    
    public boolean isValid() {
        return isValid(root) && isBalanced();
    }
    
    private boolean isBalanced() { 
        int black = 0;
        Node<E> node = root;
        while (node != null) {
            if (isBlack(node)) black++;
            node = node.left;
        }
        
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node<E> node, int black) {
        if (null == node) return 0 == black;
        if (isBlack(node)) --black;
        return isBalanced(node.left, black) && isBalanced(node.right, black);
    } 

    private boolean isValid(Node<E> node) {
        if (null == node) return true;
        if (isRed(node) && (isRed(node.left) || isRed(node.right))) return false;
        return isValid(node.left) && isValid(node.right);
    }
    
    public void printTree() {
        printNode(root);
    }
    
    /**
     * BTree Print
     * Reference:
     *  https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
     */
    private void printNode(Node root) {
        printNode(root, maxLevel(root));
    }
    
    private void printNode(Node node, int maxLevel) {
        printNodeInternal(Collections.singletonList(node), 1, maxLevel);
    }

    private void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        var newNodes = new ArrayList<Node>();
        for (var node : nodes) {
            if (node != null) {
                System.out.print(node.getFormatted());
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

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private <T> boolean isAllElementsNull(List<T> list) {
        return list.stream()
                .noneMatch(object -> (object != null));
    }
}
