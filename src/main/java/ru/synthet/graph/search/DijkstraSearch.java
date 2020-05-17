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
        visited.add(startVertex);
        PriorityVertex<V> startNode = new PriorityVertex<>(startVertex);
        startNode.setPriority(0);
        queue.add(startNode);
        V currentVertex;
        while (!queue.isEmpty()) {
            PriorityVertex<V> currentNode = queue.poll();
            currentVertex = currentNode.getVertex();
            Iterator<V> i = graph.getAdjacent(currentVertex);
            while (i.hasNext()) {
                V nextVertex = i.next();
                if (!visited.contains(nextVertex)) {
                    pathMap.put(nextVertex, currentVertex);
                    if (!Objects.equals(nextVertex, endVertex)) {
                        visited.add(nextVertex);
                        PriorityVertex<V> nextNode = new PriorityVertex<>(nextVertex);
                        nextNode.setPriority(1);
                        queue.add(nextNode);
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
    }
}
