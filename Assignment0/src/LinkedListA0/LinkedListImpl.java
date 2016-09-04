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

    public Node getRoot() {
        return root;
    }
}