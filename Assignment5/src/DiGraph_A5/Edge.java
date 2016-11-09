/*
 * Created by chathan on 11/9/16.
 */

package DiGraph_A5;

public class Edge {
    private long id;
    private long weight;

    private Node head;
    private Node tail;

    private String label;

    public Edge(long id, Node tail, Node head, long weight, String label) {
        this.id = id;
        this.tail = tail;
        this.head = head;
        this.weight = weight;
        this.label = label;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }
}
