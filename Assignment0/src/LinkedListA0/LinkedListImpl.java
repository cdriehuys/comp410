/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
 */
package LinkedListA0;

public class LinkedListImpl implements LIST_Interface {
    // Entry point for the linked list
    Node root;


    public LinkedListImpl() {
        // Populate root node with dummy data
        root = new Node(0);
    }

    /**
     * Clear the list.
     *
     * To clear the list, the {@code next} attribute of the root
     * node is set to {@code null}.
     */
    public void clear() {
        root.next = null;
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Insert a new {@code Node} in the list.
     * @param element The new {@code Node} to insert.
     * @param index The index to insert the new data at.
     * @return {@code true} if the data was successfully inserted,
     *         {@code false} otherwise.
     */
    public boolean insert(Node element, int index) {
        if (index > size()) {
            return false;
        }

        int curIndex = 0;
        Node curNode = root;

        while (curIndex < index) {
            curIndex++;
            curNode = curNode.getNext();
        }

        Node next = curNode.getNext();
        curNode.next = element;

        if (next != null) {
            element.next = next;
        }

        return true;
    }

    /**
     * Determine if the list is empty.
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return root.getNext() == null;
    }

    /**
     * Get the number of items in the list.
     * @return The number of {@code Node} instances in the list.
     */
    public int size() {
        Node current = root;
        int size = 0;

        while (current.getNext() != null) {
            size++;
            current = current.getNext();
        }

        return size;
    }
}