package org.redquark.algorithms.datastructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T>, Iterable<T> {

    public ListNode<T> head;
    private int size;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    /**
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return true, if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element - element to be checked in the list
     * @return true, if the element is present in the list, false otherwise
     */
    @Override
    public boolean contains(T element) {
        // Reference to the head
        ListNode<T> temp = head;
        // Loop through the linked list to check each element
        while (temp != null) {
            if (temp.data.equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * @param element to be added in the list at the last
     */
    @Override
    public void add(T element) {
        // Create a new node with the given data
        ListNode<T> node = new ListNode<>(element);
        // Base case when head is null
        if (head == null) {
            head = node;
            size++;
            return;
        }
        // Reference to the head
        ListNode<T> temp = head;
        // Loop through the linked list to get the last element
        while (temp.next != null) {
            temp = temp.next;
        }
        // Make the next of last node as the new node
        temp.next = node;
        size++;
    }

    /**
     * @param element to be added at the front of list
     */
    public void addFirst(T element) {
        // Create a new node with the given data
        ListNode<T> node = new ListNode<>(element);
        // Make the current head as the next of this node
        node.next = head;
        // Make this node as the new head
        head = node;
        size++;
    }

    /**
     * @param element          to be added
     * @param elementInTheList after which the element is to be added
     */
    public void addAfterNode(T element, T elementInTheList) {
        // First check if the given node exists in the list or not
        if (!contains(elementInTheList)) {
            throw new IllegalArgumentException("Given node doesn't exist in the list");
        }
        // Get the reference of the given node
        ListNode<T> temp = head;
        while (temp.next != null) {
            if (temp.data.equals(elementInTheList)) {
                break;
            }
            temp = temp.next;
        }
        // Create a new node with the given data
        ListNode<T> newNode = new ListNode<>(element);
        // Get the next element of this node
        ListNode<T> nextNode = temp.next;
        // Insert the given node in the list
        temp.next = newNode;
        // Link this node with the previous next node
        newNode.next = nextNode;
        size++;
    }

    /**
     * @param element element to be removed from the list
     * @return removed element
     */
    @Override
    public T remove(T element) {
        // Check if the node to be removed exists in the list
        if (!contains(element)) {
            throw new IllegalArgumentException("Given node doesn't exist in the list");
        }
        // For head
        if (head.data.equals(element)) {
            head = head.next;
            return element;
        }
        // Find the reference of the given node in the list
        ListNode<T> temp = head;
        while (temp.next != null) {
            if (temp.next.data.equals(element)) {
                break;
            }
            temp = temp.next;
        }
        // Remove the node by skipping it
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
        size--;
        return element;
    }

    /**
     * @return last element which will be removed
     */
    public T removeLast() {
        // Base case
        if (head == null) {
            throw new IllegalArgumentException("Cannot remove from empty list");
        }
        if (head.next == null) {
            T data = head.data;
            head = null;
            size--;
            return data;
        }
        // Traverse the list to go to the lost
        ListNode<T> temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        T data = temp.next.data;
        temp.next = null;
        size--;
        return data;

    }

    /**
     * @return removed element from the list from the front
     */
    public T removeFirst() {
        // Base case
        if (head == null) {
            throw new IllegalArgumentException("Cannot remove from the empty list");
        }
        // Get the data at head
        T data = head.data;
        // Move head to the next pointer
        head = head.next;
        size--;
        return data;
    }

    /**
     * Empties the list by removing all the elements from it
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * @return Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            ListNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                // Data from the current node
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[");
        // Reference of the head
        ListNode<T> temp = head;
        while (temp.next != null) {
            output.append(temp.data).append(", ");
            temp = temp.next;
        }
        output.append(temp.data).append("]");
        return output.toString();
    }

    static class ListNode<T> {

        T data;
        ListNode<T> next;

        ListNode(T data) {
            this.data = data;
        }
    }
}
