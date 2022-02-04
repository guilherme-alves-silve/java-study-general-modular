package br.com.guilhermealvessilve.certification.study.datastructure.linkedlist.list;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 */
public class LinkedList<E> implements Iterable<E> {
    
    private int size;
    private Node<E> head;
    private Node<E> tail;
    
    public void add(E data) {
        requireNonNull(data);
        var node = new Node<>(data);
        ++size;
        if (null == head) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }
    
    public void addLast(E data) {
        add(data);
    }
    
    public void addFirst(E data) {
        requireNonNull(data);
        var node = new Node<>(data);
        ++size;
        if (null == head) {
            head = node;
            tail = node;
        } else {
            var temp = head;
            head = node;
            head.next = temp;
        }
    }
    
    public void add(E... elements) {
        if (requireNonNull(elements).length == 0) {
            throw new IllegalArgumentException();
        }
        
        for (var data : elements) {
            add(data);
        }
    }
    
    public E get(int pos) {
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        
        Node<E> node = head;
        for (int i = 0; i < pos && node != null; i++) {
            node = node.next;
        }
        
        if (null == node) {
            return null;
        }
        
        return node.data;
    }
    
    public E getMiddleValue() {
        
        var slow = head;
        var fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow.data;
    }

    public void reverse() {
        
    }
    
    public E remove(int pos) {
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        
        int i = 0;
        Node<E> before = null;
        Node<E> actual = head;
        while (i < pos) {
            before = actual;
            actual = actual.next;
            ++i;
        }

        --size;
        if (isEmpty()) {
            head = null;
            tail = null;
            return actual.data;
        }
        
        E data = actual.data;
        if (null == before) {
            data = head.data;
            head = head.next;
        } else {
            if (tail == actual) {
                tail = before;
            }
            
            before.next = actual.next;
        }
        
        return data;
    }
    
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        
        return head.data;
    }
    
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        
        return tail.data;
    }
    
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        
        --size;
        var temp = head;
        head = head.next;
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
        return new LinkedListIterator<>(size, head);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        var node = head;
        while (node != null && node != tail) {
            builder.append(node.data)
                    .append(", ");
            node = node.next;
        }
        
        if (tail != null) {
            builder.append(tail.data);
        }
        
        return builder.append("]")
                .toString();
    }
    
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
    
    private static class LinkedListIterator<E> implements Iterator<E> {
    
        int size;
        Node<E> node;

        public LinkedListIterator(int size, Node<E> node) {
            this.size = size;
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            var temp = node;
            node = node.next;
            return temp.data;
        }
    }
}
