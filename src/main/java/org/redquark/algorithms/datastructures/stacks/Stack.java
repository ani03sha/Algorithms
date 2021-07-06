package org.redquark.algorithms.datastructures.stacks;

import java.util.Iterator;

public interface Stack<T> {

    /**
     * @return size of the stack
     */
    int size();

    /**
     * @return true, if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param element to be searched in the stack
     * @return true, if element is found, false otherwise
     */
    boolean contains(T element);

    /**
     * This method stores element in the stack at the top
     *
     * @param element to be stored in the stack
     */
    void push(T element);

    /**
     * This method returns the top of the stack after removing it
     *
     * @return the top of the stack
     */
    T pop();

    /**
     * This method returns the top of the stack without removing it
     *
     * @return the top of the stack
     */
    T peek();

    /**
     * @return Returns an iterator over the elements in this stack in proper sequence.
     */
    Iterator<T> iterator();

    /**
     * Empties the stack by removing all the elements from it
     */
    void clear();

}
