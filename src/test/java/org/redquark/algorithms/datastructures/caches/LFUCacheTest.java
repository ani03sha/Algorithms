package org.redquark.algorithms.datastructures.caches;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LFUCacheTest {

    private final Cache<String, String> testLFUCache = new LFUCache<>(5);

    @BeforeEach
    public void setUp() {
        testLFUCache.set("One", "This is the first entry");
        testLFUCache.set("Two", "This is the second entry");
        testLFUCache.set("Three", "This is the third entry");
        testLFUCache.set("Four", "This is the fourth entry");
        testLFUCache.set("Five", "This is the fifth entry");
    }

    @Test
    public void testSize() {
        assertEquals(5, testLFUCache.size());
        testLFUCache.clear();
        assertEquals(0, testLFUCache.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testLFUCache.isEmpty());
        testLFUCache.clear();
        assertTrue(testLFUCache.isEmpty());
    }

    @Test
    public void testHas() {
        assertTrue(testLFUCache.has("One"));
        assertTrue(testLFUCache.has("Two"));
        assertTrue(testLFUCache.has("Three"));
        assertTrue(testLFUCache.has("Four"));
        assertTrue(testLFUCache.has("Five"));
        assertFalse(testLFUCache.has("Six"));
        assertFalse(testLFUCache.has("Seven"));
        assertFalse(testLFUCache.has("Eight"));
        assertFalse(testLFUCache.has("Nine"));
        assertFalse(testLFUCache.has("Ten"));
    }

    @Test
    public void testGet() {
        assertEquals("This is the first entry", testLFUCache.get("One"));
        assertEquals("This is the second entry", testLFUCache.get("Two"));
        assertEquals("This is the third entry", testLFUCache.get("Three"));
        assertEquals("This is the fourth entry", testLFUCache.get("Four"));
        assertEquals("This is the fifth entry", testLFUCache.get("Five"));
        assertNull(testLFUCache.get("Six"));
    }

    @Test
    public void testSet() {
        assertEquals("This is the second entry", testLFUCache.get("Two"));
        assertEquals("This is the second entry", testLFUCache.get("Two"));
        assertEquals("This is the second entry", testLFUCache.get("Two"));
        assertNull(testLFUCache.get("Six"));
        testLFUCache.set("Six", "This is the sixth entry");
        testLFUCache.set("Seven", "This is the seventh entry");
        assertFalse(testLFUCache.has("One"));
        testLFUCache.set("Eight", "This is the eighth entry");
        assertFalse(testLFUCache.has("Four"));
        testLFUCache.set("Five", "This is the updated fifth entry");
        assertEquals("This is the updated fifth entry", testLFUCache.get("Five"));
        assertThrows(RuntimeException.class, () -> testLFUCache.delete("One"));
        testLFUCache.set("Nine", "This is the ninth entry");
        assertTrue(testLFUCache.has("Nine"));
    }

    @Test
    public void testDelete() {
        assertTrue(testLFUCache.has("One"));
        testLFUCache.delete("One");
        assertFalse(testLFUCache.has("One"));
        assertEquals(4, testLFUCache.size());
        testLFUCache.delete("Two");
        assertFalse(testLFUCache.has("Two"));
    }

    @Test
    public void testClear() {
        assertEquals(5, testLFUCache.size());
        assertFalse(testLFUCache.isEmpty());
        testLFUCache.clear();
        assertEquals(0, testLFUCache.size());
        assertTrue(testLFUCache.isEmpty());
    }
}