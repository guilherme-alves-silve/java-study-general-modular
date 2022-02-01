package br.com.guilhermealvessilve.certification.study.datastructure.array.interview;

/**
 *
 * @author Alves
 */
public class ReverseIntegerCourseSolution {
    
    /**
     * Your task is to design an efficient algorithm to reverse a given integer. 
     * For example if the input of the algorithm is 1234 then the output should be 4321.
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/30236114#overview
     */
    public static void main(String[] args) {
        System.out.println("reverseInt(1234): " + reverseInt(1234));// 4321
        System.out.println("reverseInt(8989): " + reverseInt(8989));// 9898
        System.out.println("reverseInt(1000): " + reverseInt(1000));// 1
        System.out.println("reverseInt(1002): " + reverseInt(1002));// 2001
    }

    private static int reverseInt(int n) {
        
        int remainder;
        int reversed = 0;
        while (n > 0) {
            remainder = n % 10;
            n /= 10;
            reversed = reversed * 10 + remainder;
        }
        
        return reversed;
    }
}
