package org.redquark.algorithms.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearSearchTest {

    private final LinearSearch<Integer> testLinearSearch = new LinearSearch<>();

    @Test
    public void testSearch() {
        assertEquals(-1, testLinearSearch.search(null, 4));
        Integer[] data = {4, -3, 2, 1, 7, 5, 6, 9, 8, -2, -4, 12, -3};
        assertEquals(-1, testLinearSearch.search(data, -12));
        assertEquals(0, testLinearSearch.search(data, 4));
        assertEquals(11, testLinearSearch.search(data, 12));
        assertEquals(1, testLinearSearch.search(data, -3));
    }
}