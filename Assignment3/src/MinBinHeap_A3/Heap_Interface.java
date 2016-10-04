package MinBinHeap_A3;

public interface Heap_Interface {
    void build(EntryPair [] entries);
    void delMin();
    EntryPair[] getHeap();
    EntryPair getMin();
    void insert(EntryPair entry);
    int size();
}
