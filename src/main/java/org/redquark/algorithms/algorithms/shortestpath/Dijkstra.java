package org.redquark.algorithms.algorithms.shortestpath;

import java.util.Arrays;

public class Dijkstra implements ShortestPath {

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
        // Total number of vertices in the graph
        int n = graph.length;
        // Boolean array to store the visited vertices
        boolean[] visited = new boolean[n];
        // Array to store the shortest distance of every node from the source node
        int[] shortestDistances = new int[n];
        // Since we haven't visited any node yet, the shortest distance for each
        // node should be infinity or Integer.MAX_VALUE
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        // Since we are starting from the source, the shortest distance for the source
        // vertex would be zero
        shortestDistances[source] = 0;
        // Loop through all the vertices in the graph
        for (int i = 0; i < n; i++) {
            // Find the nearest vertex (having the shortest distance) from the source vertex.
            // For the first time, it will be the source vertex as only it has the distance 0,
            // for rest, distance is infinity. After the first time, we will find the nearest
            // vertex from the current vertex.
            int u = findNearestVertex(shortestDistances, visited);
            // Since we have found the nearest vertex, mark this node as visited.
            visited[u] = true;
            // Now loop through all the vertices in the graph and find out the shortest distance
            // for the vertex from u.
            for (int v = 0; v < n; v++) {
                // Find the shortest distance if the vertex is not visited and there is an edge
                if (!visited[v] && graph[u][v] != 0 && shortestDistances[u] + graph[u][v] < shortestDistances[v]) {
                    shortestDistances[v] = shortestDistances[u] + graph[u][v];
                }
            }
        }
        return shortestDistances;
    }

    private int findNearestVertex(int[] shortestDistances, boolean[] visited) {
        // Minimum distance so far
        int minimumDistance = Integer.MAX_VALUE;
        // Index of the vertex having the minimum distance
        int minimumDistanceVertex = -1;
        // Loop through all the distances
        for (int i = 0; i < shortestDistances.length; i++) {
            if (!visited[i] && shortestDistances[i] < minimumDistance) {
                minimumDistance = shortestDistances[i];
                minimumDistanceVertex = i;
            }
        }
        return minimumDistanceVertex;
    }
}
