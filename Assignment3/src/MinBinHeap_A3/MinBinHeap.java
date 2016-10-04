package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
    private EntryPair[] array; //load this array
    private int size;
    private static final int arraySize = 10000; //Everything in the array will initially
    //be null. This is ok! Just build out
    //from array[1]

    public MinBinHeap() {
        this.array = new EntryPair[arraySize];
        array[0] = new EntryPair(null, -100000);
        size = 0;
    }

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

    @Override
    public void delMin() {
        if (array[1] == null) {
            return;
        }

        EntryPair minEntry = getMin();
        array[1] = array[size--];

        percolateDown(1);
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public void build(EntryPair[] entries) {
        int i = 1;
        for (EntryPair entry : entries) {
            array[i++] = entry;
        }

        size = entries.length;

        for (int j = size / 2; j > 0; j--) {
            percolateDown(j);
        }
    }

    //Please do not remove or modify this method! Used to test your entire Heap.
    @Override
    public EntryPair[] getHeap() {
        return this.array;
    }

    public void setArrayAt(int index, EntryPair pair) {
        array[index] = pair;
    }

    private int compareEntries(EntryPair e1, EntryPair e2) {
        return e1.getPriority() - e2.getPriority();
    }

    private void percolateDown(int hole) {
        int child;
        EntryPair tmp = array[hole];

        while (hole * 2 <= size) {
            child = hole * 2;

            if (compareEntries(array[child + 1], array[child]) < 0) {
                child++;
            }

            if (compareEntries(array[child], tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }

            hole = child;
        }

        array[hole] = tmp;
    }
}
