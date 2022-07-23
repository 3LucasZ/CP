package Misc.Helper;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DjikstraWrapper {
    private static class Edge {
        int v;
        int cost;
        public Edge(int v, int c){
            this.v=v;
            this.cost=c;
        }
    }
    private static class Djikstra {
        //1 indexed graph
        int[] distance;
        public Djikstra(ArrayList<Edge>[] graph, int source, int nodes){
            distance = new int[nodes+1];
            boolean[] visited = new boolean[nodes+1];
            PriorityQueue<Edge> pq = new PriorityQueue<>((a, b)->a.cost-b.cost);
            pq.add(new Edge(source, 0));
            while (!pq.isEmpty()){
                Edge next = pq.poll();
                if (visited[next.v]) continue;
                visited[next.v]=true;
                distance[next.v]=next.cost;
                for (Edge child : graph[next.v]){
                    if (!visited[child.v]) pq.add(new Edge(child.v,next.cost+child.cost));
                }
            }
        }
    }
}
