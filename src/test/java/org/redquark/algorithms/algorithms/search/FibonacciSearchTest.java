package org.redquark.algorithms.algorithms.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciSearchTest {

    private final Search<Integer> testFibonacciSearch = new FibonacciSearch<>();

    @Test
    public void testSearch() {
        assertEquals(-1, testFibonacciSearch.search(null, 4));
        Integer[] data = {4, -3, 2, 1, 7, 5, 6, 9, 8, -2, -4, 12, -3};
        Arrays.sort(data);
        assertEquals(-1, testFibonacciSearch.search(data, -12));
        assertEquals(6, testFibonacciSearch.search(data, 4));
        assertEquals(12, testFibonacciSearch.search(data, 12));
        assertEquals(1, testFibonacciSearch.search(data, -3));
        assertEquals(8, testFibonacciSearch.search(data, 6));
        data = new Integer[]{};
        assertEquals(-1, testFibonacciSearch.search(data, 4));
    }
}