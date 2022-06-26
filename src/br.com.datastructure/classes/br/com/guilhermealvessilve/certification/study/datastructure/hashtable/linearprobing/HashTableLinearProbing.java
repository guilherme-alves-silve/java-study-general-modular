package br.com.guilhermealvessilve.certification.study.datastructure.hashtable.linearprobing;

import br.com.guilhermealvessilve.certification.study.datastructure.array.list.List;
import br.com.guilhermealvessilve.certification.study.datastructure.hashtable.IHashTable;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 */
public class HashTableLinearProbing<K, V> implements IHashTable<K, V> {

    private static final int DEFAULT_TABLE_SIZE = 17;
    private int size;
    private int capacity;
    private Node<K, V>[] table;

    public HashTableLinearProbing() {
        this(DEFAULT_TABLE_SIZE);
    }
    
    public HashTableLinearProbing(int capacity) {
        if (capacity < 0 || capacity > Integer.MAX_VALUE - 1) throw new IllegalArgumentException();
        this.capacity = capacity;
        this.table = new Node[capacity];
    }
    
    @Override
    public V put(K key, V value) {
        
        requireNonNull(key);
        requireNonNull(value);
        
        if (size > (capacity * .75)) {
            resize(capacity * 2);
        }
        
        var index = getNextBucket(key);
        var node = table[index];
        if (node != null) {
            var oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        
        ++size;
        table[index] = new Node<>(key, value);
        return null;
    }

    @Override
    public V get(K key) {
        requireNonNull(key);
        var index = getNextBucket(key);
        var node = table[index];
        return (node != null)? node.value : null;
    }
    
    @Override
    public boolean containsKey(K key) {
        return table[getNextBucket(key)] != null;
    }

    @Override
    public V remove(K key) {
        requireNonNull(key);

        int index = getNextBucket(key);
        var node = table[index];
        if (null == node) {
            return null;
        }
        
        --size;
        table[index] = null;
        
        while (table[index = nextIndex(index)] != null) {
            var tempNode = table[index];
            table[index] = null;
            --size;
            put(tempNode.key, tempNode.value);
        }
        
        if (size <= (capacity / 3)) {
            resize(capacity / 2);
        }
        
        return node.value;
    }
    
    private int getNextBucket(K key) {
        int index = getBucketUsingHash(key);
        while (table[index] != null) {

            if (key.equals(table[index].key)) {
                return index;
            }
            
            index = nextIndex(index);
        }

        return index;
    }
    
    private int nextIndex(int index) {
        return (index + 1) % capacity;
    }
    
    private void resize(int newCapacity) {
        final var newHashTable = new HashTableLinearProbing<K, V>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            var node = table[i];
            if (null == node) continue;
            newHashTable.put(node.key, node.value);
        }
        
        this.size = newHashTable.size;
        this.capacity = newHashTable.capacity;
        this.table = newHashTable.table;
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
        var hashMapBuckets = new HashTableLinearProbing<Integer, Integer>();
        for (int i = 0; i < table.length; ++i) {
            if (null == table[i]) continue;
            
            int hash = getBucketUsingHash(table[i].key);
            Integer count = hashMapBuckets.get(hash);
            if (null == count) {
                count = 1;
            } else {
                ++count;
            }

            hashMapBuckets.put(hash, count);
        }
        
        for (var entry : hashMapBuckets.entry()) {
            int count = entry.getValue();
            str.append("bucket[").append(entry.getKey()).append(']')
                   .append(" = ").append(count)
                    .append('\n');
            total += count;
        }
        
        str.append("total = ").append(total);
        System.out.println(str.toString());
    }
    
    public List<Entry<K, V>> entry() {
        var list = new List<Entry<K, V>>(size);
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null) {
                list.insert(table[i]);
            }
        }
        
        return list;
    }
   
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        var list = new List<String>(size);
        
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                list.insert(table[i].toString());
            }
        }
        
        return list.toString();
    }
    
    private static class Node<K, V> implements Entry<K, V> {
        final K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
        
        @Override
        public String toString() {
            return "{" + key + ", " + value + '}';
        }
    }
    
    public interface Entry<K, V> {
        K getKey();
        
        V getValue();
    }
}
