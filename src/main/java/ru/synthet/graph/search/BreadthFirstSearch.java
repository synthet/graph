package ru.synthet.graph.search;

import ru.synthet.graph.Graph;
import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.exception.GraphException;
import ru.synthet.graph.exception.NoSuchVertexException;

import java.util.*;

/**
 * Breadth-first search algorithm (BFS)
 */
public class BreadthFirstSearch<V> extends BaseGraphSearch<V> implements GraphSearch<V> {

    public BreadthFirstSearch(Graph<V> graph) {
        super(graph);
    }

    @Override
    public List<Edge<V>> execute(V startVertex, V endVertex) throws GraphException {

        if ((!graph.containsVertex(startVertex)) || (!graph.containsVertex(endVertex)))  {
            throw new NoSuchVertexException();
        }

        if (Objects.equals(startVertex, endVertex)) {
            Optional<Edge<V>> edge = graph.getEdge(startVertex, endVertex);
            return edge.map(Collections::singletonList).orElse(Collections.emptyList());
        }

        Set<V> visited = new HashSet<>();
        LinkedList<V> queue = new LinkedList<>();
        Map<V, V> pathMap = new HashMap<>();
        visited.add(startVertex);
        queue.add(startVertex);
        V currentVertex;
        while (!queue.isEmpty()) {
            currentVertex = queue.poll();
            Iterator<V> i = graph.getAdjacentVertexes(currentVertex);
            while (i.hasNext()) {
                V nextVertex = i.next();
                if (!visited.contains(nextVertex)) {
                    pathMap.put(nextVertex, currentVertex);
                    if (!Objects.equals(nextVertex, endVertex)) {
                        visited.add(nextVertex);
                        queue.add(nextVertex);
                    } else {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        return reconstructPath(startVertex, endVertex, pathMap);
    }

}
