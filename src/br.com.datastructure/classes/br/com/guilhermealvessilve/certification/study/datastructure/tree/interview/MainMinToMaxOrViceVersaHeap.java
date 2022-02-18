package br.com.guilhermealvessilve.certification.study.datastructure.tree.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.tree.heap.Heap;
import java.util.Comparator;

/**
 *
 * @author Alves
 */
public class MainMinToMaxOrViceVersaHeap {

    /**
     * Design an algorithms that can check whether a heap (with array representation) 
     * is a valid min heap or not. 
     * Valid min heap is when the parent node 
     * is smaller than the children.
     * @param args 
     */
    public static void main(String[] args) {
        
        int maxSize = 20;
        var minHeap = new Heap<Integer>(maxSize, Integer.class, Comparator.reverseOrder());
        for (int i = 0; i < 10; i++) {
            minHeap.insert(i);
        }
        
        
        var maxHeap = new Heap<Integer>(maxSize, Integer.class, Comparator.naturalOrder());
        for (int i = 0; i < 10; i++) {
            maxHeap.insert(i);
        }
        
        System.out.println("min heap: " + minHeap.toList()); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println("max heap: " + maxHeap.toList()); // [9, 8, 5, 6, 7, 1, 4, 0, 3, 2]
        
        minHeap.reverse();
        minHeap.insert(-1);
        minHeap.insert(100);
        
        maxHeap.reverse();
        maxHeap.insert(100);
        maxHeap.insert(-1);
        System.out.println("now max heap (was min heap before): " + minHeap.toList()); // [9, 8, 6, 7, 4, 5, 2, 0, 3, 1, -1]
        System.out.println("now min heap (was max heap before): " + maxHeap.toList()); // [0, 2, 1, 3, 7, 5, 4, 6, 8, 9, 100] 
    }
}
