package ru.synthet.graph.edge;

import java.util.*;

public class DirectedEdgeHolder<V> implements EdgeHolder<V> {

    private Set<Edge<V>> inEdges;
    private Set<Edge<V>> outEdges;

    @Override
    public void add(V vertex, Edge<V> edge) {

        V srcVertex = edge.getSourceVertex();
        if (Objects.equals(srcVertex, vertex)) {
            addOut(edge);
        }

        V dstVertex = edge.getDestinationVertex();
        if (Objects.equals(dstVertex, vertex)) {
            addIn(edge);
        }
    }

    @Override
    public Iterator<V> getAdjacent(final V vertex) {

        return outEdges.stream().map(Edge::getDestinationVertex).iterator();
    }

    @Override
    public Optional<Edge<V>> getEdge(final V srcVertex, final V dstVertex) {

        return outEdges.stream().filter(e -> e.getDestinationVertex().equals(dstVertex)).findFirst();
    }

    private void addIn(Edge<V> edge) {
        getInEdges().add(edge);
    }

    private void addOut(Edge<V> edge) {
        getOutEdges().add(edge);
    }

    private Set<Edge<V>> getInEdges() {
        if (inEdges == null) {
            inEdges = new LinkedHashSet<>();
        }
        return inEdges;
    }

    private Set<Edge<V>> getOutEdges() {
        if (outEdges == null) {
            outEdges = new LinkedHashSet<>();
        }
        return outEdges;
    }

}
