package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
    private EntryPair[] array; //load this array
    private int size;
    private static final int arraySize = 10000; //Everything in the array will initially
    //be null. This is ok! Just build out
    //from array[1]

    public MinBinHeap() {
        this.array = new EntryPair[arraySize];
        array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity
        //of child/parent computations...
        //the book/animation page both do this.
    }

    @Override
    public void insert(EntryPair entry) {

    }

    @Override
    public void delMin() {

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
        return 0;
    }

    @Override
    public void build(EntryPair[] entries) {

    }

    //Please do not remove or modify this method! Used to test your entire Heap.
    @Override
    public EntryPair[] getHeap() {
        return this.array;
    }

    public void setArrayAt(int index, EntryPair pair) {
        array[index] = pair;
    }
}
