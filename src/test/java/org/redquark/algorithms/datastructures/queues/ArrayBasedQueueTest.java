package org.redquark.algorithms.datastructures.queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayBasedQueueTest {

    private final ArrayBasedQueue<Integer> testArrayBasedQueue = new ArrayBasedQueue<>(10);

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            testArrayBasedQueue.push(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testArrayBasedQueue.size());
        testArrayBasedQueue.poll();
        assertEquals(9, testArrayBasedQueue.size());
        testArrayBasedQueue.clear();
        assertEquals(0, testArrayBasedQueue.size());
        testArrayBasedQueue.push(0);
        assertEquals(1, testArrayBasedQueue.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testArrayBasedQueue.isEmpty());
        testArrayBasedQueue.clear();
        assertTrue(testArrayBasedQueue.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 1; i <= 10; i++) {
            assertTrue(testArrayBasedQueue.contains(i));
        }
        assertFalse(testArrayBasedQueue.contains(11));
        testArrayBasedQueue.poll();
        assertFalse(testArrayBasedQueue.contains(1));
        testArrayBasedQueue.push(11);
        assertTrue(testArrayBasedQueue.contains(11));
    }

    @Test
    public void testPush() {
        assertThrows(IndexOutOfBoundsException.class, () -> testArrayBasedQueue.push(11));
        testArrayBasedQueue.clear();
        testArrayBasedQueue.push(1);
        assertEquals(1, testArrayBasedQueue.size());
        testArrayBasedQueue.push(21);
        assertEquals(2, testArrayBasedQueue.size());
    }

    @Test
    public void testPoll() {
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, testArrayBasedQueue.poll());
        }
        assertTrue(testArrayBasedQueue.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, testArrayBasedQueue::poll);
    }

    @Test
    public void testPeek() {
        assertEquals(1, testArrayBasedQueue.peek());
        assertEquals(10, testArrayBasedQueue.size());
        testArrayBasedQueue.poll();
        assertEquals(2, testArrayBasedQueue.peek());
        testArrayBasedQueue.clear();
        assertThrows(IndexOutOfBoundsException.class, testArrayBasedQueue::peek);
    }

    @Test
    public void testClear() {
        assertFalse(testArrayBasedQueue.isEmpty());
        testArrayBasedQueue.clear();
        assertTrue(testArrayBasedQueue.isEmpty());
        assertEquals(0, testArrayBasedQueue.size());
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testArrayBasedQueue.iterator();
        Integer element = 1;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element++;
        }
    }

    @Test
    public void testToString() {
        String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
        assertEquals(expected, testArrayBasedQueue.toString());
        assertEquals(expected, testArrayBasedQueue.toString());
        testArrayBasedQueue.clear();
        assertEquals("[]", testArrayBasedQueue.toString());
    }
}