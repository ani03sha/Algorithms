package org.redquark.algorithms.algorithms.search;

public class BinarySearch<T extends Comparable<T>> implements Search<T> {

    /**
     * @param data - array in which search needs to be performed
     * @param key  - key to be searched
     * @return index of the key in data, -1 if not found
     */
    @Override
    public int search(T[] data, T key) {
        // Special case
        if (data == null || data.length == 0) {
            return -1;
        }
        // Left and right pointers
        int left = 0;
        int right = data.length - 1;
        // Loop through the array until the two pointers meet
        while (left <= right) {
            // Find the middle element
            int middle = left + (right - left) / 2;
            // Check if the middle element contains the value
            if (data[middle].compareTo(key) == 0) {
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
