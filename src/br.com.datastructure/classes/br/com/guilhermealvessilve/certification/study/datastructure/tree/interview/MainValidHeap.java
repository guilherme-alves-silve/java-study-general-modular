package br.com.guilhermealvessilve.certification.study.datastructure.tree.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.tree.heap.Heap;
import java.util.Comparator;

/**
 *
 * @author Alves
 */
public class MainValidHeap {

    public static void main(String[] args) {
        var minHeap = new Heap<Integer>(Integer.class, Comparator.reverseOrder());
        for (int i = 0; i < 10; i++) {
            minHeap.insert(i);
        }
        
        System.out.println("min element: " + minHeap.peek()); // 0
        System.out.println("valid heap: " + minHeap.isValid()); // true
        
        var maxHeap = new Heap<Integer>(Integer.class, Comparator.naturalOrder());
        for (int i = 0; i < 10; i++) {
            maxHeap.insert(i);
        }
        
        System.out.println("max element: " + maxHeap.peek()); // 9
        System.out.println("valid heap: " + maxHeap.isValid()); // true
    }
}
