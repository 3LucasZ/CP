package Gold.UsacoGuideGold.ShortestPath;

import java.io.*;
import java.util.*;

public class Djikstra {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //graph
    static int N;
    static int M;
    static ArrayList<Edge>[] adjList;

    //helper
    static final int INF = Integer.MAX_VALUE;
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
            adjList[v].add(new Edge(u, w));
        }

        //init
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.distance-b.distance);
        int[] distance = new int[N+1];
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

        out.println(Arrays.toString(distance));
        out.close();
    }
    private static class Pair {
        int distance;
        int node;
        public Pair(int d, int n){
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
/*
https://csacademy.com/app/graph_editor/
5 7
1 5 3
1 3 8
5 3 4
1 2 2
4 2 10
2 3 2
3 4 2
 */
