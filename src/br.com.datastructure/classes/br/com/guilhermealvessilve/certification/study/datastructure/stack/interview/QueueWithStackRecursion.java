package br.com.guilhermealvessilve.certification.study.datastructure.stack.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.stack.implementation.Stack;

/**
 *
 * @author Alves
 */
public class QueueWithStackRecursion<E extends Comparable<E>> {
    
    private final Stack<E> stack;

    public QueueWithStackRecursion() {
        this.stack = new Stack<>();
    }
    
    public void enqueue(E data) {
        stack.push(data);
    }
    
    public void enqueue(E... elements) {
        stack.push(elements);
    }
    
    public E dequeue() {
        
        if (stack.size() <= 1) {
            return stack.pop();
        }
        
        var item = stack.pop();
        
        E lastItemDequeue = dequeue();
        
        enqueue(item);
        
        return lastItemDequeue;
    }
    
    public int size() {
        return stack.size();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
