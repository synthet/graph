package ru.synthet;

import org.junit.Assert;
import org.junit.Test;
import ru.synthet.graph.*;
import ru.synthet.graph.edge.Edge;
import ru.synthet.graph.exception.GraphException;
import ru.synthet.graph.exception.NoSuchVertexException;
import ru.synthet.graph.exception.WrongGraphTypeException;

import java.util.List;
import java.util.Optional;


public class AppTest {

    @Test(expected = WrongGraphTypeException.class)
    public void testWrongGraphTypeException() {

        GraphCreator.getInstance().createGraph(null, String.class);
    }

    @Test
    public void shouldCreateDirectedGraph() {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        Assert.assertTrue(graph instanceof DirectedGraph);
        Assert.assertEquals(graph.getType(), GraphType.DIRECTED);
    }

    @Test
    public void shouldCreateUndirectedGraph() {

        Graph<Long> graph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, Long.class);
        Assert.assertTrue(graph instanceof UndirectedGraph);
        Assert.assertEquals(graph.getType(), GraphType.UNDIRECTED);
    }

    @Test
    public void shouldAddVertex() {

        Graph<Long> graph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, Long.class);
        boolean isAdded = graph.addVertex(0L);
        Assert.assertTrue(isAdded);
    }

    @Test
    public void shouldNotAddDuplicateVertex() {

        Graph<Long> graph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, Long.class);
        graph.addVertex(0L);
        boolean isAdded = graph.addVertex(0L);
        Assert.assertFalse(isAdded);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerException() {

        Graph<Long> graph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, Long.class);
        graph.addVertex(null);
    }

    @Test
    public void shouldAddEdge() throws GraphException {

        Graph<Long> graph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, Long.class);
        graph.addVertex(0L);
        graph.addVertex(1L);
        Optional<Edge<Long>> edge = graph.addEdge​(0L, 1L);
        Assert.assertTrue(edge.isPresent());
        Assert.assertEquals(edge.get().getSourceVertex(), (Long) 0L);
        Assert.assertEquals(edge.get().getDestinationVertex(), (Long) 1L);
    }

    @Test(expected = NoSuchVertexException.class)
    public void testAddEdgeNoSuchVertexException() throws GraphException {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        graph.addVertex("0");
        graph.addVertex("1");
        graph.addEdge​("0", "2");
    }

    @Test(expected = NoSuchVertexException.class)
    public void testGetPathNoSuchVertexException() throws GraphException {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        graph.addVertex("0");
        graph.addVertex("1");
        graph.addEdge​("0", "1");
        graph.getPath("0", "2");
    }

    @Test
    public void testSearchPathNoPath() throws GraphException {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        graph.addVertex("0");
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addEdge​("0", "1");

        List<Edge<String>> path = graph.getPath("0", "2");
        Assert.assertTrue(path.isEmpty());

        System.out.println(String.format("%s", path.toString()));
    }

    @Test
    public void testSearchPathLoop() throws GraphException {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        graph.addVertex("0");
        graph.addEdge​("0", "0");

        List<Edge<String>> path = graph.getPath("0", "0");
        Assert.assertEquals(path.size(), 1);

        System.out.println(String.format("%s", path.toString()));
    }

    @Test
    public void testSearchPathDirected() throws GraphException {

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
        Assert.assertEquals(path.get(4).getDestinationVertex(), "1");

        System.out.println(String.format("%s", path.toString()));
    }

    @Test
    public void testSearchPathUndirected() throws GraphException {

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
        Assert.assertTrue(path.get(2).getVertexList().contains("5"));

        System.out.println(String.format("%s", path.toString()));
    }

    @Test
    public void testSearchPathDirected2() throws GraphException {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.DIRECTED, String.class);
        graph.addVertex("0");
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");

        graph.addEdge​("0", "1");
        graph.addEdge​("0", "2");
        graph.addEdge​("0", "4");
        graph.addEdge​("1", "0");
        graph.addEdge​("1", "5");
        graph.addEdge​("2", "4");
        graph.addEdge​("2", "6");
        graph.addEdge​("3", "1");
        graph.addEdge​("3", "5");
        graph.addEdge​("4", "2");
        graph.addEdge​("4", "6");
        graph.addEdge​("5", "3");
        graph.addEdge​("5", "7");
        graph.addEdge​("6", "5");
        graph.addEdge​("7", "5");

        List<Edge<String>> path = graph.getPath(String.valueOf(7), String.valueOf(6));
        Assert.assertEquals(path.size(), 6);
        Assert.assertTrue(path.get(0).getVertexList().contains("7"));
        Assert.assertTrue(path.get(5).getVertexList().contains("6"));

        System.out.println(String.format("%s", path.toString()));
    }

    @Test
    public void testSearchPathUndirected2() throws GraphException {

        Graph<String> graph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECTED, String.class);
        graph.addVertex("0");
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");

        graph.addEdge​("0", "1");
        graph.addEdge​("0", "2");
        graph.addEdge​("0", "4");
        graph.addEdge​("1", "0");
        graph.addEdge​("1", "5");
        graph.addEdge​("2", "4");
        graph.addEdge​("2", "6");
        graph.addEdge​("3", "1");
        graph.addEdge​("3", "5");
        graph.addEdge​("4", "2");
        graph.addEdge​("4", "6");
        graph.addEdge​("5", "3");
        graph.addEdge​("5", "7");
        graph.addEdge​("6", "5");
        graph.addEdge​("7", "5");

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                List<Edge<String>> path = graph.getPath(String.valueOf(i), String.valueOf(j));
                System.out.println(String.format("%s", path.toString()));
            }
        }
    }
}
