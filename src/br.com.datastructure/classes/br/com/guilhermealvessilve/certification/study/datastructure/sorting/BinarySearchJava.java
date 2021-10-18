package br.com.guilhermealvessilve.certification.study.datastructure.sorting;

import java.util.ArrayList;
import static java.util.Collections.binarySearch;
import java.util.List;

/**
 *
 * @author Alves
 */
public class BinarySearchJava {
    
    public static void main(String[] args) {
        
        var root = new Node("root", List.of(4842));
        var internal1 = new Node("internal1", List.of(466,1628));
        var internal1Leaf1 = new Node("internal1Leaf1", List.of(144, 181));
        var internal1Leaf2 = new Node("internal1Leaf2", List.of(823, 1587));
        var internal1Leaf3 = new Node("internal1Leaf3", List.of(1836, 3370));
        
        var internal2 = new Node("internal2", List.of(5771,7352));
        var internal2Leaf1 = new Node("internal2Leaf1", List.of(5098, 5194));
        var internal2Leaf2 = new Node("internal2Leaf2", List.of(6379, 6683));
        var internal2Leaf3 = new Node("internal2Leaf3", List.of(7737, 9376, 9970, 9980));
        
        root.add(
                internal1.add(
                        internal1Leaf1,
                        internal1Leaf2,
                        internal1Leaf3
                ),
                internal2.add(
                        internal2Leaf1,
                        internal2Leaf2,
                        internal2Leaf3
                )
        );
        
        findInNode(root, 7737);
        
        System.out.println("");
        
        findInNode(root, 10_000);
            
        System.out.println("");
        
        findInNode(root, 6379);
        
        System.out.println();
        System.out.println("root: " + root);
        
    }

    private static void findInNode(Node node, int value) {
        
        System.out.printf("Entering node %s to search %d%n", node.name, value);
        //int index = binarySearch(node.data, value);
        int index = binarySearch2(node.data, value);
        System.out.println("Index of value: " + index);
        if (index >= 0) {
            System.out.printf("Found value %d at index %d%n", node.data.get(index), index);
        } else if (node.isLeaf()) {
            System.out.println("This node is a leaf, stop searching!");
        } else {
            //int indexPath = (-index - 1);
            int indexPath = -index;
            var child = node.children.get(indexPath);
            System.out.printf("Will search on child%s at index %d %n", child.data.toString(), indexPath);
            findInNode(child, value);
        }
    }
    
    private static <T> int binarySearch2(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size()-1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = list.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        //-(low + 1);
        return -low;  // key not found
    }
    
    private static class Node {
        
        private String name;
        private List<Integer> data = new ArrayList<>();
        private List<Node> children = new ArrayList<>();

        public Node() {
        }

        public Node(String name, List<Integer> data) {
            this.name = name;
            this.data = data;
        }
        
        public Node(List<Integer> data) {
            this.data.addAll(data);
        }
        
        public Node(List<Integer> data, List<Node> children) {
            this.data.addAll(data);
            this.children.addAll(children);
        }
        
        public Node add(Node child) {
            children.add(child);
            return this;
        }
        
        public Node add(Node... children) {
            for (var child : children) {
                add(child);
            }
            return this;
        }

        @Override
        public String toString() {
            if ("root".equalsIgnoreCase(name)) {            
                return "Node{" + "name=" + name + ", data=" + data + ", children=" + children + '}';
            }
            
            if (children.isEmpty()) {
                return "\t\tNode{" + "name=" + name + ", data=" + data + ", children=[]}";
            }
            
            return "\n\tNode{" + "name=" + name + ", data=" + data + ", children=\n" + children + '}';
        }

        private boolean isLeaf() {
            return children.isEmpty();
        }
    }
}
