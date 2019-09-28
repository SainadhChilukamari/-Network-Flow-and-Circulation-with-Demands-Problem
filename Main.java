import java.io.*;
import java.lang.*;
class Main{
    public static String fileName; // Given fileName
    public static int source; // Given source
    public static int dest;  // Given destination
    public static Graph set_graph;
    public static Graph graph;
    public static Graph circulationGraph;
    public static int sumOfDemands;
    public static int max_flow;
    public static void main(String[] args) throws IOException {
        switch (args[0].toLowerCase()){
            case "-b":
                fileName = args[1];
                source = Integer.parseInt(args[2]);
                dest = Integer.parseInt(args[3]);
                set_graph = new Graph(source, dest);
                graph  = new Graph(fileName);
                //System.out.println("\nOriginal Graph Representation...");
                //graph.display(graph.adjacency);
                BreadthFirstSearch.bfs(graph.adjacency);
                break;
            case "-f":
                fileName = args[1];
                graph = new Graph(fileName);
                //System.out.println("\nOriginal Graph Representation...");
                //graph.display(graph.adjacency);
                max_flow = FordFulkerson.maxFlow(graph.adjacency);
                System.out.println("\nmax flow for the given graph is: " +max_flow);
                //System.out.println("\nResidual Graph Representation...");
                //graph.display(FordFulkerson.residual_graph);
                break;
            case "-c":
                fileName = args[1];
                circulationGraph = new Graph(fileName, -1);
                System.out.println("\nOriginal Graph Representation...");
                circulationGraph.display(circulationGraph.adjacency);
                sumOfDemands = Graph.getSum0fDemands();
                max_flow = FordFulkerson.maxFlow(circulationGraph.adjacency);
                System.out.println("\nmax flow for the given graph is: " +max_flow);
                if(max_flow == sumOfDemands){
                    System.out.println("\nGraph has circulation ");
                }
                else{
                    System.out.println("\nGraph doesn't have circulation in it because sumOfDemands & sumOfSupplies: " +sumOfDemands + " is not equal to maxFlow: " +max_flow);
                }
                break;
        }
    }
}