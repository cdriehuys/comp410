package DiGraph_A5;

import java.util.HashMap;

public class DiGraph implements DiGraph_Interface {
    private HashMap<String, Node> nodes;

    // in here go all your data and methods for the graph
    // and the topo sort operation

    public DiGraph ( ) {
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(long idNum, String label) {
        if (idNum < 0) {
            throw new IllegalArgumentException("idNum must be greater than or equal to 0.");
        }

        if (nodes.containsKey(label)) {
            return false;
        }

        Node n = new Node(idNum, label);
        nodes.put(label, n);

        return true;
    }

    @Override
    public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
        return false;
    }

    @Override
    public boolean delNode(String label) {
        return false;
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
