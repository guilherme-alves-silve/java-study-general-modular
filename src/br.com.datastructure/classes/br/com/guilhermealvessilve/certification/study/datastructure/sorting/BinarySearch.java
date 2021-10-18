package br.com.guilhermealvessilve.certification.study.datastructure.sorting;

/**
 *
 * @author Alves
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] scores = { 30, 40, 50, 70, 85, 90, 100 };
        
        int searchValue = 40;
        int position = binarySearch(scores, searchValue);
        System.out.println(searchValue + " position: " + position);

        System.out.println("**********************");
        
        searchValue = 90;
        position = binarySearch(scores, searchValue);
        System.out.println(searchValue + " position: " + position);
        
        System.out.println("All values searched...");
        
        for (int score : scores) {
            position = binarySearch(scores, score);
            System.out.println(score + " position: " + position); 
        }
    }
    
    public static int binarySearch(int[] array, int searchValue) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        
        while (low <= high) {
            
            mid = (low + high) / 2;
            
            if (array[mid] == searchValue) {
                return mid;
            } else if (array[mid] < searchValue) {
                low = mid + 1;
            } else if (array[mid] > searchValue) {
                high = mid - 1;
            }
        }
        
        return -1;
    }
}
