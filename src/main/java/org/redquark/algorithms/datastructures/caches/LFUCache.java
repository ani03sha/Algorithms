package org.redquark.algorithms.datastructures.caches;

import java.util.HashMap;
import java.util.Map;

public class LFUCache<K, V> implements Cache<K, V> {

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

    public LFUCache(int capacity) {
        this.size = 0;
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
        // Check if the key is present in the cache
        if (!has(key)) {
            return null;
        }
        // Get the entry object corresponding to the given key
        Entry<K, V> entry = entries.get(key);
        // Since this node is accessed, we will place it at its
        // correct place according to the frequency
        deleteNode(entry);
        entry.frequency++;
        addNode(entry);
        return entry.value;
    }

    /**
     * @param key   - key of the object to be cached
     * @param value - value of the object to be cached
     */
    @Override
    public void set(K key, V value) {
        // If the cache already has given key, we will update the value
        if (entries.containsKey(key)) {
            // Get the entry object corresponding to the given key
            Entry<K, V> entry = entries.get(key);
            // Update the value
            entry.value = value;
            // Update the frequency
            entry.frequency++;
            // Since this node is accessed, we will place it at its
            // correct place according to the frequency
            deleteNode(entry);
            addNode(entry);
        }
        // If this a new key, we will create a new entry in the cache
        // and evict the least frequently used entry, if the capacity
        // is full
        else {
            if (size >= capacity) {
                // Delete head with the least frequency and least recently used
                entries.remove(head.key);
                deleteNode(head);
            }
            // Add the new entry to the cache
            Entry<K, V> entry = new Entry<>(key, value);
            entry.frequency = 1;
            addNode(entry);
            entries.put(key, entry);
            size++;
        }
    }

    /**
     * @param key - to be deleted from the cache
     */
    @Override
    public void delete(K key) {
        // Check if the cache is not empty
        if (!has(key)) {
            throw new RuntimeException("Cache doesn't have specified key. Cannot delete!");
        }
        // Get the entry object to be deleted
        Entry<K, V> entry = entries.get(key);
        // Remove the entry from the cache
        deleteNode(entry);
        // Remove the corresponding key from the cache
        entries.remove(entry.key);
        // Update the size
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


    private void addNode(Entry<K, V> entry) {
        // If the cache is empty
        if (isEmpty()) {
            head = entry;
            tail = entry;
        }
        // If there are entries in the cache, we need
        // to add the entry at the correct position
        // based on the frequency
        else {
            // Get the reference of the head
            Entry<K, V> temp = head;
            // Loop through the entries
            while (temp != null) {
                // Compare the frequencies
                if (temp.frequency > entry.frequency) {
                    if (temp == head) {
                        entry.next = temp;
                        temp.previous = entry;
                        // Update the head
                        head = temp;
                        break;
                    } else {
                        entry.next = temp;
                        entry.previous = temp.previous;
                        temp.previous.next = entry;
                        break;
                    }
                } else {
                    temp = temp.next;
                    if (temp == null) {
                        tail.next = entry;
                        entry.previous = tail;
                        entry.next = null;
                        tail = entry;
                        break;
                    }
                }
            }
        }
    }

    private void deleteNode(Entry<K, V> entry) {
        // If this entry is head
        if (entry.previous == null) {
            head = entry.next;
        }
        // If this entry is not head
        else {
            entry.previous.next = entry.next;
        }
        // If this entry is tail
        if (entry.next == null) {
            tail = entry.previous;
        }
        // If this entry is not tail
        else {
            entry.next.previous = entry.previous;
        }
    }

    static class Entry<K, V> {
        private final K key;
        private V value;
        private int frequency;
        private Entry<K, V> previous;
        private Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
