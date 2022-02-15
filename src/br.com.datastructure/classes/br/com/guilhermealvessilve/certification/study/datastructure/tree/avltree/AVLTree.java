package br.com.guilhermealvessilve.certification.study.datastructure.tree.avltree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class AVLTree<E extends Comparable<E>> {

    private int size;
    private Node<E> root;
    
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
        } else if (node.data.compareTo(data) < 0) {
            node.right = insertNode(node.right, data);
        } else {
            return node;
        }

        updateHeight(node);
        return getBalancedNode(node);
    }
    
    public E search(E data) {
        
        Objects.requireNonNull(data, "data cannot be null!");
        if (null == root) {
            return null;
        }

        return searchNode(root, data);
    }
    
    private E searchNode(Node<E> node, E data) {
        
        if (null == node) {
            return null;
        }
        
        if (node.data.compareTo(data) == 0) {
            return node.data;
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
        if (null == root || searchNode(root, data) == null) {
            return false;
        }

        --size;
        root = removeValue(root, data);
        return true;
    }
    
    private Node<E> removeValue(Node<E> node, E data) {
        
        if (null == node) {
            return null;
        }

        if (node.data.compareTo(data) > 0) {
            node.left = removeValue(node.left, data);
        } else if (node.data.compareTo(data) < 0) {
            node.right = removeValue(node.right, data);
        } else {
            if (null == node.left) {
                return node.right;
            } else if (node == node.right) {
                return node.left;
            } else {
                node.data = getMaxValue(node.left);
                node.left = removeValue(node.left, node.data);
            }
        }
        
        updateHeight(node);
        return getBalancedNode(node);
    }
    
    public E getMin() {
        if (null == root) {
            return null;
        }

        return getMinValue(root);
    }
    
    private E getMinValue(Node<E> node) {
        if (node.left != null) return getMinValue(node.left);
        return node.data;
    }
    
    public E getMax() {
        if (null == root) return null;
        return getMaxValue(root);
    }
    
    private E getMaxValue(Node<E> node) {
        if (node.right != null) return getMaxValue(node.right);
        return node.data;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return null == root;
    }
    
    public void printNode() {
        printNode(root);
    }
    
    private void updateHeight(Node<E> node) {
        if (null == node) {
            return;
        }
        
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }
    
    private int balanceFactor(Node<E> node) {
        if (null == node) {
            return 0;
        }
        
        return height(node.left) - height(node.right);
    }
    
    private int height(Node<E> node) {
        if (null == node) {
            return -1;
        }
        
        return node.height;
    }
    
    private Node<E> rightRotation(Node<E> node) {
        var tempLeft = node.left;
        var grandChild = tempLeft.right;
        
        tempLeft.right = node;
        node.left = grandChild;
        
        updateHeight(node);
        updateHeight(tempLeft);
        
        return tempLeft;
    }
    
    private Node<E> leftRotation(Node<E> node) {
        var tempRight = node.right;
        var grandChild = tempRight.left;
        
        tempRight.left = node;
        node.right = grandChild;
        
        updateHeight(node);
        updateHeight(tempRight);
        
        return tempRight;
    }
    
    private Node<E> getBalancedNode(Node<E> node) {
      
        int balance = balanceFactor(node);
        if (balance > 1) { //left heavy
            if (balanceFactor(node.left) < 0) { //left-right heavy
                node.left = leftRotation(node.left);
            }
            
            node = rightRotation(node);
        } else if (balance < -1) { //right heavy
            if (balanceFactor(node.right) > 0) { //right-left heavy
                node.right = rightRotation(node.right);
            }
            
            node = leftRotation(node);
        }
        
        return node;
    }
    
    private static class Node<E> {
        
        private E data;
        private int height;
        private Node<E> left;
        private Node<E> right;

        public Node(E data) {
            this.data = data;
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

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
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
