package BST_A2;

/**
 * Created by chathan on 9/15/16.
 */

public class BST implements BST_Interface {
    public BST_Node root;
    int size;

    public BST(){ size=0; root=null; }

    @Override
    public boolean insert(String s) {
        BST_Node newNode = new BST_Node(s);

        if (root == null) {
            root = newNode;

            return true;
        }

        return root.insertNode(newNode);
    }

    @Override
    public boolean remove(String s) {
        return false;
    }

    @Override
    public String findMin() {
        return null;
    }

    @Override
    public String findMax() {
        return null;
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public boolean contains(String s) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    //used for testing, please leave as is
    public BST_Node getRoot(){ return root; }

}
