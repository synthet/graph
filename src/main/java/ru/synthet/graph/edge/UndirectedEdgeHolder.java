package ru.synthet.graph.edge;

import java.util.LinkedHashSet;
import java.util.Set;

public class UndirectedEdgeHolder<V> implements EdgeHolder<V> {

    private Set<Edge<V>> edges;

    @Override
    public void add(V vertex, Edge<V> edge) {
        add(edge);
    }

    private void add(Edge<V> edge) {
        getEdges().add(edge);
    }

    private Set<Edge<V>> getEdges() {
        if (edges == null) {
            edges = new LinkedHashSet<>();
        }
        return edges;
    }

}
