package ru.synthet.graph.edge;

import java.util.*;

public class UndirectedEdgeHolder<V> implements EdgeHolder<V> {

    private Set<Edge<V>> edges;

    @Override
    public void add(V vertex, Edge<V> edge) {
        add(edge);
    }

    @Override
    public Iterator<V> getAdjacent(final V vertex) {

        return edges.stream()
                .map(Edge::getVertexList)
                .flatMap(Collection::stream)
                .filter(v -> !Objects.equals(v, vertex)).iterator();
    }

    @Override
    public Optional<Edge<V>> getEdge(final V srcVertex, final V dstVertex) {

        return edges.stream()
                .filter(e -> (e.getVertexList().contains(srcVertex)) && (e.getVertexList().contains(dstVertex)))
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
