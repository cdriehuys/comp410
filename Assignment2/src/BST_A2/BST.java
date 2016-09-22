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
        if (root == null) {
            return false;
        }

        if (s.equals(root.getData())) {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left != null) {
                BST_Node max = root.left.findMax();
                root.left.removeNode(max.getData());

                max.left = root.left;
                max.right = root.right;

                root = max;
            } else {
                BST_Node min = root.right.findMin();
                root.right.removeNode(min.getData());

                min.left = root.left;
                min.right = root.right;

                root = min;
            }

            return true;
        }

        return root.removeNode(s);
    }

    @Override
    public String findMin() {
        return root == null ? null : root.findMin().getData();
    }

    /**
     * Find the maximum value in the tree.
     * @return The largest value in the tree. Returns {@code null} if
     *         the root is {@code null}.
     */
    @Override
    public String findMax() {
        return root == null ? null : root.findMax().getData();
    }

    /**
     * Determine if the tree is empty.
     * @return {@code true} if the root is {@code null}, {@code false}
     *         otherwise.
     */
    @Override
    public boolean empty() {
        return root == null;
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
