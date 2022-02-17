package br.com.guilhermealvessilve.certification.study.datastructure.tree.splaytree;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class MainSplayTree {
    
    public static void main(String[] args) throws InterruptedException {
        
        testRotations();
        
        int total = 1000;
        
        final var tree = new SplayTree<Integer>();
        
        final var ints = TestUtils.randomList(0, total);
        boolean allInserted = ints.stream().allMatch(tree::insert);
        
        final var traversed = tree.traverse();
        
        final var foundAll = TestUtils.randomList(0, total)
            .stream()
            .allMatch(value -> tree.search(value) != null && tree.isValid());
        
        System.out.println("allInserted: " + allInserted);
        System.out.println("foundAll: " + foundAll);
        System.out.println("tree.size(): " + tree.size());
        System.out.println("size == traversed.size: " + (tree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + tree.isEmpty());
        System.out.println("tree.traverse(): " + traversed);
        System.out.println("tree.getMin(): " + tree.getMin());
        System.out.println("tree.getMax(): " + tree.getMax());

        System.out.println("Removing");
        
        boolean removedAll = TestUtils.randomList(0, total)
                .stream()
                .allMatch(value -> Objects.equals(tree.search(value), value) && tree.remove(value) && tree.isValid());
        
        System.out.println();
        System.out.println("tree.size(): " + tree.size());
        System.out.println("removed all: " + removedAll);
        System.out.println("size == traversed.size: " + (tree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + tree.isEmpty());
        System.out.println("tree.traverse(): " + tree.traverse());
        System.out.println("tree.getMin(): " + tree.getMin());
        System.out.println("tree.getMax(): " + tree.getMax());
    }
    
    private static void testRotations() throws InterruptedException {

        final var tree = new SplayTree<Integer>();
        int[] values = {32, 10, 55, 1, 1, 19, 41, 16, 12, 99, 47, 5, 2, 3, 0, 0};
        Arrays.stream(values)
                .forEach((value) -> tree.insert(value));
        
        System.out.println("before searching elements: " + tree.traverse());
        for (int i = 0; i < tree.size(); i++) {
            System.out.println("search: " + tree.search(values[i]));
            System.out.println("root value: " + tree.getRootValue());
        }
        System.out.println("after searching elements: " + tree.traverse());
        
        for (int i = 0; i < values.length; i++) {
            tree.remove(values[i]);
        }
        
        if (tree.getRootValue() != null) throw new IllegalStateException("Root must be null!");
    }
}
