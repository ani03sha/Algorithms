package org.redquark.algorithms.algorithms.shortestpath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DijkstraTest {

    private final ShortestPath testDijkstra = new Dijkstra();

    @Test
    public void testFindShortestPath() {
        int[][] graph = new int[][]{
                {0, 0, 1, 2, 0, 0, 0},
                {0, 0, 2, 0, 0, 3, 0},
                {1, 2, 0, 1, 3, 0, 0},
                {2, 0, 1, 0, 0, 0, 1},
                {0, 0, 3, 0, 0, 2, 0},
                {0, 3, 0, 0, 2, 0, 1},
                {0, 0, 0, 1, 0, 1, 0}
        };
        int source = 0;
        int[] expected = new int[]{0, 3, 1, 2, 4, 4, 3};
        assertArrayEquals(expected, testDijkstra.findShortestPath(graph, source));

        source = 4;
        expected = new int[]{4, 5, 3, 4, 0, 2, 3};
        assertArrayEquals(expected, testDijkstra.findShortestPath(graph, source));
    }
}