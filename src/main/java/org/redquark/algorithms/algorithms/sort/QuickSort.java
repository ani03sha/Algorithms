package org.redquark.algorithms.algorithms.sort;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {

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
        // Divide the array using pivot
        divide(data, 0, data.length - 1);
        return data;
    }

    private void divide(T[] data, int left, int right) {
        // For valid indices
        if (left < right) {
            // Get the pivot index
            int pivot = partition(data, left, right);
            // Divide the array using pivot
            divide(data, left, pivot - 1);
            divide(data, pivot, right);
        }
    }

    private int partition(T[] data, int left, int right) {
        // Find the middle index
        int middle = left + (right - left) / 2;
        // Pivot element
        T pivotElement = data[middle];
        // Loop until the two pointers meet
        while (left <= right) {
            while (data[left].compareTo(pivotElement) < 0) {
                left++;
            }
            while (data[right].compareTo(pivotElement) > 0) {
                right--;
            }
            if (left <= right) {
                // Swap the elements
                T temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }
}
