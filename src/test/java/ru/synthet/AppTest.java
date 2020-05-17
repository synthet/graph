package ru.synthet;

import org.junit.Assert;
import org.junit.Test;
import ru.synthet.graph.*;
import ru.synthet.graph.DirectedGraph;
import ru.synthet.graph.Graph;
import ru.synthet.graph.UndirectedGraph;
import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.search.BreadthFirstSearch;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class AppTest {

    @Test
    public void shouldCreateDirectedGraph() {

        Graph<String> directedGraph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        Assert.assertTrue(directedGraph instanceof DirectedGraph);
    }

    @Test
    public void shouldCreateUndirectedGraph() {

        Graph<Long> undirectedGraph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, Long.class);
        Assert.assertTrue(undirectedGraph instanceof UndirectedGraph);
    }

    @Test
    public void testBreadthFirstSearch() {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        graph.addVertex("0");
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addEdge​("0", "2");
        graph.addEdge​("0", "3");
        graph.addEdge​("1", "3");
        graph.addEdge​("1", "6");
        graph.addEdge​("2", "6");
        graph.addEdge​("3", "1");
        graph.addEdge​("4", "2");
        graph.addEdge​("5", "3");
        graph.addEdge​("5", "7");
        graph.addEdge​("6", "2");
        graph.addEdge​("6", "5");
        graph.addEdge​("6", "7");
        graph.addEdge​("7", "6");

        BreadthFirstSearch<String> search = new BreadthFirstSearch<>(graph);
        List<Edge<String>> path = search.execute("4", "1");
        System.out.println(String.format("%s", path.toString()));
    }
}
