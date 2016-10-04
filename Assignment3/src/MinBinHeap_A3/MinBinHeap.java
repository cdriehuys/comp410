package MinBinHeap_A3;

/**
 * A {@code MinBinHeap} is a binary heap priority queue. It stores
 * {link EntryPair}s in an order such that the root element is always
 * the next element priority-wise.
 */
public class MinBinHeap implements Heap_Interface {

    private static final int arraySize = 10000;

    private EntryPair[] array;

    private int size;

    /**
     * Create an empty binary heap.
     */
    public MinBinHeap() {
        this.array = new EntryPair[arraySize];
        array[0] = new EntryPair(null, -100000);
        size = 0;
    }

    /**
     * Populate the queue with a list of entries.
     *
     * Rather than performing an {@code insert} operation for each
     * entry, we can dump the entries into the heap and then correct the
     * order from there. This allows us to complete the operation in
     * linear time.
     * @param entries The list of entries to add to the queue.
     */
    @Override
    public void build(EntryPair[] entries) {
        // Dump all values into the heap
        int i = 1;
        for (EntryPair entry : entries) {
            array[i++] = entry;
        }

        size = entries.length;

        // Correct the order of the heap
        for (int j = size / 2; j > 0; j--) {
            percolateDown(j);
        }
    }

    /**
     * Remove the element with the lowest priority value from the queue.
     */
    @Override
    public void delMin() {
        if (array[1] == null) {
            return;
        }

        // Remove the root node, which must be the minimum, and populate
        // it with the last child. Then fix the ordering.
        array[1] = array[size--];
        percolateDown(1);
    }

    /**
     * Get the raw heap used to store the queue.
     * @return The binary heap containing the queue's entries.
     */
    @Override
    public EntryPair[] getHeap() {
        return this.array;
    }

    /**
     * Get the minimum value from the heap.
     *
     * This is always the value at index 1.
     * @return The {@code EntryPair} that has the minimum value in the
     *         heap.
     */
    @Override
    public EntryPair getMin() {
        return array[1];
    }

    /**
     * Insert a new value into the queue.
     * @param entry The entry to insert.
     */
    @Override
    public void insert(EntryPair entry) {
        // hole at current size & increment size for this insert
        int hole = ++size;

        // Percolate hole up while the entry to be inserted has a lower
        // priority value than its parent.
        while (compareEntries(entry, array[hole / 2]) < 0) {
            array[hole] = array[hole / 2];

            hole /= 2;

            // Break out at this point because it means the entry being
            // inserted is the new root node.
            if (hole == 1) {
                break;
            }
        }

        // We've found the spot where our new entry goes, so assign it.
        array[hole] = entry;
    }

    /**
     * Get the size of the queue.
     * @return The number of elements in the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Compare two entries by their priority values.
     * @param e1 The first entry to compare.
     * @param e2 The second entry to compare.
     * @return The difference of the priority values of the two entries.
     *         Specifically, {@code e1.priority - e2.priority}.
     */
    private int compareEntries(EntryPair e1, EntryPair e2) {
        return e1.getPriority() - e2.getPriority();
    }

    /**
     * Fill a hole by moving the hole downward until the heap order is
     * satisfied.
     * @param hole The index of the hole in the heap.
     */
    private void percolateDown(int hole) {
        int child;
        EntryPair tmp = array[hole];

        // We only have to go up to the size of the queue because there
        // are no more elements after that.
        while (hole * 2 <= size) {
            // Start with the assumption that the smallest child is the
            // left child of the hole.
            child = hole * 2;

            // If the right child is smaller, update the index of the
            // smallest child.
            if (child != size && compareEntries(array[child + 1], array[child]) < 0) {
                child++;
            }

            // If the child's priority is less than the node currently
            // occupying that space's, move the child into the hole, and
            // continue the process.
            if (compareEntries(array[child], tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }

            hole = child;
        }

        // Populate the final location of the hole with the last child
        // from the queue.
        array[hole] = tmp;
    }
}
