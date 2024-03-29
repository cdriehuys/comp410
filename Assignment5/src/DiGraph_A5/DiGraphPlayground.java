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
        exTest();

        delEdgeCase2();

        topoCase5();
        topoCaseX();
    }

    private static void delEdgeCase2() {
        DiGraph graph = new DiGraph();
        Assert.assertFalse(graph.delEdge("f", "s"));
        graph.addNode(1, "f");
        graph.addNode(3, "s");
        graph.addEdge(0, "f", "s", 0, null);
        Assert.assertTrue(graph.delEdge("f", "s"));
        Assert.assertFalse(graph.delEdge("f", "s"));
        Assert.assertTrue(graph.addEdge(0, "f", "s", 0, null));
        Assert.assertTrue(graph.delEdge("f", "s"));
    }

    private static void exTest(){
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

    private static void printTOPO(String[] toPrint){
        System.out.print("TOPO Sort: ");
        for (String string : toPrint) {
            System.out.print(string+" ");
        }
        System.out.println();
    }

    private static void topoCase5() {
        DiGraph graph = new DiGraph();
        graph.addNode(1, "p");
        graph.addNode(4, "a");
        graph.addNode(3, "g");
        graph.addNode(2, "e");
        graph.addEdge(0, "p", "a", 0, null);
        graph.addEdge(1, "a", "g", 0, null);
        graph.addEdge(2, "g", "e", 0, null);
        graph.addEdge(3, "e", "p", 0, null);
        graph.addEdge(4, "p", "g", 0, null);
        graph.addEdge(5, "a", "e", 0, null);

        String[] sorted = graph.topoSort();

        Assert.assertNull(sorted);
    }

    private static void topoCaseX() {
        DiGraph graph = new DiGraph();
        graph.addNode(0, "4");
        graph.addNode(1, "1");
        graph.addNode(2, "8");
        graph.addNode(3, "2");
        graph.addNode(4, "7");
        graph.addNode(5, "5");
        graph.addEdge(0, "2", "5", 0, null);
        graph.addEdge(1, "4", "7", 0, null);
        graph.addEdge(2, "1", "8", 0, null);
        graph.addEdge(3, "2", "8", 0, null);
        graph.addEdge(4, "4", "5", 0, null);
        graph.addEdge(5, "1", "2", 0, null);
        graph.addEdge(6, "1", "5", 0, null);
        graph.addEdge(7, "1", "7", 0, null);
        graph.addEdge(8, "8", "5", 0, null);
        graph.addEdge(9, "1", "4", 0, null);
        graph.addEdge(10, "5", "7", 0, null);
        graph.addEdge(11, "4", "2", 0, null);
        graph.addEdge(12, "8", "7", 0, null);
        graph.addEdge(13, "4", "8", 0, null);
        graph.addEdge(14, "2", "7", 0, null);

        String[] sorted = graph.topoSort();

        Assert.assertArrayEquals(new String[] {"1", "4", "2", "8", "5", "7"}, sorted);
    }
}
