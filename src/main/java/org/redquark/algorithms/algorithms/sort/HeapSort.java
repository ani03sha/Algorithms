package org.redquark.algorithms.algorithms.sort;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> implements Sort<T> {

    /**
     * @param data - array to be sorted
     * @return sorted array
     */
    @Override
    public T[] sort(T[] data) {
        // Special case
        if (data == null || data.length == 0) {
            return null;
        }
        // Length of the array
        int n = data.length;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(data, n, i);
        }
        // Loop for every element in the array
        for (int i = n - 1; i > 0; i--) {
            // Move current node to the end of the array
            T temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            // Heapify the remaining elements
            heapify(data, i, 0);
        }
        System.out.println(Arrays.toString(data));
        return data;
    }

    /**
     * Heapify a subtree rooted with for node represented by
     * index in the array
     */
    private void heapify(T[] data, int n, int index) {
        // Index of the largest value
        int largest = index;
        // Left and right children of this node
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        // Find the largest among three nodes
        if (left < n && data[left].compareTo(data[largest]) > 0) {
            largest = left;
        }
        if (right < n && data[right].compareTo(data[largest]) > 0) {
            largest = right;
        }
        // If the largest is not root
        if (largest != index) {
            // Swap the elements
            T temp = data[index];
            data[index] = data[largest];
            data[largest] = temp;
            // Recursively heapify the affected subtrees
            heapify(data, n, largest);
        }
    }
}
