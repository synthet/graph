package ru.synthet.graph.search;

import ru.synthet.graph.Graph;
import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.exception.GraphException;
import ru.synthet.graph.exception.NoSuchVertexException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
        visited.add(startVertex);
        PriorityVertex<V> startNode = new PriorityVertex<>(startVertex);
        startNode.setPriority(0);
        queue.add(startNode);
        V currentVertex;
        while (!queue.isEmpty()) {
            PriorityVertex<V> currentNode = queue.remove();
            int currentPriority = currentNode.getPriority();
            currentVertex = currentNode.getVertex();
            Iterator<Edge<V>> i = graph.getAdjacentEdges(currentVertex);
            while (i.hasNext()) {
                Edge<V> nextEdge = i.next();
                V nextVertex = nextEdge.getAdjacentVertex(currentVertex);
                if (!visited.contains(nextVertex)) {
                    pathMap.put(nextVertex, currentVertex);
                    if (!Objects.equals(nextVertex, endVertex)) {
                        visited.add(nextVertex);
                        PriorityVertex<V> nextNode = new PriorityVertex<>(nextVertex);
                        nextNode.setPriority(currentPriority + ThreadLocalRandom.current().nextInt(1, 10 + 1));
                        if (queue.contains(nextNode)) {
                            queue.add(nextNode);
                        } else {
                            queue.add(nextNode);
                        }
                    } else {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        return reconstructPath(startVertex, endVertex, pathMap);
    }

    private class PriorityVertex<U> implements Comparable<PriorityVertex<U>> {

        private U vertex;
        private int priority = Integer.MAX_VALUE;

        public PriorityVertex(U vertex) {
            this.vertex = vertex;
        }

        public U getVertex() {
            return vertex;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityVertex<U> o) {
            return Integer.compare(getPriority(), o.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PriorityVertex)) return false;
            PriorityVertex<?> that = (PriorityVertex<?>) o;
            return Objects.equals(getVertex(), that.getVertex());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getVertex());
        }
    }
}
