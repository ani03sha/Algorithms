package org.redquark.algorithms.datastructures.heaps;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> implements Heap<T> {

    // List to store the elements
    private final List<T> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    /**
     * @return size of the heap
     */
    @Override
    public int size() {
        return elements.size();
    }

    /**
     * @return true, if heap is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * @param element to be searched in the heap
     * @return true, if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return elements.contains(element);
    }

    /**
     * @param element to be added in the heap
     */
    @Override
    public void push(T element) {
        // Add element at the end of the list
        elements.add(element);
        // Call the heapify up on the last index where the element is added
        heapifyUp(size() - 1);
    }

    private void heapifyUp(int index) {
        // Get the parent index of the node at the current index
        int parentIndex = getParentIndex(index);
        // Check if the node at index and its parent violate the heap property
        if (index > 0 && elements.get(index).compareTo(elements.get(parentIndex)) > 0) {
            // Swap the two elements
            swap(index, parentIndex);
            // Recursively heapify up on the parent index
            heapifyUp(parentIndex);
        }
    }

    private void swap(int index, int parentIndex) {
        T temp = elements.get(index);
        elements.set(index, elements.get(parentIndex));
        elements.set(parentIndex, temp);
    }

    private int getParentIndex(int index) {
        // If the index is of node
        if (index == 0) {
            return 0;
        }
        return (index - 1) / 2;
    }

    /**
     * @return the element at the root of the heap, after removing it
     */
    @Override
    public T poll() {
        // Check if the heap is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty. Cannot poll!");
        }
        // Get the first element from the list
        T root = elements.get(0);
        // Get the last element from the list
        T last = elements.get(size() - 1);
        // Set the last element as the root of the heap
        elements.set(0, last);
        // Remove the last element from the list
        elements.remove(size() - 1);
        // Call heapify down on the root node
        heapifyDown(0);
        return root;
    }

    private void heapifyDown(int index) {
        // Get left and right children indices
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        // Find the index with the largest value
        int largest = index;
        if (left < size() && elements.get(left).compareTo(elements.get(index)) > 0) {
            largest = left;
        }
        if (right < size() && elements.get(right).compareTo(elements.get(largest)) > 0) {
            largest = right;
        }
        // Recursively perform heapify down on the largest index
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    /**
     * @return the element at the root of the heap, without removing it
     */
    @Override
    public T peek() {
        // Check if the heap is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty. Cannot peek!");
        }
        return elements.get(0);
    }

    /**
     * Empties the queue by deleting all the elements
     */
    @Override
    public void clear() {
        elements.clear();
    }
}
