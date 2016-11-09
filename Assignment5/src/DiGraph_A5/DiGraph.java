package DiGraph_A5;
public class DiGraph implements DiGraphInterface {

    // in here go all your data and methods for the graph
    // and the topo sort operation

    public DiGraph ( ) { // default constructor
        // explicitly include this
        // we need to have the default constructor
        // if you then write others, this one will still be there
    }

    @Override
    public boolean addNode(long idNum, String label) {
        return false;
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
