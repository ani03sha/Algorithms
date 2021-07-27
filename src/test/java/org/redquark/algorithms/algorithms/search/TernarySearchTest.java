package org.redquark.algorithms.algorithms.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TernarySearchTest {

    private final Search<Integer> testTernarySearch = new TernarySearch<>();

    @Test
    public void testSearch() {
        assertEquals(-1, testTernarySearch.search(null, 4));
        Integer[] data = {4, -3, 2, 1, 7, 5, 6, 9, 8, -2, -4, 12, -3};
        Arrays.sort(data);
        assertEquals(-1, testTernarySearch.search(data, -12));
        assertEquals(6, testTernarySearch.search(data, 4));
        assertEquals(12, testTernarySearch.search(data, 12));
        assertEquals(1, testTernarySearch.search(data, -3));
        assertEquals(8, testTernarySearch.search(data, 6));
        data = new Integer[]{};
        assertEquals(-1, testTernarySearch.search(data, 4));
    }
}