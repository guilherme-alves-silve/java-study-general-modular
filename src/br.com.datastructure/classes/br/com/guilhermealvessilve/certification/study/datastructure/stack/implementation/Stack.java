package br.com.guilhermealvessilve.certification.study.datastructure.stack.implementation;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 */
public class Stack<E extends Comparable<E>> implements Iterable<E> {

    private int size;
    private Node<E> root;
    
    public void push(E data) {
        requireNonNull(data);
        var node = new Node<>(data);
        ++size;
        if (isEmpty()) {
            this.root = node;
        } else {
            node.next = this.root;
            this.root = node;
        }
    }
    
    public void push(E... elements) {
        if (requireNonNull(elements).length == 0) {
            throw new IllegalArgumentException();
        }
        
        for (var element : elements) {
            push(element);
        }
    }
    
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        
        return root.data;
    }
    
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        
        --size;
        var temp = root;
        root = temp.next;
        return temp.data;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return 0 == size;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new StackIterator<>(root);
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        var node = root;
        for (int i = 0; i < size - 1; i++) {
            builder.append(node.data)
                    .append(", ");
            node = node.next;
        }
        
        if (node != null) {
            builder.append(node.data);
        }
        
        return builder
                .append("]")
                .toString();
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        
        Node(E data) {
            this.data = data;
        }
    }
    
    private static class StackIterator<E> implements Iterator<E> {

        private Node<E> node;

        public StackIterator(Node<E> root) {
            this.node = new Node<>(null);
            this.node.next = root;
        }
        
        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public E next() {
            node = node.next;
            final var data = node.data;
            return data;
        }
    }
}
