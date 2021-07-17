package org.redquark.algorithms.datastructures.trie;

import java.util.List;

public interface Trie {

    /**
     * @return true, if trie is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param word to be inserted in the Trie
     */
    void insert(String word);

    /**
     * @param word to be removed from the trie
     * @return true, if the word is removed, false otherwise
     */
    boolean remove(String word);

    /**
     * @param word to be searched
     * @return true if the key is found, false otherwise
     */
    boolean search(String word);

    /**
     * Empties the trie by deleting all nodes
     */
    void clear();

    List<String> getAllWords();
}
