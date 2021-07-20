package org.redquark.algorithms.datastructures.graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectedGraphTest {

    private final Graph<String> testDirectedGraph = new DirectedGraph<>();

    @BeforeEach
    public void setUp() {
        String[] vertices = new String[]{"A", "B", "C", "D", "E", "F"};
        for (String vertex : vertices) {
            testDirectedGraph.addVertex(vertex);
        }
        testDirectedGraph.addEdge("A", "B");
        testDirectedGraph.addEdge("A", "C");
        testDirectedGraph.addEdge("A", "D");
        testDirectedGraph.addEdge("B", "F");
        testDirectedGraph.addEdge("B", "E");
        testDirectedGraph.addEdge("D", "E");
        testDirectedGraph.addEdge("E", "F");
        testDirectedGraph.addEdge("C", "D");
    }

    @Test
    public void testAddVertex() {
        testDirectedGraph.addVertex("G");
        testDirectedGraph.addEdge("E", "G");
        testDirectedGraph.addEdge("F", "G");
        assertEquals(2, testDirectedGraph.inDegree("G"));
        assertEquals(0, testDirectedGraph.outDegree("G"));
        assertEquals(2, testDirectedGraph.outDegree("E"));
        assertEquals(1, testDirectedGraph.outDegree("F"));
        assertEquals(2, testDirectedGraph.inDegree("F"));
    }

    @Test
    public void testRemoveVertex() {
        testDirectedGraph.removeVertex("A");
        assertEquals(2, testDirectedGraph.outDegree("B"));
        assertEquals(0, testDirectedGraph.inDegree("B"));
        assertEquals(1, testDirectedGraph.outDegree("C"));
        assertEquals(0, testDirectedGraph.inDegree("C"));
        assertEquals(1, testDirectedGraph.outDegree("E"));
        assertEquals(2, testDirectedGraph.inDegree("E"));
        testDirectedGraph.removeVertex("B");
        assertEquals(1, testDirectedGraph.inDegree("D"));
        assertEquals(1, testDirectedGraph.outDegree("D"));
        assertEquals(1, testDirectedGraph.inDegree("F"));
        assertEquals(0, testDirectedGraph.outDegree("F"));
        assertThrows(IllegalArgumentException.class, () -> testDirectedGraph.removeVertex("G"));
    }

    @Test
    public void testAddEdge() {
        testDirectedGraph.addEdge("B", "D");
        assertEquals(1, testDirectedGraph.inDegree("B"));
        assertEquals(3, testDirectedGraph.outDegree("B"));
        assertEquals(3, testDirectedGraph.inDegree("D"));
        assertEquals(1, testDirectedGraph.outDegree("D"));
        testDirectedGraph.addEdge("G", "E");
        testDirectedGraph.addEdge("G", "H");
        assertEquals(0, testDirectedGraph.inDegree("G"));
        assertEquals(2, testDirectedGraph.outDegree("G"));
    }

    @Test
    public void testRemoveEdge() {
        testDirectedGraph.removeEdge("A", "B");
        assertEquals(2, testDirectedGraph.outDegree("A"));
        assertEquals(0, testDirectedGraph.inDegree("A"));
        assertEquals(0, testDirectedGraph.inDegree("B"));
        assertEquals(2, testDirectedGraph.outDegree("B"));
        testDirectedGraph.removeEdge("D", "E");
        assertEquals(0, testDirectedGraph.outDegree("D"));
        assertEquals(2, testDirectedGraph.inDegree("D"));
        assertEquals(1, testDirectedGraph.inDegree("E"));
        assertEquals(1, testDirectedGraph.outDegree("E"));
        assertThrows(IllegalArgumentException.class, () -> testDirectedGraph.removeEdge("A", "G"));
    }

    @Test
    public void testOutDegree() {
        assertEquals(3, testDirectedGraph.outDegree("A"));
        assertEquals(2, testDirectedGraph.outDegree("B"));
        assertEquals(1, testDirectedGraph.outDegree("C"));
        assertEquals(1, testDirectedGraph.outDegree("D"));
        assertEquals(1, testDirectedGraph.outDegree("E"));
        assertEquals(0, testDirectedGraph.outDegree("F"));
    }

    @Test
    public void testInDegree() {
        assertEquals(0, testDirectedGraph.inDegree("A"));
        assertEquals(1, testDirectedGraph.inDegree("B"));
        assertEquals(1, testDirectedGraph.inDegree("C"));
        assertEquals(2, testDirectedGraph.inDegree("D"));
        assertEquals(2, testDirectedGraph.inDegree("E"));
        assertEquals(2, testDirectedGraph.inDegree("F"));
    }

    @Test
    public void testBfs() {
        List<String> expected = Arrays.asList("A", "B", "C", "D", "F", "E");
        assertEquals(expected, testDirectedGraph.bfs("A"));
        expected = Arrays.asList("B", "F", "E");
        assertEquals(expected, testDirectedGraph.bfs("B"));
        expected = Arrays.asList("C", "D", "E", "F");
        assertEquals(expected, testDirectedGraph.bfs("C"));
        expected = Arrays.asList("D", "E", "F");
        assertEquals(expected, testDirectedGraph.bfs("D"));
        expected = Arrays.asList("E", "F");
        assertEquals(expected, testDirectedGraph.bfs("E"));
        expected = Collections.singletonList("F");
        assertEquals(expected, testDirectedGraph.bfs("F"));
    }

    @Test
    public void testDfs() {
        List<String> expected = Arrays.asList("A", "D", "E", "F", "C", "B");
        assertEquals(expected, testDirectedGraph.dfs("A"));
        expected = Arrays.asList("B", "E", "F");
        assertEquals(expected, testDirectedGraph.dfs("B"));
        expected = Arrays.asList("C", "D", "E", "F");
        assertEquals(expected, testDirectedGraph.dfs("C"));
        expected = Arrays.asList("D", "E", "F");
        assertEquals(expected, testDirectedGraph.dfs("D"));
        expected = Arrays.asList("E", "F");
        assertEquals(expected, testDirectedGraph.dfs("E"));
        expected = Collections.singletonList("F");
        assertEquals(expected, testDirectedGraph.dfs("F"));
    }
}