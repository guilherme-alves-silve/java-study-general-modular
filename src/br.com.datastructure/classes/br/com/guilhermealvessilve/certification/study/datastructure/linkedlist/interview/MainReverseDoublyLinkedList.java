package br.com.guilhermealvessilve.certification.study.datastructure.linkedlist.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.linkedlist.list.DoublyLinkedList;

/**
 *
 * @author Alves
 */
public class MainReverseDoublyLinkedList {

    /**
     * Construct an in-place algorithm (without the need for extra memory) to reverse a linked list!
     * For example: 1 -> 2 -> 3 -> 4 should be transformed into 4 -> 3 -> 2 -> 1
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/11259686#overview
     */
    public static void main(String[] args) {
        var list = new DoublyLinkedList<Integer>();
        list.add(1, 2, 3, 4);
        System.out.println("list: " + list);
        list.reverse();
        System.out.println("list: " + list);
    }
}
