package br.com.guilhermealvessilve.certification.study.datastructure.simplegraph;

/**
 *
 * @author Alves
 */
public class MainSimpleGraphBFS {
    
    public static void main(String[] args) {
        var graph = new IntGraph(4);
        
        graph.addEdge(0, 3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        graph.addEdge(3, 2);
        graph.addEdge(3, 3);
        
        System.out.println(graph);
        
        graph.breadthFirstSearch(0);
    }
}
