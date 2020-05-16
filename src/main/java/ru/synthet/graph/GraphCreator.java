package ru.synthet.graph;

public class GraphCreator {

    private static GraphCreator INSTANCE;

    private GraphCreator() {
    }

    public static GraphCreator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphCreator();
        }
        return INSTANCE;
    }

    public <V> Graph<V> createGraph(GraphType type, Class<V> clazz) throws Exception {
        if (type != null) {
            switch (type) {
                case DIRECTED:
                    return new DirectedGraph<>(clazz);
                case UNDIRECTED:
                    return new UndirectedGraph<>(clazz);
            }
        }
        throw new Exception("Wrong type of graph");
    }
}
