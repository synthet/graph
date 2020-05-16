package ru.synthet.graph.edge;

public interface EdgeHolder<V> {

    void add(V vertex, Edge<V> edge);

}
