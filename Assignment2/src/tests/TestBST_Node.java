package tests;

import BST_A2.BST_Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chathan on 9/15/16.
 */
public class TestBST_Node {

    @Test
    public void containsDirectChild() {
        BST_Node root = new BST_Node("foo");
        root.setLeft(new BST_Node("bar"));

        Assert.assertTrue(root.containsNode("bar"));
    }

    @Test
    public void containsRecursive() {
        BST_Node root = new BST_Node("foo");
        root.setLeft(new BST_Node("bar"));
        root.getLeft().setRight(new BST_Node("baz"));

        Assert.assertTrue(root.containsNode("baz"));
    }

    @Test
    public void containsSelf() {
        BST_Node node = new BST_Node("foo");

        Assert.assertTrue(node.containsNode("foo"));
    }

    @Test
    public void containsWithNoChildren() {
        BST_Node node = new BST_Node("foo");

        Assert.assertFalse(node.containsNode("bar"));
    }
}
