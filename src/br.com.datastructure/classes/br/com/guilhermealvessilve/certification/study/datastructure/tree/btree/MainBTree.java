package br.com.guilhermealvessilve.certification.study.datastructure.tree.btree;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class MainBTree {

    public static void main(String[] args) {
        //simpleTestBTreeOperation();
        testBTreeOperations();
    }
    
    private static void simpleTestBTreeOperation() {
        int[] item = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 15, 17, 18, 19, 20, 13};
        final var btree = new BTree<Integer, String>(5);
        for (int i : item) {
            btree.put(i, "[" + i + "]");
            if (!btree.validate()) {
                System.out.println("****INVALID****");
            }
        }
        
        System.out.println(btree);
        System.out.println("16：" + btree.search(16));

        System.out.println("15");
        btree.remove(15);
        System.out.println(btree.validate());

        System.out.println("14");
        btree.remove(14);
        System.out.println(btree.validate());

        System.out.println("17");
        btree.remove(17);
        System.out.println(btree.validate());

        System.out.println("5");
        btree.remove(5);
        System.out.println(btree.validate());
        
        System.out.println("***********************");
    }
    
    private static void testBTreeOperations() {
        
        int m = 5;
        final var btree = new BTree<Integer, String>(m);
        
        int total = 1000;
        final var ints = TestUtils.sequentialList(0, total); //TestUtils.randomList(0, total)
        boolean allInserted = ints.stream().allMatch(i -> btree.put(i, String.valueOf(i)) && btree.validate());
        
        final var traversed = btree.traverse();
        
        final var foundAll = TestUtils.randomList(0, total)
            .stream()
            .allMatch(value -> Objects.equals(Integer.parseInt(btree.search(value)), value) && btree.validate());

        System.out.println("allInserted: " + allInserted);
        System.out.println("foundAll: " + foundAll);
        System.out.println("tree.size(): " + btree.size());
        System.out.println("size == traversed.size: " + (btree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + btree.isEmpty());
        System.out.println("tree.traverse() contains ints: " + traversed.containsAll(ints));
        System.out.println("tree.traverse(): " + traversed);
        //System.out.println("tree.getMin(): " + btree.getMin());
        //System.out.println("tree.getMax(): " + btree.getMax());

        System.out.println("Removing");
        
        boolean removedAll = TestUtils.sequentialList(0, total)//TestUtils.randomList(0, total)
                .stream()
                .allMatch(key -> {
                        boolean invalid = false;
                        if (null == btree.search(key) || !Objects.equals(btree.search(key), String.valueOf(key))) {
                            btree.search(key);
                            invalid = true;
                        }
                        
                        if (invalid) {
                            System.out.println("invalid key " + key);
                        }
                    
                        if (!btree.remove(key)) {
                            System.out.println("can't remove key " + key);
                        }
                        
                        return true;
                            /*&& btree.validate()*/
                });
        
        System.out.println();
        System.out.println("tree.size(): " + btree.size());
        System.out.println("removed all: " + removedAll);
        System.out.println("size == traversed.size: " + (btree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + btree.isEmpty());
        System.out.println("tree.traverse(): " + btree.traverse());
        //System.out.println("tree.getMin(): " + btree.getMin());
        //System.out.println("tree.getMax(): " + btree.getMax());
        
        //System.out.println(btree);
    }
}
