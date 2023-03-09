package Solutions.USACO.Season2018_2019.Dec2018.Gold;

import java.io.*;
import java.util.*;
/*
USACO 2018 December Contest, Gold
Problem 1. Fine Dining
Thoughts:
Wow, another very elegant problem...
djikstra1 finds the distance from all nodes to N
djikstra2 finds the distance from all nodes to N if forced to use a haybale
if dist2[i]<dist1[i] then print 1 for eating the haybale
somewhat close ... when I was reasoning, but kinda far from the solution ... makes sense now though
 */
public class FineDining {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;
    static int K;
    static ArrayList<Bale> bales = new ArrayList<>();

    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        //parse
        setup("dining");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+2]; for (int i=1;i<=N+1;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v,c));
            graph[v].add(new Edge(u,c));
        }
        for (int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            bales.add(new Bale(node,cost));
        }

        //log
        Djikstra djk1 = new Djikstra(graph, N, N);
        if (debug){
            System.out.println(Arrays.toString(djk1.distance));
        }
        for (Bale bale : bales){
            graph[N+1].add(new Edge(bale.node,djk1.distance[bale.node]-bale.cost));
        }
        Djikstra djk2 = new Djikstra(graph, N+1,N+1);
        if (debug){
            System.out.println(Arrays.toString(djk2.distance));
        }

        //ret
        for (int node=1;node<N;node++){
            if (djk2.distance[node]<=djk1.distance[node]) out.println(1);
            else out.println(0);
        }
        out.close();
    }
    private static class Bale {
        int node;
        int cost;
        public Bale(int node, int cost){
            this.node=node;
            this.cost=cost;
        }
    }
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
        public Djikstra(ArrayList<Edge>[] graph, int source,int nodes){
            distance = new int[nodes+1];
            boolean[] visited = new boolean[nodes+1];
            PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
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



















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
