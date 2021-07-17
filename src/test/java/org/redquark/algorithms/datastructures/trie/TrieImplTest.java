package org.redquark.algorithms.datastructures.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieImplTest {

    private final Trie testTrie = new TrieImpl();
    private String[] items;

    @BeforeEach
    public void setUp() {
        items = new String[]{"dear", "deal", "done", "do", "techie", "tech", "boy", "boss", "but"};
        for (String item : items) {
            testTrie.insert(item);
        }
    }

    @Test
    public void testisEmpty() {
        assertFalse(testTrie.isEmpty());
        testTrie.clear();
        assertTrue(testTrie.isEmpty());
    }

    @Test
    public void testInsert() {
        testTrie.insert("book");
        assertTrue(testTrie.search("book"));
        testTrie.insert("fun");
        assertTrue(testTrie.search("fun"));
    }

    @Test
    public void testRemove() {
        for (String item : items) {
            System.out.println("Item: " + item);
            assertTrue(testTrie.remove(item));
        }
        assertFalse(testTrie.remove("done"));
    }

    @Test
    public void testSearch() {
        for (String item : items) {
            assertTrue(testTrie.search(item));
        }
        assertFalse(testTrie.search("book"));
        testTrie.insert("book");
        testTrie.insert("book");
        assertTrue(testTrie.search("book"));
        testTrie.clear();
        assertFalse(testTrie.search("do"));
    }

    @Test
    public void testClear() {
        assertFalse(testTrie.isEmpty());
        testTrie.clear();
        assertTrue(testTrie.isEmpty());
    }

    @Test
    public void testGetAllWords() {
        List<String> expected = Arrays.asList("dear", "deal", "done", "do", "techie", "tech", "boy", "boss", "but");
        assertEquals(expected, testTrie.getAllWords());
    }
}