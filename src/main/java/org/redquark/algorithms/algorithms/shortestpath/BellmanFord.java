package org.redquark.algorithms.algorithms.shortestpath;

import java.util.Arrays;

public class BellmanFord implements ShortestPath {

    /**
     * @param graph  - input graph
     * @param source - source node/vertex
     * @return array of the shortest paths for all the nodes from the source
     */
    @Override
    public int[] findShortestPath(int[][] graph, int source) {
        // Special case
        if (graph == null || graph.length == 0) {
            throw new IllegalArgumentException("Invalid adjacency matrix");
        }
        // Total number of vertices
        int V = graph.length;
        // Distance array to store the shortest distance of every node from the source
        int[] shortestDistances = new int[V];
        // Populate the array with infinity/Integer.MAX_VALUE
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        // Since we are starting from the source the shortest distance to self will be 0.
        shortestDistances[source] = 0;
        // Relax all edges V - 1 times as there can be at most V - 1 edges to a vertex
        for (int vertex = 0; vertex < V - 1; vertex++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (shortestDistances[i] != Integer.MAX_VALUE && graph[i][j] != 0 && shortestDistances[i] + graph[i][j] < shortestDistances[j]) {
                        shortestDistances[j] = shortestDistances[i] + graph[i][j];
                    }
                }
            }
        }
        // Check if the negative cycles exist or not
        for (int vertex = 0; vertex < V - 1; vertex++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (graph[i][j] != 0 && shortestDistances[i] + graph[i][j] < shortestDistances[j]) {
                        shortestDistances[j] = Integer.MIN_VALUE;
                    }
                }
            }
        }
        return shortestDistances;
    }
}
