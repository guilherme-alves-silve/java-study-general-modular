package br.com.guilhermealvessilve.certification.study.datastructure.queue;

/**
 *
 * @author Alves
 */
public class MainQueue {
    
    public static void main(String[] args) {
        final var queue = new Queue<Integer>();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        
        System.out.println(queue);
        System.out.println("queue.isEmpty(): " + queue.isEmpty());
        System.out.println("queue.size(): " + queue.size());
        
        System.out.print("Queue[");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue());
            if (!queue.isEmpty()) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        System.out.println(queue);
        System.out.println("queue.isEmpty(): " + queue.isEmpty());
        System.out.println("queue.size(): " + queue.size());
    }
}
