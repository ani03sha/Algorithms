package org.redquark.algorithms.datastructures.queues;

import java.util.Iterator;

public class ListBasedQueue<T> implements Queue<T>, Iterable<T> {

    // Size of the queue
    private int size;
    // Front and rear nodes of the queue
    private QueueNode<T> front;
    private QueueNode<T> rear;

    public ListBasedQueue() {
        this.size = 0;
        this.front = null;
        this.rear = null;
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
        // Reference of the front
        QueueNode<T> temp = front;
        // Iterate through every node
        while (temp != null) {
            if (temp.data.equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * @param element to be added at the end/rear of the queue
     */
    @Override
    public void push(T element) {
        // Create a new node with the given element
        QueueNode<T> node = new QueueNode<>(element);
        // If the queue is empty, then this node will both be front and rear
        if (rear == null) {
            front = node;
            rear = node;
            size++;
            return;
        }
        // Add this node as the next of the current rear
        rear.next = node;
        // Update the new rear
        rear = node;
        // Update the size
        size++;
    }

    /**
     * @return element from the start/front of the queue after removing it
     */
    @Override
    public T poll() {
        // Check for the empty queue
        if (rear == null) {
            throw new IndexOutOfBoundsException("Queue is empty. Cannot poll!");
        }
        // Reference of the previous front
        QueueNode<T> previousFront = front;
        // Move front one pointer ahead
        front = front.next;
        // If front becomes null, the make rear also null
        if (front == null) {
            rear = null;
        }
        // Get the data stored in the previous front
        T data = previousFront.data;
        // Update the size
        size--;
        return data;
    }

    /**
     * @return element from the start/front of the queue without removing it
     */
    @Override
    public T peek() {
        // Check for the empty queue
        if (rear == null) {
            throw new IndexOutOfBoundsException("Queue is empty. Cannot poll!");
        }
        // Return the data stored in the front
        return front.data;
    }

    /**
     * Empties the queue by deleting all the elements
     */
    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            // Reference to the front
            QueueNode<T> cursor = front;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public T next() {
                T data = cursor.data;
                cursor = cursor.next;
                return data;
            }
        };
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if (front == null && rear == null) {
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        output.append("[");
        QueueNode<T> temp = front;
        while (temp != null && temp.next != null) {
            output.append(temp.data).append(", ");
            temp = temp.next;
        }
        output.append(temp.data).append("]");
        return output.toString();
    }

    static class QueueNode<T> {
        T data;
        QueueNode<T> next;

        QueueNode(T data) {
            this.data = data;
        }
    }
}
