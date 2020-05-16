package ru.synthet.graph;

public interface Graph<V> {

    void addVertex(V vertex);

    Edge addEdge​(V srcVertex, V dstVertex);

}
