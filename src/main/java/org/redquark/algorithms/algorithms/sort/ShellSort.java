package org.redquark.algorithms.algorithms.sort;

public class ShellSort<T extends Comparable<T>> implements Sort<T> {

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
        // We will be using original's Shell sequence (n/2, n/4, n/8,...)
        for (int interval = n / 2; interval > 0; interval /= 2) {
            // Perform insertion sort for the elements at the gaps
            for (int i = interval; i < n; i++) {
                T temp = data[i];
                // Find correct location of data[i]
                int j = i;
                while (j >= interval && data[j - interval].compareTo(temp) > 0) {
                    data[j] = data[j - interval];
                    j -= interval;
                }
                // Put temp at its correct position
                data[j] = temp;
            }
        }
        return data;
    }
}
