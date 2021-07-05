package org.redquark.algorithms.datastructures.lists;

import java.util.Iterator;

public class ArrayList<T> implements List<T>, Iterable<T> {

    // Size of the ArrayList, i.e. total elements in the list
    private int size;
    // Internal array to store elements
    private Object[] elements;

    public ArrayList() {
        this.size = 0;
        this.elements = new Object[10];
    }

    /**
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return true, if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @param element - element to be checked in the list
     * @return true, if the element is present in the list, false otherwise
     */
    @Override
    public boolean contains(T element) {
        // Search for the element in the list
        for (Object item : elements) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param element to be added in the list
     */
    @Override
    public void add(T element) {
        // Check if we have already reached to the capacity of list
        if (this.size == this.elements.length) {
            // Increase the capacity of the list
            ensureCapacity();
        }
        elements[size++] = element;
    }

    /**
     * @param element to be added in the list
     * @param index   the index at which the element is to be added
     */
    @Override
    public void addAtIndex(T element, int index) {
        // Check for out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        // Check if we have the capacity or not
        if (elements.length == size) {
            // Update the capacity
            ensureCapacity();
        }
        // Move all the elements after index one position right
        int i = index;
        while (i < size) {
            elements[i + 1] = elements[i];
            i++;
        }
        // Set the new value at the specified index
        elements[index] = element;
        size++;
    }

    /**
     * @param element element to be removed from the list
     * @return removed element
     */
    @Override
    public T remove(T element) {
        // Index of the element to be removed
        int index = -1;
        // Search for the element in the list
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                index = i;
                break;
            }
        }
        return removeAtIndex(index);
    }

    /**
     * @param index at which the element is to be removed
     * @return true, if the element is removed, false otherwise
     */
    @Override
    @SuppressWarnings("unchecked")
    public T removeAtIndex(int index) {
        // Check for out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        Object removedElement = elements[index];
        // Move all the elements after the removed element one place left
        int i = index;
        while (i < size - 1) {
            elements[i] = elements[i + 1];
            i++;
        }
        size--;
        return (T) removedElement;
    }

    /**
     * Empties the list by removing all the elements from it
     */
    @Override
    public void clear() {
        // Make all object null in the list
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * @param index at which the element is needed
     * @return element at the specified index, null otherwise
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        // Check for out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        return (T) elements[index];
    }

    /**
     * @param element to be updated
     * @param index   at which the element is to be updated
     */
    @Override
    public void set(T element, int index) {
        // Check for out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        elements[index] = element;
    }

    /**
     * @return Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {

        return new Iterator<>() {
            // Index of the next element to return
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                return (T) elements[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = 0; i < size - 1; i++) {
            output.append(elements[i]).append(", ");
        }
        output.append(elements[size - 1]).append("]");
        return output.toString();
    }

    private void ensureCapacity() {
        int newCapacity = 2 * elements.length;
        // Create a new array with this capacity
        Object[] updatedElements = new Object[newCapacity];
        // Copy all the elements of the old array to the new array
        System.arraycopy(elements, 0, updatedElements, 0, elements.length);
        // Assign this updated array to the original array
        elements = updatedElements;
    }
}
