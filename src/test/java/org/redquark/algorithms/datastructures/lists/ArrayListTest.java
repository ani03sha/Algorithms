package org.redquark.algorithms.datastructures.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayListTest {

    private final ArrayList<Integer> testArrayList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            testArrayList.add(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testArrayList.size());
        testArrayList.add(11);
        testArrayList.add(12);
        assertEquals(12, testArrayList.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testArrayList.isEmpty());
        testArrayList.clear();
        assertTrue(testArrayList.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(testArrayList.contains(5));
        assertFalse(testArrayList.contains(11));
        testArrayList.add(11);
        assertTrue(testArrayList.contains(11));
    }

    @Test
    public void testAdd() {
        testArrayList.add(11);
        assertEquals(11, testArrayList.size());
        assertEquals(11, testArrayList.get(10));
    }

    @Test
    public void tesAddAtIndex() {
        testArrayList.addAtIndex(11, 9);
        assertEquals(11, testArrayList.get(9));
        assertEquals(10, testArrayList.get(10));
        assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.addAtIndex(33, 12));
    }

    @Test
    public void testRemove() {
        assertEquals(5, testArrayList.remove(5));
        assertEquals(9, testArrayList.size());
        assertEquals(7, testArrayList.get(5));
    }

    @Test
    public void testRemoveAtIndex() {
        assertEquals(1, testArrayList.removeAtIndex(0));
        assertEquals(9, testArrayList.size());
        assertEquals(2, testArrayList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.removeAtIndex(12));
    }

    @Test
    public void testClear() {
        testArrayList.clear();
        assertEquals(0, testArrayList.size());
    }

    @Test
    public void testGet() {
        for (int i = 0; i < testArrayList.size(); i++) {
            assertEquals(i + 1, testArrayList.get(i));
        }
        assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.get(20));
        assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.get(-1));
    }

    @Test
    public void testSet() {
        testArrayList.set(11, 4);
        assertEquals(11, testArrayList.get(4));
        assertEquals(6, testArrayList.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.set(43, 20));
        assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.set(20, -3));
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testArrayList.iterator();
        int element = 1;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element++;
        }
    }

    @Test
    public void testToString() {
        String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
        assertEquals(expected, testArrayList.toString());
        testArrayList.add(11);
        expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]";
        assertEquals(expected, testArrayList.toString());
        testArrayList.remove(6);
        expected = "[1, 2, 3, 4, 5, 7, 8, 9, 10, 11]";
        assertEquals(expected, testArrayList.toString());
    }
}