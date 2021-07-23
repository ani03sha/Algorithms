package org.redquark.algorithms.datastructures.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {

    // Default capacity
    private static final int DEFAULT_CAPACITY = 16;
    // Default load factor
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    // Load factor of the map
    private final double loadFactor;
    // Capacity of the map
    private int capacity;
    // Size of the Map
    private int size;
    // Array to store the entries of map
    private Entry<K, V>[] entries;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity, double loadFactor) {
        this.size = 0;
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.entries = new Entry[capacity];
    }

    /**
     * @return size of the map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true, if the map is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param key - key to be searched in the map
     * @return true, if the key is found, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        // Special case
        if (isEmpty()) {
            return false;
        }
        // Find the hash for the given key
        int keyHash = getHash(key);
        // Get the entry object associated with the key hash
        Entry<K, V> entry = entries[keyHash];
        // Iterate over the list until we find the key
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    /**
     * @param value - to be searched in the map
     * @return true, if the value is found, false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        // Special case
        if (isEmpty()) {
            return false;
        }
        // Iterate over all the entries present in the map
        for (Entry<K, V> entry : entries) {
            while (entry != null) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    /**
     * @param key - key for which the value is required
     * @return the value associated with the given key
     */
    @Override
    public V get(K key) {
        // Resultant value
        V value = null;
        // Find the corresponding hash for the given key
        int keyHash = getHash(key);
        // Get the value associated with the given keyHash
        Entry<K, V> entry = entries[keyHash];
        // Iterate the linked list until we find the key
        while (entry != null) {
            if (entry.getKey() != null && entry.getKey().equals(key)) {
                value = entry.getValue();
            } else {
                value = entries[0].value;
            }
            entry = entry.next;
        }
        return value;
    }

    /**
     * @param key   - key to be stored in the map
     * @param value - value corresponding to the given key
     */
    @Override
    public void put(K key, V value) {
        // Get the hash of the given key
        int keyHash = getHash(key);
        // If there is no value at the index represented by the calculated
        // hash, store the value there
        if (entries[keyHash] == null) {
            // Check if the size of map has reached its load factor limit
            if (size == loadFactor * capacity) {
                ensureCapacity();
            }
            entries[keyHash] = new Entry<>(key, value);
            size++;
        }
        // Else we will have a hash collision, and we will have to create
        // a new node at the linked list
        else {
            // Previous node
            Entry<K, V> previous = null;
            Entry<K, V> current = entries[keyHash];
            // Loop until we reach the right position
            while (current != null) {
                // If the key is already present, we will update the value
                if (current.getKey().equals(key)) {
                    current.value = value;
                    break;
                }
                previous = current;
                current = current.next;
            }
            // Link the newly created node at the end of the list
            if (previous != null) {
                // Check if the size of map has reached its load factor limit
                if (size == loadFactor * capacity) {
                    ensureCapacity();
                }
                previous.next = new Entry<>(key, value);
                size++;
            }
        }
    }

    /**
     * @param key - key which needs to be removed
     */
    @Override
    public void remove(K key) {
        // If the key doesn't exist
        if (!containsKey(key)) {
            throw new IllegalArgumentException("Key is not present in the map. Cannot delete");
        }
        // Get the hash of the given key
        int keyHash = getHash(key);
        // Previous and current nodes
        Entry<K, V> previous = null;
        Entry<K, V> current = entries[keyHash];
        // Iterate of the current bucket
        while (current != null) {
            // Check if we have found the key
            if (current.getKey().equals(key)) {
                // If the node to be removed is the first node in the bucket
                if (previous == null) {
                    current = current.next;
                    entries[keyHash] = current;
                }
                // If the node to be removed is present isn't the first node
                else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    /**
     * @return set of all the keys present in the map
     */
    @Override
    public Set<K> keys() {
        // Set to store all the keys in the map
        Set<K> allKeys = new HashSet<>();
        // Base case
        if (isEmpty()) {
            return allKeys;
        }
        // Loop through all the entries in the map
        for (Entry<K, V> entry : entries) {
            if (entry != null) {
                allKeys.add(entry.getKey());
            }
        }
        return allKeys;
    }

    /**
     * @return all the values present in the map
     */
    @Override
    public Collection<V> values() {
        // Collection to store all the values in the map
        Collection<V> allValues = new HashSet<>();
        // Base case
        if (isEmpty()) {
            return allValues;
        }
        // Loop through all the entries in the map
        for (Entry<K, V> entry : entries) {
            if (entry != null) {
                allValues.add(entry.getValue());
            }
        }
        return allValues;
    }

    /**
     * Empties the map by deleting all the elements
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            entries[i] = null;
        }
        size = 0;
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        List<Entry<K, V>> pairs = new ArrayList<>();
        for (Entry<K, V> entry : entries) {
            if (entry != null) {
                findEntryByNext(entry, pairs);
            }
        }
        output.append("[");
        for (int i = 0; i < pairs.size() - 1; i++) {
            output.append(pairs.get(i).key).append(" = ").append(pairs.get(i).value).append(", ");
        }
        output.append(pairs.get(pairs.size() - 1).key).append(" = ").append(pairs.get(pairs.size() - 1).value).append("]");
        return output.toString();
    }

    private int getHash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % capacity);
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        // list to store all the key value pairs present in the map
        List<Entry<K, V>> pairs = new ArrayList<>();
        // Traverse the current array
        for (Entry<K, V> entry : entries) {
            // For non empty slots only
            if (entry != null) {
                findEntryByNext(entry, pairs);
                if (!pairs.isEmpty()) {
                    // Reset size
                    size = 0;
                    // Update the capacity
                    capacity *= 2;
                    // Update the entries array
                    entries = new Entry[capacity];
                    // Put all the entries stored in the list in the new table
                    for (Entry<K, V> pair : pairs) {
                        if (pair.next != null) {
                            // Set next pointers of all entries to null
                            pair.next = null;
                        }
                        // Rehash new table
                        put(pair.getKey(), pair.getValue());
                    }
                }
            }
        }
    }

    private void findEntryByNext(Entry<K, V> entry, List<Entry<K, V>> pairs) {
        if (entry != null && entry.next != null) {
            pairs.add(entry);
            findEntryByNext(entry.next, pairs);
        } else {
            pairs.add(entry);
        }
    }

    static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
