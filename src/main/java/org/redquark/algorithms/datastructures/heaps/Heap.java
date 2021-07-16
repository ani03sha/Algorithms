package org.redquark.algorithms.datastructures.heaps;

public interface Heap<T> {

    /**
     * @return size of the heap
     */
    int size();

    /**
     * @return true, if heap is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param element to be searched in the heap
     * @return true, if the element is found, false otherwise
     */
    boolean contains(T element);

    /**
     * @param element to be added in the heap
     */
    void push(T element);

    /**
     * @return the element at the root of the heap, after removing it
     */
    T poll();

    /**
     * @return the element at the root of the heap, without removing it
     */
    T peek();

    /**
     * Empties the queue by deleting all the elements
     */
    void clear();

}
