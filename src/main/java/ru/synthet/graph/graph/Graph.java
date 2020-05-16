package ru.synthet.graph.graph;

import ru.synthet.graph.edge.Edge;

public interface Graph<V> {

    void addVertex(V vertex);

    Edge addEdge​(V srcVertex, V dstVertex);

}
