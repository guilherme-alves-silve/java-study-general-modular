package br.com.guilhermealvessilve.certification.study.datastructure.tree.redblacktree;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;

/**
 *
 * @author Alves
 */
public class MainRedBlackTree {
    
    public static void main(String[] args) throws InterruptedException {
        
        for (int i = 0; i < 1; i++) {
        
            int endValue = 3000;
            int startValue = -endValue;

            final var tree = new RedBlackTree<Integer>();

            final var ints = TestUtils.randomList(startValue, endValue);
            boolean allInserted = ints.stream().allMatch(value -> tree.insert(value) && tree.isValid());

            System.out.println("ALL INSERTED");
            
            final var traversed = tree.traverse();

            long start = System.currentTimeMillis();
            
            final var foundAll = TestUtils.randomList(startValue, endValue)
                .stream()
                .allMatch(value -> tree.search(value) != null);

            System.out.println("ELAPSED TIME: " + (System.currentTimeMillis() - start));
            
            System.out.println("FOUND ALL");
            
            System.out.println("allInserted: " + allInserted);
            System.out.println("foundAll: " + foundAll);
            System.out.println("tree.size(): " + tree.size());
            System.out.println("size == traversed.size: " + (tree.size() == traversed.size()));
            System.out.println("tree.isEmpty(): " + tree.isEmpty());
            //System.out.println("tree.traverse(): " + traversed);
            System.out.println("tree.getMin(): " + tree.getMin());
            System.out.println("tree.getMax(): " + tree.getMax());
            System.out.println("tree.isValid(): " + tree.isValid());
            
            //TODO remove
            //tree.printTree();

            /*
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
            System.out.println("*******************************");
            */
        }
    }
}
