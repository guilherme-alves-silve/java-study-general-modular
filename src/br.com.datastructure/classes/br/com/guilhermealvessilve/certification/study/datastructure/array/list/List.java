package br.com.guilhermealvessilve.certification.study.datastructure.array.list;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class List<E> implements Iterable<E> {
    
    private static final int BASE_SIZE = 16;
    
    private int size;
    private Object[] array;
    
    public List() {
        this(BASE_SIZE);
    }
    
    public List(int initial) {
        this.array = new Object[initial];
    }
    
    public List(E... elements) {
        this(Objects.requireNonNull(elements).length);
        System.arraycopy(elements, 0, array, 0, elements.length);
    }
    
    public void insert(E value) {
        growSizeIfNecessary();
        array[size++] = value;
    }
    
    public boolean contains(E value) {
        if (null == value) return false;
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(value)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void insert(E... values) {
        if (values.length == 0) throw new IllegalArgumentException();
        for (var value : values) {
            List.this.insert(value);
        }
    }
    
    public void remove(int i) {
        checkIndexRange(i);
        for(int j = i; j < size; ++j) {
            array[j] = array[j + 1];
        }
        --size;
        shrinkSizeIfNecessary();
    }
    
    public void set(int i, E value) {
        checkIndexRange(i);
        array[i] = value;
    }
    
    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        return (E) array[i];
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(size, array);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        var builder = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            builder.append(array[i])
                    .append(", ");
        }
        builder.append(array[size - 1])
                .append("]");
        return builder.toString();
    }
    
    private static class ListIterator<E> implements Iterator<E> {

        private final int size;
        private final Object[] array;
        
        private int i;

        public ListIterator(int size, Object[] array) {
            this.size = size;
            this.array = array;
        }
        
        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E) array[i++];
        }
    }
    
    private void checkIndexRange(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
    }
    
    private void growSizeIfNecessary() {
        if (size < array.length) return;
        int newSize = array.length == 0? BASE_SIZE : array.length * 2;
        var newArray = new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
    
    private void shrinkSizeIfNecessary() {
        if (BASE_SIZE >= array.length / 2) return;
        if (array.length < (size * 2)) return;
        var newArray = new Object[array.length / 2 + 1];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
