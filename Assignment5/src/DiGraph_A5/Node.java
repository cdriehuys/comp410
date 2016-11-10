package DiGraph_A5;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Node} class represents a node in a graph.
 */
public class Node {
    private Map<Node, Edge> inEdges;
    private Map<Node, Edge> outEdges;

    private int indegree;

    private long id;

    private String label;

    /**
     * Create a new node.
     * @param id The id of the node.
     * @param label The label of the node.
     */
    public Node(long id, String label) {
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
    public void addEdge(Edge e) {
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
    public long getId() {
        return id;
    }

    /**
     * Get the <em>indegree</em> of the node.
     * @return The number of edges that terminate at this node.
     */
    public int getIndegree() {
        return indegree;
    }

    /**
     * Get the edges that point to the current node.
     * @return A list of edges that point to the current node.
     */
    public Map<Node, Edge> getInEdges() {
        return inEdges;
    }

    /**
     * Get the label of the node.
     * @return The label of the node.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the edges that point from the current node.
     * @return A list of edges that point from the current node.
     */
    public Map<Node, Edge> getOutEdges() {
        return outEdges;
    }

    /**
     * Remove an edge from the node.
     *
     * This method removes the node from either the list of in-edges or
     * out-edges depending on how the edge is structured.
     * @param e The edge to remove from the node.
     * @throws RuntimeException If the given edge does not start or end
     *                          at the current node.
     */
    public void removeEdge(Edge e) {
        if (e.getTail().equals(this)) {
            outEdges.remove(e.getHead(), e);

            return;
        } else if (e.getHead().equals(this)) {
            indegree--;
            inEdges.remove(e.getTail(), e);

            return;
        }

        throw new RuntimeException("Cannot remove edge that is unrelated to current node.");
    }

    public void removeInEdge(Node n) {
        inEdges.remove(n);
        indegree--;
    }

    public void removeOutEdge(Node n) {
        outEdges.remove(n);
    }

    /**
     * Set the <em>indegree</em> of the node.
     * @param newIndegree The new <em>indegree</em> of the node.
     */
    public void setIndegree(int newIndegree) {
        indegree = newIndegree;
    }
}
