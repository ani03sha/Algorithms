package org.redquark.algorithms.datastructures.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListTest {

    private final LinkedList<Integer> testLinkedList = new LinkedList<>();

    @BeforeEach
    public void setUp() {
        // Add some test data
        for (int i = 1; i <= 10; i++) {
            testLinkedList.add(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testLinkedList.size());
        testLinkedList.removeFirst();
        testLinkedList.removeLast();
        assertEquals(8, testLinkedList.size());
        testLinkedList.add(12);
        assertEquals(9, testLinkedList.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testLinkedList.isEmpty());
        testLinkedList.clear();
        assertTrue(testLinkedList.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(testLinkedList.contains(55));
        assertTrue(testLinkedList.contains(3));
        testLinkedList.add(55);
        assertTrue(testLinkedList.contains(55));
    }

    @Test
    public void testAdd() {
        testLinkedList.add(11);
        testLinkedList.add(12);
        assertEquals(12, testLinkedList.size());
    }

    @Test
    public void testAddFirst() {
        testLinkedList.addFirst(0);
        assertEquals(0, testLinkedList.head.data);
        assertEquals(11, testLinkedList.size());
        testLinkedList.addFirst(-1);
        assertEquals(-1, testLinkedList.head.data);
        assertEquals(12, testLinkedList.size());
    }

    @Test
    public void testAddAfterNode() {
        testLinkedList.addAfterNode(11, 5);
        assertEquals(11, testLinkedList.head.next.next.next.next.next.data);
        assertEquals(6, testLinkedList.head.next.next.next.next.next.next.data);
        assertEquals(11, testLinkedList.size());
        assertThrows(IllegalArgumentException.class, () -> testLinkedList.addAfterNode(12, 20));
    }

    @Test
    public void testRemove() {
        assertThrows(IllegalArgumentException.class, () -> testLinkedList.remove(11));
        assertEquals(10, testLinkedList.remove(10));
        assertEquals(9, testLinkedList.size());
        assertFalse(testLinkedList.contains(10));
        assertEquals(1, testLinkedList.remove(testLinkedList.head.data));
    }

    @Test
    public void testRemoveLast() {
        for (int i = 10; i > 0; i--) {
            assertEquals(i, testLinkedList.removeLast());
        }
        assertTrue(testLinkedList.isEmpty());
        assertNull(testLinkedList.head);
        assertThrows(IllegalArgumentException.class, testLinkedList::removeLast);
    }

    @Test
    public void testRemoveFirst() {
        for (int i = 1; i < 10; i++) {
            assertEquals(i, testLinkedList.removeFirst());
            assertEquals(i + 1, testLinkedList.head.data);
        }
        assertEquals(10, testLinkedList.removeFirst());
        assertTrue(testLinkedList.isEmpty());
        assertNull(testLinkedList.head);
    }

    @Test
    public void testClear() {
        testLinkedList.clear();
        assertTrue(testLinkedList.isEmpty());
        assertNull(testLinkedList.head);
        assertThrows(IllegalArgumentException.class, testLinkedList::removeFirst);
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testLinkedList.iterator();
        int element = 1;
        while (iterator.hasNext()) {
            assertEquals(element, iterator.next());
            element++;
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testToString() {
        String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
        assertEquals(expected, testLinkedList.toString());
        testLinkedList.add(11);
        expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]";
        assertEquals(expected, testLinkedList.toString());
        testLinkedList.remove(6);
        expected = "[1, 2, 3, 4, 5, 7, 8, 9, 10, 11]";
        assertEquals(expected, testLinkedList.toString());
    }
}