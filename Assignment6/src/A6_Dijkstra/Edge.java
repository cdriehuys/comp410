/*
 * Created by chathan on 11/9/16.
 */

package A6_Dijkstra;

/**
 * An {@code Edge} represents a link between two nodes in a directed
 * graph. It provides a way to store a weighted link from one node to
 * another. Note that this link is one-way only.
 */
class Edge {
    private final long id;

    private final Node head;
    private final Node tail;

    private final long weight;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String label;

    /**
     * Create a new edge.
     * @param id The id of the edge.
     * @param tail The node to start the edge at.
     * @param head The node to end the edge at.
     * @param weight The weight of the edge.
     * @param label The label of the edge.
     */
    Edge(long id, Node tail, Node head, long weight, String label) {
        this.id = id;
        this.tail = tail;
        this.head = head;
        this.weight = weight;
        this.label = label;
    }

    /**
     * Get the ending node for the edge.
     * @return The node that the edge points to.
     */
    Node getHead() {
        return head;
    }

    /**
     * Get the id of the node.
     * @return The id of the node.
     */
    long getId() {
        return id;
    }

    /**
     * Get the starting node for the edge.
     * @return The node that the edge points from.
     */
    Node getTail() {
        return tail;
    }

    /**
     * Get the weight of the edge.
     * @return The edge's weight.
     */
    long getWeight() {
        return weight;
    }
}
