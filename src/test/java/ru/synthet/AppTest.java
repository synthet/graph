package ru.synthet;

import org.junit.Assert;
import org.junit.Test;
import ru.synthet.graph.*;
import ru.synthet.graph.DirectedGraph;
import ru.synthet.graph.Graph;
import ru.synthet.graph.UndirectedGraph;

import static org.junit.Assert.assertTrue;


public class AppTest {

    @Test
    public void shouldCreateDirectGraph() {

        Graph<String> directedGraph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        Assert.assertTrue(directedGraph instanceof DirectedGraph);
    }

    @Test
    public void shouldCreateUndirectGraph() {

        Graph<Long> undirectedGraph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, Long.class);
        Assert.assertTrue(undirectedGraph instanceof UndirectedGraph);
    }

    @Test
    public void testBreadthFirstSearch() {

        Graph<Long> graph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, Long.class);
        graph.addVertex(0L);
        graph.addVertex(1L);
        graph.addVertex(2L);
        graph.addVertex(3L);
        graph.addEdge​(0L, 1L);
        graph.addEdge(0L, 1L);
        graph.addEdge(0L, 2L);
        graph.addEdge(1L, 2L);
        graph.addEdge(2L, 0L);
        graph.addEdge(2L, 3L);
        graph.addEdge(3L, 3L);

        BreadthFirstSearch<Long> search = new BreadthFirstSearch<>(graph);
        search.execute(2L);
    }
}
