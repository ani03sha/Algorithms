package org.redquark.algorithms.datastructures.queues;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class StackBasedQueue<T> implements Queue<T>, Iterable<T> {

    // Stacks to perform queue operations
    private final Stack<T> A;
    private final Stack<T> B;
    // Size of the queue
    private int size;

    public StackBasedQueue() {
        this.size = 0;
        A = new Stack<>();
        B = new Stack<>();
    }

    /**
     * @return size of the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true, if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element to be searched in the queue
     * @return true, if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return A.contains(element);
    }

    /**
     * @param element to be added at the end/rear of the queue
     */
    @Override
    public void push(T element) {
        // Move all the elements in A to B
        while (!A.isEmpty()) {
            B.push(A.pop());
        }
        // Push the given element to A
        A.push(element);
        // Move all elements in B to A
        while (!B.isEmpty()) {
            A.push(B.pop());
        }
        // Update the size
        size++;
    }

    /**
     * @return element from the start/front of the queue after removing it
     */
    @Override
    public T poll() {
        // Check if the queue is empty
        if (A.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty. Cannot poll!");
        }
        size--;
        return A.pop();
    }

    /**
     * @return element from the start/front of the queue without removing it
     */
    @Override
    public T peek() {
        // Check if the queue is empty
        if (A.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty. Cannot peek!");
        }
        return A.peek();
    }

    /**
     * Empties the queue by deleting all the elements
     */
    @Override
    public void clear() {
        A.clear();
        size = 0;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        Collections.reverse(A);
        return A.iterator();
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if (A.isEmpty()) {
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = A.size() - 1; i >= 1; i--) {
            output.append(A.get(i)).append(", ");
        }
        output.append(A.get(0)).append("]");
        return output.toString();
    }
}
