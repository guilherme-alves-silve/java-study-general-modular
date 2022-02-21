package br.com.guilhermealvessilve.certification.study.datastructure.tree.fenwicktree;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 */
public class FenwickTree {

    private final int[] tree;

    public FenwickTree(int[] array) {
        requireNonNull(array);
        this.tree = new int[array.length + 1];
        construct(array);
    }

    private void construct(int[] array) {
        for (int i = 1; i <= array.length; ++i) {
            add(i, array[i - 1]);
        }
    }
    
    public int rangeSum(int start, int end) {
        return sum(end) - sum(start - 1);
    }

    public int sum(int i) {
        ++i;
        
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i = parent(i);
        }
        
        return sum;
    }

    public void add(int i, int number) {
        while (i < tree.length) {
            tree[i] += number;
            i = next(i);
        }
    }

    private int next(int i) {
        return i + (i & -i);
    }
    
    private int parent(int i) {
        return i - (i & -i);
    }
}
