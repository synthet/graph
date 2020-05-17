package ru.synthet.graph.search;

import ru.synthet.graph.Graph;
import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.exception.GraphException;

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

        List<Edge<V>> edges = checkVertexes(startVertex, endVertex);
        if (edges != null) {
            return edges;
        }

        Set<V> visited = new HashSet<>();
        LinkedList<V> queue = new LinkedList<>();
        Map<V, V> pathMap = new HashMap<>();
        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            V currentVertex = queue.poll();
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
