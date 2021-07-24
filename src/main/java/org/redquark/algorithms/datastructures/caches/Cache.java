package org.redquark.algorithms.datastructures.caches;

public interface Cache<K, V> {

    /**
     * @return size of the cache
     */
    int size();

    /**
     * @return true, if the cache is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param key - to be searched in the cache
     * @return true, if the key is cached, false otherwise
     */
    boolean has(K key);

    /**
     * @param key - to be searched in the cache
     * @return value associated with key
     */
    V get(K key);

    /**
     * @param key   - key of the object to be cached
     * @param value - value of the object to be cached
     */
    void set(K key, V value);

    /**
     * @param key - to be deleted from the cache
     */
    void delete(K key);

    /**
     * Empties the cache
     */
    void clear();
}
