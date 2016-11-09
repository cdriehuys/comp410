package DiGraph_A5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

    public DiGraph(DiGraph graph) {
        edges = graph.edges;
        nodes = graph.nodes;

        edgeIds = graph.edgeIds;
        nodeIds = graph.nodeIds;
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
        head.setIndegree(head.getIndegree() + 1);
        edgeIds.add(idNum);

        return true;
    }

    @Override
    public boolean delNode(String label) {
        Node node = nodes.get(label);

        if (node == null) {
            return false;
        }

        for (Node n : nodes.values()) {
            delEdge(n.getLabel(), node.getLabel());
            delEdge(node.getLabel(), n.getLabel());
        }

        edges.remove(node);

        nodeIds.remove(node.getId());
        nodes.remove(node.getLabel());

        return true;
    }

    @Override
    public boolean delEdge(String sLabel, String dLabel) {
        Node head = nodes.get(dLabel);
        Node tail = nodes.get(sLabel);

        if (head == null || tail == null) {
            return false;
        }

        ArrayList<Edge> edgeSet = edges.get(tail);

        for (Edge edge : edgeSet) {
            if (edge.getHead().equals(head) && edge.getTail().equals(tail)) {
                Node node = edge.getHead();
                node.setIndegree(node.getIndegree() - 1);

                edgeIds.remove(edge.getId());
                edgeSet.remove(edge);

                return true;
            }
        }

        return false;
    }

    @Override
    public long numNodes() {
        return nodes.size();
    }

    @Override
    public long numEdges() {
        int sum = 0;

        for (ArrayList<Edge> edgeSet : edges.values()) {
            sum += edgeSet.size();
        }

        return sum;
    }

    @Override
    public String[] topoSort() {
        Iterator<Node> nodeIterator = new ZeroIndegreeIterator(new DiGraph(this));
        ArrayList<String> sorted = new ArrayList<>();

        while (nodeIterator.hasNext()) {
            sorted.add(nodeIterator.next().getLabel());
        }

        return sorted.toArray(new String[sorted.size()]);
    }

    private class ZeroIndegreeIterator implements Iterator<Node> {
        private ArrayDeque<Node> zeroIndegreeNodes;

        private DiGraph graph;

        ZeroIndegreeIterator(DiGraph graph) {
            this.graph = graph;

            zeroIndegreeNodes = new ArrayDeque<>();
        }

        public boolean hasNext() {
            return graph.nodes.size() != 0;
        }

        public Node next() {
            if (zeroIndegreeNodes.size() != 0) {
                Node node = zeroIndegreeNodes.removeFirst();
                graph.delNode(node.getLabel());

                return node;
            }

            for (Node node : graph.nodes.values()) {
                if (node.getIndegree() == 0) {
                    zeroIndegreeNodes.push(node);
                }
            }

            return next();
        }
    }
}
