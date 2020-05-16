package ru.synthet;

import org.junit.Assert;
import org.junit.Test;
import ru.synthet.graph.*;
import ru.synthet.graph.DirectGraph;
import ru.synthet.graph.Graph;
import ru.synthet.graph.UndirectGraph;

import static org.junit.Assert.assertTrue;


public class AppTest {

    @Test
    public void shouldCreateDirectGraph() {

        Graph<String> directedGraph = GraphCreator.getInstance().createGraph(GraphType.DIRECT, String.class);
        Assert.assertTrue(directedGraph instanceof DirectGraph);
    }

    @Test
    public void shouldCreateUndirectGraph() {

        Graph<Long> undirectedGraph = GraphCreator.getInstance().createGraph(GraphType.UNDIRECT, Long.class);
        Assert.assertTrue(undirectedGraph instanceof UndirectGraph);
    }
}
