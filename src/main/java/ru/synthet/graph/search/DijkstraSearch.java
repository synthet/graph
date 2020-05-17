package ru.synthet.graph.search;

import ru.synthet.graph.Graph;
import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.exception.GraphException;
import ru.synthet.graph.exception.NoSuchVertexException;

import java.util.*;

public class DijkstraSearch<V> extends BaseGraphSearch<V> implements GraphSearch<V> {

    public DijkstraSearch(Graph<V> graph) {
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
        PriorityQueue<PriorityVertex<V>> queue = new PriorityQueue<>();
        Map<V, V> pathMap = new HashMap<>();
        Map<V, Long> priorityMap = new LinkedHashMap<>();
        visited.add(startVertex);
        PriorityVertex<V> startNode = new PriorityVertex<>(startVertex);
        startNode.setPriority(0L);
        priorityMap.put(startVertex, 0L);
        queue.add(startNode);
        V currentVertex;
        while (!queue.isEmpty()) {
            PriorityVertex<V> currentNode = queue.remove();
            currentVertex = currentNode.getVertex();
            visited.add(currentVertex);
            Iterator<Edge<V>> i = graph.getAdjacentEdges(currentVertex);
            while (i.hasNext()) {
                Edge<V> nextEdge = i.next();
                V nextVertex = nextEdge.getAdjacentVertex(currentVertex);
                if (!visited.contains(nextVertex)) {
                    long edgePriority = getEdgePriority(nextEdge);
                    long newPriority = getPriority(priorityMap, currentVertex) + edgePriority;
                    if (newPriority < getPriority(priorityMap, nextVertex)) {
                        priorityMap.put(nextVertex, newPriority);
                        pathMap.put(nextVertex, currentVertex);
                    }
                    PriorityVertex<V> nextNode = new PriorityVertex<>(nextVertex);
                    nextNode.setPriority(getPriority(priorityMap, nextVertex));
                    queue.add(nextNode);
                }
            }
        }

        return reconstructPath(startVertex, endVertex, pathMap);
    }

    private Long getPriority(Map<V, Long> priorityMap, V vertex) {

        return priorityMap.computeIfAbsent(vertex, k -> Long.MAX_VALUE);
    }

    private long getEdgePriority(Edge<V> edge) {

        return 1L;
    }

    private class PriorityVertex<U> implements Comparable<PriorityVertex<U>> {

        private U vertex;
        private long priority = Long.MAX_VALUE;

        public PriorityVertex(U vertex) {
            this.vertex = vertex;
        }

        public U getVertex() {
            return vertex;
        }

        public long getPriority() {
            return priority;
        }

        public void setPriority(long priority) {
            this.priority = priority;
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
