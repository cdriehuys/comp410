package DiGraph_A5;

public class Node {
    private int indegree;

    private long id;

    private String label;

    public Node(long id, String label) {
        this.id = id;
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public int getIndegree() {
        return indegree;
    }

    public String getLabel() {
        return label;
    }

    public void setIndegree(int newIndegree) {
        indegree = newIndegree;
    }
}
