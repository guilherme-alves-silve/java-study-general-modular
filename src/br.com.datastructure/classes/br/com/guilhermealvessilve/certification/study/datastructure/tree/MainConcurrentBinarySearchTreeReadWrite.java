package br.com.guilhermealvessilve.certification.study.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            final var ints = randomList(0, 1000);
            ints.forEach(tree::add);
            return null;
        });
        
        final var traversed = tree.traverse();
        
        final var foundAll = new AtomicBoolean(true);
        executeParallel(threads, () -> {
            final var innerFoundAll = randomList(0, 1000)
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
        
        final var removedAll = new AtomicBoolean(true);
        executeParallel(threads, () -> {
            final var innerRemoveAll = randomList(0, 1000)
                .stream()
                .allMatch(value -> tree.remove(value) != null);
            
            if (!innerRemoveAll) {
                foundAll.set(false);
            }
            
            return null;
        });
        
        System.out.println("removedAll: " + removedAll.get());
        System.out.println("tree.size(): " + tree.size());
        System.out.println("size == traversed.size: " + (tree.size() == traversed.size()));
        System.out.println("tree.isEmpty(): " + tree.isEmpty());
    }
    
    private static List<Integer> randomList(int startInclusive, int endExclusive) {
        final var ints = IntStream.range(startInclusive, endExclusive)
                .collect(() -> new ArrayList<Integer>(), 
                        (list, intValue) -> list.add(intValue), 
                        (list1, list2) -> list1.addAll(list2));
        Collections.shuffle(ints);
        return ints;
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
