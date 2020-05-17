package ru.synthet.graph.edge;

import java.util.Arrays;
import java.util.List;

public class SimpleEdge<V> implements Edge<V> {

    private V srcVertex;
    private V dstVertex;

    /**
     * Construct an edge based on source and destination vertexes
     * @param srcVertex - source vertex
     * @param dstVertex - destination vertex
     */
    public SimpleEdge(V srcVertex, V dstVertex) {
        this.srcVertex = srcVertex;
        this.dstVertex = dstVertex;
    }

    @Override
    public V getSourceVertex() {
        return srcVertex;
    }

    @Override
    public V getDestinationVertex() {
        return dstVertex;
    }

    @Override
    public List<V> getVertexList() {
        return Arrays.asList(srcVertex, dstVertex);
    }

    @Override
    public String toString() {
        return '{' + String.valueOf(srcVertex) + ", " + String.valueOf(dstVertex) + '}';
    }
}
