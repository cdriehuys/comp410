package A6_Dijkstra;

public interface Heap_Interface {
    void build(EntryPair[] entries);
    void delMin();
    EntryPair[] getHeap();
    EntryPair getMin();
    void insert(EntryPair entry);
    int size();
}
