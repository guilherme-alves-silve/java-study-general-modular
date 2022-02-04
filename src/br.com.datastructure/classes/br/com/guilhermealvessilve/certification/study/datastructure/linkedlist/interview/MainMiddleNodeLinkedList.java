package br.com.guilhermealvessilve.certification.study.datastructure.linkedlist.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.linkedlist.list.DoublyLinkedList;

/**
 *
 * @author Alves
 */
public class MainMiddleNodeLinkedList {

    /**
     * Finding the middle node in a linked list overview
     * Suppose we have a standard linked list. 
     * Construct an in-place (without extra memory) 
     * algorithm thats able to find the middle node!
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/11266854#overview
     */
    public static void main(String[] args) {
        var list = new DoublyLinkedList<Integer>();
        list.add(1, 2, 3, 4, 5);
        System.out.println("list: " + list);
        System.out.println("middleValue: " + list.getMiddleValue()); // 3
        list.add(6, 7);
        System.out.println("list: " + list);
        System.out.println("middleValue: " + list.getMiddleValue()); // 4
    }
    
}
