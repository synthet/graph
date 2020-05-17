package ru.synthet.graph.search;

import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.exception.GraphException;

import java.util.List;

public interface GraphSearch<V> {

    List<Edge<V>> execute(V startVertex, V endVertex) throws GraphException;
}
