package org.redquark.algorithms.datastructures.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CircularLinkedListTest {

    private final CircularLinkedList<Integer> testCircularLinkedList = new CircularLinkedList<>();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            testCircularLinkedList.add(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testCircularLinkedList.size());
        testCircularLinkedList.remove(1);
        testCircularLinkedList.remove(2);
        assertEquals(8, testCircularLinkedList.size());
        testCircularLinkedList.add(11);
        assertEquals(9, testCircularLinkedList.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testCircularLinkedList.isEmpty());
        testCircularLinkedList.clear();
        assertTrue(testCircularLinkedList.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 1; i <= 10; i++) {
            if (!testCircularLinkedList.contains(i)) {
                System.out.println("Failed for: " + i);
            }
            assertTrue(testCircularLinkedList.contains(i));
        }
        testCircularLinkedList.remove(4);
        assertFalse(testCircularLinkedList.contains(4));
        assertFalse(testCircularLinkedList.contains(11));
        testCircularLinkedList.add(11);
        assertTrue(testCircularLinkedList.contains(11));
        testCircularLinkedList.clear();
        assertFalse(testCircularLinkedList.contains(1));

    }

    @Test
    public void testAdd() {
        testCircularLinkedList.add(11);
        assertEquals(11, testCircularLinkedList.tail.data);
        assertEquals(1, testCircularLinkedList.tail.next.data);
        assertEquals(11, testCircularLinkedList.size());
    }

    @Test
    public void testAddAfterNode() {
        testCircularLinkedList.addAfterNode(11, 5);
        assertEquals(11, testCircularLinkedList.size());
        assertEquals(11, testCircularLinkedList.head.next.next.next.next.next.data);
        assertEquals(6, testCircularLinkedList.head.next.next.next.next.next.next.data);
        assertThrows(IllegalArgumentException.class, () -> testCircularLinkedList.addAfterNode(20, 12));
    }

    @Test
    public void testRemove() {
        assertEquals(10, testCircularLinkedList.remove(10));
        assertEquals(9,testCircularLinkedList.tail.data);
        assertFalse(testCircularLinkedList.contains(10));
        assertEquals(9, testCircularLinkedList.size());
        assertThrows(IllegalArgumentException.class, () -> testCircularLinkedList.remove(10));
    }

    @Test
    public void testClear() {
        assertFalse(testCircularLinkedList.isEmpty());
        testCircularLinkedList.clear();
        assertTrue(testCircularLinkedList.isEmpty());
        assertNull(testCircularLinkedList.head);
        assertNull(testCircularLinkedList.tail);
        assertEquals(0, testCircularLinkedList.size());
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testCircularLinkedList.iterator();
        int element = 1;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element++;
        }
    }

    @Test
    public void testToString() {
        String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9]";
        assertEquals(expected, testCircularLinkedList.toString());
        testCircularLinkedList.add(11);
        expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
        assertEquals(expected, testCircularLinkedList.toString());
        testCircularLinkedList.remove(6);
        expected = "[1, 2, 3, 4, 5, 7, 8, 9, 10]";
        assertEquals(expected, testCircularLinkedList.toString());
    }
}