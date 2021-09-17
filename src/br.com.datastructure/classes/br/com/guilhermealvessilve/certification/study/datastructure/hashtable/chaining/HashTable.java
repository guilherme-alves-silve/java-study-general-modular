package br.com.guilhermealvessilve.certification.study.datastructure.hashtable.chaining;

import br.com.guilhermealvessilve.certification.study.datastructure.hashtable.IHashTable;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 */
public class HashTable<K, V> implements IHashTable<K, V> {

    private static final int DEFAULT_TABLE_SIZE = 16;
    private int size;
    private Node<K, V>[] table;

    public HashTable() {
        this.table = new Node[DEFAULT_TABLE_SIZE];
    }
    
    @Override
    public V put(K key, V value) {
        
        requireNonNull(key);
        requireNonNull(value);
        
        int bucket = getBucketUsingHash(key);
        if (null == table[bucket]) {
            ++size;
            table[bucket] = new Node<>(key, value);
            return null;
        } 
        
        var node = search(bucket, key);
        if (key.equals(node.key)) {
            var oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        ++size;
        node.next = new Node<>(key, value);
        return null;
    }

    @Override
    public V get(K key) {
        requireNonNull(key);
        int bucket = getBucketUsingHash(key);
        if (null == table[bucket]) {
            return null;
        }
        
        var node = search(bucket, key);
        if (key.equals(node.key)) {
            return node.value;
        }
        
        return null;
    }
    
    private Node<K, V> search(final int bucket, final K key) {
        var actual = table[bucket];
        while (actual.next != null && !key.equals(actual.key)) {
            actual = actual.next;
        }
        
        return actual;
    }
    
    @Override
    public V remove(K key) {
        requireNonNull(key);
        int bucket = getBucketUsingHash(key);
        if (null == table[bucket]) {
            return null;
        }

        Node<K, V> before = null;        
        var actual = table[bucket];
        while (actual.next != null && !key.equals(actual.key)) {
            before = actual;
            actual = actual.next;
        }
        
        if (key.equals(actual.key)) {
            --size;
            if (null == before) {
                table[bucket] = table[bucket].next;
            } else {
                before.next = actual.next;
            }
        }
        
        return actual.value;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    public int size() {
        return size;
    }
    
    private int getBucketUsingHash(K key) {
        return key.hashCode() % table.length;
    }
    
    public void printElementsPerBucket() {
        final var str = new StringBuilder();
        int total = 0;
        for (int bucket = 0; bucket < table.length; bucket++) {
            var it = table[bucket];
            int count = 0;
            if (it != null) {
                ++count;
                while (it.next != null) {
                    ++count;
                    it = it.next;
                }
            }
            
            total += count;
            str.append("bucket[").append(bucket).append(']')
                .append(" = ").append(count)
                .append('\n');
        }
        
        str.append("total = ").append(total);
        System.out.println(str.toString());
    }

    @Override
    public String toString() {
        final var str = new StringBuilder("[");
        for (var node : table) {
            if (null == node) continue;
            
            str.append(node.toString())
                .append(",");
            var it = node.next;
            while (it != null) {
                str.append(it.toString())
                    .append(",");
                it = it.next;
            }
        }
        
        if (size > 1) {
            str.deleteCharAt(str.length() - 1);
        }
        
        str.append("]");
        return str.toString();
    }
    
    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + ", " + value + '}';
        }
    }
}
