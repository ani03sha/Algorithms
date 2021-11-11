package org.redquark.algorithms.algorithms.shortestpath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BellmanFordTest {

    private final ShortestPath testBellmanFord = new BellmanFord();

    @Test
    public void testFindShortestPath() {
        int[][] graph = new int[][]{
                {0, -1, 4, 0, 0},
                {0, 0, 3, 2, 2},
                {0, 0, 0, 0, 0},
                {0, 1, 5, 0, 0},
                {0, 0, 0, -3, 0}
        };
        int source = 0;
        int[] expected = new int[]{0, -1, 2, -2, 1};
        assertArrayEquals(expected, testBellmanFord.findShortestPath(graph, source));

        assertThrows(IllegalArgumentException.class, () -> testBellmanFord.findShortestPath(null, source));

        graph = new int[][]{
                {0, -1, 4, 0, 0},
                {0, 0, 3, 2, 2},
                {0, 0, -2, 0, 0},
                {0, 1, 5, 0, 0},
                {0, 0, 0, -3, 0}
        };
        expected = new int[]{0, -1, -2147483648, -2, 1};
        assertArrayEquals(expected, testBellmanFord.findShortestPath(graph, source));
    }
}