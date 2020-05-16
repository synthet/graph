package ru.synthet.graph.edge;

import ru.synthet.graph.edge.Edge;

import java.util.Arrays;
import java.util.List;

public class SimpleEdge<V> implements Edge<V> {

    private V srcVertex;
    private V dstVertex;

    SimpleEdge(V srcVertex, V dstVertex) {
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
}
