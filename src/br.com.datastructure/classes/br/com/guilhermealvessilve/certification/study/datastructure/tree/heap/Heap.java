package br.com.guilhermealvessilve.certification.study.datastructure.tree.heap;

/**
 *
 * @author Alves
 */
public class Heap<E> {
    
    private static final int DEFAULT_HEAP_SIZE = 11;
    
    private int size;
    private Object[] heap;
    
    public Heap() {
        this.heap = new Object[DEFAULT_HEAP_SIZE];
    }
}
