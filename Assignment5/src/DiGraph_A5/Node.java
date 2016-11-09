package DiGraph_A5;

public class Node {
    private long id;

    private String label;

    public Node(long id, String label) {
        this.id = id;
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
