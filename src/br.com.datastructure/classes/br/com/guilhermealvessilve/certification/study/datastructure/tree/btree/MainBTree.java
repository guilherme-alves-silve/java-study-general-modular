package br.com.guilhermealvessilve.certification.study.datastructure.tree.btree;

/**
 *
 * @author Alves
 */
public class MainBTree {

    public static void main(String[] args) {
        int[] item = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 15, 17, 18, 19, 20, 13};
        final var btree = new BTree<Integer, String>(5);
        for (int i : item) {
            btree.put(i, "-->" + i);
        }
        
        System.out.println(btree);
        System.out.println("16：" + btree.search(16));

        System.out.println("15");
        btree.remove(15);
        System.out.println(btree);

        System.out.println("14");
        btree.remove(14);
        System.out.println(btree);

        System.out.println("17");
        btree.remove(17);
        System.out.println(btree);

        System.out.println("5");
        btree.remove(5);
        System.out.println(btree);
    }
}
