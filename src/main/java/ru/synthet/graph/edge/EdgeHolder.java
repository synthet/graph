package ru.synthet.graph.edge;

import java.util.Iterator;

public interface EdgeHolder<V> {

    void add(V vertex, Edge<V> edge);

    Iterator<V> getAdjacent();

}
