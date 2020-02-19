import java.util.*;

public class BreadthFirstSearch {
    static Edge curr_q_node;
    static boolean[] visited_arr;
    static boolean[] assignedParent;
    static int[] parent;
    static Queue<Edge> queue = new LinkedList<>();
    static long time = 0;
    public static long getTime(){
        return time;
    }
    public static void setTime(long endTimeMs){
        time = endTimeMs;
    }
    public static boolean bfs(List<Edge> adjacency[]){
        int n = Graph.getV();
        int source = Graph.getSource();
        int dest = Graph.getDest();
        List<Edge> cv;
        visited_arr = new boolean[n];
        assignedParent = new boolean[n];
        assignedParent[source] = true;
        queue.add(new Edge(source, 0));
        parent = new int[n];
        Arrays.fill(parent, -1);
        long startTime = System.nanoTime();
        System.out.print("\nBreadth First Search: ");
        while(!queue.isEmpty()){
            curr_q_node = queue.poll();
            System.out.print(curr_q_node.toNode + " ");
            visited_arr[curr_q_node.toNode] = true;
            cv = adjacency[curr_q_node.toNode];
            for(Edge edge: cv){
                int curr_vertex = edge.toNode;
                if(!visited_arr[curr_vertex] && edge.weight > 0){
                    visited_arr[curr_vertex] = true;
                    queue.add(edge);
                    if(!assignedParent[curr_vertex]){
                        parent[curr_vertex] = curr_q_node.toNode;
                        assignedParent[curr_vertex] = false;
                    }
                }
            }
        }

        long endTime = System.nanoTime() - startTime;
        //System.out.println("\n\nTime taken for bfs to run in nano-seconds: " +endTime);
        long endTimeMS =  endTime / 1_000_000;
        //System.out.println("\nTime taken for bfs to run in milli-seconds: " +endTimeMS);

        setTime(endTimeMS);

        if(visited_arr[dest]){
            System.out.print("\nShortest path between " +source+ " and " + dest + " is: ");
            System.out.println("having " + path_finder(parent, dest, -1, n) + " edges in between them");
        }
        else{
            System.out.println("\nThere is no path that exists between " +source+ " and " + dest);
        }
        return (visited_arr[dest] == true);
    }
    public static int path_finder(int[] parent, int v, int no_of_edges, int n){
        if (v >= 0)
        {
            no_of_edges = path_finder(parent, parent[v], no_of_edges, n);
            no_of_edges++;
            if (v < n)
                System.out.print(v + " ");
        }
        return no_of_edges;
    }
}
