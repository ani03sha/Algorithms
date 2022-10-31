package org.redquark.algorithms.datastructures.queues;

import java.util.Iterator;

public class ArrayBasedQueue<T> implements Queue<T>, Iterable<T> {

    // Internal array to store elements
    private final Object[] elements;
    // Capacity of the queue
    private final int capacity;
    // Size of the queue
    private int size;
    // Front and rear of the queue
    private int front;
    private int rear;

    public ArrayBasedQueue(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.front = 0;
        this.rear = -1;
    }

    /**
     * @return size of the queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return true, if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @param element to be searched in the queue
     * @return true, if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        // Search in the array for the given element
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param element to be added at the end/rear of the queue
     */
    @Override
    public void push(T element) {
        // Check for the available space
        if (size == capacity) {
            throw new IndexOutOfBoundsException("Queue is full. Cannot add!");
        }
        // Add the new element to the rear of the queue
        rear++;
        // Check if we reach to the end of the index
        if (rear == capacity) {
            // Rotate the rear of the queue
            rear = 0;
        }
        elements[rear] = element;
        size++;
    }

    /**
     * @return element from the start/front of the queue
     */
    @Override
    @SuppressWarnings("unchecked")
    public T poll() {
        // Check if the queue is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty. Cannot poll!");
        }
        Object data = elements[front];
        elements[front] = null;
        // Move the front
        front++;
        // If we reach to the last index in the array
        if (front == capacity) {
            // Rotate front
            front = 0;
        }
        size--;
        return (T) data;
    }

    /**
     * @return element from the start/front of the queue without removing it
     */
    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        // Check if the queue is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty. Cannot peek!");
        }
        return (T) elements[front];
    }

    /**
     * Empties the queue by deleting all the elements
     */
    @Override
    public void clear() {
        // Make all the elements in the internal array null
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != capacity;
            }

            @Override
            public T next() {
                Object data = elements[cursor];
                cursor++;
                return (T) data;
            }
        };
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = 0; i < size - 1; i++) {
            output.append(elements[i]).append(", ");
        }
        output.append(elements[size - 1]).append("]");
        return output.toString();
    }
}
