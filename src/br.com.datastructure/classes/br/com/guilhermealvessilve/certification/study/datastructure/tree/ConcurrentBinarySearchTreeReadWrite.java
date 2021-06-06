package br.com.guilhermealvessilve.certification.study.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Alves
 * @param <E>
 */
public class ConcurrentBinarySearchTreeReadWrite<E extends Comparable<E>> {
    
    private final ReentrantReadWriteLock readWriteLock;
    private volatile int size;
    private volatile Node<E> root;

    public ConcurrentBinarySearchTreeReadWrite() {
        this(false);
    }

    public ConcurrentBinarySearchTreeReadWrite(boolean fair) {
        this.readWriteLock = new ReentrantReadWriteLock(fair);
    }
    
    public boolean add(E data) {
        
        Objects.requireNonNull(data, "data cannot be null!");
        readWriteLock.writeLock().lock();
        try {
            if (null == root) {
                ++size;
                this.root = new Node<>(data);
                return true;
            }
            
            return addNode(root, data);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    
    private boolean addNode(Node<E> node, E data) {
        if (node.data.compareTo(data) == 0) {
            return false;
        }
        
        if (node.data.compareTo(data) > 0) {
            if (null == node.left) {
                ++size;
                node.left = new Node<>(data);
                return true;
            }
            
            return addNode(node.left, data);
        }
        
        if (null == node.right) {
            ++size;
            node.right = new Node<>(data);
            return true;
        }

        return addNode(node.right, data);
    }
    
    public E search(E data) {
        
        Objects.requireNonNull(data, "data cannot be null!");
        if (null == root) {
            return null;
        }
        
        readWriteLock.readLock().lock();
        try {
            return searchNode(root, data);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
    
    private E searchNode(Node<E> node, E data) {
        
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
        
        readWriteLock.readLock().lock();
        try {
            final List<E> elements = new ArrayList<>(size);
            inOrderTraverse(root, elements);
            return elements;
        } finally {
            readWriteLock.readLock().unlock();
        }
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
    
    public E remove(E data) {
        Objects.requireNonNull(data, "data cannot be null!");
        if (null == root) {
            return null;
        }
        
        readWriteLock.writeLock().lock();
        try {
            final var bag = new ObjectBag<E>();
            //TODO
            return bag.element;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    
    private Node<E> findPredecessor(Node<E> node) {
        
        if (node.onlyLeft() || node.withoutChildren()) {
            return node;
        }
        
        return findPredecessor(node.right);
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return null == root;
    }
    
    private static class Node<E> {
        
        private Node<E> left;
        private Node<E> right;
        private E data;

        public Node(E data) {
            this.data = data;
        }
        
        public boolean withoutChildren() {
            return null == left && null == right;
        }
        
        public boolean onlyLeft() {
            return left != null && null == right;
        }
        
        public boolean onlyRight() {
            return right != null && null == left;
        }
    }
    
    private static class ObjectBag<E> {
        E element;
    }
}
