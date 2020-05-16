package ru.synthet.graph.edge;

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
     * @return list of vertexes
     */
    List<V> getVertexList();
}
