package org.redquark.algorithms.algorithms.search;

public class TernarySearch<T extends Comparable<T>> implements Search<T> {

    /**
     * @param data - array in which search needs to be performed
     * @param key  - key to be searched
     * @return index of key in data, -1 if not found
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
        // Loop until the two pointers meet
        while (left <= right) {
            // Find the two pivots which divide the array in three parts
            int pivotOne = left + (right - left) / 3;
            int pivotTwo = right - (right - left) / 3;
            // Check if the key is present at any of the pivot
            if (key.compareTo(data[pivotOne]) == 0) {
                return pivotOne;
            }
            if (key.compareTo(data[pivotTwo]) == 0) {
                return pivotTwo;
            }
            // Now, we will find in which region the element
            // might be present
            // Key lies between left and pivotOne
            if (key.compareTo(data[pivotOne]) < 0) {
                right = pivotOne - 1;
            }
            // Key lies between pivotTwo and right
            else if (key.compareTo(data[pivotTwo]) > 0) {
                left = pivotTwo + 1;
            }
            // If the key is present between pivots
            else {
                left = pivotOne + 1;
                right = pivotTwo - 1;
            }
        }
        return -1;
    }
}
