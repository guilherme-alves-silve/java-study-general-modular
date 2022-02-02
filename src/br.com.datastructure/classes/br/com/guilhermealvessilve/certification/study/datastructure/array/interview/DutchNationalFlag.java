package br.com.guilhermealvessilve.certification.study.datastructure.array.interview;

import java.util.Arrays;

/**
 *
 * @author Alves
 */
public class DutchNationalFlag {

    /**
     * The problem is that we want to sort a T[] one-dimensional 
     * array of integers in O(N) running time - and without any extra memory. 
     * The array can contain values: 0, 1 and 2 
     * (check out the theoretical section for further information).
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/30223746#overview
     *  https://www.youtube.com/watch?v=2C4CQ32961Y&t=325s&ab_channel=ApnaCollege
     */
    public static void main(String[] args) {
        System.out.println("threeWayPartition: " 
                + Arrays.toString(threeWayPartition(new int[]{1, 1, 2, 0, 0, 1, 2, 2, 1, 0}, 1)));
        System.out.println("threeWayPartition: " 
                + Arrays.toString(threeWayPartition(new int[]{0, 1, 2, 1, 2, 0, 0}, 1)));
        System.out.println("threeWayPartition: " 
                + Arrays.toString(threeWayPartition(new int[]{2, 2, 1, 1, 0, 0}, 1)));
        System.out.println("threeWayPartition: " 
                + Arrays.toString(threeWayPartition(new int[]{0, 1, 2}, 1)));
    }
    
    private static int[] threeWayPartition(int[] array, int pivot) {
        int low = 0;
        int mid = 0;
        int high = array.length - 1;
        
        while (mid <= high) {
            if (array[mid] < pivot) {
                swap(array, low, mid);
                ++low;
                ++mid;
            }  else if (array[mid] > pivot) {
                swap(array, mid, high);
                --high;
            } else {
                ++mid;
            }
        }
        
        return array;
    }
    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
