package org.redquark.algorithms.algorithms.search;

public class InterpolationSearch<T extends Comparable<T>> implements Search<T> {

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
        // Left and right pointers
        int left = 0;
        int right = n - 1;
        // Loop until the two pointers meet or the key is
        // in the appropriate range
        while ((left <= right && key.compareTo(data[left]) >= 0 && key.compareTo(data[right]) <= 0)) {
            // Special case
            if (left == right) {
                return data[left] == key ? left : -1;
            }
            // Probe position assuming uniform distribution
            int position = getPosition(data, key, left, right);
            // Check if the element is found at the middle index
            if (key.compareTo(data[position]) == 0) {
                return position;
            }
            // Check in the left half
            else if (key.compareTo(data[position]) < 0) {
                right = position - 1;
            }
            // Check in the right half
            else {
                left = position + 1;
            }
        }
        return -1;
    }

    private int getPosition(T[] data, T key, int left, int right) {
        // Convert all the values in the integer for computations
        int keyInt = Integer.parseInt(key.toString());
        int leftInt = Integer.parseInt(data[left].toString());
        int rightInt = Integer.parseInt(data[right].toString());
        // Find the position using interpolation formula
        return left + (right - left) / (rightInt - leftInt) * (keyInt - leftInt);
    }
}
