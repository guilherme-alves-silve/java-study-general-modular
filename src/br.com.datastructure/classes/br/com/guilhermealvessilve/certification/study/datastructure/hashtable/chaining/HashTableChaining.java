package br.com.guilhermealvessilve.certification.study.datastructure.hashtable.chaining;

import br.com.guilhermealvessilve.certification.study.datastructure.hashtable.IHashTable;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 */
public class HashTableChaining<K, V> implements IHashTable<K, V> {

    private static final int DEFAULT_TABLE_SIZE = 16;
    private int size;
    private final Node<K, V>[] table;

    public HashTableChaining() {
        this.table = new Node[DEFAULT_TABLE_SIZE];
    }
    
    @Override
    public V put(K key, V value) {
        
        requireNonNull(key);
        requireNonNull(value);
        
        var node = getNode(key);
        if (node != null) {
            node.value = value;
            return node.value;
        }
        
        ++size;
        int bucket = getBucketUsingHash(key);
        table[bucket] = new Node<>(key, value, table[bucket]);
        return null;
    }

    @Override
    public V get(K key) {
        requireNonNull(key);
        var node = getNode(key);
        return (node != null)? node.value : null;
    }
    
    private Node<K, V> getNode(K key) {
        requireNonNull(key);

        int bucket = getBucketUsingHash(key);
        var node = table[bucket];
        while (node != null) {

            if (node.key.equals(key)) {
                return node;
            }

            node = node.next;
        }

        return null;
    }
    
    @Override
    public V remove(K key) {
        requireNonNull(key);

        int bucket = getBucketUsingHash(key);
        
        Node<K, V> before = null;    
        var actual = table[bucket];
        while (actual != null) {
        
            if (actual.key.equals(key)) {
                --size;
                if (null == before) {
                    table[bucket] = table[bucket].next;
                } else {
                    before.next = actual.next;
                }
                
                return actual.value;
            }
            
            before = actual;
            actual = actual.next;
        }
        
        return null;
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
        return Math.abs(key.hashCode()) % table.length;
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
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        @Override
        public String toString() {
            return "{" + key + ", " + value + '}';
        }
    }
}
