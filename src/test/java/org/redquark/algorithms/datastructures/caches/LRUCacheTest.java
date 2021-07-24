package org.redquark.algorithms.datastructures.caches;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LRUCacheTest {

    private final Cache<String, String> testLRUCache = new LRUCache<>(5);

    @BeforeEach
    public void setUp() {
        testLRUCache.set("One", "This is the first entry");
        testLRUCache.set("Two", "This is the second entry");
        testLRUCache.set("Three", "This is the third entry");
        testLRUCache.set("Four", "This is the fourth entry");
        testLRUCache.set("Five", "This is the fifth entry");
    }

    @Test
    public void testSize() {
        assertEquals(5, testLRUCache.size());
        testLRUCache.clear();
        assertEquals(0, testLRUCache.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testLRUCache.isEmpty());
        testLRUCache.clear();
        assertTrue(testLRUCache.isEmpty());
    }

    @Test
    public void testHas() {
        assertTrue(testLRUCache.has("One"));
        assertTrue(testLRUCache.has("Two"));
        assertTrue(testLRUCache.has("Three"));
        assertTrue(testLRUCache.has("Four"));
        assertTrue(testLRUCache.has("Five"));
        assertFalse(testLRUCache.has("Six"));
        assertFalse(testLRUCache.has("Seven"));
        assertFalse(testLRUCache.has("Eight"));
        assertFalse(testLRUCache.has("Nine"));
        assertFalse(testLRUCache.has("Ten"));
    }

    @Test
    public void testGet() {
        assertEquals("This is the first entry", testLRUCache.get("One"));
        assertEquals("This is the second entry", testLRUCache.get("Two"));
        assertEquals("This is the third entry", testLRUCache.get("Three"));
        assertEquals("This is the fourth entry", testLRUCache.get("Four"));
        assertEquals("This is the fifth entry", testLRUCache.get("Five"));
        assertNull(testLRUCache.get("Six"));
    }

    @Test
    public void testSet() {
        assertNull(testLRUCache.get("Six"));
        testLRUCache.set("Six", "This is the sixth entry");
        testLRUCache.set("Seven", "This is the seventh entry");
        assertFalse(testLRUCache.has("One"));
        assertFalse(testLRUCache.has("Two"));
        assertEquals("This is the third entry", testLRUCache.get("Three"));
        testLRUCache.set("Eight", "This is the eighth entry");
        assertTrue(testLRUCache.has("Three"));
        assertFalse(testLRUCache.has("Four"));
        testLRUCache.set("Five", "This is the updated fifth entry");
        assertEquals("This is the updated fifth entry", testLRUCache.get("Five"));
    }

    @Test
    public void testDelete() {
        assertTrue(testLRUCache.has("One"));
        testLRUCache.delete("One");
        assertFalse(testLRUCache.has("One"));
        assertEquals(4, testLRUCache.size());
        assertThrows(RuntimeException.class, () -> testLRUCache.delete("One"));
    }

    @Test
    public void testClear() {
        assertEquals(5, testLRUCache.size());
        assertFalse(testLRUCache.isEmpty());
        testLRUCache.clear();
        assertEquals(0, testLRUCache.size());
        assertTrue(testLRUCache.isEmpty());
    }
}