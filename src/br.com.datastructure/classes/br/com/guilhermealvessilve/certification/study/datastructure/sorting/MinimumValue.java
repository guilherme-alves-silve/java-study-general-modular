package br.com.guilhermealvessilve.certification.study.datastructure.sorting;

/**
 *
 * @author Alves
 */
public class MinimumValue {

    public static void main(String[] args) {
        int[] scores = { 60, 80, 95, 50, 70 };
        int minValue = min(scores);
        System.out.println("Min Value = " + minValue);
    }
    
    public static int min(int[] array) {
        int minIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[minIndex] > array[i]) {
                minIndex = i;
            }
        }
        
        return array[minIndex];
    }
}
