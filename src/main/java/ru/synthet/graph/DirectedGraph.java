package ru.synthet.graph;

public class DirectedGraph<V> extends BaseGraph<V> implements Graph<V> {

    protected DirectedGraph(Class<V> clazz) {
        super(clazz);
    }
}
