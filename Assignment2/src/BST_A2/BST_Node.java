package BST_A2;

/**
 * Represents a node in a Binary Search Tree. It stores a single piece
 * of data, and contains most of the logic for the tree.
 */
public class BST_Node {
    String data;
    BST_Node left;
    BST_Node right;

    /**
     * Create a new node.
     * @param data The data to store in this node.
     */
    public BST_Node(String data) {
        this.data=data;
    }

    /**
     * Check if a value is contained within or under the current node.
     * @param s The value to check for.
     * @return {@code true} if {@code s} is equivalent to the current
     *         node's data, or if it is contained in the children of
     *         the current node. Returns {@code false} otherwise.
     */
    public boolean containsNode(String s) {
        if (data.equals(s)) {
            return true;
        }

        if (s.compareTo(data) < 0) {
            return left != null && left.containsNode(s);
        } else {
            return right != null && right.containsNode(s);
        }
    }

    /**
     * Find the maximum value in the tree that has the current node as
     * the root node.
     * @return The right-most leaf of the tree with this node as the
     *         root.
     */
    public BST_Node findMax() {
        return right == null ? this : right.findMax();
    }

    /**
     * Find the minimum value in the tree that has the current node as
     * the root node.
     * @return The left-most leaf of the tree with this node as the
     *         root.
     */
    public BST_Node findMin() {
        return left == null ? this : left.findMin();
    }

    /**
     * Get the data stored in this node.
     * @return The string stored in this node.
     */
    public String getData() {
        return data;
    }

    /**
     * Find the height of the tree that has the current node as the
     * root node.
     * @return The maximum edge-length from the current node to a leaf.
     */
    public int getHeight() {
        if (left != null && right != null) {
            return 1 + Math.max(left.getHeight(), right.getHeight());
        } else if (left != null) {
            return 1 + left.getHeight();
        } else if (right != null) {
            return 1 + right.getHeight();
        }

        return 0;
    }

    /**
     * Get the left child of this node.
     * @return The left child of this node.
     */
    public BST_Node getLeft() {
        return left;
    }

    /**
     * Get the right child of this node.
     * @return The right child of this node.
     */
    public BST_Node getRight() {
        return right;
    }

    public String inOrder() {
        String ret;

        if (left != null) {
            ret = left.inOrder();
        } else {
            ret = "";
        }

        if (!ret.isEmpty()) {
            ret += ", ";
        }

        ret += data;

        if (right != null) {
            String rightData = right.inOrder();

            if (!rightData.isEmpty()) {
                ret += ", ";
            }

            ret += rightData;
        }

        return ret;
    }

    /**
     * Insert a node as a child of this node.
     *
     * The node should be successfully inserted unless it is equivalent
     * to a node that is already present.
     * @param node The node to insert.
     * @return {@code true} if the insert succeeds, {@code false}
     *         otherwise.
     */
    public boolean insertNode(BST_Node node) {
        // If inserting duplicated data, return false
        if (node.getData().equals(data)) {
            return false;
        }

        if (node.getData().compareTo(data) < 0) {
            if (left == null) {
                left = node;

                return true;
            }

            return left.insertNode(node);
        }

        if (right == null) {
            right = node;

            return true;
        }

        return right.insertNode(node);
    }

    /**
     * Remove the node with the given data from the tree with the
     * current node as the root node.
     * @param s The data to search for.
     * @return {@code} true if the node was removed, {@code false}
     *         otherwise.
     */
    public boolean removeNode(String s) {
        return (s.compareTo(data) < 0) ? removeLeft(s) : removeRight(s);
    }

    /**
     * Set the left child of the current node.
     * @param node The node to set as the left child of the current
     *             node.
     */
    public void setLeft(BST_Node node) {
        left = node;
    }

    /**
     * Set the right child of the current node.
     * @param node The node to set as the right child of the current
     *             node.
     */
    public void setRight(BST_Node node) {
        right = node;
    }

    /**
     * Get a string representation of the node.
     * @return A string containing information about the node.
     */
    public String toString(){
        return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
                +",Right: "+((this.right!=null)?right.data:"null");
    }


    // -------------------- BEGIN PRIVATE METHODS ------------------ //


    /**
     * Remove a value from the tree with this node's left child as the
     * root.
     * @param s The value to remove.
     * @return {@code true} if the removal was successful,
     *         {@code false} otherwise.
     */
    private boolean removeLeft(String s) {
        if (left == null) {
            return false;
        }

        if (s.equals(left.getData())) {
            if (left.left == null && left.right == null) {
                left = null;
            } else if (left.right != null) {
                if (left.left == null) {
                    left = left.right;

                    return true;
                }

                BST_Node min = left.right.findMin();
                left.right.removeNode(min.getData());

                min.left = left.left;
                min.right = left.right == min ? null : left.right;

                left = min;
            } else {
                left = left.left;
            }

            return true;
        }

        return left.removeNode(s);
    }

    /**
     * Remove a value from the tree with this node's right child as the
     * root.
     * @param s The value to remove.
     * @return {@code true} if the removal was successful,
     *         {@code false} otherwise.
     */
    private boolean removeRight(String s) {
        if (right == null) {
            return false;
        }

        if (s.equals(right.getData())) {
            if (right.left == null && right.right == null) {
                right = null;
            } else if (right.left != null) {
                if (right.right == null) {
                    right = right.left;

                    return true;
                }

                BST_Node max = right.left.findMax();
                right.left.removeNode(max.getData());

                max.left = right.left == max ? null : right.left;
                max.right = right.right;

                right = max;
            } else {
                right = right.right;
            }

            return true;
        }

        return right.removeNode(s);
    }
}
