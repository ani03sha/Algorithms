package org.redquark.algorithms.datastructures.maps;

import java.util.Collection;
import java.util.Set;

public interface Map<K, V> {

    /**
     * @return size of the map
     */
    int size();

    /**
     * @return true, if the map is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param key - key to be searched in the map
     * @return true, if the key is found, false otherwise
     */
    boolean containsKey(K key);

    /**
     * @param value - to be searched in the map
     * @return true, if the value is found, false otherwise
     */
    boolean containsValue(V value);

    /**
     * @param key - key for which the value is required
     * @return the value associated with the given key
     */
    V get(K key);

    /**
     * @param key   - key to be stored in the map
     * @param value - value corresponding to the given key
     */
    void put(K key, V value);

    /**
     * @param key - key which needs to be removed
     */
    void remove(K key);

    /**
     * @return set of all the keys present in the map
     */
    Set<K> keys();

    /**
     * @return all the values present in the map
     */
    Collection<V> values();

    /**
     * Empties the map by deleting all the elements
     */
    void clear();
}
