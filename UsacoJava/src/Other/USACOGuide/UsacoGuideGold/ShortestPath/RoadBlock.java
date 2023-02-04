package Other.USACOGuide.UsacoGuideGold.ShortestPath;

import java.io.*;
import java.util.*;
/*
USACO 2014 February Contest, Silver
Problem 2. Roadblock
USACO Silver/Gold Training
Thoughts:
First try:
do Djikstra once to find init best cost
do Djikstra E times, doubling the path length first, to find alteration best cost
print(init-alter)
This TLE in cases 9&10 :(
looked at solution, and crazy observation!!
you only need to double the edges that are along the init best cost path!
do Djikstra V times
O(V(V+ElogV)) algorithm! quite elegant :)
 */
public class RoadBlock {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //graph
    static int N;
    static int M;
    static ArrayList<Edge>[] adjList;
    static ArrayList<Edge> importantEdges;
    //helper
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("rblock.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1]; for (int i=1;i<=N;i++) adjList[i]=new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new Edge(v, w));
            adjList[v].add(new Edge(u, w));
        }

        //logic
        int noInterferenceLength = shortestPath(true);
        if (!submission) System.out.println(noInterferenceLength);
        int interferenceLength = 0;


        for (Edge v : importantEdges){
            v.weight*=2;
            interferenceLength = Math.max(shortestPath(false), interferenceLength);
            v.weight/=2;
        }

        if (!submission) System.out.println(interferenceLength);

        //turn in answer
        out.println(interferenceLength-noInterferenceLength);
        out.close();
    }
    public static int shortestPath(boolean init){
        //init
        PriorityQueue<Query> pq = new PriorityQueue<>((a, b)->a.distance-b.distance);
        int[] distance = new int[N+1];
        boolean[] processed = new boolean[N+1];
        Arrays.fill(distance, INF);
        ArrayList<Edge> pathEdges = new ArrayList<>();

        //source node: 1
        distance[1]=0;
        pq.add(new Query(0, 1, new Edge(0,0)));

        //djikstra sssp
        while (!pq.isEmpty()) {
            Query next = pq.poll();
            if (processed[next.node]) continue;
            processed[next.node]=true;
            pathEdges.add(next.edge);
            if (processed[N]) break;

            for (Edge child : adjList[next.node]) {
                if (distance[next.node]+child.weight < distance[child.node]){
                    distance[child.node]=distance[next.node]+child.weight;
                    pq.add(new Query(distance[child.node], child.node, child));
                }
            }
        }
        if (init) importantEdges = pathEdges;
        return distance[N];
    }
    private static class Query {
        int distance;
        Edge edge;
        int node;
        public Query(int d, int n, Edge e){
            distance=d;
            node=n;
            edge=e;
        }
    }
    private static class Edge {
        int node;
        int weight;
        public Edge(int node, int weight){
            this.node=node;
            this.weight=weight;
        }
    }
}
