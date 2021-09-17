package br.com.guilhermealvessilve.certification.study.datastructure.tree.avltree;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class MainAVLTree {
    
    public static void main(String[] args) throws InterruptedException {
        
        testRotations();
        
        int total = 1000;
        
        final var tree = new AVLTree<Integer>();
        
        final var ints = TestUtils.randomList(0, total);
        boolean allInserted = ints.stream().allMatch(tree::insert);
        
        final var traversed = tree.traverse();
        
        final var foundAll = TestUtils.randomList(0, total)
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
        
        boolean removedAll = TestUtils.randomList(0, total)
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
    
    private static void testRotations() throws InterruptedException {
        //left-right heavy
        final var tree0 = new AVLTree<Integer>();
        tree0.insert(10);
        tree0.insert(8);
        tree0.insert(9);
        System.out.println("tree.traverse(): " + tree0.traverse());
        tree0.printNode();
        
        //right-left heavy
        final var tree1 = new AVLTree<Integer>();
        tree1.insert(8);
        tree1.insert(10);
        tree1.insert(9);
        System.out.println("tree.traverse(): " + tree1.traverse());
        tree1.printNode();
        
        //left-left heavy
        final var tree2 = new AVLTree<Integer>();
        tree2.insert(10);
        tree2.insert(9);
        tree2.insert(8);
        System.out.println("tree.traverse(): " + tree2.traverse());
        tree2.printNode();
        
        //right-right heavy
        final var tree3 = new AVLTree<Integer>();
        tree3.insert(8);
        tree3.insert(9);
        tree3.insert(10);
        System.out.println("tree.traverse(): " + tree3.traverse());
        tree3.printNode();
    }
}
