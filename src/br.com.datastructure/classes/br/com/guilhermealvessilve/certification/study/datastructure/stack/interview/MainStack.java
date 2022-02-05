package br.com.guilhermealvessilve.certification.study.datastructure.stack.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.stack.implementation.Stack;

/**
 *
 * @author Alves
 */
public class MainStack {

    public static void main(String[] args) {
        var stack = new Stack<Integer>();
        stack.push(1, 2, 3, 4, 5);
        System.out.println("stack: " + stack); // [5, 4, 3, 2, 1]
        System.out.println("stack: " + stack.isEmpty()); // false
        System.out.println("stack: " + stack.size()); // 5
        System.out.println("stack.peek: " + stack.peek()); // 5
        System.out.println("stack: " + stack); // [5, 4, 3, 2, 1]
        System.out.println("stack.pop: " + stack.pop()); // 5
        System.out.println("stack.pop: " + stack.pop()); // 4
        System.out.println("stack: " + stack); // [3, 2, 1]
        System.out.println("stack: " + stack.isEmpty()); // false
        System.out.println("stack: " + stack.size()); // 3
        stack.forEach(i -> System.out.print(i + " "));
        System.out.println();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            stack.pop();
        }
        System.out.println("stack.pop: " + stack.pop()); // null
        System.out.println("stack.pop: " + stack.size()); // 0
        System.out.println("stack.pop: " + stack.isEmpty()); // true
    }
}
