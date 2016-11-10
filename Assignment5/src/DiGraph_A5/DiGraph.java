package DiGraph_A5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A {@code DiGraph} is a directed graph composed of nodes and edges.
 */
public class DiGraph implements DiGraph_Interface {
    private ArrayList<Long> edgeIds;
    private ArrayList<Long> nodeIds;

    private HashMap<String, Node> nodes;
    private HashMap<Node, ArrayList<Edge>> edges;

    /**
     * Create a new directed graph.
     */
    public DiGraph() {
        edges = new HashMap<>();
        nodes = new HashMap<>();

        edgeIds = new ArrayList<>();
        nodeIds = new ArrayList<>();
    }

    /**
     * Create a copy of a directed graph.
     * @param graph The graph to make a copy of.
     */
    public DiGraph(DiGraph graph) {
        edges = graph.edges;
        nodes = graph.nodes;

        edgeIds = graph.edgeIds;
        nodeIds = graph.nodeIds;
    }

    /**
     * Add a node to the graph.
     * @param idNum The id of the node being added. This must be greater
     *              than or equal to 0, and unique across all nodes in
     *              the graph.
     * @param label The label of the node being added. This must be
     *              unique across all nodes in the graph.
     * @return {@code true} if the node was successfully inserted,
     *         {@code false} otherwise. Reasons for {@code false} being
     *         returned include the label not being unique, the id not
     *         being unique, or the id being less than 0.
     */
    @Override
    public boolean addNode(long idNum, String label) {
        // Check if the id and label are valid and unique
        if (nodes.containsKey(label)
                || idNum < 0
                || nodeIds.contains(idNum)) {
            return false;
        }

        // Add the node to the lookup table, and create a spot for it in
        // the adjacency list.
        Node n = new Node(idNum, label);
        nodes.put(label, n);
        edges.put(n, new ArrayList<>());
        nodeIds.add(idNum);

        return true;
    }

    /**
     * Add an edge to the graph.
     * @param idNum The id of the edge being added. This must be greater
     *              than or equal to 0, and must be unique across all
     *              edges in the graph.
     * @param sLabel The label of the node to start the edge at. In
     *               other words, the tail of the edge.
     * @param dLabel The label of the node to end the edge at. In other
     *               words, the head of the edge.
     * @param weight The weight of the edge.
     * @param eLabel The label of the edge.
     * @return {@code true} if the edge was successfully created,
     *         {@code false} otherwise. Reasons for {@code false} being
     *         returned include the edge already existing, either the
     *         node with the label {@code sLabel} or the node with the
     *         label {@code dLabel} not existing, or the id being
     *         invalid or not unique.
     */
    @Override
    public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
        // Make sure ID is unique
        if (edgeIds.contains(idNum)) {
            return false;
        }

        Node tail = nodes.get(sLabel);
        Node head = nodes.get(dLabel);

        if (tail == null || head == null) {
            return false;
        }

        // Traverse existing edges for the starting node, and make sure
        // this edge doesn't already exist.
        ArrayList<Edge> nodeEdges = edges.get(tail);

        for (Edge e : nodeEdges) {
            if (e.getTail().equals(tail) && e.getHead().equals(head)) {
                return false;
            }
        }

        // Create the new edge
        nodeEdges.add(new Edge(idNum, tail, head, weight, eLabel));
        head.setIndegree(head.getIndegree() + 1);
        edgeIds.add(idNum);

        return true;
    }

    /**
     * Remove a node from the graph.
     *
     * This has the side effect of removing all edges that were
     * connected to the node.
     * @param label The label of the node to delete.
     * @return {@code true} if the node was successfully removed,
     *         {@code false} if there was no node with the label
     *         {@code label}.
     */
    @Override
    public boolean delNode(String label) {
        Node node = nodes.get(label);

        if (node == null) {
            return false;
        }

        // Delete any edges associated with the node being removed
        for (Node n : nodes.values()) {
            delEdge(n.getLabel(), node.getLabel());
            delEdge(node.getLabel(), n.getLabel());
        }

        edges.remove(node);

        nodeIds.remove(node.getId());
        nodes.remove(node.getLabel());

        return true;
    }

    /**
     * Remove an edge from the graph.
     * @param sLabel The label of the node where the edge starts.
     * @param dLabel The label of the node where the edge ends.
     * @return {@code true} if the edge was successfully deleted,
     *         {@code false} if there was no edge between the given
     *         nodes, or if the given nodes don't exist.
     */
    @Override
    public boolean delEdge(String sLabel, String dLabel) {
        Node head = nodes.get(dLabel);
        Node tail = nodes.get(sLabel);

        if (head == null || tail == null) {
            return false;
        }

        ArrayList<Edge> edgeSet = edges.get(tail);

        // TODO: More efficient edge lookup
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

    /**
     * Get the number of nodes in the graph.
     * @return The number of nodes in the graph.
     */
    @Override
    public long numNodes() {
        return nodes.size();
    }

    /**
     * Get the number of edges in the graph.
     * @return The number of edges in the graph.
     */
    @Override
    public long numEdges() {
        int sum = 0;

        for (ArrayList<Edge> edgeSet : edges.values()) {
            sum += edgeSet.size();
        }

        return sum;
    }

    /**
     * Get a topographical sort of the graph.
     * @return A topographically sorted array of the labels of the nodes
     *         in the graph. If there is a cycle in the graph,
     *         {@code null} is returned instead.
     */
    @Override
    public String[] topoSort() {
        Iterator<Node> nodeIterator = new ZeroIndegreeIterator(new DiGraph(this));
        ArrayList<String> sorted = new ArrayList<>();

        while (nodeIterator.hasNext()) {
            Node next = nodeIterator.next();

            if (next == null) {
                break;
            }

            sorted.add(next.getLabel());
        }

        // If the sorted list doesn't contain the same number of nodes
        // as the original graph, a cycle was found.
        if (sorted.size() != numNodes()) {
            return null;
        }

        return sorted.toArray(new String[sorted.size()]);
    }

    /**
     * An iterator that traverses through a graph in topographical
     * order.
     *
     * This means that all nodes with no edges pointing to them are
     * presented first. As each node is returned, it is removed from the
     * remaining data. After all nodes with no edges pointing to them
     * are returned, the next set of nodes with no edges pointing to
     * them is found. This continues until there are no nodes left, or a
     * cycle is found.
     */
    private class ZeroIndegreeIterator implements Iterator<Node> {
        private ArrayDeque<Node> zeroIndegreeNodes;

        private DiGraph graph;

        /**
         * Create a new iterator.
         * @param graph The graph to iterate through.
         */
        ZeroIndegreeIterator(DiGraph graph) {
            this.graph = graph;

            zeroIndegreeNodes = new ArrayDeque<>();
        }

        /**
         * Determine if the iterator has more nodes to return.
         * @return {@code true} if there are more nodes to return,
         *         {@code false} otherwise.
         */
        public boolean hasNext() {
            return graph.nodes.size() != 0;
        }

        /**
         * Get the next node.
         * @return The next node in topographical order.
         */
        public Node next() {
            // If we still have nodes with no edges pointing to them,
            // return the next node from that set.
            if (zeroIndegreeNodes.size() != 0) {
                Node node = zeroIndegreeNodes.removeFirst();
                graph.delNode(node.getLabel());

                return node;
            }

            // We've run out of nodes with nothing pointing to them, so
            // find the next set of nodes like that.
            for (Node node : graph.nodes.values()) {
                if (node.getIndegree() == 0) {
                    zeroIndegreeNodes.push(node);
                }
            }

            if (zeroIndegreeNodes.size() == 0 && graph.numNodes() != 0) {
                return null;
            }

            return next();
        }
    }
}
