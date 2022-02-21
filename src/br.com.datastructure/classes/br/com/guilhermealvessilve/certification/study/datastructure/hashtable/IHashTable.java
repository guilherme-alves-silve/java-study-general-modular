package br.com.guilhermealvessilve.certification.study.datastructure.hashtable;

/**
 *
 * @author Alves
 */
public interface IHashTable<K, V> {
    
    public V get(K key);
    
    public V put(K key, V value);
    
    public V remove(K key);
    
    public boolean containsKey(K key);
    
    public boolean isEmpty();
    
    public int size();
}
