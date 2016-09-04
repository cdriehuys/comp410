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