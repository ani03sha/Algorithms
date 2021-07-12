package org.redquark.algorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    private TreeNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * @return true, if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @param element to be searched in the tree
     * @return true if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(TreeNode<T> node, T element) {
        // Base case
        if (node == null) {
            return false;
        } else if (node.data.equals(element)) {
            // If the root contains the element - terminal case
            return true;
        } else if (node.data.compareTo(element) < 0) {
            // Search in right subtree
            return contains(node.right, element);
        } else {
            // Search in the left subtree
            return contains(node.left, element);
        }
    }

    /**
     * @return height of the tree
     */
    @Override
    public int height() {
        return height(root);
    }

    private int height(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * @return diameter of the tree
     */
    @Override
    public int diameter() {
        AtomicInteger d = new AtomicInteger(0);
        diameter(root, d);
        return d.get();
    }

    private int diameter(TreeNode<T> node, AtomicInteger d) {
        if (node == null) {
            return 0;
        }
        // Get the left and right heights of the tree
        int leftHeight = diameter(node.left, d);
        int rightHeight = diameter(node.right, d);
        // Diameter "through" the current node
        int maxD = leftHeight + rightHeight + 1;
        // Update the diameter
        d.set(Math.max(d.get(), maxD));
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * @param element to be inserted in the tree
     */
    @Override
    public void insert(T element) {
        root = insert(root, element);
    }

    private TreeNode<T> insert(TreeNode<T> node, T element) {
        // If the tree is empty
        if (node == null) {
            // Create a new node and make it as root
            return new TreeNode<>(element);
        }
        // If the element is smaller than the current node,
        // move to left subtree
        if (element.compareTo(node.data) < 0) {
            node.left = insert(node.left, element);
        }
        // If the element is greater than the current node,
        // move to right subtree
        else if (element.compareTo(node.data) > 0) {
            node.right = insert(node.right, element);
        }
        // If we try to insert already existing value
        else {
            throw new IllegalArgumentException("A BST cannot have duplicate values.");
        }
        return node;
    }

    /**
     * @param element to be removed from the tree
     * @return removed element
     */
    @Override
    public T remove(T element) {
        return remove(root, element).data;
    }

    private TreeNode<T> remove(TreeNode<T> node, T element) {
        // Base case
        if (node == null) {
            throw new RuntimeException("Tree is empty. Cannot remove!");
        }
        // Traverse the tree until we reach the node with given element
        if (element.compareTo(node.data) < 0) {
            node.left = remove(node.left, element);
        } else if (element.compareTo(node.data) > 0) {
            node.right = remove(node.right, element);
        }
        // If the key is same as the root's key, then we'll delete it
        else if (element.compareTo(node.data) == 0) {
            // Node with only one child or no children
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // For node with two children, get the smallest node in the
            // right subtree
            node.data = minimumValueInRightSubtree(node.right);
            // Delete that node
            node.right = remove(node.right, node.data);
        }
        return node;
    }

    private T minimumValueInRightSubtree(TreeNode<T> node) {
        T minimumValue = node.data;
        while (node.left != null) {
            minimumValue = node.left.data;
            node = node.left;
        }
        return minimumValue;
    }

    /**
     * @return preorder traversal of the tree
     */
    @Override
    public List<T> preorderTraversal() {
        List<T> output = new ArrayList<>();
        preorderTraversal(root, output);
        return output;
    }

    private void preorderTraversal(TreeNode<T> node, List<T> output) {
        // Base case
        if (node == null) {
            return;
        }
        // Add current node's data to the list
        output.add(node.data);
        // Recursively traverse left and right subtrees
        preorderTraversal(node.left, output);
        preorderTraversal(node.right, output);
    }

    /**
     * @return inorder traversal of the tree
     */
    @Override
    public List<T> inorderTraversal() {
        List<T> output = new ArrayList<>();
        inorderTraversal(root, output);
        return output;
    }

    private void inorderTraversal(TreeNode<T> node, List<T> output) {
        // Base case
        if (node == null) {
            return;
        }
        // Traverse left subtree
        inorderTraversal(node.left, output);
        // Add current node's data to the list
        output.add(node.data);
        // Traverse right subtree
        inorderTraversal(node.right, output);
    }

    /**
     * @return postorder traversal of the tree
     */
    @Override
    public List<T> postorderTraversal() {
        List<T> output = new ArrayList<>();
        postorderTraversal(root, output);
        return output;
    }

    private void postorderTraversal(TreeNode<T> node, List<T> output) {
        // Base case
        if (node == null) {
            return;
        }
        // Traverse left and right subtrees
        postorderTraversal(node.left, output);
        postorderTraversal(node.right, output);
        // Add the data at the current node to the list
        output.add(node.data);
    }

    /**
     * @return level order traversal of the tree
     */
    @Override
    public List<List<T>> levelOrderTraversal() {
        // List to store the final output
        List<List<T>> output = new ArrayList<>();
        // Base case
        if (root == null) {
            return output;
        }
        // Queue to store nodes
        Queue<TreeNode<T>> nodes = new LinkedList<>();
        // Add root to the queue
        nodes.add(root);
        // Loop until the queue is empty
        while (!nodes.isEmpty()) {
            // Get the size of the queue. This represents nodes
            // at the current level
            int size = nodes.size();
            // list of nodes at the current level
            List<T> currentLevelNodes = new ArrayList<>();
            // Loop for every node at the given level
            for (int i = 0; i < size; i++) {
                // Current node
                TreeNode<T> current = nodes.poll();
                // Add this node to the list
                if (current != null) {
                    currentLevelNodes.add(current.data);
                    // Check for left and right children
                    if (current.left != null) {
                        nodes.add(current.left);
                    }
                    if (current.right != null) {
                        nodes.add(current.right);
                    }
                }
            }
            // Add current level nodes to the output list
            output.add(currentLevelNodes);
        }
        return output;
    }

    /**
     * @return left view of the tree
     */
    @Override
    public List<T> leftView() {
        // List to store the final output
        List<T> output = new ArrayList<>();
        // Base case
        if (root == null) {
            return output;
        }
        // Queue to store the nodes of the tree
        Queue<TreeNode<T>> nodes = new LinkedList<>();
        // Add root nodes to the queue
        nodes.add(root);
        // Loop until the queue is empty
        while (!nodes.isEmpty()) {
            // Get the size of the queue
            int size = nodes.size();
            // Loop for all the nodes at the current level
            for (int i = 0; i < size; i++) {
                // Get the current node
                TreeNode<T> current = nodes.poll();
                // If this is the first node of the current level,
                // add it to the list
                if (current != null && i == 0) {
                    output.add(current.data);
                }
                // Move to the left and right children
                if (current != null && current.left != null) {
                    nodes.add(current.left);
                }
                if (current != null && current.right != null) {
                    nodes.add(current.right);
                }
            }
        }
        return output;
    }

    /**
     * @return right view of the tree
     */
    @Override
    public List<T> rightView() {
        // List to store the final output
        List<T> output = new ArrayList<>();
        // Base case
        if (root == null) {
            return output;
        }
        // Queue to store the nodes of the tree
        Queue<TreeNode<T>> nodes = new LinkedList<>();
        // Add root nodes to the queue
        nodes.add(root);
        // Loop until the queue is empty
        while (!nodes.isEmpty()) {
            // Get the size of the queue
            int size = nodes.size();
            // Loop for all the nodes at the current level
            for (int i = 0; i < size; i++) {
                // Get the current node
                TreeNode<T> current = nodes.poll();
                // If this is the last node of the current level,
                // add it to the list
                if (current != null && i == size - 1) {
                    output.add(current.data);
                }
                // Move to the left and right children
                if (current != null && current.left != null) {
                    nodes.add(current.left);
                }
                if (current != null && current.right != null) {
                    nodes.add(current.right);
                }
            }
        }
        return output;
    }

    /**
     * @return top view of the tree
     */
    @Override
    public List<T> topView() {
        // List to store the final output
        List<T> output = new ArrayList<>();
        // Map to store node's data, height and horizontal distance
        Map<Integer, Pair<T>> nodeLevelMap = new TreeMap<>();
        topView(root, 0, 0, nodeLevelMap);
        // Traverse the map and add all nodes to the result
        for (Pair<T> value : nodeLevelMap.values()) {
            output.add(value.nodeData);
        }
        return output;
    }

    private void topView(TreeNode<T> node, int verticalDistance, int horizontalDistance, Map<Integer, Pair<T>> nodeLevelMap) {
        // Base case
        if (node == null) {
            return;
        }
        // If the node for a horizontal distance is not present, add it
        if (!nodeLevelMap.containsKey(horizontalDistance)) {
            nodeLevelMap.put(horizontalDistance, new Pair<>(node.data, verticalDistance));
        }
        // If the node for a horizontal distance is present, we will
        // choose the one that comes first
        else {
            Pair<T> current = nodeLevelMap.get(horizontalDistance);
            if (current.height >= verticalDistance) {
                nodeLevelMap.put(horizontalDistance, new Pair<>(node.data, verticalDistance));
            }
        }
        topView(node.left, verticalDistance + 1, horizontalDistance - 1, nodeLevelMap);
        topView(node.right, verticalDistance + 1, horizontalDistance + 1, nodeLevelMap);
    }

    /**
     * @return bottom view of the tree
     */
    @Override
    public List<T> bottomView() {
        // List to store the final output
        List<T> output = new ArrayList<>();
        // Map to store the horizontal distance and node's value
        // and the respective vertical distance
        Map<Integer, Pair<T>> nodeLevelMap = new TreeMap<>();
        bottomView(root, 0, 0, nodeLevelMap);
        // Traverse the map and add all node's data to the final output
        for (Pair<T> value : nodeLevelMap.values()) {
            output.add(value.nodeData);
        }
        return output;
    }

    private void bottomView(TreeNode<T> node, int verticalDistance, int horizontalDistance, Map<Integer, Pair<T>> nodeLevelMap) {
        // Base case
        if (node == null) {
            return;
        }
        // If the current level is greater than the maximum level seen so far
        // for the same horizontal distance or horizontal distance is seen for
        // the first time, update the map
        if (!nodeLevelMap.containsKey(horizontalDistance) || verticalDistance >= nodeLevelMap.get(horizontalDistance).height) {
            nodeLevelMap.put(horizontalDistance, new Pair<>(node.data, verticalDistance));
        }
        // Recur for left and right subtrees
        bottomView(node.left, verticalDistance + 1, horizontalDistance - 1, nodeLevelMap);
        bottomView(node.right, verticalDistance + 1, horizontalDistance + 1, nodeLevelMap);
    }

    /**
     * Deletes all nodes from the tree
     */
    @Override
    public void clear() {
        root = null;
    }

    static class TreeNode<T> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T data) {
            this.data = data;
        }
    }

    static class Pair<T> {
        T nodeData;
        int height;

        Pair(T nodeData, int height) {
            this.nodeData = nodeData;
            this.height = height;
        }
    }
}