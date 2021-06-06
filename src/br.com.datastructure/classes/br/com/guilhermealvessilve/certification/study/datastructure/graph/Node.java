package br.com.guilhermealvessilve.certification.study.datastructure.graph;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Alves
 * @param <T>
 */
public class Node<T> {

    public static <T> Node<T> with(T object) {
        return new Node<>(object);
    }
    
    private boolean visited;
    private T object;
    private final LinkedList<Node<T>> adjacentes;

    public Node() {
        this.adjacentes = new LinkedList<>();
    }

    public Node(T object) {
        this();
        this.object = object;
    }
    
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
    
    public Node<T> addNext(Node<T> next) {
        adjacentes.add(next);
        return this;
    }

    public LinkedList<Node<T>> getAdjacentes() {
        return adjacentes;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.object);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node<?> other = (Node<?>) obj;
        return Objects.equals(this.object, other.object);
    }


    @Override
    public String toString() {
        
        String collect = adjacentes.stream()
                .map(node -> node.object.toString())
                .collect(Collectors.joining(",", "[", "]"));

        return String.format("%s=%s", object, collect);
    }
}
