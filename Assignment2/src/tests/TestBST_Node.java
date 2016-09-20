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
    public void getHeightNoChildren() {
        BST_Node root = new BST_Node("foo");

        Assert.assertEquals(0, root.getHeight());
    }

    @Test
    public void getHeightRecursive() {
        BST_Node root = new BST_Node("alpha");
        root.insertNode(new BST_Node("beta"));
        root.insertNode(new BST_Node("gamma"));

        Assert.assertEquals(2, root.getHeight());
    }

    @Test
    public void getHeightWithChildren() {
        BST_Node root = new BST_Node("beta");
        BST_Node left = new BST_Node("alpha");
        BST_Node right = new BST_Node("gamma");

        root.setLeft(left);

        Assert.assertEquals(1, root.getHeight());

        root.setLeft(null);
        root.setRight(right);

        Assert.assertEquals(1, root.getHeight());
    }

    @Test
    public void findMaxNoChildren() {
        BST_Node root = new BST_Node("foo");

        Assert.assertEquals(root, root.findMax());
    }

    @Test
    public void findMaxRecursive() {
        BST_Node root = new BST_Node("alpha");
        BST_Node child = new BST_Node("beta");
        BST_Node grandchild = new BST_Node("gamma");

        root.setRight(child);
        child.setRight(grandchild);

        Assert.assertEquals(grandchild, root.findMax());
    }

    @Test
    public void findMaxWithChildren() {
        BST_Node root = new BST_Node("alpha");

        BST_Node right = new BST_Node("beta");
        root.setRight(right);

        Assert.assertEquals(right, root.findMax());
    }

    @Test
    public void findMinNoChildren() {
        BST_Node root = new BST_Node("foo");

        Assert.assertEquals(root, root.findMin());
    }

    @Test
    public void findMinRecursive() {
        BST_Node root = new BST_Node("gamma");
        BST_Node child = new BST_Node("beta");
        BST_Node grandchild = new BST_Node("alpha");

        root.setLeft(child);
        child.setLeft(grandchild);

        Assert.assertEquals(grandchild, root.findMin());
    }

    @Test
    public void findMinWithChildren() {
        BST_Node root = new BST_Node("beta");

        BST_Node left = new BST_Node("alpha");
        root.setLeft(left);

        Assert.assertEquals(left, root.findMin());
    }

    @Test
    public void containsWithNoChildren() {
        BST_Node node = new BST_Node("foo");

        Assert.assertFalse(node.containsNode("bar"));
    }

    @Test
    public void insertDuplicate() {
        BST_Node node = new BST_Node("foo");

        Assert.assertFalse(node.insertNode(new BST_Node("foo")));
    }

    @Test
    public void insertLeftChild() {
        BST_Node root = new BST_Node("beta");
        BST_Node child = new BST_Node("alpha");

        root.insertNode(child);

        Assert.assertEquals(child, root.getLeft());
    }

    @Test
    public void insertRecursive() {
        BST_Node root = new BST_Node("gamma");

        BST_Node child = new BST_Node("alpha");
        root.setLeft(child);

        BST_Node grandchild = new BST_Node("beta");
        root.insertNode(grandchild);

        Assert.assertEquals(grandchild, child.getRight());
    }

    @Test
    public void insertRightChild() {
        BST_Node root = new BST_Node("beta");
        BST_Node child = new BST_Node("gamma");

        root.insertNode(child);

        Assert.assertEquals(child, root.getRight());
    }

    @Test
    public void removeLeaf() {
        BST_Node root = new BST_Node("beta");
        BST_Node left = new BST_Node("alpha");
        BST_Node right = new BST_Node("gamma");

        root.insertNode(left);

        Assert.assertTrue(root.removeNode("alpha"));
        Assert.assertNull(root.getLeft());

        root.insertNode(right);

        Assert.assertTrue(root.removeNode("gamma"));
        Assert.assertNull(root.getRight());
    }

    @Test
    public void removeNodeWithSingleChild() {
        BST_Node root = new BST_Node("gamma");
        BST_Node child = new BST_Node("beta");
        BST_Node grandchild = new BST_Node("alpha");

        root.insertNode(child);
        root.insertNode(grandchild);

        Assert.assertTrue(root.removeNode(child.getData()));
        Assert.assertEquals(grandchild, root.getLeft());
    }

    @Test
    public void removeNonExistent() {
        BST_Node root = new BST_Node("foo");

        Assert.assertFalse(root.removeNode("bar"));
    }
}
