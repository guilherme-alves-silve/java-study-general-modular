package br.com.guilhermealvessilve.certification.study.datastructure.graph;

/**
 *
 * @author Alves
 */
public class MainGraphMarker {
    
    public static void main(String[] args) {
        
        Graph graph = new Graph();
        graph.addEdge(Node.with(0), Node.with(3));
        graph.addEdge(Node.with(0), Node.with(1));
        graph.addEdge(Node.with(1), Node.with(0));
        graph.addEdge(Node.with(2), Node.with(3));
        graph.addEdge(Node.with(3), Node.with(0));
        graph.addEdge(Node.with(3), Node.with(2));
        graph.addEdge(Node.with(3), Node.with(3));
        
        graph.marker(0);
        
        System.out.println(graph);
    }
}
