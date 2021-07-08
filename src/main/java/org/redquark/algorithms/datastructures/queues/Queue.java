package org.redquark.algorithms.datastructures.queues;

public interface Queue<T> {

    /**
     * @return size of the queue
     */
    int size();

    /**
     * @return true, if the queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param element to be searched in the queue
     * @return true, if the element is found, false otherwise
     */
    boolean contains(T element);

    /**
     * @param element to be added at the end/rear of the queue
     */
    void push(T element);

    /**
     * @return element from the start/front of the queue after removing it
     */
    T poll();

    /**
     * @return element from the start/front of the queue without removing it
     */
    T peek();

    /**
     * Empties the queue by deleting all the elements
     */
    void clear();
}
