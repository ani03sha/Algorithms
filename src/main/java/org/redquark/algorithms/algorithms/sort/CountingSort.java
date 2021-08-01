package org.redquark.algorithms.algorithms.sort;

public class CountingSort implements Sort<Integer> {

    /**
     * @param data - array to be sorted
     * @return sorted array
     */
    @Override
    public Integer[] sort(Integer[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        // Length of the array
        int n = data.length;
        // Find the maximum and minimum values in the array
        int max = data[0];
        int min = data[0];
        for (int i = 1; i < n; i++) {
            if (data[i] > max) {
                max = data[i];
            }
            if (data[i] < min) {
                min = data[i];
            }
        }
        // Range of the values
        int range = max - min + 1;
        // Count array
        int[] count = new int[range];
        // Output array
        int[] output = new int[n];
        // Fill the count array
        for (int datum : data) {
            count[datum - min]++;
        }
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[data[i] - min] - 1] = data[i];
            count[data[i] - min]--;
        }
        for (int i = 0; i < n; i++) {
            data[i] = output[i];
        }
        return data;
    }

}
