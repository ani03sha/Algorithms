package org.redquark.algorithms.datastructures.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinarySearchTreeTest {

    private final BinarySearchTree<Integer> testBST = new BinarySearchTree<>();

    @BeforeEach
    public void setUp() {
        int[] elements = {5, 7, 1, 6, 3, 9, 2, 8, 4, 0};
        for (int element : elements) {
            testBST.insert(element);
        }
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testBST.isEmpty());
        testBST.clear();
        assertTrue(testBST.isEmpty());
    }

    @Test
    public void testContains() {
        for (int i = 0; i <= 9; i++) {
            assertTrue(testBST.contains(i));
        }
        assertFalse(testBST.contains(10));
        assertFalse(testBST.contains(14));
    }

    @Test
    public void testHeight() {
        assertEquals(4, testBST.height());
        testBST.remove(3);
        assertEquals(4, testBST.height());
        testBST.remove(8);
        testBST.remove(4);
        assertEquals(3, testBST.height());
        testBST.clear();
        assertEquals(0, testBST.height());
    }

    @Test
    public void testDiameter() {
        assertEquals(7, testBST.diameter());
        testBST.remove(9);
        assertEquals(6, testBST.diameter());
        testBST.clear();
        assertEquals(0, testBST.diameter());
    }

    @Test
    public void testInsert() {
        testBST.insert(11);
        assertTrue(testBST.contains(11));
        assertThrows(IllegalArgumentException.class, () -> testBST.insert(4));
    }

    @Test
    public void testRemove() {
        testBST.remove(3);
        testBST.remove(4);
        testBST.remove(9);
        testBST.remove(8);
        assertEquals(3, testBST.height());
        testBST.remove(1);
        testBST.remove(7);
        testBST.insert(13);
        testBST.insert(17);
        testBST.insert(15);
        testBST.insert(14);
        testBST.remove(13);
        testBST.clear();
        assertThrows(RuntimeException.class, () -> testBST.remove(1));
    }

    @Test
    public void testPreorderTraversal() {
        List<Integer> expected = Arrays.asList(5, 1, 0, 3, 2, 4, 7, 6, 9, 8);
        assertEquals(expected, testBST.preorderTraversal());
        testBST.remove(4);
        expected = Arrays.asList(5, 1, 0, 3, 2, 7, 6, 9, 8);
        assertEquals(expected, testBST.preorderTraversal());
        testBST.clear();
        assertEquals(Collections.emptyList(), testBST.preorderTraversal());
    }

    @Test
    public void testInorderTraversal() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(expected, testBST.inorderTraversal());
        testBST.remove(4);
        expected = Arrays.asList(0, 1, 2, 3, 5, 6, 7, 8, 9);
        assertEquals(expected, testBST.inorderTraversal());
        testBST.clear();
        assertEquals(Collections.emptyList(), testBST.inorderTraversal());
    }

    @Test
    public void testPostorderTraversal() {
        List<Integer> expected = Arrays.asList(0, 2, 4, 3, 1, 6, 8, 9, 7, 5);
        assertEquals(expected, testBST.postorderTraversal());
        testBST.remove(4);
        expected = Arrays.asList(0, 2, 3, 1, 6, 8, 9, 7, 5);
        assertEquals(expected, testBST.postorderTraversal());
        testBST.clear();
        assertEquals(Collections.emptyList(), testBST.postorderTraversal());
    }

    @Test
    public void testLevelOrderTraversal() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Collections.singletonList(5));
        expected.add(Arrays.asList(1, 7));
        expected.add(Arrays.asList(0, 3, 6, 9));
        expected.add(Arrays.asList(2, 4, 8));
        assertEquals(expected, testBST.levelOrderTraversal());
        testBST.clear();
        assertEquals(Collections.emptyList(), testBST.levelOrderTraversal());
    }

    @Test
    public void testLeftView() {
        List<Integer> expected = Arrays.asList(5, 1, 0, 2);
        assertEquals(expected, testBST.leftView());
        testBST.clear();
        assertEquals(Collections.emptyList(), testBST.leftView());
    }

    @Test
    public void testRightView() {
        List<Integer> expected = Arrays.asList(5, 7, 9, 8);
        assertEquals(expected, testBST.rightView());
        testBST.clear();
        assertEquals(Collections.emptyList(), testBST.rightView());
    }

    @Test
    public void testTopView() {
        List<Integer> expected = Arrays.asList(0, 1, 5, 7, 9);
        assertEquals(expected, testBST.topView());
        testBST.clear();
        assertEquals(Collections.emptyList(), testBST.topView());
    }

    @Test
    public void testBottomView() {
        List<Integer> expected = Arrays.asList(0, 2, 6, 8, 9);
        assertEquals(expected, testBST.bottomView());
        testBST.clear();
        assertEquals(Collections.emptyList(), testBST.bottomView());
    }
}