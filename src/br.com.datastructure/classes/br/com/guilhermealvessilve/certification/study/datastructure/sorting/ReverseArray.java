package br.com.guilhermealvessilve.certification.study.datastructure.sorting;

/**
 *
 * @author Alves
 */
public class ReverseArray {
    
    public static void main(String[] args) {
        int[] scores = { 50, 60, 70, 80, 90, 100 };
        reverse(scores);
        for (int score : scores) {
            System.out.print(score + ",");
        }
        
        System.out.println("");
    }
    
    public static void reverse(int[] array) {
        int length = array.length;
        int middle = length / 2;
        for (int i = 0; i < middle; i++) {
            int temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
    }
}
