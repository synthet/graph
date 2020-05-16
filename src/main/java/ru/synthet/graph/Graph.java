package ru.synthet.graph;

import ru.synthet.graph.edge.Edge;

import java.util.Optional;

public interface Graph<V> {

    /**
     * Add a vertex to the graph
     * @param vertex - vertex
     *
     * @return true if vertex is successfully added, else otherwise
     */
    boolean addVertex(V vertex);

    /**
     * Add an edge to the graph
     * @param srcVertex - source vertex of an edge
     * @param dstVertex - destination vertex of an edge
     *
     * @return optional edge
     */
    Optional<Edge> addEdge​(V srcVertex, V dstVertex);

    /**
     * @return type of the graph
     */
    GraphType getType();
}
