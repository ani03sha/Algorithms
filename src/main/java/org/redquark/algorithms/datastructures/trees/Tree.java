package org.redquark.algorithms.datastructures.trees;

import java.util.List;

public interface Tree<T> {

    /**
     * @return true, if the tree is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param element to be searched in the tree
     * @return true if the element is found, false otherwise
     */
    boolean contains(T element);

    /**
     * @return height of the tree
     */
    int height();

    /**
     * @return diameter of the tree
     */
    int diameter();

    /**
     * @param element to be inserted in the tree
     */
    void insert(T element);

    /**
     * @param element to be removed from the tree
     * @return removed element
     */
    T remove(T element);

    /**
     * @return preorder traversal of the tree
     */
    List<T> preorderTraversal();

    /**
     * @return inorder traversal of the tree
     */
    List<T> inorderTraversal();

    /**
     * @return postorder traversal of the tree
     */
    List<T> postorderTraversal();

    /**
     * @return level order traversal of the tree
     */
    List<List<T>> levelOrderTraversal();

    /**
     * @return left view of the tree
     */
    List<T> leftView();

    /**
     * @return right view of the tree
     */
    List<T> rightView();

    /**
     * @return top view of the tree
     */
    List<T> topView();

    /**
     * @return bottom view of the tree
     */
    List<T> bottomView();

    /**
     * Deletes all nodes from the tree
     */
    void clear();
}
