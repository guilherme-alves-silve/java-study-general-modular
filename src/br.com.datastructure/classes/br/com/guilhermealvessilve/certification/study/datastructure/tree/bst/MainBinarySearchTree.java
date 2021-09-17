package br.com.guilhermealvessilve.certification.study.datastructure.tree.bst;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class MainBinarySearchTree {
    
    public static void main(String[] args) throws InterruptedException {
        final var tree = new BinarySearchTree<Integer>();
        
        final var ints = TestUtils.randomList(0, 1000);
        boolean allInserted = ints.stream().allMatch(tree::insert);
        
        final var traversed = tree.traverse();
        
        final var foundAll = TestUtils.randomList(0, 1000)
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
        
        boolean removedAll = TestUtils.randomList(0, 1000)
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
}
