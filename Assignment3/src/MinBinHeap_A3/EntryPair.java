package MinBinHeap_A3;

/**
 * An {@code EntryPair} represents a value in a priority queue. It is a
 * wrapper around the value to be stored as well as that value's
 * priority.
 */
public class EntryPair implements EntryPair_Interface {
    public String value;
    public int priority;

    /**
     * Create a new {@code EntryPair}.
     * @param aValue The value of the new entry.
     * @param aPriority The priority of the new entry. A lower priority
     *                  value means it will be closer to the front of
     *                  the queue.
     */
    public EntryPair(String aValue, int aPriority) {
        value = aValue;
        priority = aPriority;
    }

    /**
     * Get the priority of the entry.
     * @return The priority of the entry.
     */
    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * Get the value contained in the entry.
     * @return The entry's value.
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Convert the {@code EntryPair} to a string.
     * @return The entry's value.
     */
    @Override
    public String toString() {
        return value;
    }
}
