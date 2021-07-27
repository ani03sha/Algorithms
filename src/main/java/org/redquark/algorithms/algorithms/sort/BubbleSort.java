package org.redquark.algorithms.algorithms.sort;

public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

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
        // length of the array
        int n = data.length;
        // Loop through the array and swap adjacent elements,
        // if they are in the wrong order
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    // Swap
                    T temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        return data;
    }
}
