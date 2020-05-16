package ru.synthet.graph;

import ru.synthet.graph.edge.EdgeSet;
import ru.synthet.graph.edge.UndirectEdgeSet;

public class UndirectGraph<V> extends BaseGraph<V> implements Graph<V> {

    private final static GraphType TYPE = GraphType.DIRECT;

    UndirectGraph(Class<V> clazz) {
        super(clazz);
    }

    @Override
    protected EdgeSet<V> emptyEdgeSet() {
        return new UndirectEdgeSet<>();
    }

    @Override
    public GraphType getType() {
        return TYPE;
    }
}
