package org.redquark.algorithms.datastructures.stacks;

import java.util.Iterator;

public class ListBasedStack<T> implements Stack<T>, Iterable<T> {

    // Top of the stack (head of the list)
    public StackNode<T> top;
    // Size of the stack
    private int size;

    public ListBasedStack() {
        this.size = 0;
        this.top = null;
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
        return top == null;
    }

    /**
     * @param element to be searched in the stack
     * @return true, if element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        // Reference of the top
        StackNode<T> temp = top;
        // Compare with each element in the stack
        while (temp != null) {
            if (temp.data.equals(element)) {
                return true;
            }
            temp = temp.next;
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
        // Create a new node with the given data
        StackNode<T> node = new StackNode<>(element);
        // Update the top
        node.next = top;
        top = node;
        size++;
    }

    /**
     * This method returns the top of the stack after removing it
     *
     * @return the top of the stack
     */
    @Override
    public T pop() {
        // Check if the stack is empty
        if (top == null) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        StackNode<T> currentTop = top;
        // Update the top
        top = top.next;
        // Update the size
        size--;
        // Get the data from the top
        return currentTop.data;
    }

    /**
     * This method returns the top of the stack without removing it
     *
     * @return the top of the stack
     */
    @Override
    public T peek() {
        // Check if the stack is empty
        if (top == null) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return top.data;
    }

    /**
     * Empties the stack by removing all the elements from it
     */
    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    /**
     * @return Returns an iterator over the elements in this stack in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            // Reference of the top
            StackNode<T> temp = top;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T data = temp.data;
                temp = temp.next;
                return data;
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
        // Reference of the top
        StackNode<T> temp = top;
        while (temp.next != null) {
            output.append(temp.data).append(", ");
            temp = temp.next;
        }
        output.append(temp.data).append("]");
        return output.toString();
    }

    static class StackNode<T> {
        T data;
        StackNode<T> next;

        StackNode(T data) {
            this.data = data;
        }
    }
}
