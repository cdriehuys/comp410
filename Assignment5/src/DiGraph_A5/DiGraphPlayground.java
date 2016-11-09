package DiGraph_A5;

import org.junit.Assert;

public class DiGraphPlayground {

    public static void main (String[] args) {

        // thorough testing is your responsibility
        //
        // you may wish to create methods like
        //    -- print
        //    -- sort
        //    -- random fill
        //    -- etc.
        // in order to convince yourself your code is producing
        // the correct behavior
        //exTest();

        DiGraph graph = new DiGraph();
        graph.addNode(0, "a");
        graph.addNode(1, "b");
        graph.addNode(2, "c");
        graph.addNode(3, "d");

        graph.addEdge(0, "a", "b", 0, null);
        graph.addEdge(1, "b", "c", 0, null);
        graph.addEdge(2, "c", "d", 0, null);

        String[] sorted = graph.topoSort();

        Assert.assertArrayEquals(new String[] {"a", "b", "c", "d"}, sorted);
    }

    public static void exTest(){
        DiGraph d = new DiGraph();
        d.addNode(1, "f");
        d.addNode(3, "s");
        d.addNode(7, "t");
        d.addNode(0, "fo");
        d.addNode(4, "fi");
        d.addNode(6, "si");
        d.addEdge(0, "f", "s", 0, null);
        d.addEdge(1, "f", "si", 0, null);
        d.addEdge(2, "s", "t", 0, null);
        d.addEdge(3, "fo", "fi", 0, null);
        d.addEdge(4, "fi", "si", 0, null);
        System.out.println("numEdges: "+d.numEdges());
        System.out.println("numNodes: "+d.numNodes());
        printTOPO(d.topoSort());

    }
    public static void printTOPO(String[] toPrint){
        System.out.print("TOPO Sort: ");
        for (String string : toPrint) {
            System.out.print(string+" ");
        }
        System.out.println();
    }

}
