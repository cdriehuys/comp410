package LinkedListA0;

/**
 * The {@code LinkedListImpl} class is an implementation of a linked
 * list. It is comprised of {@link Node Node} instances that store the
 * actual data.
 */
public class LinkedListImpl implements LIST_Interface {
    Node root;

    /**
     * Create a new linked list.
     *
     * The beginning of the list is marked by the sentinel {@code Node},
     * which can be retrieved with the {@link #getRoot() getRoot}
     * method.
     */
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
     * Get the {@code Node} at the given index.
     * @param index The index of the data to retrieve from the list.
     * @return The {@code Node} at the given index if the index is in
     *         the list, {@code null} otherwise.
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

    /**
     * Get the sentinel {@code Node} for the list.
     *
     * This {@code Node} marks the beginning of the list.
     * @return The root {@code Node}.
     */
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
     *
     * A list is defined as empty if the {@code next} attribute of the
     * sentinel {@code Node} is {@code null}.
     * @return {@code true} if the list is empty, {@code false}
     *         otherwise.
     */
    public boolean isEmpty() {
        return root.getNext() == null;
    }

    /**
     * Remove the {@code Node} at the given index.
     * @param index The index from which to remove the {@code Node}.
     * @return {@code true} if the {@code Node} was successfully removed,
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
