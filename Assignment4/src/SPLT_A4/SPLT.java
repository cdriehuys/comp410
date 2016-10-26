package SPLT_A4;

public class SPLT implements SPLT_Interface{
    private BST_Node root;
    private int size;

    public SPLT() {
        this.size = 0;
    }

    @Override
    public boolean contains(String s) {
        return false;
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
