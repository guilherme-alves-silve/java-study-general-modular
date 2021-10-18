/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermealvessilve.certification.study.datastructure.sorting;

/**
 *
 * @author Alves
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] scores = { 60, 50, 95, 80, 70 };
        sort(scores);
        
        for (int i : scores) {
            System.out.print(i + ",");
        }
        
        System.out.println("");
    }
    
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {

            boolean swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {

                if (array[j] > array[j + 1]) { //swap
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }

            }

            if (!swapped) { //No swap, so terminate sorting
                break;
            }
        }
    }
}
