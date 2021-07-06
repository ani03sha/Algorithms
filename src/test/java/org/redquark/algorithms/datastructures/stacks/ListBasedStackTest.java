package org.redquark.algorithms.datastructures.stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListBasedStackTest {

    private final ListBasedStack<Integer> testListBasedStack = new ListBasedStack<>();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 5; i++) {
            testListBasedStack.push(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(5, testListBasedStack.size());
        testListBasedStack.pop();
        assertEquals(4, testListBasedStack.size());
        testListBasedStack.push(11);
        assertEquals(5, testListBasedStack.size());
        testListBasedStack.clear();
        assertEquals(0, testListBasedStack.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testListBasedStack.isEmpty());
        testListBasedStack.clear();
        assertTrue(testListBasedStack.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 1; i <= 5; i++) {
            assertTrue(testListBasedStack.contains(i));
        }
        assertFalse(testListBasedStack.contains(11));
    }

    @Test
    public void testPush() {
        testListBasedStack.push(11);
        assertEquals(6, testListBasedStack.size());
        assertTrue(testListBasedStack.contains(11));
        testListBasedStack.push(12);
        assertEquals(7, testListBasedStack.size());
        assertTrue(testListBasedStack.contains(12));
        testListBasedStack.push(12);
        testListBasedStack.push(12);
        testListBasedStack.push(12);
        assertEquals(10, testListBasedStack.size());
    }

    @Test
    public void testPop() {
        assertEquals(5, testListBasedStack.pop());
        assertEquals(4, testListBasedStack.pop());
        assertEquals(3, testListBasedStack.pop());
        assertEquals(2, testListBasedStack.pop());
        assertEquals(1, testListBasedStack.pop());
        assertThrows(IndexOutOfBoundsException.class, testListBasedStack::pop);
        assertEquals(0, testListBasedStack.size());
        assertTrue(testListBasedStack.isEmpty());
    }

    @Test
    public void testPeek() {
        assertEquals(5, testListBasedStack.peek());
        assertEquals(5, testListBasedStack.peek());
        testListBasedStack.pop();
        assertEquals(4, testListBasedStack.peek());
        assertEquals(4, testListBasedStack.size());
        testListBasedStack.clear();
        assertThrows(IndexOutOfBoundsException.class, testListBasedStack::peek);
    }

    @Test
    public void testClear() {
        assertFalse(testListBasedStack.isEmpty());
        testListBasedStack.clear();
        assertTrue(testListBasedStack.isEmpty());
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testListBasedStack.iterator();
        Integer element = 5;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element--;
        }
    }

    @Test
    public void testToString() {
        String expected = "[5, 4, 3, 2, 1]";
        assertEquals(expected, testListBasedStack.toString());
        testListBasedStack.pop();
        expected = "[4, 3, 2, 1]";
        assertEquals(expected, testListBasedStack.toString());
        testListBasedStack.peek();
        assertEquals(expected, testListBasedStack.toString());
        testListBasedStack.push(7);
        expected = "[7, 4, 3, 2, 1]";
        assertEquals(expected, testListBasedStack.toString());
        testListBasedStack.clear();
        assertEquals("[]", testListBasedStack.toString());
    }

}