package ru.synthet.graph;

import ru.synthet.graph.edge.EdgeHolder;
import ru.synthet.graph.edge.UndirectedEdgeHolder;

public class UndirectedGraph<V> extends BaseGraph<V> implements Graph<V> {

    private final static GraphType TYPE = GraphType.UNDIRECTED;

    UndirectedGraph(Class<V> clazz) {
        super(clazz);
    }

    @Override
    protected EdgeHolder<V> emptyEdgeHolder() {
        return new UndirectedEdgeHolder<>();
    }

    @Override
    public GraphType getType() {
        return TYPE;
    }
}
