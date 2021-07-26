package org.redquark.algorithms.algorithms.search;

public class LinearSearch<T extends Comparable<T>> implements Search<T> {

    /**
     * @param data - array in which search needs to be performed
     * @param key  - key to be searched
     * @return index of the first occurrence of key in data, -1 if not found
     */
    @Override
    public int search(T[] data, T key) {
        // Special case
        if (data == null || data.length == 0) {
            return -1;
        }
        // Iterate through the array and search for the key
        for (int i = 0; i < data.length; i++) {
            // Check if the current element is equal to the key
            if (data[i].compareTo(key) == 0) {
                return i;
            }
        }
        return -1;
    }
}
