package org.redquark.algorithms.datastructures.queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackBasedQueueTest {

    private final StackBasedQueue<Integer> testStackBasedQueue = new StackBasedQueue<>();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            testStackBasedQueue.push(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testStackBasedQueue.size());
        testStackBasedQueue.poll();
        assertEquals(9, testStackBasedQueue.size());
        testStackBasedQueue.clear();
        assertEquals(0, testStackBasedQueue.size());
        testStackBasedQueue.push(0);
        assertEquals(1, testStackBasedQueue.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testStackBasedQueue.isEmpty());
        testStackBasedQueue.clear();
        assertTrue(testStackBasedQueue.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 1; i <= 10; i++) {
            assertTrue(testStackBasedQueue.contains(i));
        }
        assertFalse(testStackBasedQueue.contains(11));
        testStackBasedQueue.poll();
        assertFalse(testStackBasedQueue.contains(1));
        testStackBasedQueue.push(11);
        assertTrue(testStackBasedQueue.contains(11));
    }

    @Test
    public void testPush() {
        testStackBasedQueue.clear();
        testStackBasedQueue.push(1);
        assertEquals(1, testStackBasedQueue.size());
        testStackBasedQueue.push(21);
        assertEquals(2, testStackBasedQueue.size());
    }

    @Test
    public void testPoll() {
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, testStackBasedQueue.poll());
        }
        assertTrue(testStackBasedQueue.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, testStackBasedQueue::poll);
    }

    @Test
    public void testPeek() {
        assertEquals(1, testStackBasedQueue.peek());
        assertEquals(10, testStackBasedQueue.size());
        testStackBasedQueue.poll();
        assertEquals(2, testStackBasedQueue.peek());
        testStackBasedQueue.clear();
        assertThrows(IndexOutOfBoundsException.class, testStackBasedQueue::peek);
    }

    @Test
    public void testClear() {
        assertFalse(testStackBasedQueue.isEmpty());
        testStackBasedQueue.clear();
        assertTrue(testStackBasedQueue.isEmpty());
        assertEquals(0, testStackBasedQueue.size());
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testStackBasedQueue.iterator();
        Integer element = 1;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element++;
        }
    }

    @Test
    public void testToString() {
        String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
        assertEquals(expected, testStackBasedQueue.toString());
        assertEquals(expected, testStackBasedQueue.toString());
        testStackBasedQueue.clear();
        assertEquals("[]", testStackBasedQueue.toString());
    }

}