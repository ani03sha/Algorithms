package org.redquark.algorithms.algorithms.string.matching;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt {

    public List<Integer> findAllOccurrences(String text, String pattern) {
        // List to store all occurrences
        List<Integer> occurrences = new ArrayList<>();
        // Special case
        if (text == null || text.isEmpty() || pattern == null || pattern.isEmpty()) {
            return occurrences;
        }
        // Lengths of the strings
        int textLength = text.length();
        int patternLength = pattern.length();
        // LPS array for pattern string
        int[] lps = new int[patternLength];
        // Populate the LPS array
        populateLPSArray(pattern, lps);
        // Indices for traversing through both text and pattern strings respectively
        int i = 0;
        int j = 0;
        // Loop through the text string
        while (i < textLength) {
            // If the current characters in the strings are same
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            // If we have traversed the pattern string completely
            if (j == patternLength) {
                occurrences.add(i - j);
                j = lps[j - 1];
            }
            // Mismatch after j matches
            else if (i < textLength && text.charAt(i) != pattern.charAt(j)) {
                // Skip lps[0...lps[j-1]] characters
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return occurrences;
    }

    private void populateLPSArray(String pattern, int[] lps) {
        // Variable to store the length of the longest string
        // with consecutive same characters
        int length = 0;
        // Variable to traverse through the string
        int index = 1;
        // First element of LPS array will always be zero
        lps[0] = 0;
        // Loop through the pattern string
        while (index < pattern.length()) {
            // If the consecutive characters are same
            if (pattern.charAt(length) == pattern.charAt(index)) {
                length++;
                lps[index] = length;
                index++;
            }
            // If the consecutive characters are not same
            else {
                // If the current value of length is not zero
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[index] = length;
                    index++;
                }
            }
        }
    }
}
