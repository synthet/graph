package ru.synthet.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class BreadthFirstSearch<V> {

    private Graph<V> graph;

    public BreadthFirstSearch(Graph<V> graph) {
        this.graph = graph;
    }

    void execute(V currentVertex) {
        Set<V> visited = new HashSet<>();
        LinkedList<V> queue = new LinkedList<>();
        visited.add(currentVertex);
        queue.add(currentVertex);
        while (queue.size() != 0) {
            currentVertex = queue.poll();
            System.out.print(String.valueOf(currentVertex));
            Iterator<V> i = graph.getAdjacent(currentVertex);
            while (i.hasNext()) {
                V nextVertex = i.next();
                if (!visited.contains(nextVertex)) {
                    visited.add(nextVertex);
                    queue.add(nextVertex);
                }
            }
        }
    }
}
