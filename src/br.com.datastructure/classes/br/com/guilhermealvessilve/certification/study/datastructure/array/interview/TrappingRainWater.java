package br.com.guilhermealvessilve.certification.study.datastructure.array.interview;

import java.util.Arrays;

/**
 *
 * @author Alves
 */
public class TrappingRainWater {

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
        System.out.println("trapRainWater([7,10]): " + trapRainWater(new int[]{7,10})); // 0
        System.out.println("trapRainWater([4,1,3]): " + trapRainWater(new int[]{4,0,3})); // 3
        System.out.println("trapRainWater([4,1,3]): " + trapRainWater(new int[]{4,1,3})); // 2
        System.out.println("trapRainWater([4,1,3,1,5]): " + trapRainWater(new int[]{4,1,3,1,5})); // 7
        System.out.println("trapRainWater([1,0,2,1,3,1,2,0,3]): " + trapRainWater(new int[]{1,0,2,1,3,1,2,0,3})); // 8
        System.out.println("trapRainWater([2,1,2,2,1,1,5]): " + trapRainWater(new int[]{2,1,2,2,1,1,5})); // 3
    }

    private static int trapRainWater(int[] height) {
        if (height.length <= 2) return 0;
        
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        int[] trappedWater = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            maxLeft[i] = findMaxLeft(height, i);
            maxRight[i] = findMaxRight(height, i);
            int result = Math.min(maxLeft[i], maxRight[i]) - height[i];
            trappedWater[i] = result > 0? result : 0;
        }
        
        int total = 0;
        for (int i = 0; i < trappedWater.length; i++) {
            total += trappedWater[i];
        }
        
        return total;
    }

    private static int findMaxLeft(int[] height, int pos) {
        
        int max = 0;
        for (int i = pos; i >= 0; i--) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        
        return max;
    }

    private static int findMaxRight(int[] height, int pos) {
        int max = 0;
        for (int i = pos; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        
        return max;
    }
}
