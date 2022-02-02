package br.com.guilhermealvessilve.certification.study.datastructure.array.list;

/**
 *
 * @author Alves
 */
public class MainList {
    
    public static void main(String[] args) {
        var list1 = new List<Integer>();
        list1.add(0, 1, 2, 3, 4, 5);
        System.out.println("list1.size: " + list1.size());
        System.out.println("list1.isEmpty: " + list1.isEmpty());
        System.out.println("list1: " + list1);
        list1.forEach(i -> System.out.print(i + " "));
        System.out.println();
        list1.remove(0);
        list1.remove(3);
        list1.remove(3);
        list1.set(0, 50);
        list1.add(25);
        System.out.println("list1: " + list1);
        
        var list2 = new List<Integer>();
        for (int i = 0; i < 500; i++) {
            list2.add(i);
        }
        for (int i = 0; i < 500; i++) {
            if (!list2.contains(i)) {
                throw new IllegalStateException("Array is invalid!");
            }
        }
        int size2 = list2.size();
        for (int i = 5; i < size2; i++) {
            if (!list2.contains(list2.size() - 1)) {
                throw new IllegalStateException("Array is invalid! (1)");
            }
            list2.remove(list2.size() - 1);
        }
        System.out.println("list2.size: " + list2.size());
        System.out.println("list2: " + list2);
        for (int i = 5; i < 100; i++) {
            list2.add(i);
        }
        System.out.println("list2.size: " + list2.size());
        System.out.println("list2.isEmpty: " + list2.isEmpty());
        System.out.println("list2: " + list2);
    }
}
