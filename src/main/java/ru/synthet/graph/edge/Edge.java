package ru.synthet.graph.edge;

import java.util.List;

public interface Edge<V> {

    V getSourceVertex();

    V getDestinationVertex();

    List<V> getVertexList();
}
