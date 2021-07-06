package org.redquark.algorithms.datastructures.stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueueBasedStackTest {

    private final QueueBasedStack<Integer> testQueueBasedStack = new QueueBasedStack<>();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 5; i++) {
            testQueueBasedStack.push(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(5, testQueueBasedStack.size());
        testQueueBasedStack.pop();
        assertEquals(4, testQueueBasedStack.size());
        testQueueBasedStack.push(11);
        assertEquals(5, testQueueBasedStack.size());
        testQueueBasedStack.clear();
        assertEquals(0, testQueueBasedStack.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testQueueBasedStack.isEmpty());
        testQueueBasedStack.clear();
        assertTrue(testQueueBasedStack.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 1; i <= 5; i++) {
            assertTrue(testQueueBasedStack.contains(i));
        }
        assertFalse(testQueueBasedStack.contains(11));
    }

    @Test
    public void testPush() {
        testQueueBasedStack.push(11);
        assertEquals(6, testQueueBasedStack.size());
        assertTrue(testQueueBasedStack.contains(11));
        testQueueBasedStack.push(12);
        assertEquals(7, testQueueBasedStack.size());
        assertTrue(testQueueBasedStack.contains(12));
        testQueueBasedStack.push(12);
        testQueueBasedStack.push(12);
        testQueueBasedStack.push(12);
        assertEquals(10, testQueueBasedStack.size());
    }

    @Test
    public void testPop() {
        assertEquals(5, testQueueBasedStack.pop());
        assertEquals(4, testQueueBasedStack.pop());
        assertEquals(3, testQueueBasedStack.pop());
        assertEquals(2, testQueueBasedStack.pop());
        assertEquals(1, testQueueBasedStack.pop());
        assertThrows(IndexOutOfBoundsException.class, testQueueBasedStack::pop);
        assertEquals(0, testQueueBasedStack.size());
        assertTrue(testQueueBasedStack.isEmpty());
    }

    @Test
    public void testPeek() {
        assertEquals(5, testQueueBasedStack.peek());
        assertEquals(5, testQueueBasedStack.peek());
        testQueueBasedStack.pop();
        assertEquals(4, testQueueBasedStack.peek());
        assertEquals(4, testQueueBasedStack.size());
        testQueueBasedStack.clear();
        assertThrows(IndexOutOfBoundsException.class, testQueueBasedStack::peek);
    }

    @Test
    public void testClear() {
        assertFalse(testQueueBasedStack.isEmpty());
        testQueueBasedStack.clear();
        assertTrue(testQueueBasedStack.isEmpty());
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testQueueBasedStack.iterator();
        Integer element = 1;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element++;
        }
    }

    @Test
    public void testToString() {
        String expected = "[1, 2, 3, 4, 5]";
        assertEquals(expected, testQueueBasedStack.toString());
        testQueueBasedStack.pop();
        expected = "[1, 2, 3, 4]";
        assertEquals(expected, testQueueBasedStack.toString());
        testQueueBasedStack.peek();
        assertEquals(expected, testQueueBasedStack.toString());
        testQueueBasedStack.push(7);
        expected = "[1, 2, 3, 4, 7]";
        assertEquals(expected, testQueueBasedStack.toString());
        testQueueBasedStack.clear();
        assertEquals("[]", testQueueBasedStack.toString());
    }
}