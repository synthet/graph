package ru.synthet.graph;

import ru.synthet.graph.exception.WrongGraphTypeException;

public class GraphCreator {

    private static GraphCreator INSTANCE;

    private GraphCreator() {
    }

    /**
     * @return singleton instance of GraphCreator
     */
    public static GraphCreator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphCreator();
        }
        return INSTANCE;
    }

    /**
     * @param type - type of graph
     * @param clazz - class of vertexes
     *
     * @return graph object
     */
    public <V> Graph<V> createGraph(GraphType type, Class<V> clazz) throws WrongGraphTypeException {
        if (type != null) {
            switch (type) {
                case DIRECTED:
                    return new DirectedGraph<>(clazz);
                case UNDIRECTED:
                    return new UndirectedGraph<>(clazz);
            }
        }
        throw new WrongGraphTypeException();
    }
}
