package br.com.guilhermealvessilve.certification.study.datastructure.tree.btree;

import br.com.guilhermealvessilve.certification.study.datastructure.tree.btree.BTree2.Entry2;
import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Alves
 */
public class MainBTree2 {

    public static void main(String[] args) {
        simpleTestBTreeOperation();
        //testBTreeOperations();
    }

    private static void simpleTestBTreeOperation() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 15, 17, 18, 19, 20, 13};
        var list = new ArrayList<Integer>();
        list.addAll(Arrays.stream(array).boxed().collect(toList()));
        for (int i = 0; i < 300; i++) {
            list.add(i);
        }

        final var btree = new BTree2(5);
        int idx = 0;
        for (int i : list) {
            if (idx == 33) {
                System.out.println("");
            }

            btree.add(new Entry2(i, "[" + i + "]"));
            
            if (btree.searchEntry(i).key != i) {
                System.out.println("INVALID KEY");
            }
            
            if (!btree.validate()) {
                System.out.println("INVALID " + idx);
            }
            idx++;
        }

        System.out.println("16：" + btree.searchEntry(16));

        int[] toRemove = {15, 14, 17, 5};
        
        for (int i : toRemove) {
            System.out.println("Test " + i);
            btree.remove(i);
            
            if (!btree.validate()) {
                System.out.println("INVALID KEY " + i);
            }
            idx++;
        }
    }

    private static void testBTreeOperations() {

        int m = 5;
        final var btree = new BTree2(m);

        int total = 1000;
        final var ints = TestUtils.randomList(0, total);
        boolean allInserted = ints.stream().allMatch(i -> btree.add(new Entry2(i, String.valueOf(i))) && btree.validate());

        final var traversed = btree.traverse();

        final var foundAll = TestUtils.randomList(0, total)
                .stream()
                .allMatch(value -> Objects.equals(Integer.parseInt(btree.searchEntry(value).getValue()), value) && btree.validate());

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

        boolean removedAll = TestUtils.randomList(0, total)
                .stream()
                .allMatch(value -> {
                    return Objects.equals(Integer.parseInt(btree.searchEntry(value).getValue()), value)
                            && btree.remove(value)
                            && btree.validate();
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
