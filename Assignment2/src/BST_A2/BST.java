package BST_A2;

/**
 * This is an implementation of a Binary Search Tree. It stores data,
 * in this case strings, as nodes.
 */
public class BST implements BST_Interface {
    public BST_Node root;
    int size;

    /**
     * Create a new Binary Search Tree.
     */
    public BST() {
        size=0;
        root=null;
    }

    /**
     * Determine if the tree contains a particular value.
     * @param s The value to search for.
     * @return {@code true} if the value is in the tree, {@code false}
     *         otherwise.
     */
    @Override
    public boolean contains(String s) {
        return root != null && root.containsNode(s);
    }

    /**
     * Determine if the tree is empty.
     * @return {@code true} if the root is {@code null}, {@code false}
     *         otherwise.
     */
    @Override
    public boolean empty() {
        return root == null;
    }

    /**
     * Find the maximum value in the tree.
     * @return The largest value in the tree. Returns {@code null} if
     *         the root is {@code null}.
     */
    @Override
    public String findMax() {
        return root == null ? null : root.findMax().getData();
    }

    /**
     * Find the minimum value in the tree.
     * @return The smallest value in the tree. Returns {@code null} if
     *         the root is {@code null}.
     */
    @Override
    public String findMin() {
        return root == null ? null : root.findMin().getData();
    }

    /**
     * Get the root node of the tree.
     * @return The root node of the tree.
     */
    @Override
    public BST_Node getRoot() {
        return root;
    }

    /**
     * Get the height of the tree.
     * @return The maximum number of edges between the root node and a
     *         leaf node. If the root node is {@code null}, returns -1.
     */
    @Override
    public int height() {
        return root == null ? -1 : root.getHeight();
    }

    /**
     * Insert a new value into the tree.
     * @param s The value to insert.
     * @return {@code true} if the insert was successful, {@code false}
     *         otherwise.
     */
    @Override
    public boolean insert(String s) {
        BST_Node newNode = new BST_Node(s);

        if (root == null) {
            root = newNode;
            size++;

            return true;
        }

        boolean success = root.insertNode(newNode);

        if (success) {
            size++;
        }

        return success;
    }

    /**
     * Remove a value from the tree.
     * @param s The value to remove.
     * @return {@code true} if the remove was successful, {@code false}
     *         otherwise.
     */
    @Override
    public boolean remove(String s) {
        if (root == null) {
            return false;
        }

        if (s.equals(root.getData())) {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left != null) {
                if (root.right == null) {
                    root = root.left;
                    size--;

                    return true;
                }

                BST_Node max = root.left.findMax();
                root.left.removeNode(max.getData());

                max.left = root.left == max ? null : root.left;
                max.right = root.right;

                root = max;
            } else {
                root = root.right;
            }

            size--;

            return true;
        }
/*
        if (s.equals(root.getData())) {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left != null) {
                if (root.right == null) {
                    root = root.left;
                    size--;

                    return true;
                }

                BST_Node max = root.left.findMax();
                root.left.removeNode(max.getData());

                max.left = root.left;
                max.right = root.right;

                root = max;
            } else {
                BST_Node min = root.right.findMin();
                root.right.removeNode(min.getData());

                min.left = root.left;
                min.right = root.right;

                root = min;
            }

            size--;

            return true;
        }
*/
        boolean success = root.removeNode(s);

        if (success) {
            size--;
        }

        return success;
    }

    /**
     * Get the size of the tree.
     * @return The number of nodes in the tree.
     */
    @Override
    public int size() {
        return size;
    }
}
