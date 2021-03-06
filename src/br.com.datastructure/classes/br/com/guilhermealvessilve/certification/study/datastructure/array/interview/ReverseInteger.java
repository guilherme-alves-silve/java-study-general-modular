package br.com.guilhermealvessilve.certification.study.datastructure.array.interview;

/**
 *
 * @author Alves
 */
public class ReverseInteger {
    
    /**
     * Your task is to design an efficient algorithm to reverse a given integer. 
     * For example if the input of the algorithm is 1234 then the output should be 4321.
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/30236114#overview
     */
    public static void main(String[] args) {
        System.out.println("reverseInt(1234): " + reverseInt(1234));// 4321
        System.out.println("reverseInt(8989): " + reverseInt(8989));// 9898
    }

    private static int reverseInt(int value) {
        char[] array = String.valueOf(value).toCharArray();
        
        int length = array.length;
        int middle = length / 2;
        for (int i = 0; i < middle; i++) {
            char temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
            
        return Integer.parseInt(new String(array));
    }
}
