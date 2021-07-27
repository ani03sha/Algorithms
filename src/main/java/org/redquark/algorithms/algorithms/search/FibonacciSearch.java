package org.redquark.algorithms.algorithms.search;

public class FibonacciSearch<T extends Comparable<T>> implements Search<T> {

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
        // Three variables for finding the Fibonacci sequence
        int a = 0;
        int b = 1;
        int c = a + b;
        // Loop until we find the Fibonacci number which is
        // smaller than or equal to the length
        while (c < n) {
            a = b;
            b = c;
            c = a + b;
        }
        // Offset to mark the eliminated range
        int offset = -1;
        // Loop until there's only one element left
        while (c > 1) {
            // Check  a is a valid location
            int index = Math.min(offset + a, n - 1);
            // If key is greater than the value at index, cut the
            // subarray from offset to index
            if (key.compareTo(data[index]) > 0) {
                c = b;
                b = a;
                a = c - b;
                offset = index;
            }
            // If key is smaller than the value at index, cut the
            // subarray from index + 1 to the last index of array
            else if (key.compareTo(data[index]) < 0) {
                c = a;
                b = b - a;
                a = c - b;
            }
            // If the key is equal to the value at index, return the index
            else {
                return index;
            }
        }
        // Compare the last element with key
        if (c == 1 && data[offset + 1].compareTo(key) == 0) {
            return offset + 1;
        }
        return -1;
    }
}
