package org.redquark.algorithms.datastructures.stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayBasedStackTest {

    private final ArrayBasedStack<Integer> testArrayBasedStack = new ArrayBasedStack<>(10);

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 5; i++) {
            testArrayBasedStack.push(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(5, testArrayBasedStack.size());
        testArrayBasedStack.pop();
        assertEquals(4, testArrayBasedStack.size());
        testArrayBasedStack.push(11);
        assertEquals(5, testArrayBasedStack.size());
        testArrayBasedStack.clear();
        assertEquals(0, testArrayBasedStack.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testArrayBasedStack.isEmpty());
        testArrayBasedStack.clear();
        assertTrue(testArrayBasedStack.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 1; i <= 5; i++) {
            assertTrue(testArrayBasedStack.contains(i));
        }
        assertFalse(testArrayBasedStack.contains(11));
    }

    @Test
    public void testPush() {
        testArrayBasedStack.push(11);
        assertEquals(6, testArrayBasedStack.size());
        assertTrue(testArrayBasedStack.contains(11));
        testArrayBasedStack.push(12);
        assertEquals(7, testArrayBasedStack.size());
        assertTrue(testArrayBasedStack.contains(12));
        testArrayBasedStack.push(12);
        testArrayBasedStack.push(12);
        testArrayBasedStack.push(12);
        assertThrows(StackOverflowError.class, () -> testArrayBasedStack.push(16));
    }

    @Test
    public void testPop() {
        assertEquals(5, testArrayBasedStack.pop());
        assertEquals(4, testArrayBasedStack.pop());
        assertEquals(3, testArrayBasedStack.pop());
        assertEquals(2, testArrayBasedStack.pop());
        assertEquals(1, testArrayBasedStack.pop());
        assertThrows(IndexOutOfBoundsException.class, testArrayBasedStack::pop);
        assertEquals(0, testArrayBasedStack.size());
        assertTrue(testArrayBasedStack.isEmpty());
    }

    @Test
    public void testPeek() {
        assertEquals(5, testArrayBasedStack.peek());
        assertEquals(5, testArrayBasedStack.peek());
        testArrayBasedStack.pop();
        assertEquals(4, testArrayBasedStack.peek());
        assertEquals(4, testArrayBasedStack.size());
        testArrayBasedStack.clear();
        assertThrows(IndexOutOfBoundsException.class, testArrayBasedStack::peek);
    }

    @Test
    public void testClear() {
        assertFalse(testArrayBasedStack.isEmpty());
        testArrayBasedStack.clear();
        assertTrue(testArrayBasedStack.isEmpty());
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testArrayBasedStack.iterator();
        Integer element = 5;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element--;
        }
    }

    @Test
    public void testToString() {
        String expected = "[5, 4, 3, 2, 1]";
        assertEquals(expected, testArrayBasedStack.toString());
        testArrayBasedStack.pop();
        expected = "[4, 3, 2, 1]";
        assertEquals(expected, testArrayBasedStack.toString());
        testArrayBasedStack.peek();
        assertEquals(expected, testArrayBasedStack.toString());
        testArrayBasedStack.push(7);
        expected = "[7, 4, 3, 2, 1]";
        assertEquals(expected, testArrayBasedStack.toString());
        testArrayBasedStack.clear();
        assertEquals("[]", testArrayBasedStack.toString());
    }
}