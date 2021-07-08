package org.redquark.algorithms.datastructures.queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListBasedQueueTest {

    private final ListBasedQueue<Integer> testListBasedQueue = new ListBasedQueue<>();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            testListBasedQueue.push(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testListBasedQueue.size());
        testListBasedQueue.poll();
        assertEquals(9, testListBasedQueue.size());
        testListBasedQueue.clear();
        assertEquals(0, testListBasedQueue.size());
        testListBasedQueue.push(0);
        assertEquals(1, testListBasedQueue.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testListBasedQueue.isEmpty());
        testListBasedQueue.clear();
        assertTrue(testListBasedQueue.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 1; i <= 10; i++) {
            assertTrue(testListBasedQueue.contains(i));
        }
        assertFalse(testListBasedQueue.contains(11));
        testListBasedQueue.poll();
        assertFalse(testListBasedQueue.contains(1));
        testListBasedQueue.push(11);
        assertTrue(testListBasedQueue.contains(11));
    }

    @Test
    public void testPush() {
        testListBasedQueue.clear();
        testListBasedQueue.push(1);
        assertEquals(1, testListBasedQueue.size());
        testListBasedQueue.push(21);
        assertEquals(2, testListBasedQueue.size());
    }

    @Test
    public void testPoll() {
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, testListBasedQueue.poll());
        }
        assertTrue(testListBasedQueue.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, testListBasedQueue::poll);
    }

    @Test
    public void testPeek() {
        assertEquals(1, testListBasedQueue.peek());
        assertEquals(10, testListBasedQueue.size());
        testListBasedQueue.poll();
        assertEquals(2, testListBasedQueue.peek());
        testListBasedQueue.clear();
        assertThrows(IndexOutOfBoundsException.class, testListBasedQueue::peek);
    }

    @Test
    public void testClear() {
        assertFalse(testListBasedQueue.isEmpty());
        testListBasedQueue.clear();
        assertTrue(testListBasedQueue.isEmpty());
        assertEquals(0, testListBasedQueue.size());
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testListBasedQueue.iterator();
        Integer element = 1;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element++;
        }
    }

    @Test
    public void testToString() {
        String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
        assertEquals(expected, testListBasedQueue.toString());
        assertEquals(expected, testListBasedQueue.toString());
        testListBasedQueue.clear();
        assertEquals("[]", testListBasedQueue.toString());
    }

}