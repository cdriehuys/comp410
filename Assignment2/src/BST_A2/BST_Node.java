package BST_A2;

/**
 * Created by chathan on 9/15/16.
 */

public class BST_Node {
    String data;
    BST_Node left;
    BST_Node right;

    public BST_Node(String data){ this.data=data; }

    // --- used for testing  ----------------------------------------------
    //
    // leave these 3 methods in, as is

    public String getData(){ return data; }
    public BST_Node getLeft(){ return left; }
    public BST_Node getRight(){ return right; }

    // --- end used for testing -------------------------------------------

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
        if (s.compareTo(data) < 0) {
            if (left == null) {
                return false;
            }

            if (s.equals(left.getData())) {
                if (left.left == null && left.right == null) {
                    left = null;
                } else if (left.right != null) {
                    BST_Node min = left.right.findMin();
                    left.right.removeNode(min.getData());

                    min.left = left.left;
                    min.right = left.right;

                    left = min;
                } else {
                    left = left.left;
                }

                return true;
            }

            return false;
        }

        if (s.compareTo(data) > 0) {
            if (right == null) {
                return false;
            }

            if (s.equals(right.getData())) {
                if (right.left == null && right.right == null) {
                    right = null;
                } else if (right.left != null) {
                    BST_Node min = right.left.findMin();
                    right.left.removeNode(min.getData());

                    min.left = right.left;
                    min.right = right.right;

                    right = min;
                } else {
                    right = right.right;
                }

                return true;
            }

            return false;
        }

        return false;
    }

    public void setLeft(BST_Node node) {
        left = node;
    }

    public void setRight(BST_Node node) {
        right = node;
    }

    // --- end fill in these methods --------------------------------------


    // --------------------------------------------------------------------
    // you may add any other methods you want to get the job done
    // --------------------------------------------------------------------

    public String toString(){
        return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
                +",Right: "+((this.right!=null)?right.data:"null");
    }
}
