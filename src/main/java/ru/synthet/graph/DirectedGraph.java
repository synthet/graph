package ru.synthet.graph;

import ru.synthet.graph.edge.DirectedEdgeHolder;
import ru.synthet.graph.edge.EdgeHolder;

public class DirectedGraph<V> extends BaseGraph<V> implements Graph<V> {

    private final static GraphType TYPE = GraphType.DIRECTED;

    DirectedGraph(Class<V> clazz) {
        super(clazz);
    }

    @Override
    public GraphType getType() {
        return TYPE;
    }

    @Override
    protected EdgeHolder<V> emptyEdgeHolder() {
        return new DirectedEdgeHolder<>();
    }

}
