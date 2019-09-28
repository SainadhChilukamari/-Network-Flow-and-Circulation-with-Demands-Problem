import java.util.*;
import java.io.*;

public class GraphGeneration {
    static ArrayList<ArrayList<Integer>> adj;
    static int numOfVertices;
    static int maxNumOfEdges;
    static int numOfEdges;
    static int weight;
    static int a;
    static int b;
    static int index;
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
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of vertices for graph: ");
        numOfVertices = s.nextInt();
        setVertices(numOfVertices);
        maxNumOfEdges = (numOfVertices *(numOfVertices - 1))/2; //max number of edges in complete graph
        int range = (int) (maxNumOfEdges*0.7);
        numOfEdges = (int) (maxNumOfEdges*0.3);
        //numOfEdges = (int)(Math.random()*range) + (int) (0.3 * maxNumOfEdges); // random number of edges from max number of edges
        setNumOfEdges(numOfEdges);
        adj = new ArrayList<>(numOfVertices);
        for(int v=0; v < numOfVertices; v++){
            adj.add(new ArrayList<>());
        }
        //System.out.println("Edges created..... ");
        edge_count = 0;
        long startTime = System.nanoTime();
        for(int i =1; i <= numOfEdges; i++){
            index = -1;
            a = (int) (Math.random() * (numOfVertices - 1));
            b = (int) (Math.random() * (numOfVertices) );
            weight = (int) (Math.random() * 15) + 1;
            if(a!=b){
                ArrayList<Integer> a_nodes = adj.get(a);
                for(int j =0; j < a_nodes.size(); j = j+2){
                    if(b == a_nodes.get(j)){
                        index = j;
                    }
                }
//                ArrayList<Integer> b_nodes = adj.get(b);
//                for(int k =0; k<b_nodes.size(); k = k+2){
//                    if(a == b_nodes.get(k)){
//                        index = k;
//                    }
//                }
                if(index == -1 && b!=0){
                    adj.get(a).add(b);
                    adj.get(a).add(weight);
                    edge_count++;
                    //System.out.println(a + " " + b + " " + weight);
                }
            }
        }
        System.out.println("Vertices: " +numOfVertices + " , Max Number of Edges: " +maxNumOfEdges + " , Random Number of Edges: " +edge_count);
        long endTime = System.nanoTime() - startTime;
        System.out.println("\n\nTime taken to generate random graph in nano-seconds: " +endTime);
        endTime = endTime / 1_000_000_000;
        System.out.println("\n\nTime taken to generate random graph in seconds: " +endTime);
        fileGeneration();
        System.out.println("File created..");
    }
    public static void fileGeneration() throws IOException{
        String new_file = "test_50000.txt";
        FileWriter fileWriter = new FileWriter(new_file);
        for(int i=0; i < getNumOfVertices(); i++){
             ArrayList<Integer> nodes = adj.get(i);
             for(int j=0; j<nodes.size(); j = j+2){
                 fileWriter.write(Integer.toString(nodes.get(j)) + " " + Integer.toString(nodes.get(j+1)) + " ");
             }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
}
