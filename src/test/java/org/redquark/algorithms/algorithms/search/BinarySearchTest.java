package org.redquark.algorithms.algorithms.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    private final Search<Integer> testBinarySearch = new BinarySearch<>();

    @Test
    public void testSearch() {
        assertEquals(-1, testBinarySearch.search(null, 4));
        Integer[] data = {4, -3, 2, 1, 7, 5, 6, 9, 8, -2, -4, 12, -3};
        Arrays.sort(data);
        // -4,-3,-3,-2,1,2,4,5,6,7,8,9,12
        assertEquals(-1, testBinarySearch.search(data, -12));
        assertEquals(6, testBinarySearch.search(data, 4));
        assertEquals(12, testBinarySearch.search(data, 12));
        assertEquals(2, testBinarySearch.search(data, -3));
        data = new Integer[]{};
        assertEquals(-1, testBinarySearch.search(data, 4));
    }
}