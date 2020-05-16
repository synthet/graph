package ru.synthet.graph;

import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.edge.EdgeSet;
import ru.synthet.graph.edge.SimpleEdge;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class BaseGraph<V> implements Graph<V> {

    private final Class<V> typeParameterClass;

    private Map<V, EdgeSet<V>> vertexMap;

    BaseGraph(Class<V> clazz) {
        this.typeParameterClass = clazz;
    }

    protected abstract EdgeSet<V> emptyEdgeSet();

    @Override
    public boolean addVertex(V vertex) {

        if (containsVertex(vertex)) {
            return false;
        }

        getVertexMap().put(vertex, emptyEdgeSet());
        return true;
    }

    @Override
    public Optional<Edge> addEdge​(V srcVertex, V dstVertex) {

        if ((!containsVertex(srcVertex)) || (!containsVertex(dstVertex)))  {
            return Optional.empty();
        }

        SimpleEdge<V> edge = new SimpleEdge<>(srcVertex, dstVertex);

        getVertexMap().get(srcVertex).add(srcVertex, edge);
        getVertexMap().get(dstVertex).add(dstVertex, edge);

        return Optional.of(edge);
    }

    private boolean containsVertex(V vertex) {

        if (vertex == null) {
            throw new NullPointerException();
        }

        return getVertexSet().contains(vertex);
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
