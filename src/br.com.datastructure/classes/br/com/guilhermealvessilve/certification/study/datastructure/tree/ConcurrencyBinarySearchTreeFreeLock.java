package br.com.guilhermealvessilve.certification.study.datastructure.tree;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Alves
 * @param <E>
 */
public class ConcurrencyBinarySearchTreeFreeLock<E extends Comparable<E>> {
    
    private final AtomicInteger size;
    private final AtomicReference<Node<E>> root;

    public ConcurrencyBinarySearchTreeFreeLock() {
        this.size = new AtomicInteger();
        this.root = new AtomicReference<>();
    }
    
    public boolean add(E data) {
        
        var tempRef = root.get();
        if (null == tempRef) {
            final var newNode = new Node<E>(null, data);
            if (root.compareAndSet(null, newNode)) {
                size.incrementAndGet();
                return true;
            }
        }
        
        return addNode(root, data);
    }
    
    private boolean addNode(AtomicReference<Node<E>> node, E data) {
        
        var tempRef = node.get();
        if (null == tempRef) {
            return add(data);
        }
        
        if (tempRef.compareTo(data) == 0) {
            return false;
        }
        
        
        
        
        return false;
    }
    
    private static class Node<E extends Comparable<E>> implements Comparable<E> {
        private final Node<E> parent;
        private final AtomicReference<Node<E>> left;
        private final AtomicReference<Node<E>> right;
        private volatile E data;
        private volatile boolean usedByAnotherThread;

        public Node(Node<E> parent, E data) {
            this.left = new AtomicReference<>();
            this.right = new AtomicReference<>();
            this.parent = parent;
            this.data = data;
        }
        
        @Override
        public int compareTo(E otherData) {
            return data.compareTo(otherData);
        }
    }
}
