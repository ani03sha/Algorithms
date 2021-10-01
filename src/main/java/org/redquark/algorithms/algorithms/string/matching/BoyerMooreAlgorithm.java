package org.redquark.algorithms.algorithms.string.matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoyerMooreAlgorithm {

    public List<Integer> findAllOccurrences(String text, String pattern) {
        // List to store the starting indices of all occurrences
        // of pattern string in the text string
        List<Integer> occurrences = new ArrayList<>();
        // Special case
        if (text == null || text.isEmpty() || pattern == null || pattern.isEmpty()) {
            return occurrences;
        }
        // Lengths of the strings
        int textLength = text.length();
        int patternLength = pattern.length();
        // Array to store preprocessed values from bad character heuristic
        int[] badCharacters = new int[256];
        // Populate the array with default value -1
        Arrays.fill(badCharacters, -1);
        // Update the array for pattern string
        for (int i = 0; i < patternLength; i++) {
            badCharacters[pattern.charAt(i)] = i;
        }
        // Index to traverse the text
        int index = 0;
        // Loop through the text string
        while (index <= textLength - patternLength) {
            // Index to traverse the text with patternLength window
            int i = patternLength - 1;
            // As long as characters are matching, keep reducing indices
            while (i >= 0 && pattern.charAt(i) == text.charAt(index + i)) {
                i--;
            }
            // If the pattern is present at the current index, then i will
            // become -1 at this position
            if (i == -1) {
                occurrences.add(index);
                // Shift the pattern
                index += (index + patternLength < textLength) ? patternLength - badCharacters[text.charAt(index + patternLength)] : 1;
            }
            // Shift the pattern further
            else {
                index += Math.max(1, i - badCharacters[text.charAt(index + i)]);
            }
        }
        return occurrences;
    }
}
