package br.com.guilhermealvessilve.certification.study.datastructure.tree.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.tree.bst.BinarySearchTree;
import java.util.Comparator;

/**
 *
 * @author Alves
 */
public class MainFamilyAgeProblem {
    
    /**
     * Family age problem overview
     * Interview Question #3:
     * Write an efficient algorithm to calculate the total sum of ages in a family tree. 
     * A family tree is a binary search tree in this case where all the nodes 
     * contain a Person object with [name,age] attributes.
     * Hint: we have to make a tree traversal so the running time of the algoritm will be O(N) linear running time
     * Reference:
     *  https://www.udemy.com/course/algorithms-and-data-structures/learn/lecture/11587266#overview
     */
    public static void main(String[] args) {
       var bst = new BinarySearchTree<Person>();
        bst.insert(
                new Person("Julia", 12),
                new Person("John Wick", 4), 
                new Person("Kenshiro", 1),
                new Person("Ball", 5), 
                new Person("Heihachi", 20),
                new Person("Marshall Law", 15),
                new Person("Eddy Gordo", 24)
        );
        System.out.println("Family age sum");
        System.out.println("Sum age: " + bst.sum(person -> person.age));
    }
    
    private static class Person implements Comparable<Person> {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person o) {
            int compare = String.CASE_INSENSITIVE_ORDER.compare(name, o.name);
            if (compare != 0) return compare;
            else return Integer.compare(age, o.age);
        }
    }
}
