package br.com.guilhermealvessilve.certification.study.datastructure.tree.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.tree.bst.BinarySearchTree;

/**
 *
 * @author Alves
 */
public class MainCompareBinaryTrees {

    /**
     * Compare binary trees overview
     * Interview Question #1:
     * Write an efficient algorithm thats able to compare two binary search trees. 
     * The method returns true if the trees are identical 
     * (same topology with same values in the nodes) otherwise it returns false.
     * @param args 
     */
    public static void main(String[] args) {
        var bst1 = new BinarySearchTree<Integer>();
        bst1.insert(0, 1, 2, 3, 4);
        
        var bst2 = new BinarySearchTree<Integer>();
        bst2.insert(0, 1, 2, 3, 4);
        
        System.out.println("Identical 1: " + bst1.identical(bst2)); // true
        
        var bst3 = new BinarySearchTree<Integer>();
        bst3.insert(0, 1, 2, 3, 4);
        
        var bst4 = new BinarySearchTree<Integer>();
        bst4.insert(0, 1, 2, 3);
        
        System.out.println("Identical 2: " + bst3.identical(bst4)); // false
        
        var bst5 = new BinarySearchTree<Integer>();
        bst5.insert(4, 2, 3, 1, 0);
        
        var bst6 = new BinarySearchTree<Integer>();
        bst6.insert(2, 3, 1, 0, 4);
        
        System.out.println("Identical 3: " + bst5.identical(bst6)); // false
        
        System.out.println("Identical 4: " + new BinarySearchTree<Integer>().identical(new BinarySearchTree<>())); // true

        var bst7 = new BinarySearchTree<Integer>();
        bst7.insert(2, 0, -1, 1, 4, 3, 5);
        
        var bst8 = new BinarySearchTree<Integer>();
        bst8.insert(2, 0, -1, 1, 4, 3, 6);
        
        System.out.println("Identical 5: " + bst7.identical(bst8)); // false
        
        var bst9 = new BinarySearchTree<Integer>();
        bst9.insert(2, 1, 0, 5);
        
        var bst10 = new BinarySearchTree<Integer>();
        bst10.insert(2, 1, 0, 6);
        
        System.out.println("Identical 6: " + bst9.identical(bst10)); // false
        
        var bst11 = new BinarySearchTree<Integer>();
        bst11.insert(2, 6);
        
        var bst12 = new BinarySearchTree<Integer>();
        bst12.insert(2, 5);
        
        System.out.println("Identical 7: " + bst11.identical(bst12)); // false
    }
}
