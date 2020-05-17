package ru.synthet.graph;

import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.edge.EdgeHolder;
import ru.synthet.graph.edge.SimpleEdge;
import ru.synthet.graph.exception.GraphException;
import ru.synthet.graph.exception.NoSuchVertexException;
import ru.synthet.graph.search.DijkstraSearch;

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
    public Optional<Edge<V>> addEdge​(V srcVertex, V dstVertex) throws GraphException {

        if ((!containsVertex(srcVertex)) || (!containsVertex(dstVertex)))  {
            throw new NoSuchVertexException();
        }

        Edge<V> edge = new SimpleEdge<>(srcVertex, dstVertex);

        getVertexMap().get(srcVertex).add(srcVertex, edge);
        getVertexMap().get(dstVertex).add(dstVertex, edge);

        return Optional.of(edge);
    }

    @Override
    public Optional<Edge<V>> getEdge(V srcVertex, V dstVertex) {

        if ((srcVertex == null) || (dstVertex == null)) {
            return Optional.empty();
        }

        return getVertexMap().get(srcVertex).getEdge(srcVertex, dstVertex);
    }

    @Override
    public Iterator<V> getAdjacentVertexes(V vertex) {

        return getVertexMap().get(vertex).getAdjacentVertexes(vertex);
    }

    @Override
    public Iterator<Edge<V>> getAdjacentEdges(V vertex) {

        return getVertexMap().get(vertex).getAdjacentEdges(vertex);
    }

    @Override
    public List<Edge<V>> getPath(V srcVertex, V dstVertex) throws GraphException {

        return new DijkstraSearch<>(this).execute(srcVertex, dstVertex);
        //return new BreadthFirstSearch<>(this).execute(srcVertex, dstVertex);
    }

    @Override
    public  boolean containsVertex(V vertex) {

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
