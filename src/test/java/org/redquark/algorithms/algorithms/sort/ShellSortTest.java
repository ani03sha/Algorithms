package org.redquark.algorithms.algorithms.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ShellSortTest {

    private final Sort<Integer> testShellSort = new ShellSort<>();

    @Test
    public void testSort() {
        Integer[] data = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Integer[] expected = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(expected, testShellSort.sort(data));

        data = new Integer[]{64, 34, 25, 12, 22, 11, 90};
        expected = new Integer[]{11, 12, 22, 25, 34, 64, 90};
        assertArrayEquals(expected, testShellSort.sort(data));

        data = new Integer[]{};
        assertNull(testShellSort.sort(data));

        assertNull(testShellSort.sort(null));
    }
}