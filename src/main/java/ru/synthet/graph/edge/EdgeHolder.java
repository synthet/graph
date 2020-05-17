package ru.synthet.graph.edge;

import java.util.Iterator;
import java.util.Optional;

public interface EdgeHolder<V> {

    void add(V vertex, Edge<V> edge);

    Iterator<V> getAdjacent();

    Optional<Edge<V>> getEdge(V dstVertex);
}
