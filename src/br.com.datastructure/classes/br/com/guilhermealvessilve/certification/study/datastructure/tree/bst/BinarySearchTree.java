package br.com.guilhermealvessilve.certification.study.datastructure.tree.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private volatile int size;
    private volatile Node<E> root;
    
    public boolean insert(E data) {
        Objects.requireNonNull(data, "data cannot be null!");
        final int tempSize = size;
        this.root = insertNode(root, data);
        return size > tempSize;
    }
    
    @SuppressWarnings("unchecked")
    public boolean[] insert(E... elements) {
        if (Objects.requireNonNull(elements).length == 0) {
            throw new IllegalArgumentException();
        }
        
        boolean[] insertions = new boolean[elements.length];
        for (int i = 0; i < elements.length; i++) {
            insertions[i] = insert(elements[i]);
        }
        
        return insertions;
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

        return node;
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
            if (null == node.left && null == node.right) {
                return null;
            } else if (null == node.left) {
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
    
    public E getMin() {
        if (null == root) {
            return null;
        }

        return getMinValue(root, root.data);
    }
    
    private E getMinValue(Node<E> node, E minValue) {
        if (null == node) {
            return minValue;
        }
        
        return getMinValue(node.left, node.data);
    }
    
    public E getMax() {
        if (null == root) {
            return null;
        }

        return getMaxValue(root, root.data);
    }
    
    private E getMaxValue(Node<E> node, E maxValue) {
        if (null == node) {
            return maxValue;
        }
        
        return getMaxValue(node.right, node.data);
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return null == root;
    }

    public boolean identical(BinarySearchTree<E> other) {
        Objects.requireNonNull(other);
        if (size != other.size) return false;
        return identicalNode(this.root, other.root);
    }
    
    private boolean identicalNode(Node<E> node1, Node<E> node2) {
        if (node1 == node2) return true;
        if (node1.data.compareTo(node2.data) != 0) return false;
        return identicalNode(node1.left, node2.left) &&
                identicalNode(node1.right, node2.right);
    }
    
    private static class Node<E> {
        
        private E data;
        private Node<E> left;
        private Node<E> right;

        public Node(E data) {
            this.data = data;
        }
    }
}
