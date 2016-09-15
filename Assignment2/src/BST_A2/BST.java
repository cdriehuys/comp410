package BST_A2;

/**
 * Created by chathan on 9/15/16.
 */

public class BST implements BST_Interface {
    public BST_Node root;
    int size;

    public BST(){ size=0; root=null; }

    @Override
    //used for testing, please leave as is
    public BST_Node getRoot(){ return root; }

}
