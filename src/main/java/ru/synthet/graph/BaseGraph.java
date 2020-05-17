package ru.synthet.graph;

import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.edge.EdgeHolder;
import ru.synthet.graph.edge.SimpleEdge;
import ru.synthet.graph.search.BreadthFirstSearch;

import java.util.*;

public abstract class BaseGraph<V> implements Graph<V> {

    private final Class<V> typeParameterClass;

    private Map<V, EdgeHolder<V>> vertexMap;

    BaseGraph(Class<V> clazz) {
        this.typeParameterClass = clazz;
    }

    protected abstract EdgeHolder<V> emptyEdgeHolder();

    @Override
    public boolean addVertex(V vertex) {

        if (containsVertex(vertex)) {
            return false;
        }

        getVertexMap().put(vertex, emptyEdgeHolder());
        return true;
    }

    @Override
    public Optional<Edge<V>> addEdge​(V srcVertex, V dstVertex) {

        if ((!containsVertex(srcVertex)) || (!containsVertex(dstVertex)))  {
            return Optional.empty();
        }

        SimpleEdge<V> edge = new SimpleEdge<>(srcVertex, dstVertex);

        getVertexMap().get(srcVertex).add(srcVertex, edge);
        getVertexMap().get(dstVertex).add(dstVertex, edge);

        return Optional.of(edge);
    }

    @Override
    public Optional<Edge<V>> getEdge(V srcVertex, V dstVertex) {

        return getVertexMap().get(srcVertex).getEdge(srcVertex, dstVertex);
    }

    @Override
    public Iterator<V> getAdjacent(V vertex) {

        return getVertexMap().get(vertex).getAdjacent(vertex);
    }

    @Override
    public List<Edge<V>> getPath(V srcVertex, V dstVertex) {

        return new BreadthFirstSearch<>(this).execute(srcVertex, dstVertex);
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

    private Map<V, EdgeHolder<V>> getVertexMap() {
        if (vertexMap == null) {
            vertexMap = new LinkedHashMap<>();
        }
        return vertexMap;
    }
}
