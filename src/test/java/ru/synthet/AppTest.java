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
    public void testBreadthFirstSearchDirected() {

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

        List<Edge<String>> path = graph.getPath("4", "1");
        Assert.assertEquals(path.size(), 5);
        Assert.assertEquals(path.get(0).getSourceVertex(), "4");
        Assert.assertEquals(path.get(0).getDestinationVertex(), "2");
        Assert.assertEquals(path.get(1).getDestinationVertex(), "6");
        Assert.assertEquals(path.get(2).getDestinationVertex(), "5");
        Assert.assertEquals(path.get(3).getDestinationVertex(), "3");
        Assert.assertEquals(path.get(4).getDestinationVertex(), "1");

        System.out.println(String.format("%s", path.toString()));
    }

    @Test
    public void testBreadthFirstSearchUndirected() {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, String.class);
        graph.addVertex("0");
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addEdge​("0", "3");
        graph.addEdge​("0", "2");
        graph.addEdge​("0", "4");
        graph.addEdge​("1", "2");
        graph.addEdge​("1", "5");
        graph.addEdge​("2", "4");
        graph.addEdge​("2", "6");
        graph.addEdge​("3", "7");
        graph.addEdge​("5", "6");
        graph.addEdge​("7", "7");

        List<Edge<String>> path = graph.getPath("4", "5");
        Assert.assertEquals(path.size(), 3);
        Assert.assertTrue(path.get(0).getVertexList().contains("4"));
        Assert.assertTrue(path.get(0).getVertexList().contains("2"));
        Assert.assertTrue(path.get(1).getVertexList().contains("1"));
        Assert.assertTrue(path.get(2).getVertexList().contains("5"));

        System.out.println(String.format("%s", path.toString()));
    }
}
