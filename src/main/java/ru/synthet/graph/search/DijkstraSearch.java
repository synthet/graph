package ru.synthet.graph.search;

import ru.synthet.graph.Graph;
import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.exception.GraphException;

import java.util.*;

public class DijkstraSearch<V> extends BaseGraphSearch<V> implements GraphSearch<V> {

    public DijkstraSearch(Graph<V> graph) {
        super(graph);
    }

    @Override
    public List<Edge<V>> execute(V startVertex, V endVertex) throws GraphException {

        List<Edge<V>> edges = checkVertexes(startVertex, endVertex);
        if (edges != null) {
            return edges;
        }

        Set<V> visited = new HashSet<>();
        PriorityQueue<PriorityVertex<V>> queue = new PriorityQueue<>();
        Map<V, V> pathMap = new HashMap<>();
        Map<V, Long> priorityMap = new LinkedHashMap<>();
        visited.add(startVertex);
        priorityMap.put(startVertex, 0L);
        PriorityVertex<V> startNode = new PriorityVertex<>(startVertex, 0L);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            PriorityVertex<V> currentNode = queue.remove();
            V currentVertex = currentNode.getVertex();
            visited.add(currentVertex);
            Iterator<Edge<V>> i = graph.getAdjacentEdges(currentVertex);
            while (i.hasNext()) {
                Edge<V> nextEdge = i.next();
                V nextVertex = nextEdge.getAdjacentVertex(currentVertex);
                if (!visited.contains(nextVertex)) {
                    long priority = getPriority(priorityMap, currentVertex) + 1L;
                    if (priority < getPriority(priorityMap, nextVertex)) {
                        priorityMap.put(nextVertex, priority);
                        pathMap.put(nextVertex, currentVertex);
                    }
                    PriorityVertex<V> nextNode = new PriorityVertex<>(nextVertex, getPriority(priorityMap, nextVertex));
                    queue.add(nextNode);
                }
            }
        }

        return reconstructPath(startVertex, endVertex, pathMap);
    }

    private Long getPriority(Map<V, Long> priorityMap, V vertex) {

        return priorityMap.computeIfAbsent(vertex, k -> Long.MAX_VALUE);
    }

    private class PriorityVertex<U> implements Comparable<PriorityVertex<U>> {

        private U vertex;
        private long priority;

        PriorityVertex(U vertex, long priority) {
            this.vertex = vertex;
            this.priority = priority;
        }

        U getVertex() {
            return vertex;
        }

        long getPriority() {
            return priority;
        }

        @Override
        public int compareTo(PriorityVertex<U> o) {
            return Long.compare(getPriority(), o.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PriorityVertex)) return false;
            PriorityVertex<U> that = (PriorityVertex<U>) o;
            return getPriority() == that.getPriority() && Objects.equals(getVertex(), that.getVertex());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getVertex(), getPriority());
        }
    }
}
