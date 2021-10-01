package org.redquark.algorithms.algorithms.string.matching;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {

    public List<Integer> findAllOccurrences(String text, String pattern) {
        // List to store the indices where pattern is found
        List<Integer> occurrences = new ArrayList<>();
        // Special case
        if (text == null || text.isEmpty() || pattern == null || pattern.isEmpty()) {
            return occurrences;
        }
        // Number of characters in the input strings
        final int d = 256;
        // A prime number to find modulus
        final int q = 257;
        // lengths of the pattern and text respectively
        int m = pattern.length();
        int n = text.length();
        // Hash value for pattern and text
        int p = 0;
        int t = 0;
        // Exponential term - d ^ (m - 1)
        int h = 1;
        // Calculate h
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }
        // Calculate the hash value for pattern and text for first window
        for (int i = 0; i < m; i++) {
            p = (p * d + pattern.charAt(i)) % q;
            t = (t * d + text.charAt(i)) % q;
        }
        // Now move the window one character at a time
        for (int i = 0; i <= n - m; i++) {
            // Check if the hash values calculated before are matching
            if (p == t) {
                int j;
                // Compare character by character
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }
                // If all the characters matched then we have found the occurrence
                if (j == m) {
                    occurrences.add(i);
                }
            }
            // Calculate hash value for the next window
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
                // Handle negative hash value
                if (t < 0) {
                    t += q;
                }
            }
        }
        return occurrences;
    }
}
