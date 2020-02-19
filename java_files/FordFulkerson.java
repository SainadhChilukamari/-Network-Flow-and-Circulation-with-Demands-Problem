import java.util.*;
import java.util.concurrent.*;
public class FordFulkerson {
    static int max_flow = 0;
    static int V;
    static List<Edge> residual_graph[];
    static Edge vertex = null;
    static long time = 0;
    public static long getFFATime(){
        return time;
    }
    public static void setFFATime(long endTimeMs){
        time = endTimeMs;
    }
    @SuppressWarnings("unchecked")
    public static int maxFlow(List<Edge> adjacency[]){
        int v, u; int index =0;
        V = Graph.getV();
        residual_graph = adjacency;
        int source = 0;
        int dest = V-1;
        System.out.println("source: " +source + " dest: " +dest);
        Graph.setSource(source);
        Graph.setDest(dest);
        long startTime = System.nanoTime();
        while(BreadthFirstSearch.bfs(residual_graph)){
            int path_flow = Integer.MAX_VALUE;
            for(v = dest; v!=source; v = BreadthFirstSearch.parent[v]){
                u = BreadthFirstSearch.parent[v];
                List<Edge> edges_list = residual_graph[u];
                for(int i =0; i<edges_list.size(); i++){
                    if(v == edges_list.get(i).toNode){
                        index = i;
                        break;
                    }
                }
                path_flow = Math.min(path_flow, residual_graph[u].get(index).weight);
            }
            System.out.println("Min-Path-flow: " +path_flow);
            max_flow += path_flow;
            for(v = dest; v!=source; v = BreadthFirstSearch.parent[v]){
                u = BreadthFirstSearch.parent[v];
                List<Edge> edges_list = residual_graph[u];
                for(int i=0; i<edges_list.size(); i++){
                    if(v == edges_list.get(i).toNode){
                        vertex = edges_list.get(i);
                        index = i;
                    }
                }
                residual_graph[u].get(index).weight -= path_flow;
                index = -1;
                if(!residual_graph[vertex.toNode].isEmpty()){
                    List<Edge> add_edge = residual_graph[vertex.toNode];
                    for (int i =0; i<add_edge.size(); i++) {
                        if (u == add_edge.get(i).toNode) {
                            index = i;
                        }
                    }
                    if(index != -1){
                        residual_graph[vertex.toNode].get(index).weight += path_flow;
                    }
                    else{
                        residual_graph[vertex.toNode].add(new Edge(u, path_flow));
                    }
                }
                else{
                    List<Edge> new_list = new LinkedList<>();
                    new_list.add(new Edge(u, path_flow));
                    residual_graph[vertex.toNode] = new_list;
                }
            }
        }
        long endTime = System.nanoTime() - startTime;
        //System.out.println("\n\nTime taken for ford-fulkerson to run in nano-seconds: " +endTime);
        long endTimeS = endTime / 1_000_000_000;
        //System.out.println("\nTime taken for ford-fulkerson to run in seconds: " +endTimeS);
        setFFATime(endTimeS);
        return max_flow;
    }
}
