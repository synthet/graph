package ru.synthet.graph.graph;

import ru.synthet.graph.edge.EdgeSet;
import ru.synthet.graph.edge.UndirectEdgeSet;

public class UndirectGraph<V> extends BaseGraph<V> implements Graph<V> {

    protected UndirectGraph(Class<V> clazz) {
        super(clazz);
    }

    @Override
    protected EdgeSet<V> createEdgeSet() {
        return new UndirectEdgeSet<>();
    }
}
