package SPLT_A4;

/**
 * This class is responsible for storing the data in a binary tree.
 */
public class BST_Node {
    // These variables are public for testing purposes
    BST_Node left;
    BST_Node right;

    String data;

    private boolean justMade;

    private BST_Node parent;

    /**
     * Create a new BST_Node to store the given data.
     * @param data The data to store in the node.
     */
    BST_Node(String data){
        this.data = data;
        justMade = true;
    }

    /**
     * Search for a node with the given value in the subtree rooted at this node.
     * @param s The data to search for.
     * @return The node with the data matching what was given if it exists. Otherwise, the last searched node is
     *         returned.
     */
    public BST_Node containsNode(String s) {
        int comparison = data.compareTo(s);

        if(comparison == 0) {
            return this;
        }

        if(comparison > 0) {
            if (left==null) {
                return this;
            }

            return left.containsNode(s);
        }

        if (comparison < 0) {
            if (right==null) {
                return this;
            }

            return right.containsNode(s);
        }

        // This should never happen
        return null;
    }

    /**
     * Determine if two BST_Nodes are equal.
     *
     * Two nodes are considered equal if they contain the same data.
     * @param other The node to compare the current node to.
     * @return {@code true} if both nodes contain the same data, {@code false} otherwise.
     */
    public boolean equals(BST_Node other) {
        if (other == null) {
            return false;
        }

        return this.getData().equals(other.getData());
    }

    /**
     * Find the node with the largest value in the tree with the current node as its root.
     * @return The node with the largest value.
     */
    public BST_Node findMax(){
        if(right!=null)return right.findMax();
        return this;
    }

    /**
     * Find the node with the smallest value in the tree with the current node as its root.
     * @return The node with the smallest value.
     */
    public BST_Node findMin(){
        if (left != null) {
            return left.findMin();
        }

        return this;
    }

    /**
     * Get the data stored in the node.
     * @return The data stored in the node.
     */
    public String getData(){
        return data;
    }

    /**
     * Get the height of the tree with the current node as its root.
     * @return The longest path between this node and one of its leaves.
     */
    public int getHeight() {
        int l = 0;
        int r = 0;

        if (left != null) {
            l += left.getHeight() + 1;
        }

        if (right != null) {
            r += right.getHeight() + 1;
        }

        return Integer.max(l, r);
    }

    /**
     * Get a boolean representing if the node was just created.
     * @return {@code true} if the node was just created, {@code false} otherwise.
     */
    public boolean getJustMade() {
        return justMade;
    }

    /**
     * Get the left child of the current node.
     *
     * If the node has no left child, the left child is {@code null}.
     * @return The left child of the current node.
     */
    public BST_Node getLeft(){
        return left;
    }

    /**
     * Get the parent of the current node.
     *
     * If the current node is the root of the tree, the parent node is {@code null}.
     * @return The parent of the current node.
     */
    public BST_Node getParent() {
        return parent;
    }

    /**
     * Get the right child of the current node.
     *
     * If the node has no right child, the right child is {@code null}.
     * @return The right child of the current node.
     */
    public BST_Node getRight(){
        return right;
    }

    /**
     * Insert a new value into the tree that has the current node as the root.
     * @param s The data to insert.
     * @return The created node if the data didn't already exist in the tree. If the data was already contained in the
     *         tree, the existing node is returned.
     */
    public BST_Node insertNode(String s){
        int comparison = data.compareTo(s);

        if (comparison == 0) {
            return this;
        }

        if (comparison > 0) {
            if (left == null) {
                setLeft(new BST_Node(s));

                return left;
            }

            return left.insertNode(s);
        }

        if (comparison < 0) {
            if (right == null) {
                setRight(new BST_Node(s));

                return right;
            }

            return right.insertNode(s);
        }

        // Wut? How did we get here.
        return null;
    }

    /**
     * Remove the node with the given data from the tree.
     * @param s The data to search for.
     * @return {@code true} if the node was successfully removed, {@code false} otherwise.
     */
    public boolean removeNode(String s) {
        if (data == null) {
            return false;
        }

        if (data.equals(s)) {
            if (left != null) {
                data = left.findMax().getData();
                left.removeNode(data);

                if (left.data == null) {
                    left = null;
                }
            }
            else if(right != null) {
                data = right.findMin().getData();
                right.removeNode(data);

                if (right.data == null) {
                    right = null;
                }
            } else {
                data=null;
            }

            return true;
        } else if (data.compareTo(s) > 0) {
            if (left == null) {
                return false;
            }

            if (!left.removeNode(s)) {
                return false;
            }

            if (left.data == null) {
                left = null;
            }

            return true;
        } else if (data.compareTo(s) < 0) {
            if (right == null) {
                return false;
            }

            if (!right.removeNode(s)) {
                return false;
            }

            if (right.data == null) {
                right=null;
            }

            return true;
        }

        return false;
    }

    /**
     * Set justMade to a new value.
     * @param newVal The new value of justMade.
     */
    public void setJustMade(boolean newVal) {
        justMade = newVal;
    }

    /**
     * Set the left child of the current node.
     * @param node The node to set as the left child.
     */
    public void setLeft(BST_Node node) {
        left = node;

        if (left != null) {
            left.parent = this;
        }
    }

    /**
     * Set the parent of the current node.
     * @param node The node to set as the parent of the current node.
     */
    public void setParent(BST_Node node) {
        parent = node;
    }

    /**
     * Set the right child of the current node.
     * @param node The node to set as the right child.
     */
    public void setRight(BST_Node node) {
        right = node;

        if (right != null) {
            right.parent = this;
        }
    }

    /**
     * Convert the node to a string.
     * @return A string representation of the node containing information about the node.
     */
    public String toString() {
        return String.format(
                "Data: %s, Left: %s, Right: %s",
                this.data,
                this.left == null ? "null" : left.getData(),
                this.right == null ? "null" : right.getData());
    }
}
