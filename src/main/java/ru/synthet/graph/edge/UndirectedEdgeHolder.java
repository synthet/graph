package ru.synthet.graph.edge;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class UndirectedEdgeHolder<V> implements EdgeHolder<V> {

    private Set<Edge<V>> edges;

    @Override
    public void add(V vertex, Edge<V> edge) {
        add(edge);
    }

    @Override
    public Iterator<V> getAdjacent() {

        return edges.stream().map(Edge::getDestinationVertex).iterator();
    }

    @Override
    public Optional<Edge<V>> getEdge(final V dstVertex) {

        return edges.stream().filter(e -> e.getDestinationVertex().equals(dstVertex)).findFirst();
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
