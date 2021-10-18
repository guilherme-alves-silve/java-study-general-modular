package br.com.guilhermealvessilve.certification.study.datastructure.sorting;

/**
 *
 * @author Alves
 */
public class InsertionSort {
    
    public static void main(String[] args) {
        int[] scores = { 80, 70, 60, 50, 95 };
        sort(scores);
        for (int score : scores) {
            System.out.print(score + ",");
        }
        
        System.out.println("");
    }
    
    public static void sort(int[] array) {
        
        for (int i = 1; i < array.length; i++) {
            
            int insertElement = array[i]; //Take unsorted new element
            int insertPosition = i;
            
            for (int j = insertPosition - 1; j >= 0; j--) {
                
                if (insertElement < array[j]) {
                    array[j + 1] = array[j];
                    --insertPosition;
                } else {
                    break;
                }
            }
            
            array[insertPosition] = insertElement; //insert the new element
        }
    }
}
