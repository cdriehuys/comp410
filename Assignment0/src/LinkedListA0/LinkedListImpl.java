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

    /**
     * Get the node at the given index.
     * @param index The index to retrieve the node from.
     * @return The node at the given index if the index is in the list,
     *         {@code null} otherwise.
     */
    public Node get(int index) {
        if (isEmpty()) {
            return null;
        }

        int curIndex = 0;
        Node curNode = root.getNext();

        while (curIndex < index) {
            if (curNode.getNext() == null) {
                return null;
            }

            curNode = curNode.getNext();
            curIndex++;
        }

        return curNode;
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
        int size = size();

        if (index > size) {
            return false;
        }

        if (index == 0) {
            if (size == 0) {
                root.next = element;
                element.prev = root;

                return true;
            }

            Node next = get(index);

            root.next = element;
            element.prev = root;

            element.next = next;
            next.prev = element;

            return true;
        }

        if (index == size) {
            Node prev = get(index - 1);

            prev.next = element;
            element.prev = prev;

            return true;
        }

        Node prev = get(index - 1);
        Node next = get(index);

        prev.next = element;
        element.prev = prev;

        element.next = next;
        next.prev = element;

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
     * Remove the node at the given index.
     * @param index The 0-based index from which to remove the node
     *              from.
     * @return {@code true} if the node was successfully removed,
     *         {@code false} otherwise.
     */
    public boolean remove(int index) {
        Node toRemove = get(index);

        if (toRemove == null) {
            return false;
        }

        Node prev = toRemove.getPrev();
        Node next = toRemove.getNext();

        prev.next = next;

        if (next != null) {
            next.prev = prev;
        }

        return true;
    }

    /**
     * Get the number of items in the list.
     * @return The number of {@code Node} instances in the list.
     */
    public int size() {
        int size = 0;

        while (get(size) != null) {
            size++;
        }

        return size;
    }
}