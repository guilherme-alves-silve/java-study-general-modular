package br.com.guilhermealvessilve.certification.study.datastructure.stack.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.stack.implementation.Stack;
import java.util.Iterator;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alves
 */
public class MaxItemStack<E extends Comparable<E>> implements Iterable<E> {
    
    private final Stack<E> mainStack;
    private final Stack<E> maxStack;

    public MaxItemStack() {
        this.mainStack = new Stack<>();
        this.maxStack = new Stack<>();
    }
 
    public void push(E data) {
        requireNonNull(data);

        if (mainStack.isEmpty()) {
            mainStack.push(data);
            maxStack.push(data);
            return;
        } 
        
        if (data.compareTo(maxStack.peek()) > 0) {
            maxStack.push(data);
        } else {
            maxStack.push(maxStack.peek());
        }
        
        mainStack.push(data);
    }
    
    public void push(E... elements) {
        if (requireNonNull(elements).length == 0) {
            throw new IllegalArgumentException();
        }
        
        for (var element : elements) {
            push(element);
        }
    }
    
    public E pop() {
        maxStack.pop();
        return mainStack.pop();
    }
    
    public E peek() {
        return mainStack.peek();
    }
    
    public E getMaxItem() {
        return maxStack.peek();
    }
    
    public int size() {
        return mainStack.size();
    }
    
    public boolean isEmpty() {
        return mainStack.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return mainStack.iterator();
    }
    
    @Override
    public String toString() {
        return "{\"mainStack\":" + mainStack + ", \"maxStack\": " + maxStack + '}';
    }
}
