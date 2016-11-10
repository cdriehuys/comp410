package DiGraph_A5;

/**
 * The {@code Node} class represents a node in a graph.
 */
public class Node {
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
     * Get the label of the node.
     * @return The label of the node.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the <em>indegree</em> of the node.
     * @param newIndegree The new <em>indegree</em> of the node.
     */
    public void setIndegree(int newIndegree) {
        indegree = newIndegree;
    }
}
