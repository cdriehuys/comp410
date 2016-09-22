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
    public void contains() {
        BST tree = new BST();
        tree.insert("foo");

        Assert.assertTrue(tree.contains("foo"));
    }

    @Test
    public void containsNullRoot() {
        BST tree = new BST();

        Assert.assertFalse(tree.contains("foo"));
    }

    @Test
    public void empty() {
        BST tree = new BST();

        Assert.assertTrue(tree.empty());
    }

    @Test
    public void emptyNotEmpty() {
        BST tree = new BST();
        tree.insert("foo");

        Assert.assertFalse(tree.empty());
    }

    @Test
    public void findMaxChildren() {
        BST tree = new BST();
        tree.insert("alpha");
        tree.insert("beta");

        Assert.assertEquals("beta", tree.findMax());
    }

    @Test
    public void findMaxNullRoot() {
        BST tree = new BST();

        Assert.assertNull(tree.findMax());
    }

    @Test
    public void findMaxRootOnly() {
        BST tree = new BST();
        tree.insert("alpha");

        Assert.assertEquals("alpha", tree.findMax());
    }

    @Test
    public void findMinChildren() {
        BST tree = new BST();
        tree.insert("beta");
        tree.insert("alpha");

        Assert.assertEquals("alpha", tree.findMin());
    }

    @Test
    public void findMinNullRoot() {
        BST tree = new BST();

        Assert.assertNull(tree.findMin());
    }

    @Test
    public void findMinRootOnly() {
        BST tree = new BST();
        tree.insert("alpha");

        Assert.assertEquals(tree.getRoot().getData(), tree.findMin());
    }

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

    @Test
    public void removeChild() {
        BST tree = new BST();

        BST_Node root = new BST_Node("alpha");
        BST_Node child = new BST_Node("beta");

        tree.root = root;
        tree.root.setRight(child);

        Assert.assertTrue(tree.remove(child.getData()));
        Assert.assertNull(root.getRight());
    }

    @Test
    public void removeNullRoot() {
        BST tree = new BST();

        Assert.assertFalse(tree.remove("foo"));
    }

    @Test
    public void removeRoot() {
        BST tree = new BST();
        tree.root = new BST_Node("foo");

        Assert.assertTrue(tree.remove(tree.root.getData()));
        Assert.assertNull(tree.root);
    }

    @Test
    public void sizeInitial() {
        Assert.assertEquals(0, (new BST()).size());
    }

    @Test
    public void sizeInsert() {
        BST tree = new BST();
        tree.insert("foo");

        Assert.assertEquals(1, tree.size());
    }

    @Test
    public void sizeRemove() {
        BST tree = new BST();
        tree.insert("foo");
        tree.remove("foo");

        Assert.assertEquals(0, tree.size());
    }
}
