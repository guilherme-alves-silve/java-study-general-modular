package br.com.guilhermealvessilve.certification.study.datastructure.sorting;

/**
 *
 * @author Alves
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] scores = { 60, 80, 95, 50, 70 };
        sort(scores);
        for (int score : scores) {
            System.out.print(score + ",");
        }
        
        System.out.println("");
    }
    
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            
            int minIndex = i; //the index of the selected minimum
            for (int j = i + 1; j < array.length; j++) {
                
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            
            if (i != minIndex) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
