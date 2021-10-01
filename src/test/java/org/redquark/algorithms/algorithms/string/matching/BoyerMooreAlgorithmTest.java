package org.redquark.algorithms.algorithms.string.matching;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoyerMooreAlgorithmTest {

    private final BoyerMooreAlgorithm testBoyerMoore = new BoyerMooreAlgorithm();

    @Test
    public void testFindAllOccurrences() {
        String text = "ABCCDDAEFG";
        String pattern = "CDD";
        List<Integer> expected = Collections.singletonList(3);
        assertEquals(expected, testBoyerMoore.findAllOccurrences(text, pattern));

        text = "";
        expected = Collections.emptyList();
        assertEquals(expected, testBoyerMoore.findAllOccurrences(text, null));

        text = "123456";
        pattern = "678";
        assertEquals(expected, testBoyerMoore.findAllOccurrences(text, pattern));

        text = "aaaa";
        pattern = "aa";
        expected = Arrays.asList(0, 1, 2);
        assertEquals(expected, testBoyerMoore.findAllOccurrences(text, pattern));

        text = "AABAACAADAABAABA";
        pattern = "AABA";
        expected = Arrays.asList(0, 9, 12);
        assertEquals(expected, testBoyerMoore.findAllOccurrences(text, pattern));
    }
}