package org.redquark.algorithms.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpolationSearchTest {

    private final Search<Integer> testInterpolationSearch = new InterpolationSearch<>();

    @Test
    public void testSearch() {
        assertEquals(-1, testInterpolationSearch.search(null, 4));
        Integer[] data = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
        assertEquals(-1, testInterpolationSearch.search(data, 32));
        assertEquals(0, testInterpolationSearch.search(data, 10));
        assertEquals(1, testInterpolationSearch.search(data, 12));
        assertEquals(2, testInterpolationSearch.search(data, 13));
        assertEquals(3, testInterpolationSearch.search(data, 16));
        assertEquals(4, testInterpolationSearch.search(data, 18));
        assertEquals(5, testInterpolationSearch.search(data, 19));
        assertEquals(6, testInterpolationSearch.search(data, 20));
        assertEquals(7, testInterpolationSearch.search(data, 21));
        assertEquals(8, testInterpolationSearch.search(data, 22));
        assertEquals(9, testInterpolationSearch.search(data, 23));
        assertEquals(10, testInterpolationSearch.search(data, 24));
        assertEquals(11, testInterpolationSearch.search(data, 33));
        assertEquals(12, testInterpolationSearch.search(data, 35));
        assertEquals(13, testInterpolationSearch.search(data, 42));
        assertEquals(14, testInterpolationSearch.search(data, 47));
        data = new Integer[]{};
        assertEquals(-1, testInterpolationSearch.search(data, 4));
    }
}