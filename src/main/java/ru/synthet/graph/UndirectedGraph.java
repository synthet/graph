package ru.synthet.graph;

public class UndirectedGraph<V> extends BaseGraph<V> implements Graph<V> {

    protected UndirectedGraph(Class<V> clazz) {
        super(clazz);
    }

    @Override
    protected EdgeSet<V> createEdgeSet() {
        return null;
    }
}
