/*
 * Created by chathan on 11/9/16.
 */

package DiGraph_A5;

/**
 * An {@code Edge} represents a link between two nodes in a directed
 * graph. It provides a way to store a weighted link from one node to
 * another. Note that this link is one-way only.
 */
public class Edge {
    private long id;
    private long weight;

    private Node head;
    private Node tail;

    private String label;

    /**
     * Create a new edge.
     * @param id The id of the edge.
     * @param tail The node to start the edge at.
     * @param head The node to end the edge at.
     * @param weight The weight of the edge.
     * @param label The label of the edge.
     */
    public Edge(long id, Node tail, Node head, long weight, String label) {
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
    public Node getHead() {
        return head;
    }

    /**
     * Get the id of the node.
     * @return The id of the node.
     */
    public long getId() {
        return id;
    }

    /**
     * Get the label of the edge.
     * @return The label of the edge.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the starting node for the edge.
     * @return The node that the edge points from.
     */
    public Node getTail() {
        return tail;
    }
}
