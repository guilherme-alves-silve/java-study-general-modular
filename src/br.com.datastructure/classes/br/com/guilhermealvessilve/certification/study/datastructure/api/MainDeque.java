package br.com.guilhermealvessilve.certification.study.datastructure.api;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Alves
 */
public class MainDeque {

    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        
        deque.offer("b");
        deque.offerFirst("a");
        deque.offerLast("c");
        
        System.out.println(deque);
        
        System.out.println("first: " + deque.pollFirst());
        System.out.println("last: " + deque.pollLast());
        System.out.println("size: " + deque.size());
        System.out.println("peekLast: " + deque.peekFirst());
        
        System.out.println(deque);
    }
}
