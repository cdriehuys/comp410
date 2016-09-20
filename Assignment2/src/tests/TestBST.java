package tests;

import BST_A2.BST;
import BST_A2.BST_Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chathan on 9/20/16.
 */
public class TestBST {

    @Test
    public void insertData() {
        BST tree = new BST();

        BST_Node root = new BST_Node("beta");
        tree.root = root;

        Assert.assertTrue(tree.insert("alpha"));
        Assert.assertEquals("alpha", tree.root.getLeft().getData());
    }

    @Test
    public void insertRoot() {
        BST tree = new BST();

        Assert.assertTrue(tree.insert("foo"));
        Assert.assertEquals("foo", tree.getRoot().getData());
    }
}
