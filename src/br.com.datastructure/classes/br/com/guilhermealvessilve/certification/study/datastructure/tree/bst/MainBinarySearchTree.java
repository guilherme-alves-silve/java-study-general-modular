package br.com.guilhermealvessilve.certification.study.datastructure.tree.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 *
 * @author Alves
 */
public class MainBinarySearchTree {
    
    public static void main(String[] args) throws InterruptedException {
        final var tree = new BinarySearchTree<Integer>();
        
        final var ints = randomList(0, 1000);
        boolean allInserted = ints.stream().allMatch(tree::insert);
        
        final var traversed = tree.traverse();
        
        final var foundAll = randomList(0, 1000)
            .stream()
            .allMatch(value -> tree.search(value) != null);

        System.out.println("allInserted: " + allInserted);
        System.out.println("foundAll: " + foundAll);
        System.out.println("tree.size(): " + tree.size());
        System.out.println("size == traversed.size: " + (tree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + tree.isEmpty());
        System.out.println("tree.traverse(): " + traversed);
        System.out.println("tree.getMin(): " + tree.getMin());
        System.out.println("tree.getMax(): " + tree.getMax());

        System.out.println("Removing");
        
        boolean removedAll = randomList(0, 1000)
                .stream()
                .allMatch(value -> Objects.equals(tree.search(value), value) && tree.remove(value));
        
        System.out.println();
        System.out.println("tree.size(): " + tree.size());
        System.out.println("removed all: " + removedAll);
        System.out.println("size == traversed.size: " + (tree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + tree.isEmpty());
        System.out.println("tree.traverse(): " + tree.traverse());
        System.out.println("tree.getMin(): " + tree.getMin());
        System.out.println("tree.getMax(): " + tree.getMax());
    }
    
    private static List<Integer> randomList(int startInclusive, int endExclusive) {
        final var ints = IntStream.range(startInclusive, endExclusive)
                .collect(() -> new ArrayList<Integer>(), 
                        (list, intValue) -> list.add(intValue), 
                        (list1, list2) -> list1.addAll(list2));
        Collections.shuffle(ints);
        return ints;
    }
}
