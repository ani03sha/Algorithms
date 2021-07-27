package org.redquark.algorithms.algorithms.search;

public class JumpSearch<T extends Comparable<T>> implements Search<T> {

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
        // Length of the array
        int n = data.length;
        // Size of the step to jump
        int step = (int) Math.sqrt(n);
        // Last index of the window
        int index = step;
        // Loop through the array until we find the block in
        // which our key is supposed to be present
        while (index < n && key.compareTo(data[index]) > 0) {
            // Update the window keeping in mind that we don't
            // go outside the array limits
            index = Math.min(index + step, n - 1);
        }
        // At this point, we have found the last index of the
        // block and we need to perform linear search in it
        for (int i = index - step; i <= index; i++) {
            // Check if the current element is equal to the key
            if (key.compareTo(data[i]) == 0) {
                return i;
            }
        }
        return -1;
    }
}
