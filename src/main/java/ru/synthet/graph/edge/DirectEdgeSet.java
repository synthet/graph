package ru.synthet.graph.edge;

import java.util.LinkedHashSet;
import java.util.Set;

public class DirectEdgeSet<V> implements EdgeSet<V> {

    private Set<Edge<V>> inEdges;
    private Set<Edge<V>> outEdges;

    public void add(Edge<V> edge) {

        V srcVertex = edge.getSourceVertex();
        V dstVertex = edge.getDestinationVertex();

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
