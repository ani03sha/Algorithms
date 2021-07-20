package org.redquark.algorithms.datastructures.graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UndirectedGraphTest {

    private final Graph<String> testUndirectedGraph = new UndirectedGraph<>();

    @BeforeEach
    public void setUp() {
        String[] vertices = new String[]{"A", "B", "C", "D", "E", "F"};
        for (String vertex : vertices) {
            testUndirectedGraph.addVertex(vertex);
        }
        testUndirectedGraph.addEdge("A", "B");
        testUndirectedGraph.addEdge("A", "C");
        testUndirectedGraph.addEdge("A", "D");
        testUndirectedGraph.addEdge("B", "F");
        testUndirectedGraph.addEdge("B", "E");
        testUndirectedGraph.addEdge("D", "E");
        testUndirectedGraph.addEdge("E", "F");
        testUndirectedGraph.addEdge("C", "D");
    }

    @Test
    public void testAddVertex() {
        testUndirectedGraph.addVertex("G");
        testUndirectedGraph.addEdge("E", "G");
        testUndirectedGraph.addEdge("F", "G");
        assertEquals(2, testUndirectedGraph.inDegree("G"));
        assertEquals(2, testUndirectedGraph.outDegree("G"));
        assertEquals(4, testUndirectedGraph.outDegree("E"));
        assertEquals(3, testUndirectedGraph.outDegree("F"));
        assertEquals(4, testUndirectedGraph.outDegree("E"));
        assertEquals(3, testUndirectedGraph.outDegree("F"));
    }

    @Test
    public void testRemoveVertex() {
        testUndirectedGraph.removeVertex("A");
        assertEquals(2, testUndirectedGraph.outDegree("B"));
        assertEquals(2, testUndirectedGraph.inDegree("B"));
        assertEquals(1, testUndirectedGraph.outDegree("C"));
        assertEquals(1, testUndirectedGraph.inDegree("C"));
        assertEquals(3, testUndirectedGraph.outDegree("E"));
        assertEquals(3, testUndirectedGraph.outDegree("E"));
        testUndirectedGraph.removeVertex("B");
        assertEquals(1, testUndirectedGraph.inDegree("C"));
        assertEquals(1, testUndirectedGraph.outDegree("C"));
        assertEquals(1, testUndirectedGraph.inDegree("F"));
        assertEquals(1, testUndirectedGraph.outDegree("F"));
        assertThrows(IllegalArgumentException.class, () -> testUndirectedGraph.removeVertex("G"));
    }

    @Test
    public void testAddEdge() {
        testUndirectedGraph.addEdge("B", "D");
        assertEquals(4, testUndirectedGraph.inDegree("B"));
        assertEquals(4, testUndirectedGraph.outDegree("B"));
        assertEquals(4, testUndirectedGraph.inDegree("D"));
        assertEquals(4, testUndirectedGraph.outDegree("D"));
        testUndirectedGraph.addEdge("G", "E");
        testUndirectedGraph.addEdge("G", "H");
        assertEquals(2, testUndirectedGraph.inDegree("G"));
    }

    @Test
    public void testRemoveEdge() {
        testUndirectedGraph.removeEdge("A", "B");
        assertEquals(2, testUndirectedGraph.outDegree("A"));
        assertEquals(2, testUndirectedGraph.inDegree("A"));
        assertEquals(2, testUndirectedGraph.inDegree("B"));
        assertEquals(2, testUndirectedGraph.outDegree("B"));
        testUndirectedGraph.removeEdge("D", "E");
        assertEquals(2, testUndirectedGraph.outDegree("D"));
        assertEquals(2, testUndirectedGraph.inDegree("D"));
        assertEquals(2, testUndirectedGraph.inDegree("E"));
        assertEquals(2, testUndirectedGraph.outDegree("E"));
        assertThrows(IllegalArgumentException.class, () -> testUndirectedGraph.removeEdge("A", "G"));
    }

    @Test
    public void testOutDegree() {
        assertEquals(3, testUndirectedGraph.outDegree("A"));
        assertEquals(3, testUndirectedGraph.outDegree("B"));
        assertEquals(2, testUndirectedGraph.outDegree("C"));
        assertEquals(3, testUndirectedGraph.outDegree("D"));
        assertEquals(3, testUndirectedGraph.outDegree("E"));
        assertEquals(2, testUndirectedGraph.outDegree("F"));
    }

    @Test
    public void testInDegree() {
        assertEquals(3, testUndirectedGraph.inDegree("A"));
        assertEquals(3, testUndirectedGraph.inDegree("B"));
        assertEquals(2, testUndirectedGraph.inDegree("C"));
        assertEquals(3, testUndirectedGraph.inDegree("D"));
        assertEquals(3, testUndirectedGraph.inDegree("E"));
        assertEquals(2, testUndirectedGraph.inDegree("F"));
    }

    @Test
    public void testBfs() {
        List<String> expected = Arrays.asList("A", "B", "C", "D", "F", "E");
        assertEquals(expected, testUndirectedGraph.bfs("A"));
        expected = Arrays.asList("B", "A", "F", "E", "C", "D");
        assertEquals(expected, testUndirectedGraph.bfs("B"));
        expected = Arrays.asList("C", "A", "D", "B", "E", "F");
        assertEquals(expected, testUndirectedGraph.bfs("C"));
        expected = Arrays.asList("D", "A", "E", "C", "B", "F");
        assertEquals(expected, testUndirectedGraph.bfs("D"));
        expected = Arrays.asList("E", "B", "D", "F", "A", "C");
        assertEquals(expected, testUndirectedGraph.bfs("E"));
        expected = Arrays.asList("F", "B", "E", "A", "D", "C");
        assertEquals(expected, testUndirectedGraph.bfs("F"));
    }

    @Test
    public void testDfs() {
        List<String> expected = Arrays.asList("A", "D", "C", "E", "F", "B");
        assertEquals(expected, testUndirectedGraph.dfs("A"));
        expected = Arrays.asList("B", "E", "F", "D", "C", "A");
        assertEquals(expected, testUndirectedGraph.dfs("B"));
        expected = Arrays.asList("C", "D", "E", "F", "B", "A");
        assertEquals(expected, testUndirectedGraph.dfs("C"));
        expected = Arrays.asList("D", "C", "A", "B", "E", "F");
        assertEquals(expected, testUndirectedGraph.dfs("D"));
        expected = Arrays.asList("E", "F", "B", "A", "D", "C");
        assertEquals(expected, testUndirectedGraph.dfs("E"));
        expected = Arrays.asList("F", "E", "D", "C", "A", "B");
        assertEquals(expected, testUndirectedGraph.dfs("F"));
    }
}