package ru.synthet.graph;

import ru.synthet.graph.graph.DirectGraph;
import ru.synthet.graph.graph.Graph;
import ru.synthet.graph.graph.UndirectGraph;

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
