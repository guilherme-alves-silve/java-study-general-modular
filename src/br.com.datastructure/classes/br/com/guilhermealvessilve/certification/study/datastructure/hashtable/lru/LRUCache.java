package br.com.guilhermealvessilve.certification.study.datastructure.hashtable.lru;

import br.com.guilhermealvessilve.certification.study.datastructure.hashtable.IHashTable;
import br.com.guilhermealvessilve.certification.study.datastructure.hashtable.linearprobing.HashTableLinearProbing;

/**
 *
 * @author Alves
 * Reference:
 *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/5825132#overview
 */
public class LRUCache<K, V> {
    
    private int capacity;
    private IHashTable<K, Node<K, V>> hashTable;
    private DoublyLinkedList<K, V> linkedList;

    public LRUCache(int capacity) {
        if (capacity < 0 || capacity > Integer.MAX_VALUE - 1) throw new IllegalArgumentException();
        this.capacity = capacity;
        this.hashTable = new HashTableLinearProbing<>();
        this.linkedList = new DoublyLinkedList<>();
    }
    
    public V put(K key, V value) {
        
        if (hashTable.containsKey(key)) {
            var node = hashTable.get(key);
            var tempValue = node.value;
            node.value = value;
            update(node);
            return tempValue;
        }
        
        var newNode = new Node<>(key, value);
        add(newNode);
        
        return null;
    }
    
    public V get(K key) {
        if (!hashTable.containsKey(key)) {
            return null;
        }
        
        var node = hashTable.get(key);
        update(node);
        return node.value;
    }
    
    public void printNodes() {
        var node = linkedList.head;
        
        if (null == node) {
            System.out.print(" <-> ");
        }
        
        while (node != null) {
            System.out.print(" <-> " + node.toString());
            node = node.next;
        }
        
        System.out.println();
    }
    
    public boolean isFull() {
        return linkedList.size == capacity;
    }
    
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public int size() {
        return linkedList.size;
    }
    
    private void update(Node<K, V> node) {
        
        if (null == node.previous) {
            return;
        }

        var previous = node.previous;
        var next = node.next;  
        --linkedList.size;
        linkedList.addFirst(node);
        node.previous = null;
        previous.next = next;
        next.previous = previous;
    }

    private void add(Node<K, V> node) {
        
        hashTable.put(node.key, node);
        linkedList.addFirst(node);
        if (linkedList.size > capacity) {
            var lru = linkedList.pollLast();
            hashTable.remove(lru.key);
        }
    }
    
    private static class DoublyLinkedList<K, V> {

        int size;
        Node<K, V> head;
        Node<K, V> tail;

        void addFirst(Node<K, V> node) {
            ++size;
            if (null == head) {
                head = node;
                tail = node;
            } else {
                var temp = head;
                head = node;
                head.next = temp;
                temp.previous = head;
            }
        }

        Node<K, V> pollLast() {
            if (isEmpty()) {
                return null;
            }

            --size;
            var temp = tail;
            tail = tail.previous;
            
            if (null == tail) {
                head = null;
            } else {
                tail.next = null;
            }
            
            return temp;
        }

        boolean isEmpty() {
            return null == head;
        }
    }
    
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> previous;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s(%s)", key, value);
        }
    }
}
