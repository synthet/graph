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

    public <V> Graph<V> createGraph(GraphType type, Class<V> clazz) {
        if (type != null) {
            switch (type) {
                case DIRECT:
                    return new DirectGraph<>(clazz);
                case UNDIRECT:
                    return new UndirectGraph<>(clazz);
            }
        }
        throw new RuntimeException("Wrong type of graph");
    }
}
