import java.util.*;
import java.io.*;

public class GraphGeneration {
    static LinkedList<Integer>[] vertices;
    static Map<Integer, Integer> map = new HashMap<>();
    static int numOfVertices;
    static int maxNumOfEdges;
    static int numOfEdges;
    static int weight;
    static int a;
    static int b;
    static int edge_count;
    public static void setVertices(int numOfVertices){
        numOfVertices = numOfVertices;
    }
    public static int getNumOfVertices(){
        return numOfVertices;
    }
    public static void setNumOfEdges(int numOfEdges){
        numOfEdges = numOfEdges;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
//        Scanner s = new Scanner(System.in);
//        System.out.print("Enter the number of vertices for graph: ");
        for(int k =5; k<=1000; k++){
            numOfVertices = k;
            setVertices(numOfVertices);
            maxNumOfEdges = (numOfVertices *(numOfVertices - 1))/2; //max number of edges in complete graph
            numOfEdges = (int) (maxNumOfEdges*0.6);
            setNumOfEdges(numOfEdges);
            vertices = new LinkedList[numOfVertices];
            for(int v =0; v<numOfVertices; v++){
                vertices[v] = new LinkedList<>();
            }
            edge_count = 0;
            for(int i =1; i <= numOfEdges; i++){
                a = (int) (Math.random() * (numOfVertices - 1));
                b = (int) (Math.random() * (numOfVertices) );
                weight = (int) (Math.random() * 15) + 1;
                if(a!=b && !(vertices[a].contains(b)) && !(vertices[b].contains(a)) && b!=0){
                    vertices[a].add(b);
                    vertices[a].add(weight);
                    edge_count++;
                    //System.out.println(edge_count);
                }
            }
            System.out.println("Vertices: " +numOfVertices + " , Max Number of Edges: " +maxNumOfEdges + " , Random Number of Edges: " +edge_count);
            map.put(numOfVertices, edge_count );
            fileGeneration(numOfVertices, edge_count );
        }
        String csv_file = "./bfs_graphs/time.txt";
        FileWriter fileWriter = new FileWriter(csv_file);
        fileWriter.write("Nodes" + "\t" +"Edges"+"\n");
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            fileWriter.write(Integer.toString(entry.getKey()) + "\t" + Integer.toString(entry.getValue()));
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();


        System.out.println("File created..");
    }
    public static void fileGeneration(int numOfVertices, int edge_count) throws IOException{
        String new_file = "./bfs_graphs/" + numOfVertices + ".txt";
        FileWriter fileWriter = new FileWriter(new_file);
        fileWriter.write("// Nodes: "+numOfVertices+"\t Edges: "+edge_count+"\n\n");
        for(int i=0; i < numOfVertices; i++){
             LinkedList<Integer> nodes = vertices[i];
             for(int j=0; j<nodes.size(); j = j+2) {
                 fileWriter.write(Integer.toString(nodes.get(j)) + " " + Integer.toString(nodes.get(j+1)) + " ");
             }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
}
