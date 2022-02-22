package br.com.guilhermealvessilve.certification.study.datastructure.tree.heap;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;

/**
 *
 * @author Alves
 */
public class MainHeap {
    
    public static void main(String[] args) {
        var heap1 = new Heap<Integer>(Integer.class, Integer::compare);
        heap1.insert(0, 1, 2, 3, 4, 5);
        System.out.println("heap1.size: " + heap1.size());
        System.out.println("heap1.isEmpty: " + heap1.isEmpty());
        System.out.println("heap1: " + heap1);
        heap1.forEach(i -> System.out.print(i + " "));
        System.out.println();
        
        heap1.remove(5);
        heap1.remove(3);
        heap1.remove(0);
        heap1.insert(25);
        System.out.println("heap1: " + heap1);
        
        int size = 500;
        var heap2 = new Heap<Integer>(size, Integer.class, Integer::compare);
        for (int i = 1; i <= 500; i++) {
            heap2.insert(i);
        }
        for (int i = 1; i <= 500; i++) {
            if (!heap2.contains(i)) {
                System.out.println("heap2(" + i + "): " + heap2);
                throw new IllegalStateException("Heap is invalid!");
            }
        }
        
        System.out.println("peek: " + heap2.peek()); // 500
        int size2 = heap2.size();
        for (int i = 5; i < size2; i++) {
            if (!heap2.contains(heap2.size() - 1)) {
                throw new IllegalStateException("Heap is invalid! (1)");
            }
            heap2.remove(heap2.size());
        }
        System.out.println("heap2.size: " + heap2.size());
        System.out.println("heap2: " + heap2);
        for (int i = 5; i < 100; i++) {
            heap2.insert(i);
        }
        System.out.println("heap2.size: " + heap2.size());
        System.out.println("heap2.isEmpty: " + heap2.isEmpty());
        System.out.println("heap2: " + heap2);
        
        
        var heap3Size = 100;
        var heap3 = new Heap<Integer>(heap3Size, Integer.class, Integer::compare);
        
        boolean allInserted = TestUtils.randomList(0, heap3Size)
                .stream().allMatch(heap3::insert);
        
        System.out.println("heap3 allInserted: " + allInserted);
        System.out.println("heap3.sorted: " + heap3.sorted());
        
        boolean removedAll = TestUtils.randomList(0, heap3Size)
                .stream()
                .allMatch(value -> heap3.contains(value) && heap3.remove(value));
        
        System.out.println("heap3 removedAll: " + removedAll);
        
        var heap4 = new Heap<Integer>(size, Integer.class, Integer::compare);
        for (int i = 1; i <= 150; i++) {
            heap4.insert(i);
        }
        
        heap4.reverse();
        System.out.println("heap4: " + heap4.sorted());
    }
}
