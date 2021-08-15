package org.redquark.algorithms.datastructures.heaps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaxHeapTest {

    private final MaxHeap<Integer> testMaxHeap = new MaxHeap<>();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i <= 9; i++) {
            testMaxHeap.push(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testMaxHeap.size());
        testMaxHeap.push(-4);
        assertEquals(11, testMaxHeap.size());
        testMaxHeap.poll();
        assertEquals(10, testMaxHeap.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testMaxHeap.isEmpty());
        testMaxHeap.clear();
        assertTrue(testMaxHeap.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 0; i <= 9; i++) {
            assertTrue(testMaxHeap.contains(i));
        }
        assertFalse(testMaxHeap.contains(-4));
        testMaxHeap.push(-4);
        assertTrue(testMaxHeap.contains(-4));
        testMaxHeap.poll();
        testMaxHeap.poll();
        assertFalse(testMaxHeap.contains(8));
    }

    @Test
    public void testPush() {
        for (int i = 10; i <= 19; i++) {
            testMaxHeap.push(i);
            assertTrue(testMaxHeap.contains(i));
            assertEquals(i + 1, testMaxHeap.size());
        }
    }

    @Test
    public void testPoll() {
        for (int i = 9; i >= 0; i--) {
            assertTrue(testMaxHeap.contains(i));
            assertEquals(i, testMaxHeap.poll());
            assertFalse(testMaxHeap.contains(i));
        }
        assertThrows(IndexOutOfBoundsException.class, testMaxHeap::poll);
    }

    @Test
    public void testPeek() {
        assertEquals(9, testMaxHeap.peek());
        testMaxHeap.push(14);
        assertEquals(14, testMaxHeap.peek());
        testMaxHeap.poll();
        assertEquals(9, testMaxHeap.peek());
        testMaxHeap.poll();
        testMaxHeap.poll();
        assertEquals(7, testMaxHeap.peek());
        testMaxHeap.clear();
        assertThrows(IndexOutOfBoundsException.class, testMaxHeap::peek);
    }

    @Test
    public void testClear() {
        assertFalse(testMaxHeap.isEmpty());
        testMaxHeap.clear();
        assertTrue(testMaxHeap.isEmpty());
    }

}