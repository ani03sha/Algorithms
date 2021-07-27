package org.redquark.algorithms.algorithms.sort;

public class InsertionSort<T extends Comparable<T>> implements Sort<T> {

    /**
     * @param data - array to be sorted
     * @return sorted array
     */
    @Override
    public T[] sort(T[] data) {
        // Special case
        if (data == null || data.length == 0) {
            return null;
        }
        // Length of the array
        int n = data.length;
        // Loop for each element of the array
        for (int i = 1; i < n; i++) {
            // Current element
            T current = data[i];
            // Pointer to compare elements before current
            int j = i - 1;
            // Loop for the sub array 0...j and move the current
            // element to its current position
            while (j >= 0 && data[j].compareTo(current) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            // Put the current element to its correct position
            data[j + 1] = current;
        }
        return data;
    }
}
