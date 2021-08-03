package org.redquark.algorithms.datastructures.lists;

import java.util.Iterator;

public class CircularLinkedList<T> implements List<T>, Iterable<T> {

    // Head of the list
    public ListNode<T> head;
    // Tail of the linked list
    public ListNode<T> tail;
    // Size of the linked list
    private int size;

    public CircularLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
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
        // Base case
        if (head == null) {
            return false;
        } else {
            // Reference to the head
            ListNode<T> temp = head;
            do {
                if (temp.data.equals(element)) {
                    return true;
                }
                temp = temp.next;
            } while (temp != head);
        }
        return false;
    }

    /**
     * @param element to be added in the list at the last position
     */
    @Override
    public void add(T element) {
        // Create a new node with the given data
        ListNode<T> node = new ListNode<>(element);
        // For empty list
        if (head == null) {
            head = node;
            tail = node;
            tail.next = head;
            size++;
            return;
        }
        tail.next = node;
        tail = node;
        tail.next = head;
        size++;
    }

    /**
     * @param element          to be added
     * @param elementInTheList element after which we need to add
     */
    public void addAfterNode(T element, T elementInTheList) {
        // First check if the given node exists in the list or not
        if (!contains(elementInTheList)) {
            throw new IllegalArgumentException("Given node doesn't exist in the list");
        }
        // Reference of the first node
        ListNode<T> temp = tail.next;
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
            size--;
            return element;
        }

        if(tail.data == element) {
            //Find reference to element prior to tail
            ListNode<T> temp = head;
            while(temp.next!=tail) {
                temp = temp.next;
            }

            T data = tail.data;
            tail = temp;
            tail.next = head;
            size--;
            return data;
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
     * Empties the list by removing all the elements from it
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * @return Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            // Reference of the first node
            ListNode<T> current = tail.next;

            @Override
            public boolean hasNext() {
                return current != tail;
            }

            @Override
            public T next() {
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
        ListNode<T> temp = tail.next;
        while (temp.next != tail) {
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
