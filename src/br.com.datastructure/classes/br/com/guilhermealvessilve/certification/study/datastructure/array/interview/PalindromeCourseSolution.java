package br.com.guilhermealvessilve.certification.study.datastructure.array.interview;

/**
 *
 * @author Alves
 */
public class PalindromeCourseSolution {

    /**
     * "A palindrome is a string that reads the same forward and backward"
     * For example: radar or madam
     * Your task is to design an optimal algorithm for checking whether a given string is palindrome or not! 
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/30236134#overview
     */
    public static void main(String[] args) {
        System.out.println("isPalindrome(radar): " + isPalindrome("radar")); // true
        System.out.println("isPalindrome(madam): " + isPalindrome("madam")); // true
        System.out.println("isPalindrome(sacas): " + isPalindrome("sacas")); // true
        System.out.println("isPalindrome(Anotaram a data da maratona): " + isPalindrome("Anotaram a data da maratona")); // true
        System.out.println("isPalindrome(john): " + isPalindrome("john")); // false
    }

    private static boolean isPalindrome(String word) {
       
        char[] array = word.toLowerCase()
                .replaceAll("\\s*", "")
                .toCharArray();
        int forward = 0;
        int backward = array.length - 1;
        
        while (forward <= backward) {
            if (array[forward] != array[backward]) return false;
            forward++;
            backward--;
        }

        return true;
    }
}
