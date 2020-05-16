package ru.synthet.graph;

import ru.synthet.graph.edge.DirectEdgeSet;
import ru.synthet.graph.edge.EdgeSet;

public class DirectGraph<V> extends BaseGraph<V> implements Graph<V> {

    protected DirectGraph(Class<V> clazz) {
        super(clazz);
    }

    @Override
    protected EdgeSet<V> emptyEdgeSet() {
        return new DirectEdgeSet<>();
    }

}
