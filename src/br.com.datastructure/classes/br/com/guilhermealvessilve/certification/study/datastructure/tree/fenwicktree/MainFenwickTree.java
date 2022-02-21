package br.com.guilhermealvessilve.certification.study.datastructure.tree.fenwicktree;

/**
 *
 * @author Alves
 * Reference:
 *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/12407864#overview
 */
public class MainFenwickTree {
    
    public static void main(String[] args) {
        int[] numbers = {3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};
        var fenwickTree = new FenwickTree(numbers);
        System.out.println(fenwickTree.rangeSum(8, 10)); // 12
        System.out.println(fenwickTree.sum(10)); // 31
    }
}
