package DiGraph_A5;


import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A {@code DiGraph} is a directed graph composed of {@link Node}s and
 * {@link Edge}s.
 */
public class DiGraph implements DiGraph_Interface {
    private ArrayDeque<Node> zeroIndegreeNodes;

    private enum EdgeDelete {DEL_BOTH, DEL_HEAD, DEL_TAIL}

    private HashMap<String, Node> nodes;

    private HashSet<Long> edgeIds;
    private HashSet<Long> nodeIds;

    private long numEdges;
    private long numNodes;

    /**
     * Create a new directed graph.
     */
    public DiGraph() {
        zeroIndegreeNodes = new ArrayDeque<>();

        nodes = new HashMap<>();

        edgeIds = new HashSet<>();
        nodeIds = new HashSet<>();

        numEdges = 0;
        numNodes = 0;
    }

    /**
     * Create a copy of a directed graph.
     * @param graph The graph to make a copy of.
     */
    public DiGraph(DiGraph graph) {
        zeroIndegreeNodes = new ArrayDeque<>(graph.zeroIndegreeNodes);

        nodes = new HashMap<>(graph.nodes);

        edgeIds = new HashSet<>(graph.edgeIds);
        nodeIds = new HashSet<>(graph.nodeIds);

        numEdges = graph.numEdges;
        numNodes = graph.numNodes;
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
        nodeIds.add(idNum);

        // A new node has nothing pointing to it, so it automatically
        // has an indegree of zero.
        zeroIndegreeNodes.push(n);

        numNodes++;

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

        // Make sure edge doesn't already exist
        if (tail.getOutEdges().containsKey(head)) {
            return false;
        }

        // Create the new edge
        Edge edge = new Edge(idNum, tail, head, weight, eLabel);

        tail.addEdge(edge);
        head.addEdge(edge);

        if (zeroIndegreeNodes.contains(head)) {
            zeroIndegreeNodes.remove(head);
        }

        edgeIds.add(idNum);
        numEdges++;

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

        for (Node tail : node.getInEdges().keySet()) {
            delEdge(tail, node, EdgeDelete.DEL_TAIL);
        }

        for (Node head : node.getOutEdges().keySet()) {
            delEdge(node, head, EdgeDelete.DEL_HEAD);
        }

        nodeIds.remove(node.getId());
        nodes.remove(node.getLabel());

        numNodes--;

        return true;
    }

    public boolean delEdge(Node tail, Node head) {
        return delEdge(tail, head, EdgeDelete.DEL_BOTH);
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

        return delEdge(tail, head);
    }

    /**
     * Get the number of nodes in the graph.
     * @return The number of nodes in the graph.
     */
    @Override
    public long numNodes() {
        return numNodes;
    }

    /**
     * Get the number of edges in the graph.
     * @return The number of edges in the graph.
     */
    @Override
    public long numEdges() {
        return numEdges;
    }

    /**
     * Get a topographical sort of the graph.
     * @return A topographically sorted array of the labels of the nodes
     *         in the graph. If there is a cycle in the graph,
     *         {@code null} is returned instead.
     */
    @Override
    public String[] topoSort() {
        Iterator<Node> nodeIterator = new ZeroIndegreeIterator(this);
        // TODO: This will break if there are more than MAX_INT nodes.
        String[] sorted = new String[(int) numNodes()];

        for (int i = 0; nodeIterator.hasNext(); i++) {
            Node next = nodeIterator.next();

            if (next == null) {
                return null;
            }

            sorted[i] = next.getLabel();
        }

        // If the sorted list doesn't contain the same number of nodes
        // as the original graph, a cycle was found.
        if (sorted.length != numNodes()) {
            return null;
        }

        return sorted;
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
        private DiGraph graph;

        /**
         * Create a new iterator.
         * @param graph The graph to iterate through. A copy is made for
         *              the iterator so that the actual graph is not
         *              modified.
         */
        ZeroIndegreeIterator(DiGraph graph) {
            this.graph = new DiGraph(graph);
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
            if (graph.zeroIndegreeNodes.size() != 0) {
                Node node = graph.zeroIndegreeNodes.removeFirst();
                graph.delNode(node.getLabel());

                return node;
            }

            // If we can't find any more nodes with no edges pointing to
            // them, but we still have nodes in the graph, we have
            // reached a cycle, so return null.
            if (graph.zeroIndegreeNodes.size() == 0 && graph.numNodes() != 0) {
                return null;
            }

            return next();
        }
    }

    public boolean delEdge(Node tail, Node head, EdgeDelete deleteBehavior) {
        Edge edge = head.getInEdges().get(tail);

        if (edge == null) {
            return false;
        }

        if (deleteBehavior == EdgeDelete.DEL_BOTH
                || deleteBehavior == EdgeDelete.DEL_HEAD) {
            head.removeEdge(edge);
        }

        if (deleteBehavior == EdgeDelete.DEL_BOTH
                || deleteBehavior == EdgeDelete.DEL_TAIL) {
            tail.removeEdge(edge);
        }

        if (head.getIndegree() == 0) {
            zeroIndegreeNodes.push(head);
        }

        edgeIds.remove(edge.getId());
        numEdges--;

        return true;
    }
}
