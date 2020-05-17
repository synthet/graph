package ru.synthet.graph.edge;

import java.util.*;

public class UndirectedEdgeHolder<V> implements EdgeHolder<V> {

    private Set<Edge<V>> edges;

    @Override
    public void add(V vertex, Edge<V> edge) {
        add(edge);
    }

    @Override
    public Iterator<V> getAdjacentVertexes(final V vertex) {

        return getEdges().stream()
                .map(e -> e.getAdjacentVertex(vertex))
                .iterator();
    }

    @Override
    public Iterator<Edge<V>> getAdjacentEdges(final V vertex) {

        return getEdges().stream().iterator();
    }

    @Override
    public Optional<Edge<V>> getEdge(final V srcVertex, final V dstVertex) {

        return getEdges().stream()
                .filter(e -> (e.getAdjacentVertex(srcVertex).equals(dstVertex)))
                .findFirst();
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
