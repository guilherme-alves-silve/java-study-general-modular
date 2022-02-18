package br.com.guilhermealvessilve.certification.study.datastructure.tree.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.tree.heap.Heap;
import java.util.Comparator;

/**
 *
 * @author Alves
 */
public class MainMinOrMaxValidHeap {

    /**
     * Design an algorithms that can check whether a heap (with array representation) 
     * is a valid min heap or not. 
     * Valid min heap is when the parent node 
     * is smaller than the children.
     * @param args 
     */
    public static void main(String[] args) {
        
        var minHeap = new Heap<Integer>(Integer.class, Comparator.reverseOrder());
        for (int i = 0; i < 10; i++) {
            minHeap.insert(i);
        }
        
        
        var maxHeap = new Heap<Integer>(Integer.class, Comparator.naturalOrder());
        for (int i = 0; i < 10; i++) {
            maxHeap.insert(i);
        }
        
        System.out.println("valid min heap: " + checkMinHeap(minHeap.toArray())); // true
        System.out.println("valid min heap: " + checkMinHeap(maxHeap.toArray())); // false
        
        System.out.println("valid max heap: " + checkMaxHeap(minHeap.toArray())); // false
        System.out.println("valid max heap: " + checkMaxHeap(maxHeap.toArray())); // true
    }
    
    private static boolean checkMinHeap(Integer[] heap) {
        
        for (int i = 0; i < (heap.length - 2) / 2; ++i) {
            if (heap[i] > heap[2 * i + 1] || heap[i] > heap[2 * i + 2]) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean checkMaxHeap(Integer[] heap) {
        
        for (int i = 0; i < (heap.length - 2) / 2; ++i) {
            if (heap[i] < heap[2 * i + 1] || heap[i] < heap[2 * i + 2]) {
                return false;
            }
        }
        
        return true;
    }
}
