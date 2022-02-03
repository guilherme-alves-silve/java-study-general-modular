package br.com.guilhermealvessilve.certification.study.datastructure.array.interview;

import java.util.Arrays;

/**
 *
 * @author Alves
 */
public class TrappingRainWaterCourseSolution {
    
    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1. 
     * Compute how much water it can trap after raining!
     * Example:
     * # = land
     * @ = water
     *     #
     * #@@@#
     * #@#@#
     * #@#@#
     * #####
     * Here the elevation map (the input for the algorithm) is [4,1,3,1,5] 
     * and the output is the total units of trapped rain water - which is 7.
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/30224348#overview
     */
    public static void main(String[] args) {
        var solver = new TrappingRainWaterCourseSolution();
        System.out.println("solve([7,10]): " + solver.solve(new int[]{7,10})); // 0
        System.out.println("solve([4,1,3]): " + solver.solve(new int[]{4,0,3})); // 3
        System.out.println("solve([4,1,3]): " + solver.solve(new int[]{4,1,3})); // 2
        System.out.println("solve([4,1,3,1,5]): " + solver.solve(new int[]{4,1,3,1,5})); // 7
        System.out.println("solve([1,0,2,1,3,1,2,0,3]): " + solver.solve(new int[]{1,0,2,1,3,1,2,0,3})); // 8
        System.out.println("solve([2,1,2,2,1,1,5]): " + solver.solve(new int[]{2,1,2,2,1,1,5})); // 3
    }
    
    public int solve(int[] height) {
        if (height.length <= 2) return 0;
        
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        
        fillMaxLeft(maxLeft, height);
        fillMaxRight(maxRight, height);
        
        int trapped = 0;
        for (int i = 0; i < height.length; i++) {
            int result = Math.min(maxLeft[i], maxRight[i]) - height[i];
            trapped += result > 0? result : 0;
        }
        
        return trapped;
    }

    private void fillMaxLeft(int[] maxLeft, int[] height) {
        int max = 0;
        for (int i = 0; i < maxLeft.length; ++i) {
            if (height[i] > max) max = height[i];
            maxLeft[i] = max;
        }
    }

    private void fillMaxRight(int[] maxRight, int[] height) {
        int max = 0;
        for (int i = maxRight.length - 1; i >= 0; --i) {
            if (height[i] > max) max = height[i];
            maxRight[i] = max;
        }
    }
}
