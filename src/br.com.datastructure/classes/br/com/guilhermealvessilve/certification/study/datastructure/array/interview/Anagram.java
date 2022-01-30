package br.com.guilhermealvessilve.certification.study.datastructure.array.interview;

/**
 *
 * @author Alves
 */
public class Anagram {
    
    /**
     * Your task is to construct an algorithm to check whether two words (or phrases) are anagrams or not!
     * "An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once"
     * For example: restful and fluster are anagrams.
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/11573246#overview
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("isAnagram(restful, fluster): " + isAnagram("restful", "fluster")); // true
        System.out.println("isAnagram(mad, dam): " + isAnagram("Mad", "Dam")); // true
        System.out.println("isAnagram(Adam, John Wick): " + isAnagram("Adam", "John Wick")); // false
    }

    private static boolean isAnagram(String word1, String word2) {
        if (null == word1 || null == word2) return false;
        if (word1.length() != word2.length()) return false;
        
        var sorted1 = sort(word1.toLowerCase());
        var sorted2 = sort(word2.toLowerCase());
        
        for (int i = 0; i < sorted1.length(); i++) {
            if (sorted1.charAt(i) != sorted2.charAt(i)) return false;
        }
        
        return true;
    }

    private static String sort(String word) {
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    char temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) break;
        }

        return new String(arr);
    }
}
