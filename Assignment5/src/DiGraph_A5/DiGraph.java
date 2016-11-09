package DiGraph_A5;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DiGraph implements DiGraph_Interface {
    private ArrayList<Long> edgeIds;
    private ArrayList<Long> nodeIds;

    private HashMap<String, Node> nodes;
    private HashMap<Node, ArrayList<Edge>> edges;

    // in here go all your data and methods for the graph
    // and the topo sort operation

    public DiGraph ( ) {
        edges = new HashMap<>();
        nodes = new HashMap<>();

        edgeIds = new ArrayList<>();
        nodeIds = new ArrayList<>();
    }

    @Override
    public boolean addNode(long idNum, String label) {
        if (nodes.containsKey(label)
                || idNum < 0
                || nodeIds.contains(idNum)) {
            return false;
        }

        Node n = new Node(idNum, label);
        nodes.put(label, n);
        edges.put(n, new ArrayList<>());
        nodeIds.add(idNum);

        return true;
    }

    @Override
    public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
        if (edgeIds.contains(idNum)) {
            return false;
        }

        Node tail = nodes.get(sLabel);
        Node head = nodes.get(dLabel);

        if (tail == null || head == null) {
            return false;
        }

        ArrayList<Edge> nodeEdges = edges.get(tail);

        for (Edge e : nodeEdges) {
            if (e.getTail().equals(tail) && e.getHead().equals(head)) {
                return false;
            }
        }

        nodeEdges.add(new Edge(idNum, tail, head, weight, eLabel));
        edgeIds.add(idNum);

        return true;
    }

    @Override
    public boolean delNode(String label) {
        Node node = nodes.get(label);

        if (node == null) {
            return false;
        }

        for (ArrayList<Edge> edgeSet : edges.values()) {
            for (Edge edge : edgeSet) {
                if (edge.getHead().equals(node) || edge.getTail().equals(node)) {
                    edgeIds.remove(edge.getId());
                    edgeSet.remove(edge);
                }
            }
        }

        edges.remove(node);

        nodeIds.remove(node.getId());
        nodes.remove(node.getLabel());

        return true;
    }

    @Override
    public boolean delEdge(String sLabel, String dLabel) {
        return false;
    }

    @Override
    public long numNodes() {
        return 0;
    }

    @Override
    public long numEdges() {
        return 0;
    }

    @Override
    public String[] topoSort() {
        return new String[0];
    }

    // rest of your code to implement the various operations
}
