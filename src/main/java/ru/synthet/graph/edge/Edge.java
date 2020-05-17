package ru.synthet.graph.edge;

import ru.synthet.graph.exception.NoSuchVertexException;

import java.util.List;

public interface Edge<V> {

    /**
     * @return source vertex
     */
    V getSourceVertex();

    /**
     * @return destination vertex
     */
    V getDestinationVertex();

    /**
     * @return adjacent vertex
     */
    V getAdjacentVertex(V vertex) throws NoSuchVertexException;

    /**
     * @return list of vertexes
     */
    List<V> getVertexList();

}
