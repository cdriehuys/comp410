/*
 * Created by chathan on 10/3/16.
 */

package tests;

import MinBinHeap_A3.EntryPair;
import MinBinHeap_A3.Heap_Interface;
import MinBinHeap_A3.MinBinHeap;
import org.junit.Assert;
import org.junit.Test;

public class TestMinBinHeap {

    @Test
    public void testGetMin() {
        MinBinHeap heap = new MinBinHeap();
        EntryPair entry = new EntryPair("foo", 1);
        heap.setArrayAt(1, entry);

        Assert.assertEquals("Min heap value should be at index 1.",
                entry, heap.getMin());
    }
    @Test
    public void testGetMinEmpty() {
        Heap_Interface heap = new MinBinHeap();

        Assert.assertNull("The minimum of a new heap should be null",
                heap.getMin());
    }
}
