package A6_Dijkstra;

/**
 * Enumeration for specifying edge deletion behavior.
 *
 * The only use case for this is when edges are being deleted from
 * within a loop, because in that case one of the nodes cannot be
 * modified without a {@code ConcurrentModificationException} being
 * thrown.
 */
enum EdgeDelete {
    /**
     * Delete edge links from both the head and the tail nodes.
     */
    DEL_BOTH,

    /**
     * Only delete the edge link from the head node.
     */
    DEL_HEAD,

    /**
     * Only delete the edge link from the tail node.
     */
    DEL_TAIL
}
