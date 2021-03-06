package org.redquark.algorithms.sorting;

import org.junit.Assert;
import org.junit.Test;

public class CombSortTest {

    private CombSort combSort = new CombSort();

    @Test
    public void combSortIntegerTest() {

        // Unsorted integer array
        Integer[] unsorted = new Integer[]{5, 1, 7, 0, 2, 9, 6, 3, 4, 8};

        // Sorted integer array
        Integer[] sorted = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Comparing the two integer arrays
        Assert.assertArrayEquals(sorted, combSort.sort(unsorted, unsorted.length));
    }

    @Test
    public void combSortFloatTest() {

        // Unsorted floating array
        Float[] unsorted = new Float[]{6.7f, 21.1f, 0.9f, -3.2f, 5.9f, -21.3f};

        // Sorted floating array
        Float[] sorted = new Float[]{-21.3f, -3.2f, 0.9f, 5.9f, 6.7f, 21.1f};

        // Comparing the two floating arrays
        Assert.assertArrayEquals(sorted, combSort.sort(unsorted, unsorted.length));
    }

    @Test
    public void combSortCharacterTest() {

        // Unsorted character array
        Character[] unsorted = new Character[]{'c', 'a', 'b', 'A', 'C', 'B'};

        // Sorted Character array
        Character[] sorted = new Character[]{'A', 'B', 'C', 'a', 'b', 'c'};

        // Comparing the two character arrays
        Assert.assertArrayEquals(sorted, combSort.sort(unsorted, unsorted.length));
    }

    @Test
    public void combSortStringTest() {

        // Unsorted String array
        String[] unsorted = new String[]{"Edward", "Linus", "David", "Alan", "Dennis", "Robert", "Ken"};

        // Sorted String array
        String[] sorted = new String[]{"Alan", "David", "Dennis", "Edward", "Ken", "Linus", "Robert"};

        // Comparing the two String arrays
        Assert.assertArrayEquals(sorted, combSort.sort(unsorted, unsorted.length));
    }
}
