package br.com.guilhermealvessilve.certification.study.datastructure.simplegraph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *
 * @author Alves
 */
public class IntGraph {

    private final int vertices;
    private final LinkedList<Integer>[] adjacentes;
    
    public IntGraph(int vertices) {
        this.vertices = vertices;
        this.adjacentes = new LinkedList[vertices];
    }
    
    public IntGraph addEdge(int vertice, int edge) {
        if (null == adjacentes[vertice]) {
            adjacentes[vertice] = new LinkedList<>();
        }
        adjacentes[vertice].add(edge);
        return this;
    }
    
    public void breadthFirstSearch(int start) {
    
        final var visited = new boolean[vertices];
        visited[start] = true;
        
        final var queue = new LinkedList<Integer>();
        queue.add(start);
        
        while (!queue.isEmpty()) {
            
            int v = queue.pollFirst();
            
            System.out.println(v);
            
            final var adjacente = this.adjacentes[v];
            final var iterator = adjacente.iterator();
            while (iterator.hasNext()) {
                int edge = iterator.next();
                if (visited[edge]) {
                    continue;
                }
                
                visited[edge] = true;
                queue.add(edge);
            }
        }
    }

    @Override
    public String toString() {
        
        final AtomicInteger counter = new AtomicInteger();
        final var joined = Arrays.stream(adjacentes)
                .map(list -> String.format("\"%d\":%s", counter.getAndIncrement(), list.toString()))
                .collect(Collectors.joining(",", "[", "]"));

        return String.format("{\"class\":\"IntGraph\",\"vertices\"%d,\"adjacentes\":%s}", vertices, joined);
    }
}
