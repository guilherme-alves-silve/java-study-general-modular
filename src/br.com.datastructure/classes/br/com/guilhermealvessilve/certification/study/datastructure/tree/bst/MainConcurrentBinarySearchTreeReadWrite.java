package br.com.guilhermealvessilve.certification.study.datastructure.tree.bst;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 *
 * @author Alves
 */
public class MainConcurrentBinarySearchTreeReadWrite {
    
    public static void main(String[] args) throws InterruptedException {
        final var tree = new ConcurrentBinarySearchTreeReadWrite<Integer>();
        
        final var threads = 10;
        executeParallel(threads, () -> {
            final var ints = TestUtils.randomList(0, 1000);
            ints.forEach(tree::add);
            return null;
        });
        
        final var traversed = tree.traverse();
        
        final var foundAll = new AtomicBoolean(true);
        executeParallel(threads, () -> {
            final var innerFoundAll = TestUtils.randomList(0, 1000)
                .stream()
                .allMatch(value -> tree.search(value) != null);
            
            if (!innerFoundAll) {
                foundAll.set(false);
            }
            
            return null;
        });
        
        System.out.println("foundAll: " + foundAll.get());
        System.out.println("tree.size(): " + tree.size());
        System.out.println("size == traversed.size: " + (tree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + tree.isEmpty());
        System.out.println("tree.traverse(): " + traversed);
        System.out.println("tree.getMin(): " + tree.getMin());
        System.out.println("tree.getMax(): " + tree.getMax());
        
        final var removedAll = new AtomicBoolean(false);
        executeParallel(threads, () -> {
            TestUtils.randomList(0, 1000)
                .stream()
                .forEach(value -> tree.remove(value));
            
            if (tree.isEmpty()) {
                removedAll.set(true);
            }
            
            return null;
        });
        
        System.out.println("Removed async");
        
        TestUtils.randomList(0, 1000)
                .stream()
                .forEach(value -> tree.remove(value));
        
        System.out.println();
        System.out.println("tree.size(): " + tree.size());
        System.out.println("size == traversed.size: " + (tree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + tree.isEmpty());
        System.out.println("tree.traverse(): " + tree.traverse());
        System.out.println("tree.getMin(): " + tree.getMin());
        System.out.println("tree.getMax(): " + tree.getMax());
    }
    
    private static void executeParallel(int threads, Callable<Void> task) {
        final var threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
        threadPool.prestartAllCoreThreads();
        
        try {
            IntStream.range(0, threads)
                .forEach(i -> threadPool.submit(task));
        } finally {
            threadPool.shutdown();
            try {
                threadPool.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
