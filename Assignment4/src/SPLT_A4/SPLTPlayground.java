package SPLT_A4;

import org.junit.Assert;

import java.util.Objects;

/**
 * Created by chathan on 10/26/16.
 */
public class SPLTPlayground {
    public static void main(String[] args) {
        SPLT tree = new SPLT();
        tree.insert("A");
        tree.insert("A");

        if (tree.size() != 1) {
            throw new AssertionError("Tree size should be 1, not " + tree.size());
        }
    }

    public static String inOrder(SPLT_Interface tree) {
        return inOrder(tree.getRoot());
    }

    private static String inOrder(BST_Node node) {
        String ret = node.getData();

        if (node.getLeft() != null) {
            ret = inOrder(node.getLeft()) + ", " + ret;
        }

        if (node.getRight() != null) {
            ret = ret + ", " + inOrder(node.getRight());
        }

        return ret;
    }
}
