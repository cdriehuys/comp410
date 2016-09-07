/*
 * Created by chathan on 9/7/16.
 */

package tests;

import LinkedListA0.LinkedListImpl;
import LinkedListA0.Node;
import org.junit.Assert;
import org.junit.Test;

public class TestLinkedListImpl {

    @Test
    public void testGetEmpty() {
        LinkedListImpl list = new LinkedListImpl();

        Assert.assertNull(list.get(0));
    }

    @Test
    public void testGetSingleElement() {
        LinkedListImpl list = new LinkedListImpl();
        Node node = new Node(1);

        list.insert(node, 0);

        Assert.assertEquals(node, list.get(0));
    }

    @Test
    public void testInsertEnd() {
        LinkedListImpl list = new LinkedListImpl();
        Node start = new Node(1);
        Node end = new Node(2);
        list.getRoot().next = start;

        list.insert(end, 1);

        Assert.assertEquals(end, list.getRoot().next.next);
    }

    @Test
    public void testInsertMiddle() {
        LinkedListImpl list = new LinkedListImpl();

        Node start = new Node(1);
        Node middle = new Node(2);
        Node end = new Node(3);

        list.insert(start, 0);
        list.insert(end, 1);

        list.insert(middle, 1);

        Assert.assertEquals(middle, list.getRoot().getNext().getNext());
    }

    @Test
    public void testInsertRoot() {
        LinkedListImpl list = new LinkedListImpl();
        Node node = new Node(1.0);

        list.insert(node, 0);

        Assert.assertEquals(node, list.getRoot().getNext());
    }
}
