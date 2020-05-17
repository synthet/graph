package ru.synthet.graph.search;

import ru.synthet.graph.Graph;
import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.exception.NoSuchVertexException;

import java.util.*;

public abstract class BaseGraphSearch<V> implements GraphSearch<V> {

    protected Graph<V> graph;

    BaseGraphSearch(Graph<V> graph) {
        this.graph = graph;
    }

    List<Edge<V>> reconstructPath(V startVertex, V endVertex, Map<V, V> pathMap) {

        List<Edge<V>> reversePath = new ArrayList<>();

        V currentVertex = endVertex;
        do {
            V previousVertex = pathMap.get(currentVertex);
            Optional<Edge<V>> edge = graph.getEdge(previousVertex, currentVertex);
            edge.ifPresent(reversePath::add);
            currentVertex = previousVertex;
        } while ((currentVertex != null) && (!currentVertex.equals(startVertex)));

        Collections.reverse(reversePath);

        return reversePath;
    }

    List<Edge<V>> checkVertexes(V startVertex, V endVertex) {

        if ((!graph.containsVertex(startVertex)) || (!graph.containsVertex(endVertex)))  {
            throw new NoSuchVertexException();
        }

        if (Objects.equals(startVertex, endVertex)) {
            Optional<Edge<V>> edge = graph.getEdge(startVertex, endVertex);
            return edge.map(Collections::singletonList).orElse(Collections.emptyList());
        }

        return null;
    }

}
