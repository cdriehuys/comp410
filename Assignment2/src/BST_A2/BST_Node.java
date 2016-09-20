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

    public void setLeft(BST_Node node) {
        left = node;
    }

    public void setRight(BST_Node node) {
        right = node;
    }


    // --- fill in these methods ------------------------------------------
    //
    // at the moment, they are stubs returning false
    // or some appropriate "fake" value
    //
    // you make them work properly
    // add the meat of correct implementation logic to them

    // you MAY change the signatures if you wish...
    // make the take more or different parameters
    // have them return different types
    //
    // you may use recursive or iterative implementations

  /*
  public boolean removeNode(String s){ return false; }
  public BST_Node findMin(){ return left; }
  public BST_Node findMax(){ return right; }
  public int getHeight(){ return 0; }
  */

    // --- end fill in these methods --------------------------------------


    // --------------------------------------------------------------------
    // you may add any other methods you want to get the job done
    // --------------------------------------------------------------------

    public String toString(){
        return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
                +",Right: "+((this.right!=null)?right.data:"null");
    }
}
