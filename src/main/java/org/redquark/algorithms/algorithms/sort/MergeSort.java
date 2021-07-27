package org.redquark.algorithms.algorithms.sort;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {

    /**
     * @param data - array to be sorted
     * @return sorted array
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] sort(T[] data) {
        // Special case
        if (data == null || data.length == 0) {
            return null;
        }
        // Temp array for intermediate operations
        T[] temp = (T[]) new Comparable[data.length];
        // Function which will divide the array into
        // smaller subarrays
        divide(data, temp, 0, data.length - 1);
        return data;
    }

    private void divide(T[] data, T[] temp, int left, int right) {
        // For valid indices
        if (left < right) {
            // Middle index
            int middle = left + (right - left) / 2;
            // Divide the array into smaller subarrays
            divide(data, temp, left, middle);
            divide(data, temp, middle + 1, right);
            // Merge the sorted subarrays
            merge(data, temp, left, middle, right);
        }
    }

    private void merge(T[] data, T[] temp, int left, int middle, int right) {
        // Copy the array elements to temp
        System.arraycopy(data, left, temp, left, right - left + 1);
        // Indices for subarrays
        int i = left;
        int j = middle + 1;
        int k = left;
        // Loop through the array
        while (i <= middle && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                data[k] = temp[i];
                i++;
            } else {
                data[k] = temp[j];
                j++;
            }
            k++;
        }
        // If there are remaining elements
        while (i <= middle) {
            data[k] = temp[i];
            i++;
            k++;
        }
        while (j <= right) {
            data[k] = temp[j];
            j++;
            k++;
        }
    }
}
