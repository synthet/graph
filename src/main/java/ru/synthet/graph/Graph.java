package ru.synthet.graph;

import ru.synthet.graph.edge.Edge;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface Graph<V> {

    /**
     * @return type of the graph
     */
    GraphType getType();

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
    Optional<Edge<V>> addEdge​(V srcVertex, V dstVertex);

    /**
     * Get list of edges between two vertexes
     * @param startVertex - start vertex of a path
     * @param endVertex - end vertex of a path
     *
     * @return list of edges between two vertexes
     */
    List<Edge<V>> getPath(V startVertex, V endVertex);

    /**
     * Iterate trough adjacent vertexes
     * @param vertex - start vertex
     *
     * @return iterator through adjacent vertexes
     */
    Iterator<V> getAdjacent(V vertex);
}
