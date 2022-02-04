package br.com.guilhermealvessilve.certification.study.datastructure.linkedlist.list;

/**
 *
 * @author Alves
 */
public class MainLinkedList {

    public static void main(String[] args) {
        var list = new LinkedList<Integer>();
        list.add(1, 2, 3, 4, 5);
        System.out.println("list.size: " + list.size());
        System.out.println("list.isEmpty: " + list.isEmpty());
        System.out.println("list: " + list);
        System.out.println("list: " + new LinkedList<Integer>());
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d ", list.get(i));
        }
        System.out.println();
        
        for (var element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
        
        list.remove(0);
        list.remove(list.size() - 1);
        list.add(10);
        list.addFirst(-1);
        System.out.println("list.size: " + list.size());
        System.out.println("list.isEmpty: " + list.isEmpty());
        System.out.println("list: " + list);
        System.out.println("list.pollFirst: " + list.pollFirst());
        System.out.println("list.pollFirst: " + list.pollFirst());
        System.out.println("list: " + list);
        System.out.println("list.peekFirst: " + list.peekFirst());
        System.out.println("list.peekLast: " + list.peekLast());
        System.out.println("list: " + list);
    }
}
