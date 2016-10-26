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

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public String findMax() {
        return null;
    }

    @Override
    public String findMin() {
        return null;
    }

    public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
        return root;
    }

    @Override
    public int height() {
        return 0;
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
