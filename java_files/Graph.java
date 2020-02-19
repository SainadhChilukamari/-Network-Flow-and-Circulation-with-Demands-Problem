import java.io.*;
import java.util.*;
class Graph {
    private static int source;
    private static int dest;
    private static int V; //No of vertices
    private static int sum0fDemands = 0;
    private static int sumOfSupplies = 0;
    private static boolean doDemandsMatchSupplies = true;
    List<Edge> adjacency[]; // List of lists - Adjacency List
    List<Integer> demand; // to store demand vertices
    List<Integer> supply; // to store supply vertices
    public static int getSource(){
        return source;
    }
    public static void setSource(int source){
        Graph.source = source;
    }
    public static int getDest(){
        return dest;
    }
    public static void setDest(int dest){
        Graph.dest = dest;
    }
    public static void setSum0fDemands(int sum0fDemands){
        Graph.sum0fDemands = sum0fDemands;
    }
    public static int getSum0fDemands(){
        return sum0fDemands;
    }
    public static int getV(){
        return V;
    }
    public static int edgeCount =0;
    public static void setV(int V){
        if(V>=0)
        {
            Graph.V =V;
        }
    }
    Graph(int source, int dest){
        setSource(source);
        setDest(dest);
    }
    @SuppressWarnings("unchecked")
    Graph(String fileName) throws IOException{
        int n =0;
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //bufferedReader.readLine();
        //bufferedReader.readLine();
        while(bufferedReader.readLine() != null){
            n++;
        }
        setV(n);
        V = getV();
        adjacency = new LinkedList[V];
        int fromNode, toNode, e, weight;
        String line; String[] parts;
        String splits = " +";  // Multiple whitespace as delimiter.
        bufferedReader = new BufferedReader(new FileReader(fileName));
        //System.out.println("Reading edges from text file...\n");
        for (int i = 0; i < V; i++) {
            adjacency[i] = new LinkedList<>();
        }
        //bufferedReader.readLine();
        //bufferedReader.readLine();
        for(e = 0; e < V; ++e){
            line = bufferedReader.readLine();
            parts = line.split(splits);
            fromNode = e;
            for(int i =0; i<parts.length-1; i= i+2){
                toNode = Integer.parseInt(parts[i]);
                weight = Integer.parseInt(parts[i+1]);
                //System.out.println("Edge from " + fromNode + " --(" + weight + ")-- " +toNode);
                edgeCount++;
                addEdge(fromNode, toNode, weight);
            }
        }
    }
    @SuppressWarnings("unchecked")
    Graph(String fileName, int circ) throws IOException{ // For Circulation Graph
        int numOfNodes =0; int sup_dem = 0; int v = 0;
        int fromNode, toNode, e, weight;
        demand = new LinkedList<>();
        supply = new LinkedList<>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while(bufferedReader.readLine() != null){
            numOfNodes++;
        }
        setV(numOfNodes + 2);
        V = getV();
        //System.out.println("Number of vertices: " +V);
        adjacency = new LinkedList[V];
        String line; String[] parts;
        String splits = " +";  // Multiple whitespace as delimiter.
        bufferedReader = new BufferedReader(new FileReader(fileName));
        System.out.println("Reading edges from text file...\n");
        for (v = 0; v < V; v++) {
            adjacency[v] = new LinkedList<>();
        }
        for(e = 0; e < numOfNodes; ++e){
            line = bufferedReader.readLine();
            parts = line.split(splits);
            fromNode = e;
            //System.out.println("sup_dem print: " + parts[0]);
            sup_dem = Integer.parseInt(parts[0]);
            if(sup_dem > 0){
                demand.add(sup_dem);
                System.out.println("Edge from " + (fromNode+1) + " --(" + sup_dem + ")-- " + (V-1));
                addEdge((fromNode+1), (V-1), sup_dem);
            }
            else if(sup_dem < 0){
                supply.add(sup_dem);
                System.out.println("Edge from " + 0 + " --(" + Math.abs(sup_dem) + ")-- " + (fromNode+1));
                addEdge(0, fromNode+1, Math.abs(sup_dem));
            }
            for(int i = 1; i<parts.length-1; i = i+2){
                toNode = Integer.parseInt(parts[i]);
                weight = Integer.parseInt(parts[i+1]);
                //System.out.println("Edge from " + (fromNode+1) + " --(" + weight + ")-- " + (toNode+1));
                addEdge(fromNode+1, toNode+1, weight);
            }
        }
        sum0fDemands = demand.stream().mapToInt(Integer::intValue).sum();
        sumOfSupplies = supply.stream().mapToInt(Integer::intValue).sum();
        setSum0fDemands(sum0fDemands);
        if(sum0fDemands!=Math.abs(sumOfSupplies)){
            doDemandsMatchSupplies = false;
            System.out.println("Given graph doesn't contain circulation in it because sumOfSupplies " +Math.abs(sumOfSupplies) + " doesn't match to sumOfDemands " +sum0fDemands);
            System.exit(0);
        }
    }
    public void addEdge(int fromNode, int toNode, int weight)
    {
        adjacency[fromNode].add(new Edge(toNode, weight));
    }
    public void display(List<Edge> adjacency[]) {
        int v;
        for(v = 0; v < V; ++v) {
            System.out.print("\nadj[" + v + "] ->" );
            for (Edge edge: adjacency[v]){
                System.out.print(" |" + edge.toNode + " | " + edge.weight + "| ->");
            }
        }
        System.out.println("");
    }
}