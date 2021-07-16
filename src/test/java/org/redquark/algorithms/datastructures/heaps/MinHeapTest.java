package org.redquark.algorithms.datastructures.heaps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinHeapTest {

    private final MinHeap<Integer> testMinHeap = new MinHeap<>();

    @BeforeEach
    public void setUp() {
        for (int i = 9; i >= 0; i--) {
            testMinHeap.push(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testMinHeap.size());
        testMinHeap.push(-4);
        assertEquals(11, testMinHeap.size());
        testMinHeap.poll();
        assertEquals(10, testMinHeap.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testMinHeap.isEmpty());
        testMinHeap.clear();
        assertTrue(testMinHeap.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 0; i <= 9; i++) {
            assertTrue(testMinHeap.contains(i));
        }
        assertFalse(testMinHeap.contains(-4));
        testMinHeap.push(-4);
        assertTrue(testMinHeap.contains(-4));
        testMinHeap.poll();
        testMinHeap.poll();
        assertFalse(testMinHeap.contains(0));
    }

    @Test
    public void testPush() {
        for (int i = 10; i <= 19; i++) {
            testMinHeap.push(i);
            assertTrue(testMinHeap.contains(i));
            assertEquals(i + 1, testMinHeap.size());
        }
    }

    @Test
    public void testPoll() {
        for (int i = 0; i <= 9; i++) {
            assertTrue(testMinHeap.contains(i));
            assertEquals(i, testMinHeap.poll());
            assertFalse(testMinHeap.contains(i));
        }
        assertThrows(IndexOutOfBoundsException.class, testMinHeap::poll);
    }

    @Test
    public void testPeek() {
        assertEquals(0, testMinHeap.peek());
        testMinHeap.push(-4);
        assertEquals(-4, testMinHeap.peek());
        testMinHeap.poll();
        assertEquals(0, testMinHeap.peek());
        testMinHeap.poll();
        testMinHeap.poll();
        assertEquals(2, testMinHeap.peek());
        testMinHeap.clear();
        assertThrows(IndexOutOfBoundsException.class, testMinHeap::peek);
    }

    @Test
    public void testClear() {
        assertFalse(testMinHeap.isEmpty());
        testMinHeap.clear();
        assertTrue(testMinHeap.isEmpty());
    }
}