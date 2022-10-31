package org.redquark.algorithms.datastructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements List<T>, Iterable<T> {

    // Head of the linked list
    public ListNode<T> head;
    // Size of list
    private int size;

    public DoublyLinkedList() {
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
        return this.size == 0;
    }

    /**
     * @param element - element to be checked in the list
     * @return true, if the element is present in the list, false otherwise
     */
    @Override
    public boolean contains(T element) {
        // Check each node one by one
        ListNode<T> temp = head;
        while (temp != null) {
            if (temp.data.equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * @param element to be added in the list at the end
     */
    @Override
    public void add(T element) {
        // Create a new node with the given element
        ListNode<T> node = new ListNode<>(element);
        // Special case
        if (head == null) {
            head = node;
            size++;
            return;
        }
        // Reference of the head node
        ListNode<T> temp = head;
        // Loop until we reach to the end of the list
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.previous = temp;
        size++;
    }

    /**
     * @param element to be added at the front of the list
     */
    public void addFirst(T element) {
        // Create a new node with the given data
        ListNode<T> node = new ListNode<>(element);
        // Special case
        if (head == null) {
            head = node;
            size++;
            return;
        }
        node.next = head;
        head.previous = node;
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
        //Link the new node with previous of next node
        nextNode.previous = newNode;
        // Insert the given node in the list
        temp.next = newNode;
        newNode.previous = temp;
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
            head.previous = null;
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
            // Next node of given node
            temp.next = temp.next.next;
        }
        size--;
        return element;
    }

    /**
     * @return removed element from the last
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
        if (head != null) {
            head.previous = null;
        }
        size--;
        return data;
    }

    /**
     * Empties the list by removing all the elements from it
     */
    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    /**
     * @return Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            // Reference of the head
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
        ListNode<T> previous;
        ListNode<T> next;

        ListNode(T data) {
            this.data = data;
        }
    }
}
