package org.redquark.algorithms.datastructures.stacks;

import java.util.Iterator;

public class ArrayBasedStack<T> implements Stack<T>, Iterable<T> {

    // Internal array to store stack elements
    private final Object[] elements;
    // Maximum capacity of the stack
    private final int capacity;
    // Size of the stack
    private int size;

    public ArrayBasedStack(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = new Object[capacity];
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
        // Search in the array to find element
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method stores element in the stack at the top
     *
     * @param element to be stored in the stack
     */
    @Override
    public void push(T element) {
        // Check if the array is already full
        if (size == capacity) {
            throw new StackOverflowError("Stack is full, cannot add");
        }
        // Else store the element at the top of the array
        elements[size] = element;
        // Update the size
        size++;
    }

    /**
     * This method returns the top of the stack after removing it
     *
     * @return the top of the stack
     */
    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        // Check if the stack is empty
        if (size == 0) {
            throw new IndexOutOfBoundsException("Stack is empty. Cannot pop!");
        }
        // Else return the top of the stack
        T top = (T) elements[size - 1];
        elements[size - 1] = null;
        size--;
        return top;
    }

    /**
     * This method returns the top of the stack without removing it
     *
     * @return the top of the stack
     */
    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        // Check if the stack is empty
        if (size == 0) {
            throw new IndexOutOfBoundsException("Stack is empty. Cannot peek!");
        }
        // Else return the top of the stack
        return (T) elements[size - 1];
    }


    /**
     * Empties the stack by removing all the elements from it
     */
    @Override
    public void clear() {
        // Make everything null in the elements array
        for (int i = 0; i < capacity; i++) {
            elements[i] = null;
        }
        // Update the size to zero
        size = 0;
    }

    /**
     * @return Returns an iterator over the elements in this stack in proper sequence.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {

        return new Iterator<>() {

            // Index of the next element to return
            int cursor = size - 1;

            @Override
            public boolean hasNext() {
                return cursor != -1;
            }

            @Override
            public T next() {
                return (T) elements[cursor--];
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
        for (int i = size - 1; i > 0; i--) {
            output.append(elements[i]).append(", ");
        }
        output.append(elements[0]).append("]");
        return output.toString();
    }
}
