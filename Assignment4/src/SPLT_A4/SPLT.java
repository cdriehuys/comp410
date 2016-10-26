package SPLT_A4;

public class SPLT implements SPLT_Interface{
    private BST_Node root;
    private int size;

    public SPLT() {
        this.size = 0;
    }

    /**
     * Determine if a node is contained in the tree.
     * @param s The data to check for in the tree.
     * @return {@code true}i f there is a node in the tree with the data {@code s}, {@code false} otherwise.
     */
    @Override
    public boolean contains(String s) {
        if (getRoot() == null) {
            return false;
        }

        BST_Node result = getRoot().containsNode(s);
        splay(result);

        return result.getData().equals(s);
    }

    /**
     * Determine if the tree is empty.
     *
     * An empty tree is defined as a tree with no root node.
     * @return {@code true} if the tree is empty, {@code false} otherwise.
     */
    @Override
    public boolean empty() {
        return getRoot() == null;
    }

    /**
     * Find the maximum value in the tree.
     * @return The largest value in the tree.
     */
    @Override
    public String findMax() {
        if (getRoot() == null) {
            return null;
        }

        BST_Node max = getRoot().findMax();
        splay(max);

        return max.getData();
    }

    /**
     * Find the minimum value in the tree.
     * @return The smallest value in the tree.
     */
    @Override
    public String findMin() {
        if (getRoot() == null) {
            return null;
        }

        BST_Node min = getRoot().findMin();
        splay(min);

        return min.getData();
    }

    /**
     * Get the root node of the tree.
     * @return The root node of the tree.
     */
    public BST_Node getRoot() {
        return root;
    }

    /**
     * Get the height of the tree.
     *
     * Note that the height of an empty tree is -1.
     * @return The height of the tree.
     */
    @Override
    public int height() {
        return getRoot() == null ? -1 : getRoot().getHeight();
    }

    /**
     * Insert a value into the tree.
     * @param s The value to insert into the tree.
     */
    @Override
    public void insert(String s) {
        if (getRoot() == null) {
            setRoot(new BST_Node(s));

            size++;

            // Size has accounted for new element, so set justMade to false
            getRoot().setJustMade(false);

            return;
        }

        BST_Node inserted = getRoot().insertNode(s);
        if (inserted.getJustMade()) {
            size++;
            inserted.setJustMade(false);
        }

        splay(inserted);
    }

    /**
     * Remove a value from the tree.
     * @param s The value to remove from the tree.
     */
    @Override
    public void remove(String s) {
        if (getRoot() == null) {
            return;
        }

        // Check if the value to remove exists in the tree.
        if (!contains(s)) {
            return;
        }

        // The value exists, and it is now at the root of the tree.
        size--;

        // Attempt to use the max of the left subtree as the new root. If it doesn't exist, use the min of the right
        // subtree.
        if (getRoot().getLeft() != null) {
            SPLT leftTree = new SPLT();
            leftTree.setRoot(getRoot().getLeft());

            // This splays the max of the left tree to the top of the tree.
            leftTree.findMax();

            // Set up the new tree with the previous root removed
            leftTree.getRoot().setRight(getRoot().getRight());
            setRoot(leftTree.getRoot());
        } else {
            SPLT rightTree = new SPLT();
            rightTree.setRoot(getRoot().getRight());

            // Splay the min of the right tree to the top.
            rightTree.findMin();

            // Set up new tree with previous root removed. We also know that there is no left subtree, so we can ignore
            // it.
            setRoot(rightTree.getRoot());
        }
    }

    /**
     * Set the root of the current tree.
     * @param node The node to set as the root of the tree.
     */
    public void setRoot(BST_Node node) {
        root = node;

        if (root != null) {
            root.setParent(null);
        }
    }

    /**
     * Get the size of the tree.
     * @return The number of elements in the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Swap a parent node with its child node, maintaining sidedness.
     *
     * Example: parent is left child, child will be swapping in as left child of grandparent.
     * @param parent The parent node to swap out.
     * @param child The child node to swap in.
     */
    private void parentChildSwap(BST_Node parent, BST_Node child) {
        BST_Node grandparent = parent.getParent();

        if (grandparent != null) {
            if (parent == grandparent.getRight()) {
                grandparent.setRight(child);
            } else {
                grandparent.setLeft(child);
            }
        } else {
            child.setParent(null);
        }
    }

    /**
     * Rotate left at the given node, bringing its right child up to replace it.
     * @param node The node to rotate at.
     */
    private void rotateLeft(BST_Node node) {
        BST_Node child = node.getRight();

        parentChildSwap(node, child);

        node.setRight(child.getLeft());
        child.setLeft(node);

        if (child.getParent() == null) {
            setRoot(child);
        }
    }

    /**
     * Rotate right at the given node, bringing its left child up to replace it.
     * @param node The node to rotate at.
     */
    private void rotateRight(BST_Node node) {
        BST_Node child = node.getLeft();

        parentChildSwap(node, child);

        node.setLeft(child.getRight());
        child.setRight(node);

        if (child.getParent() == null) {
            setRoot(child);
        }
    }

    /**
     * Splay the tree to move the specified node to the root.
     * @param node The node to move to the root of the tree.
     */
    private void splay(BST_Node node) {
        // 4 Cases:

        // Case 1: Node is root already, we can exit
        if (root.equals(node)) {
            return;
        }

        BST_Node parent = node.getParent();

        // Case 2: Node's parent is root, we do a simple rotation
        if (parent.equals(root)) {
            // Rotation direction depends on which child `node` is.
            if (node.equals(parent.getRight())) {
                rotateLeft(parent);
            } else {
                rotateRight(parent);
            }

            return;
        }

        BST_Node grandparent = parent.getParent();

        // Case 3: Node and parent are both the same side child (eg left and left). Zig-zig or zag-zag.
        if (parent.equals(grandparent.getLeft()) && node.equals(parent.getLeft())) {
            rotateRight(grandparent);
            rotateRight(parent);
        } else if (parent.equals(grandparent.getRight()) && node.equals(parent.getRight())) {
            rotateLeft(grandparent);
            rotateLeft(parent);
        }

        // Case 4: Node and parent are opposite children (eg left and right). Zig-zag or zag-zig.
        if (parent.equals(grandparent.getLeft()) && node.equals(parent.getRight())) {
            rotateLeft(parent);
            rotateRight(grandparent);
        } else if (parent.equals(grandparent.getRight()) && node.equals(parent.getLeft())) {
            rotateRight(parent);
            rotateLeft(grandparent);
        }

        // If the node is not the root, do it again
        if (!node.equals(getRoot())) {
            splay(node);
        }
    }
}
