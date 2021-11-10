package org.redquark.algorithms.algorithms.shortestpath;

public interface ShortestPath {

    /**
     * @param graph  - input graph
     * @param source - source node/vertex
     * @return array of the shortest paths for all the nodes from the source
     */
    int[] findShortestPath(int[][] graph, int source);
}
