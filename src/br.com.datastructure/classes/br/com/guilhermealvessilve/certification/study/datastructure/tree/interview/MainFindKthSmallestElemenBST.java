package br.com.guilhermealvessilve.certification.study.datastructure.tree.interview;

import br.com.guilhermealvessilve.certification.study.datastructure.tree.bst.BinarySearchTree;

/**
 *
 * @author Alves
 */
public class MainFindKthSmallestElemenBST {
    
    /**
     * K-th smallest element in a search tree overview
     * Interview Question #2:
     * Write an efficient in-place algorithm to find the k-th smallest (largest) item in a binary search tree!
     * @param args 
     */
    public static void main(String[] args) {
        var bst = new BinarySearchTree<Integer>();
        bst.insert(12, 4, 1, 5, 20, 15, 24);
        System.out.println("K-th smallests");
        for (int i = 0; i < bst.size(); i++) {
           System.out.println(i + " - " + bst.kthSmallest(i));
        }
        //Result of eeach call: null, 1, 4, 5, 12, 15, 20, 24
    }
}
