package ru.synthet.graph.edge;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
    public Iterator<V> getAdjacent() {

        return outEdges.stream().map(Edge::getDestinationVertex).iterator();
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
