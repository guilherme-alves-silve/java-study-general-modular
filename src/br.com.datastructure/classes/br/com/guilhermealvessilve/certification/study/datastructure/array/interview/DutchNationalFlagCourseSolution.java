package br.com.guilhermealvessilve.certification.study.datastructure.array.interview;

import java.util.Arrays;

/**
 *
 * @author Alves
 */
public class DutchNationalFlagCourseSolution {

    /**
     * The problem is that we want to sort a T[] one-dimensional 
     * array of integers in O(N) running time - and without any extra memory. 
     * The array can contain values: 0, 1 and 2 
     * (check out the theoretical section for further information).
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/30223752#overview
     */
    public static void main(String[] args) {
        
        final var solution1 = new DutchNationalFlagCourseSolution(new int[]{1, 1, 2, 0, 0, 1, 2, 2, 1, 0});
        solution1.solve();
        solution1.showResult();
        final var solution2 = new DutchNationalFlagCourseSolution(new int[]{0, 1, 2, 1, 2, 0, 0});
        solution2.solve();
        solution2.showResult();
        final var solution3 = new DutchNationalFlagCourseSolution(new int[]{2, 2, 1, 1, 0, 0});
        solution3.solve();
        solution3.showResult();
        final var solution4 = new DutchNationalFlagCourseSolution(new int[]{0, 1, 2});
        solution4.solve();
        solution4.showResult();
    }
   
    private int[] nums;

    public DutchNationalFlagCourseSolution(int[] nums) {
        this.nums = nums;
    }
    
    public void solve() {
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        int pivot = 1;
        
        while (j <= k) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] > pivot) {
                swap(nums, j, k);
                k--;
            } else {
                j++;
            }
        }
    }
    
    public void showResult() {
        System.out.println(Arrays.toString(nums));
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
