package org.redquark.algorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class UndirectedGraph<T> implements Graph<T> {

    // Adjacency list for the graph
    private final Map<T, List<T>> adjacencyList;

    public UndirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * @param u - value of the vertex to be added
     */
    @Override
    public void addVertex(T u) {
        // If the vertex is not present in the adjacency list, add it
        adjacencyList.putIfAbsent(u, new ArrayList<>());
    }

    /**
     * @param u value of vertex to be removed
     */
    @Override
    public void removeVertex(T u) {
        if (!adjacencyList.containsKey(u)) {
            throw new IllegalArgumentException("Vertex is not present. Cannot remove!");
        }
        // First we will remove the vertex from the adjacency list
        // This will remove all the outgoing edges from this node
        adjacencyList.remove(u);
        // Now, to remove the incoming edges to this node, we will
        // lists of all other vertices and delete if there's any edge.
        for (T vertex : adjacencyList.keySet()) {
            // Get the list corresponding to this vertex
            List<T> list = adjacencyList.get(vertex);
            for (int i = 0; i < list.size(); i++) {
                list.remove(u);
            }
        }
    }

    /**
     * This method adds a new edge(s) between given vertices
     *
     * @param u - source vertex
     * @param v - destination vertex
     */
    @Override
    public void addEdge(T u, T v) {
        // Check if we have vertices with given values, if not,
        // we will create vertex
        if (!adjacencyList.containsKey(u)) {
            addVertex(u);
        }
        if (!adjacencyList.containsKey(v)) {
            addVertex(v);
        }
        // Make bidirectional link between both nodes
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }

    /**
     * This method remove the edge(s) between given vertices
     *
     * @param u - source vertex
     * @param v - destination vertex
     */
    @Override
    public void removeEdge(T u, T v) {
        // Check if the given nodes are present in the graph or not
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException("Vertex is not present in the Graph.");
        }
        // Get the lists of both the nodes
        List<T> uList = adjacencyList.get(u);
        List<T> vList = adjacencyList.get(v);
        // If the link is present, remove it
        if (uList != null && !uList.isEmpty()) {
            uList.remove(v);
        }
        if (vList != null && !vList.isEmpty()) {
            vList.remove(u);
        }
    }

    /**
     * @param u - given vertex
     * @return number of nodes coming out of this edge
     */
    @Override
    public int outDegree(T u) {
        return adjacencyList.get(u).size();
    }

    /**
     * @param u - given vertex
     * @return number of nodes coming to this edge
     */
    @Override
    public int inDegree(T u) {
        return adjacencyList.get(u).size();
    }

    /**
     * @param u - source vertex
     * @return List of vertices in BFS
     */
    @Override
    public List<T> bfs(T u) {
        // List to store the output
        List<T> output = new ArrayList<>();
        // Set to store all the visited vertices
        Set<T> visited = new HashSet<>();
        // Queue to store all the nodes in the BFS
        Queue<T> nodes = new LinkedList<>();
        // Add source to the queue and mark visited
        nodes.add(u);
        visited.add(u);
        // Loop until the queue is empty
        while (!nodes.isEmpty()) {
            // Poll a node from the queue and add it to the output
            T current = nodes.poll();
            output.add(current);
            // Get all the adjacent nodes of the popped vertex
            for (T node : adjacencyList.get(current)) {
                if (!visited.contains(node)) {
                    visited.add(node);
                    nodes.add(node);
                }
            }
        }
        return output;
    }

    /**
     * @param u - source vertex
     * @return List of vertices in DFS
     */
    @Override
    public List<T> dfs(T u) {
        // List to store the output
        List<T> output = new ArrayList<>();
        // Set to store all the visited vertices
        Set<T> visited = new HashSet<>();
        // Stack to store the nodes
        Stack<T> nodes = new Stack<>();
        // Push the source to the stack
        nodes.push(u);
        // Loop until the stack is empty
        while (!nodes.isEmpty()) {
            // Get the top of the stack
            T current = nodes.pop();
            // If the node is not visited before, then only we
            // will add it to the output
            if (!visited.contains(current)) {
                visited.add(current);
                output.add(current);
            }
            // Get all the vertices which are neighbors of the
            // popped vertex and if they are not visited before,
            // add them to the stack
            for (T node : adjacencyList.get(current)) {
                if (!visited.contains(node)) {
                    nodes.push(node);
                }
            }
        }
        return output;
    }
}
