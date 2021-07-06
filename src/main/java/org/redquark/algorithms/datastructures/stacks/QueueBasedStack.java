package org.redquark.algorithms.datastructures.stacks;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class QueueBasedStack<T> implements Stack<T>, Iterable<T> {

    // Size of the stack
    private int size;
    // Java API queues for internal operations
    private Queue<T> A;
    private Queue<T> B;

    public QueueBasedStack() {
        this.size = 0;
        this.A = new LinkedList<>();
        this.B = new LinkedList<>();
    }

    /**
     * @return size of the stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return true, if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element to be searched in the stack
     * @return true, if element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return A.contains(element) || B.contains(element);
    }

    /**
     * This method stores element in the stack at the top
     *
     * @param element to be stored in the stack
     */
    @Override
    public void push(T element) {
        // Add the element to the Queue A
        A.add(element);
        size++;
    }

    /**
     * This method returns the top of the stack after removing it
     *
     * @return the top of the stack
     */
    @Override
    public T pop() {
        // Base case
        if (A.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        // Leave one element in the queue A and put remaining in B
        while (A.size() != 1) {
            B.add(A.poll());
        }
        // Pop the only left element from A
        T data = A.poll();
        size--;
        // SWap the queues
        Queue<T> temp = A;
        A = B;
        B = temp;
        return data;
    }

    /**
     * This method returns the top of the stack without removing it
     *
     * @return the top of the stack
     */
    @Override
    public T peek() {
        // Base case
        if (A.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        // Leave one element in the queue A and put remaining in B
        while (A.size() != 1) {
            B.add(A.poll());
        }
        // Get the last element
        T data = A.poll();
        // Add this element to the B
        B.add(data);
        // Swap the queue
        Queue<T> temp = A;
        A = B;
        B = temp;
        return data;
    }

    /**
     * Empties the stack by removing all the elements from it
     */
    @Override
    public void clear() {
        A.clear();
        size = 0;
    }

    /**
     * @return Returns an iterator over the elements in this stack in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return A.iterator();
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return A.toString();
    }
}
