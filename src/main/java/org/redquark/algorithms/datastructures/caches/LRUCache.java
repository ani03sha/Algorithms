package org.redquark.algorithms.datastructures.caches;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

    // Capacity of the cache
    private final int capacity;
    // Map to store the key and node (containing values) of the cache
    private final Map<K, Entry<K, V>> entries;
    // Size of the cache
    private int size;
    // Head of the internal doubly linked list
    private Entry<K, V> head;
    // Tail of the internal doubly linked list
    private Entry<K, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.entries = new HashMap<>();
    }

    /**
     * @return size of the cache
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true, if the cache is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param key - to be searched in the cache
     * @return true, if the key is cached, false otherwise
     */
    @Override
    public boolean has(K key) {
        return entries.containsKey(key);
    }

    /**
     * @param key - to be searched in the cache
     * @return value associated with key
     */
    @Override
    public V get(K key) {
        // Check if the cache contains the key
        if (entries.containsKey(key)) {
            // Get the entry corresponding to the key
            Entry<K, V> entry = entries.get(key);
            // Delete node from its current position
            deleteNode(entry);
            // Add this node to the front/head of the list
            updateHead(entry);
            return entry.value;
        }
        return null;
    }

    /**
     * @param key   - key of the object to be cached
     * @param value - value of the object to be cached
     */
    @Override
    public void set(K key, V value) {
        // If the key is already present in the cache,
        // we will update the value
        if (entries.containsKey(key)) {
            // Update the value
            Entry<K, V> entry = entries.get(key);
            entry.value = value;
            // Delete node from its current position
            deleteNode(entry);
            // Add this node to the front/head of the list
            updateHead(entry);
        }
        // If this is a new key, we will have to evict the
        // least recently used entry and put this one, if
        // the cache is full
        else {
            // Create a new node
            Entry<K, V> entry = new Entry<>(key, value);
            // If the cache is full
            if (size >= capacity) {
                // Remove the node from the tail which is
                // least recently used
                entries.remove(tail.key);
                // Delete node from its current position
                deleteNode(tail);
                // Add this node to the front/head of the list
            }
            updateHead(entry);
            entries.put(key, entry);
            size++;
        }
    }

    /**
     * @param key - to be deleted from the cache
     */
    @Override
    public void delete(K key) {
        // Check if the key is not present in the cache
        if (!entries.containsKey(key)) {
            throw new RuntimeException("Key is not present in the cache. Cannot delete!");
        }
        deleteNode(entries.get(key));
        entries.remove(key);
        size--;
    }

    /**
     * Empties the cache
     */
    @Override
    public void clear() {
        entries.clear();
        head = null;
        tail = null;
        size = 0;
    }

    private void updateHead(Entry<K, V> entry) {
        // Make current head as the next of the passed node
        entry.next = head;
        // Make previous of the passed node as null
        entry.previous = null;
        // If the current head is not null
        if (head != null) {
            head.previous = entry;
        }
        // Update the head
        head = entry;
        // If there is only one node
        if (tail == null) {
            tail = entry;
        }
    }

    private void deleteNode(Entry<K, V> entry) {
        // If the given entry is not the head
        if (entry.previous != null) {
            entry.previous.next = entry.next;
        }
        // If the given entry is the head
        else {
            head = entry.next;
        }
        // If the given entry is not the tail
        if (entry.next != null) {
            entry.next.previous = entry.previous;
        }
        // If the given entry is the tail
        else {
            tail = entry.previous;
        }
    }

    static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> previous;
        private Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
