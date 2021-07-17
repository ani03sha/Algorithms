package org.redquark.algorithms.datastructures.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrieImpl implements Trie {

    // Root of the Trie
    private final TrieNode root;

    TrieImpl() {
        // Create root
        this.root = new TrieNode('\u0000');
    }

    /**
     * @return true, if trie is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root.children == null || root.children.isEmpty();
    }

    /**
     * @param word to be inserted in the Trie
     */
    @Override
    public void insert(String word) {
        // Check if the word is already in the trie
        if (search(word)) {
            return;
        }
        // Reference of the root node
        TrieNode temp = root;
        // Traverse the word character by character
        for (char c : word.toCharArray()) {
            // Get the child of the current character
            TrieNode child = temp.getChild(c);
            // If the child is not null, we will move to the child
            // of the current temp
            if (child != null) {
                temp = child;
            }
            // If there is not child, then we will add current character
            // as the new node
            else {
                temp.children.add(new TrieNode(c));
                temp = temp.getChild(c);
            }
            temp.count++;
        }
        // Set last node as the leaf node
        temp.isLeaf = true;
    }

    /**
     * @param word to be removed from the trie
     * @return the removed key
     */
    @Override
    public boolean remove(String word) {
        // Check if the word is present in the trie
        if (search(word)) {
            // Get the reference of the root node
            TrieNode temp = root;
            // Remove every character in the word iteratively
            for (char c : word.toCharArray()) {
                // Get the child of the current node
                TrieNode child = temp.getChild(c);
                // If there is only one character in the child
                if (child.count == 1) {
                    temp.children.remove(child);
                    return true;
                } else {
                    child.count--;
                    temp = child;
                }
            }
        }
        return false;
    }

    /**
     * @param word to be searched
     * @return true if the key is found, false otherwise
     */
    @Override
    public boolean search(String word) {
        // Check if the trie is empty
        if (isEmpty()) {
            return false;
        }
        // Reference of the root node
        TrieNode temp = root;
        // Search in the trie character by character
        for (char c : word.toCharArray()) {
            // If the char is not found in the trie node
            if (temp.getChild(c) == null) {
                return false;
            }
            // If found, move to its child
            else {
                temp = temp.getChild(c);
            }
        }
        // If we reach to the leaf node while searching, it means
        // we have found the word
        return temp.isLeaf;
    }

    /**
     * Empties the trie by deleting all nodes
     */
    @Override
    public void clear() {
        root.count = 0;
        root.children = null;
        root.isLeaf = true;
    }

    @Override
    public List<String> getAllWords() {
        // List to store all words present in the trie
        List<String> allWords = new ArrayList<>();
        // String builder to represent one word
        getAllWords(root, "", allWords);
        return allWords;
    }

    private void getAllWords(TrieNode node, String word, List<String> allWords) {
        // If there are no children present to traverse
        if (node.children == null || node.children.isEmpty()) {
            return;
        }
        // Get the iterator of the current child list
        // Loop through the list of nodes
        for (TrieNode current : node.children) {
            // Get the next node
            word += current.data;
            getAllWords(current, word, allWords);
            // If we have reached the end
            if (current.isLeaf) {
                // Add the word to the output list
                allWords.add(word);
            }
            word = word.substring(0, word.length() - 1);
        }
    }

    static class TrieNode {
        private final char data;
        private boolean isLeaf;
        private int count;
        private List<TrieNode> children;

        TrieNode(char data) {
            this.data = data;
            this.isLeaf = false;
            this.count = 0;
            this.children = new LinkedList<>();
        }

        public TrieNode getChild(char c) {
            for (TrieNode child : children) {
                if (child.data == c) {
                    return child;
                }
            }
            return null;
        }
    }
}
