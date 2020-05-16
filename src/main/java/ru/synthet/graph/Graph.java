package ru.synthet.graph;

import ru.synthet.graph.edge.Edge;

import java.util.Optional;

public interface Graph<V> {

    boolean addVertex(V vertex);

    Optional<Edge> addEdge​(V srcVertex, V dstVertex);

}
