package org.redquark.algorithms.algorithms.sort;

public class SelectionSort<T extends Comparable<T>> implements Sort<T> {

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
        // Loop over the array for every element
        for (int i = 0; i < n - 1; i++) {
            // Index of the minimum element in the unsorted array
            int minimumIndex = i;
            // Loop for the remaining elements
            for (int j = i + 1; j < n; j++) {
                if (data[j].compareTo(data[minimumIndex]) < 0) {
                    minimumIndex = j;
                }
            }
            // Swap the found elements with the first element
            T temp = data[minimumIndex];
            data[minimumIndex] = data[i];
            data[i] = temp;
        }
        return data;
    }
}
