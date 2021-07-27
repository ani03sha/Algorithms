package org.redquark.algorithms.algorithms.search;

public class ExponentialSearch<T extends Comparable<T>> implements Search<T> {

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
        // Check if the element is present at the first location
        if (key.compareTo(data[0]) == 0) {
            return 0;
        }
        // Length of the array
        int n = data.length;
        // Find range for binary search by repeated doubling
        int index = 1;
        while (index < n && key.compareTo(data[index]) > 0) {
            index *= 2;
        }
        // Now perform binary search on the block
        int left = index / 2;
        int right = Math.max(index, n - 1);
        // Loop until the two pointers meet
        while (left <= right) {
            // Middle index
            int middle = left + (right - left) / 2;
            // Check if the element is at the middle index
            if (key.compareTo(data[middle]) == 0) {
                return middle;
            }
            // Check in the left half
            else if (key.compareTo(data[middle]) < 0) {
                right = middle - 1;
            }
            // Check in the right half
            else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
