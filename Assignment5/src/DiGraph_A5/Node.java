package DiGraph_A5;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Node} class represents a node in a graph.
 */
class Node {
    private final Map<Node, Edge> inEdges;
    private final Map<Node, Edge> outEdges;

    private final long id;

    private final String label;

    private int indegree;

    /**
     * Create a new node.
     * @param id The id of the node.
     * @param label The label of the node.
     */
    Node(long id, String label) {
        this.id = id;
        this.label = label;

        inEdges = new HashMap<>();
        outEdges = new HashMap<>();
    }

    /**
     * Add a new edge to the node.
     *
     * The method first determines whether the current node is the
     * starting or ending point of the edge being added, then stores the
     * edge in the appropriate place.
     * @param e The edge to add.
     * @throws RuntimeException If the current node is not a part of the
     *                          edge being added.
     */
    void addEdge(Edge e) {
        if (e.getTail().equals(this)) {
            outEdges.put(e.getHead(), e);

            return;
        } else if (e.getHead().equals(this)) {
            indegree++;
            inEdges.put(e.getTail(), e);

            return;
        }

        throw new RuntimeException("Cannot add edge that is unrelated to current node.");
    }

    /**
     * Get the id of the node.
     * @return The id of the node.
     */
    long getId() {
        return id;
    }

    /**
     * Get the <em>indegree</em> of the node.
     * @return The number of edges that terminate at this node.
     */
    int getIndegree() {
        return indegree;
    }

    /**
     * Get the edges that point to the current node.
     * @return A list of edges that point to the current node.
     */
    Map<Node, Edge> getInEdges() {
        return inEdges;
    }

    /**
     * Get the label of the node.
     * @return The label of the node.
     */
    String getLabel() {
        return label;
    }

    /**
     * Get the edges that point from the current node.
     * @return A list of edges that point from the current node.
     */
    Map<Node, Edge> getOutEdges() {
        return outEdges;
    }

    void removeInEdge(Node n) {
        inEdges.remove(n);
        indegree--;
    }

    void removeOutEdge(Node n) {
        outEdges.remove(n);
    }
}
