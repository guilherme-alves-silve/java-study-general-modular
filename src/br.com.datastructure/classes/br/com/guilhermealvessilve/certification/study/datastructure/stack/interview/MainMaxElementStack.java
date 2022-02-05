package br.com.guilhermealvessilve.certification.study.datastructure.stack.interview;

/**
 *
 * @author Alves
 */
public class MainMaxElementStack {
    
    /**
     * Max in a stack problem overview
     * Interview Question #1
     * The aim is to design an algorithm that can return the maximum item of a stack in O(1) running time complexity. We can use O(N) extra memory!
     * Hint: we can use another stack to track the max item
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/11582532#overview
     */
    public static void main(String[] args) {
        var stack = new MaxItemStack<Integer>();
        stack.push(10, 8, 23, 1);
        System.out.println("stack: " + stack); // [1, 23, 8, 10]
        System.out.println("stack.isEmpty: " + stack.isEmpty());
        System.out.println("stack.size: " + stack.size());
        System.out.println("stack.peek: " + stack.peek());
        System.out.println("stack.pop: " + stack.pop());
        System.out.println("stack: " + stack);
        stack.forEach(i -> System.out.print(i + " "));
        System.out.println();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.println("stack.getMaxItem: " + stack.getMaxItem());
            System.out.println("stack.pop: " + stack.pop());
        }
        System.out.println("stack: " + stack); // {"mainStack":[], "maxStack": []}
        System.out.println("stack.isEmpty: " + stack.isEmpty()); // true
        System.out.println("stack.size: " + stack.size()); // 0
    }
}
