package br.com.guilhermealvessilve.certification.study.datastructure.stack.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.stack.implementation.Stack;

/**
 *
 * @author Alves
 */
public class QueueWithStacks<E extends Comparable<E>> {
    
    private final Stack<E> enqueueStack;
    private final Stack<E> dequeueStack;

    public QueueWithStacks() {
        this.enqueueStack = new Stack<>();
        this.dequeueStack = new Stack<>();
    }
    
    public void enqueue(E data) {
        enqueueStack.push(data);
    }
    
    public void enqueue(E... elements) {
        enqueueStack.push(elements);
    }
    
    public E dequeue() {
        
        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        
        return dequeueStack.pop();
    }
    
    public int size() {
        return enqueueStack.size() + dequeueStack.size();
    }
    
    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }
}
