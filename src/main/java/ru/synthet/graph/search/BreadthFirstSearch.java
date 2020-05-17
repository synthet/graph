package ru.synthet.graph.search;

import ru.synthet.graph.Graph;
import ru.synthet.graph.edge.Edge;

import java.util.*;

/**
 * Breadth-first search algorithm (BFS)
 */
public class BreadthFirstSearch<V> {

    private Graph<V> graph;

    public BreadthFirstSearch(Graph<V> graph) {
        this.graph = graph;
    }

    public List<Edge<V>> execute(V startVertex, V endVertex) {
        Set<V> visited = new HashSet<>();
        LinkedList<V> queue = new LinkedList<>();
        Map<V, V> pathMap = new HashMap<>();
        visited.add(startVertex);
        queue.add(startVertex);
        V currentVertex;
        while (!queue.isEmpty()) {
            currentVertex = queue.poll();
            Iterator<V> i = graph.getAdjacent(currentVertex);
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
        List<Edge<V>> reversePath = new ArrayList<>();
        currentVertex = endVertex;
        do {
            V previousVertex = pathMap.get(currentVertex);
            Optional<Edge<V>> edge = graph.getEdge(previousVertex, currentVertex);
            edge.ifPresent(reversePath::add);
            currentVertex = previousVertex;
        } while ((currentVertex != null) && (!currentVertex.equals(startVertex)));
        Collections.reverse(reversePath);
        return reversePath;
    }
}
