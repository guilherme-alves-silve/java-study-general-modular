package br.com.guilhermealvessilve.certification.study.datastructure.stack.interview;

/**
 *
 * @author Alves
 */
public class MainQueueWithStack {
    
    /**
     * Stack with queue overview
     * Interview Question #2
     * The aim is to design a queue abstract data type with the help of stacks.
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/11582440#overview
     */
    public static void main(String[] args) {
        var queue = new QueueWithStack<Integer>();
        queue.enqueue(10, 18, 30);
        System.out.println("queue.size: " + queue.size()); // 3
        System.out.println("queue.isEmpty: " + queue.isEmpty()); // false
        System.out.println("queue.pop: " + queue.dequeue());
        System.out.println("queue.size: " + queue.size()); // 2
        queue.enqueue(35);
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            System.out.println("queue.pop: " + queue.dequeue());
        }
        
        System.out.println("queue.pop: " + queue.dequeue());
        System.out.println("queue.isEmpty: " + queue.isEmpty()); // true
        System.out.println("queue.size: " + queue.size()); // 0
    }
}
