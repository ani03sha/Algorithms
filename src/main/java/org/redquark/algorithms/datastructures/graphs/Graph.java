package org.redquark.algorithms.datastructures.graphs;

import java.util.List;

public interface Graph<T> {

    /**
     * @param u - value of the vertex to be added
     */
    void addVertex(T u);

    /**
     * @param u value of vertex to be removed
     */
    void removeVertex(T u);

    /**
     * This method adds a new edge(s) between given vertices
     *
     * @param u - source vertex
     * @param v - destination vertex
     */
    void addEdge(T u, T v);

    /**
     * This method remove the edge(s) between given vertices
     *
     * @param u - source vertex
     * @param v - destination vertex
     */
    void removeEdge(T u, T v);

    /**
     * @param u - given vertex
     * @return number of nodes coming out of this edge
     */
    int outDegree(T u);

    /**
     * @param u - given vertex
     * @return number of nodes coming to this edge
     */
    int inDegree(T u);

    /**
     * @param u - source vertex
     * @return List of vertices in BFS
     */
    List<T> bfs(T u);

    /**
     * @param u - source vertex
     * @return List of vertices in DFS
     */
    List<T> dfs(T u);
}
