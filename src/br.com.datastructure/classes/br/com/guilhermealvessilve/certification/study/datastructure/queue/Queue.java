package br.com.guilhermealvessilve.certification.study.datastructure.queue;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class Queue<E> implements Iterable<E> {
    
    private int size;
    private Node<E> head;
    private Node<E> tail;
    
    public void enqueue(E data) {
        final var newNode = new Node<>(Objects.requireNonNull(data, "data cannot be null!"));
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        
        ++size;
    }
    
    public E dequeue() {
        
        if (isEmpty()) {
            return null;
        }
        
        --size;
        final var temp = head;
        head = head.next;
        return temp.data;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "Queue[" + traverse() + "]";
    }
    
    private String traverse() {
        if (null == head) {
            return "";
        }
        
        final var builder = new StringBuilder();
        var actual = head;
        while (actual != null) {
            builder.append(actual.data.toString());
            
            if (actual.next != null) {
                builder.append(", ");
            }
            
            actual = actual.next;
        }
        
        return builder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            
            Node<E> node;
            {
                node = new Node<>(null);
                node.next = head;
            }
            
            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public E next() {
                node = node.next;
                return node.data;
            }
        };
    }
    
    private static class Node<E> {
        
        private E data;
        private Node<E> next;
        
        public Node(E data) {
            this.data = data;
        }
    }
}
