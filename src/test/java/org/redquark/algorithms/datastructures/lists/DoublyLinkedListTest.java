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

public class DoublyLinkedListTest {

    private final DoublyLinkedList<Integer> testDoublyLinkedList = new DoublyLinkedList<>();

    @BeforeEach
    public void setUp() {
        // Add some test data
        for (int i = 1; i <= 10; i++) {
            testDoublyLinkedList.add(i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(10, testDoublyLinkedList.size());
        testDoublyLinkedList.removeFirst();
        testDoublyLinkedList.removeLast();
        assertEquals(8, testDoublyLinkedList.size());
        testDoublyLinkedList.add(12);
        assertEquals(9, testDoublyLinkedList.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testDoublyLinkedList.isEmpty());
        testDoublyLinkedList.clear();
        assertTrue(testDoublyLinkedList.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(testDoublyLinkedList.contains(55));
        assertTrue(testDoublyLinkedList.contains(3));
        testDoublyLinkedList.add(55);
        assertTrue(testDoublyLinkedList.contains(55));
    }

    @Test
    public void testAdd() {
        testDoublyLinkedList.add(11);
        testDoublyLinkedList.add(12);
        assertEquals(12, testDoublyLinkedList.size());
    }

    @Test
    public void testAddFirst() {
        testDoublyLinkedList.addFirst(0);
        assertEquals(0, testDoublyLinkedList.head.data);
        assertEquals(0, testDoublyLinkedList.head.next.previous.data);
        assertEquals(11, testDoublyLinkedList.size());
        testDoublyLinkedList.addFirst(-1);
        assertEquals(-1, testDoublyLinkedList.head.data);
        assertEquals(-1, testDoublyLinkedList.head.next.previous.data);
        assertEquals(12, testDoublyLinkedList.size());
        testDoublyLinkedList.clear();
        assertNull(testDoublyLinkedList.head);
        testDoublyLinkedList.addFirst(120);
        assertEquals(120, testDoublyLinkedList.head.data);
    }

    @Test
    public void testAddAfterNode() {
        testDoublyLinkedList.addAfterNode(11, 5);
        assertEquals(11, testDoublyLinkedList.head.next.next.next.next.next.data);
        assertEquals(5, testDoublyLinkedList.head.next.next.next.next.next.previous.data);
        assertEquals(6, testDoublyLinkedList.head.next.next.next.next.next.next.data);
        assertEquals(11, testDoublyLinkedList.size());
        assertThrows(IllegalArgumentException.class, () -> testDoublyLinkedList.addAfterNode(12, 20));
    }

    @Test
    public void testRemove() {
        assertThrows(IllegalArgumentException.class, () -> testDoublyLinkedList.remove(11));
        assertEquals(10, testDoublyLinkedList.remove(10));
        assertEquals(9, testDoublyLinkedList.size());
        assertFalse(testDoublyLinkedList.contains(10));
        assertEquals(1, testDoublyLinkedList.remove(testDoublyLinkedList.head.data));
    }

    @Test
    public void testRemoveLast() {
        for (int i = 10; i > 0; i--) {
            assertEquals(i, testDoublyLinkedList.removeLast());
        }
        assertTrue(testDoublyLinkedList.isEmpty());
        assertNull(testDoublyLinkedList.head);
        assertThrows(IllegalArgumentException.class, testDoublyLinkedList::removeLast);
    }

    @Test
    public void testRemoveFirst() {
        for (int i = 1; i < 10; i++) {
            assertEquals(i, testDoublyLinkedList.removeFirst());
            assertEquals(i + 1, testDoublyLinkedList.head.data);
        }
        assertEquals(10, testDoublyLinkedList.removeFirst());
        assertTrue(testDoublyLinkedList.isEmpty());
        assertNull(testDoublyLinkedList.head);
    }

    @Test
    public void testClear() {
        testDoublyLinkedList.clear();
        assertTrue(testDoublyLinkedList.isEmpty());
        assertNull(testDoublyLinkedList.head);
        assertThrows(IllegalArgumentException.class, testDoublyLinkedList::removeFirst);
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = testDoublyLinkedList.iterator();
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
        assertEquals(expected, testDoublyLinkedList.toString());
        testDoublyLinkedList.add(11);
        expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]";
        assertEquals(expected, testDoublyLinkedList.toString());
        testDoublyLinkedList.remove(6);
        expected = "[1, 2, 3, 4, 5, 7, 8, 9, 10, 11]";
        assertEquals(expected, testDoublyLinkedList.toString());
    }
}