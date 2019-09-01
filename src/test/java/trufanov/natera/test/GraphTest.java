package trufanov.natera.test;

import org.junit.Test;
import trufanov.natera.DirectedGraph;
import trufanov.natera.Edge;
import trufanov.natera.Graph;
import trufanov.natera.UndirectedGraph;

import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testDirectedGraph() {
        Graph<Integer> graph = new DirectedGraph<>();
        assertTrue(graph.isDirected());
        testGraph(graph);
    }

    @Test
    public void testUndirectedGraph() {
        Graph<Integer> graph = new UndirectedGraph<>();
        assertFalse(graph.isDirected());
        testGraph(graph);
    }

    private void testGraph(Graph<Integer> graph) {

        assertTrue(graph.addVertex(1));
        assertFalse(graph.addVertex(1));

        //self-loop
        assertTrue(graph.addEdge(1, 1));
        assertFalse(graph.addEdge(1, 1));

        assertPath(graph.isDirected(), graph.getPath(1, 1), 1, 1);

        //loop
        graph.addEdge(2, 3);
        assertEquals(graph.isDirected(), graph.addEdge(3, 2));

        assertPath(graph.isDirected(), graph.getPath(2, 2), 2, 3, 2);
        assertPath(graph.isDirected(), graph.getPath(3, 3), 3, 2, 3);

        //loops
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);

        graph.addEdge(5, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 5);

        graph.addEdge(7, 9);
        graph.addEdge(9, 10);
        graph.addEdge(10, 7);

        graph.addEdge(9, 11);

        assertPath(graph.isDirected(), graph.getPath(4, 11), 4, 5, 7, 9, 11);

        //directionality
        graph.addEdge(12, 13);
        graph.addEdge(13, 14);
        graph.addEdge(14, 15);
        graph.addEdge(16, 15);
        if (graph.isDirected()) {
            assertTrue(graph.getPath(12, 16).isEmpty());
        } else {
            assertPath(false, graph.getPath(12, 16), 12, 13, 14, 15, 16);
        }
    }

    private void assertPath(boolean directed, List<Edge<Integer>> path, int... vertexes) {
        assertEquals("Unexpected path length", vertexes.length - 1, path.size());
        int i = 0;
        for (Edge<Integer> edge : path) {
            assertEquals("Incorrect edge directionality", directed, edge.isOrdered());
            if (directed) {
                assertTrue("Expected " + Edge.ordered(vertexes[i], vertexes[i + 1]) + ", actual " + edge,
                        vertexes[i] == edge.node1() && vertexes[i + 1] == edge.node2());
            } else {
                assertTrue("Expected " + Edge.unordered(vertexes[i], vertexes[i + 1]) + ", actual " + edge,
                        vertexes[i] == edge.node1() && vertexes[i + 1] == edge.node2() ||
                                 vertexes[i] == edge.node2() && vertexes[i + 1] == edge.node1());
            }
            i++;
        }
    }
}
