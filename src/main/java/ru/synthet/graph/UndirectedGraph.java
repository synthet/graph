package ru.synthet.graph;

import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.edge.EdgeHolder;
import ru.synthet.graph.edge.UndirectedEdgeHolder;

import java.util.List;

public class UndirectedGraph<V> extends BaseGraph<V> implements Graph<V> {

    private final static GraphType TYPE = GraphType.UNDIRECTED;

    UndirectedGraph(Class<V> clazz) {
        super(clazz);
    }

    @Override
    public GraphType getType() {
        return TYPE;
    }

    @Override
    protected EdgeHolder<V> emptyEdgeHolder() {
        return new UndirectedEdgeHolder<>();
    }

    @Override
    public List<Edge<V>> getPath(V srcVertex, V dstVertex) {
        return null;
    }

}
