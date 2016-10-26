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
        return root != null && root.containsNode(s);
    }

    /**
     * Determine if the tree is empty.
     *
     * An empty tree is defined as a tree with no root node.
     * @return {@code true} if the tree is empty, {@code false} otherwise.
     */
    @Override
    public boolean empty() {
        return root == null;
    }

    /**
     * Find the maximum value in the tree.
     * @return The largest value in the tree.
     */
    @Override
    public String findMax() {
        return root == null ? null : root.findMax().getData();
    }

    /**
     * Find the minimum value in the tree.
     * @return The smallest value in the tree.
     */
    @Override
    public String findMin() {
        return root == null ? null : root.findMin().getData();
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
        return root == null ? -1 : root.getHeight();
    }

    @Override
    public void insert(String s) {

    }

    @Override
    public void remove(String s) {

    }

    @Override
    public int size() {
        return 0;
    }

}
