package org.redquark.algorithms.datastructures.maps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashMapTest {

    private final Map<Integer, Integer> testHashMap = new HashMap<>();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 16; i++) {
            testHashMap.put(i, i * i);
        }
    }

    @Test
    public void testSize() {
        assertEquals(16, testHashMap.size());
        testHashMap.clear();
        assertEquals(0, testHashMap.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testHashMap.isEmpty());
        testHashMap.clear();
        assertTrue(testHashMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        for (int i = 1; i <= 16; i++) {
            assertTrue(testHashMap.containsKey(i));
            assertFalse(testHashMap.containsKey(i + 16));
        }
        testHashMap.put(3, 9);
        testHashMap.put(18, 324);
        assertTrue(testHashMap.containsKey(3));
        assertTrue(testHashMap.containsKey(18));
    }

    @Test
    public void testContainsValue() {
        for (int i = 1; i <= 16; i++) {
            assertTrue(testHashMap.containsValue(i * i));
            assertFalse(testHashMap.containsValue((i + 16) * (i + 16)));
        }
        testHashMap.clear();
        assertFalse(testHashMap.containsValue(4));
    }

    @Test
    public void testGet() {
        for (int i = 1; i <= 16; i++) {
            assertEquals(i * i, testHashMap.get(i));
        }
        assertNull(testHashMap.get(17));
    }

    @Test
    public void testPut() {
        testHashMap.put(17, 289);
        assertEquals(17, testHashMap.size());
        testHashMap.put(16, 257);
        assertEquals(257, testHashMap.get(16));
        testHashMap.put(18, 324);
        assertEquals(18, testHashMap.size());
        assertEquals(324, testHashMap.get(18));
        assertEquals(289, testHashMap.get(17));
        testHashMap.put(null, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, testHashMap.get(null));
    }

    @Test
    public void testRemove() {
        for (int i = 1; i <= 16; i++) {
            assertTrue(testHashMap.containsKey(i));
            testHashMap.remove(i);
            assertFalse(testHashMap.containsKey(i));
            assertEquals(16 - i, testHashMap.size());
        }
        assertThrows(IllegalArgumentException.class, () -> testHashMap.remove(2));
    }

    @Test
    public void testKeys() {
        Set<Integer> allKeys = new HashSet<>();
        for (int i = 1; i <= 16; i++) {
            allKeys.add(i);
        }
        assertEquals(allKeys, testHashMap.keys());
        testHashMap.clear();
        allKeys.clear();
        assertEquals(allKeys, testHashMap.keys());
    }

    @Test
    public void testValues() {
        Collection<Integer> allValues = new HashSet<>();
        for (int i = 1; i <= 16; i++) {
            allValues.add(i * i);
        }
        assertEquals(allValues, testHashMap.values());
        testHashMap.clear();
        allValues.clear();
        assertEquals(allValues, testHashMap.values());
    }

    @Test
    public void testClear() {
        assertEquals(16, testHashMap.size());
        testHashMap.clear();
        assertEquals(0, testHashMap.size());
        assertTrue(testHashMap.isEmpty());
    }

    @Test
    public void testToString() {
        String expected = "[1 = 1, 2 = 4, 3 = 9, 4 = 16, 5 = 25, 6 = 36, 7 = 49, 8 = 64, " +
                "9 = 81, 10 = 100, 11 = 121, 12 = 144, 13 = 169, 14 = 196, 15 = 225, 16 = 256]";
        assertEquals(expected, testHashMap.toString());
    }
}