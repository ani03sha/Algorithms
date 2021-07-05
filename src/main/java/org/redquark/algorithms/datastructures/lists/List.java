package org.redquark.algorithms.datastructures.lists;

import java.util.Iterator;

/**
 * @author Anirudh Sharma
 * <p>
 * This interface represents a list data structure and its most common methods
 */
public interface List<T> {

    /**
     * @return the number of elements in the list
     */
    int size();

    /**
     * @return true, if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param element - element to be checked in the list
     * @return true, if the element is present in the list, false otherwise
     */
    boolean contains(T element);

    /**
     * @param element to be added in the list
     */
    void add(T element);

    /**
     * @param element element to be removed from the list
     * @return removed element
     */
    T remove(T element);

    /**
     * @return Returns an iterator over the elements in this list in proper sequence.
     */
    Iterator<T> iterator();

    /**
     * Empties the list by removing all the elements from it
     */
    void clear();
}
