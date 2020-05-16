package ru.synthet.graph.edge;

import java.util.LinkedHashSet;
import java.util.Set;

public class UndirectEdgeSet<V> implements EdgeSet<V> {

    private Set<Edge<V>> edges;

    public void add(Edge<V> edge) {
        getEdges().add(edge);
    }

    private Set<Edge<V>> getEdges() {
        if (edges == null) {
            edges = new LinkedHashSet<>();
        }
        return edges;
    }
}
