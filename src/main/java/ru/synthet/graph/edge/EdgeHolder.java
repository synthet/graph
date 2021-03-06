package ru.synthet.graph.edge;

import java.util.Iterator;
import java.util.Optional;

public interface EdgeHolder<V> {

    void add(V vertex, Edge<V> edge);

    Iterator<V> getAdjacentVertexes(V vertex);

    Iterator<Edge<V>> getAdjacentEdges(V vertex);

    Optional<Edge<V>> getEdge(V srcVertex, V dstVertex);
}
