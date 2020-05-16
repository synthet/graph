package ru.synthet.graph.graph;

import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.edge.EdgeSet;
import ru.synthet.graph.edge.SimpleEdge;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseGraph<V> implements Graph<V> {

    protected final Class<V> typeParameterClass;

    protected Map<V, EdgeSet<V>> vertexMap;

    protected BaseGraph(Class<V> clazz) {
        this.typeParameterClass = clazz;
    }

    @Override
    public void addVertex(V vertex) {

        if (vertex == null) {
            throw new NullPointerException();
        }

        if (getVertexSet().contains(vertex)) {
            return;
        }

        getVertexMap().put(vertex, createEdgeSet());
    }

    protected abstract EdgeSet<V> createEdgeSet();

    @Override
    public Edge addEdge​(V srcVertex, V dstVertex) {

        SimpleEdge<V> edge = new SimpleEdge<>(srcVertex, dstVertex);

        getVertexMap().get(srcVertex).add(edge);
        getVertexMap().get(dstVertex).add(edge);

        return edge;
    }

    private Set<V> getVertexSet() {
        return getVertexMap().keySet();
    }

    private Map<V, EdgeSet<V>> getVertexMap() {
        if (vertexMap == null) {
            vertexMap = new LinkedHashMap<>();
        }
        return vertexMap;
    }
}
