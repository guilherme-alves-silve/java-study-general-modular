package br.com.guilhermealvessilve.certification.study.datastructure.set;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Alves
 * @param <T>
 */
public class HashSetValue<T> implements Set<T> {

    private final HashMap<T, T> map;
    private final Set<T> keySet;

    public HashSetValue() {
        this.map = new HashMap<>();
        this.keySet = map.keySet();
    }
    
    public HashSetValue(int initialCapacity) {
        this.map = new HashMap<>(initialCapacity);
        this.keySet = map.keySet();
    }
    
    public HashSetValue(int initialCapacity, float loadFactor) {
        this.map = new HashMap<>(initialCapacity, loadFactor);
        this.keySet = map.keySet();
    }
    
    public T get(T value) {
        return map.get(value);
    }
    
    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Iterator<T> iterator() {
        return keySet.iterator();
    }

    @Override
    public Object[] toArray() {
        return keySet.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return keySet.toArray(a);
    }

    @Override
    public boolean add(T e) {
        return map.put(e, e) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return keySet.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        
        boolean modified = false;
        int i = 0;
        Iterator<? extends T> it = c.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (map.put(next, next) == null) {
                modified = true;
            }
            i++;
        }
        
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        
        boolean modified = false;
        int i = 0;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (!c.contains((T) it.next())) {
                it.remove();
                modified = true;
            }
            
            i++;
        }
        
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        
        boolean modified = false;
        int i = 0;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (c.contains((T) it.next())) {
                it.remove();
                modified = true;
            }
            
            i++;
        }
        
        return modified;
    }

    @Override
    public void clear() {
        map.clear();
    }
}
