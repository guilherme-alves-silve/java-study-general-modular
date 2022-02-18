package br.com.guilhermealvessilve.certification.study.datastructure.tree.heap;

import br.com.guilhermealvessilve.certification.study.datastructure.array.list.List;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

/**
 * Implementation of binary heap using array.
 * @author Alves
 * Reference:
 *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/2548486#overview
 *  https://www.youtube.com/watch?v=L3hfc7Hvirw
 */
public class Heap<E> implements Iterable<E> {
    
    private static final int DEFAULT_HEAP_SIZE = 11;
    private static final int NOT_FOUND = -1;
    
    private int size;
    private E[] heap;
    private final Comparator<? super E> comparator;
    
    public Heap(Class<E> clazz, Comparator<? super E> comparator) {
        this(DEFAULT_HEAP_SIZE, clazz, comparator);
    }
    
    public Heap(int size, Class<E> clazz, Comparator<? super E> comparator) {
        if (size < 0 || size > Integer.MAX_VALUE - 1) throw new IllegalArgumentException();
        this.comparator = Objects.requireNonNull(comparator);
        this.heap = (E[]) Array.newInstance(clazz, size);
    }
    
    public void insert(E... values) {
        if (Objects.requireNonNull(values).length == 0) throw new IllegalArgumentException();
        for (var value : values) {
            insert(value);
        }
    }
    
    public E peek() {
        if (isEmpty()) return null;
        return heap[0];
    }
    
    public E poll() {
        if (isEmpty()) return null;
        var result = heap[0];
        heap[0] = heap[--size];
        heap[size + 1] = null;
        fixDown(0);
        return result;
    }
    
    /**
     * Where N is equal to the total elements in the heap
     * Operation made in O(N logN)
     * In this case is O(N) because i make a safe copy,
     * then it's O(N longN), as i explained above.
     * @return 
     */
    public List<E> sorted() {
        var copyHeap = Arrays.copyOf(heap, size);
        var list = new List<E>(size);
        int length = size;
        for (int i = 0; i < length; i++) {
            list.insert(poll());
        }
        
        heap = copyHeap;
        size = length;
        return list;
    }
    
    public E[] toArray() {
        return Arrays.copyOf(heap, size);
    }
    
    public boolean insert(E data) {
        if (isFull()) return false;
        heap[size++] = Objects.requireNonNull(data);
        fixUp(size - 1);
        return true;
    }
    
    public boolean remove(E data) {
        if (isEmpty()) return false;
        int index = indexOf(data);
        if (NOT_FOUND == index) return false;
        
        heap[index] = heap[size - 1];
        heap[--size] = null;
        
        fixDown(index);
        return true;
    }
    
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }
    
    private int indexOf(E value) {
        if (null == value) return NOT_FOUND;
        for (int i = 0; i < size; i++) {
            if (heap[i] != null && heap[i].equals(value)) {
                return i;
            }
        }
        
        return NOT_FOUND;
    }
    
    public boolean isFull() {
        return heap.length == size;
    }
    
    public boolean isEmpty() {
        return 0 == size;
    }
    
    public int size() {
        return size;
    }

    private void fixUp(int childIndex) {
        
        int parentIndex = (childIndex - 1) / 2;
        if (parentIndex >= 0 && compare(heap[childIndex], heap[parentIndex]) > 0) {
            swap(parentIndex, childIndex);
            fixUp(parentIndex);
        }
    }
    
    private int compare(E value1, E value2) {
        if (null == value1 || null == value2) return 0;
        return comparator.compare(value1, value2);
    }
    
    private void fixDown(int parentIndex) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;
        
        int bestIndex = NOT_FOUND;
        if (leftChildIndex < size && compare(heap[leftChildIndex], heap[parentIndex]) > 0) {
            bestIndex = leftChildIndex;
        }
        
        if (rightChildIndex < size && compare(heap[rightChildIndex], heap[leftChildIndex]) > 0) {
            bestIndex = rightChildIndex;
        }
        
        if (bestIndex != NOT_FOUND) {
            swap(bestIndex, parentIndex);
            fixDown(bestIndex);
        }
    }

    private void swap(int index1, int index2) {
        E temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        var builder = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            builder.append(heap[i])
                    .append(", ");
        }
        builder.append(heap[size - 1])
                .append("]");
        return builder.toString();
    }
    
        @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(size, heap);
    }

    public boolean isValid() {
        if (isEmpty()) return true;
        return isValid(0);
    }
    
    private boolean isValid(int parentIndex) {
        if (NOT_FOUND == parentIndex) return true;
        
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;
        
        int nextParent = NOT_FOUND;
        if (leftChildIndex < size) {
            if (compare(heap[leftChildIndex], heap[parentIndex]) > 0) return false;
            else nextParent = leftChildIndex;
        }
        
        if (rightChildIndex < size) {
            if (compare(heap[rightChildIndex], heap[parentIndex]) > 0) return false;
            else nextParent = rightChildIndex;
        }
        
        return isValid(nextParent);
    }

    private static class ListIterator<E> implements Iterator<E> {

        private final int size;
        private final E[] heap;
        
        private int i;

        public ListIterator(int size, E[] heap) {
            this.size = size;
            this.heap = heap;
        }
        
        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E) heap[i++];
        }
    }
}
