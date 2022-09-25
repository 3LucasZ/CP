package USACOGuide.UsacoGuideGold.ShortestPath;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Shortest Routes I
USACO Gold Guide - Shortest Paths (Non-neg edge weights) - Easy
Thoughts:
First ever Djikstra!!
Things to note: INF is 1e15 to prevent overflow at MAXLONG
2 custom classes: Edge and Pair
nodes are only processed ONCE (greedy)
keep distances as longs!
Single source
I still do not know EXACTLY why it works but it does : )
 */
public class ShortestRoutesI {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //graph
    static int N;
    static int M;
    static ArrayList<Edge>[] adjList;

    //helper
    static final long INF = (long)1e15;
    public static void main(String[] args) throws IOException {
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
        }

        //init
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
            if (a.distance > b.distance) return 1;
            return -1;
        });
        long[] distance = new long[N+1];
        boolean[] processed = new boolean[N+1];
        Arrays.fill(distance, INF);

        //source node: 1
        distance[1]=0;
        pq.add(new Pair(0, 1));

        //djikstra sssp
        while (!pq.isEmpty()) {
            Pair next = pq.poll();
            if (processed[next.node]) continue;
            processed[next.node]=true;
            for (Edge child : adjList[next.node]) {
                if (distance[next.node]+child.weight < distance[child.node]){
                    distance[child.node]=distance[next.node]+child.weight;
                    pq.add(new Pair(distance[child.node], child.node));
                }
            }
        }

        //print
        for (int i=1;i<=N;i++) out.print(distance[i]+" ");
        out.close();
    }
    private static class Pair {
        long distance;
        int node;
        public Pair(long d, int n){
            distance=d;
            node=n;
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