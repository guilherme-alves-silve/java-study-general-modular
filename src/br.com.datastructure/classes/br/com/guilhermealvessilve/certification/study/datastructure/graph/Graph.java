package br.com.guilhermealvessilve.certification.study.datastructure.graph;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 *
 * @author Alves
 */
public class Graph<T> {
    
    private final LinkedHashMap<Node<T>, Node<T>> verticeMapper;

    public Graph() {
        this.verticeMapper = new LinkedHashMap<>();
    }
    
    public Graph<T> addEdge(Node<T> vertice, Node<T> edge) {
        Node<T> originalVertice = getOriginalVertice(vertice);
        originalVertice.addNext(getOriginalVertice(edge));
        return this;
    }

    private Node<T> getOriginalVertice(Node<T> vertice) {
        var originalVertice = verticeMapper.get(vertice);
        if (null == originalVertice) {
            verticeMapper.put(vertice, vertice);
            originalVertice = vertice;
        }
        
        return originalVertice;
    }
    
    public void marker(final T object) {
        
        System.out.println("*** BFS ***");
        
        final var node = getOriginalVertice(Node.with(object));
        visitNodes(node);
    }
    
    private void visitNodes(Node<T> node) {
        
        if (node.isVisited()) {
            return;
        }
        
        node.setVisited(true);
        
        System.out.println(node.getObject() + " ");
        
        final var adjacentes = new LinkedList<>(node.getAdjacentes());
        final var it = adjacentes.iterator();
        
        while (it.hasNext()) {
            
            final Node<T> next = it.next();
            if (next.isVisited()) {
                it.remove();
                continue;
            }
            
            visitNodes(next);
        }
    }

    @Override
    public String toString() {
        return "Graph{verticeMapper=" + verticeMapper + '}';
    }
}
