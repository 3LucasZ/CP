package Other.TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;

/*
PROB: butter
LANG: JAVA
 */

public class butter {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int P;
    static int C;

    static int[] cows;
    static int[][] dist;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        //parse
        setup("butter");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cows = new int[N];
        dist = new int[P+1][P+1];
        graph = new ArrayList[P+1]; for (int i=1;i<=P;i++) graph[i] = new ArrayList<>();

        for (int i=0;i<N;i++){
            int cow = Integer.parseInt(br.readLine());
            cows[i]=cow;
        }
        for (int i=0;i<C;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v,c));
            graph[v].add(new Edge(u,c));
        }

        //APSP Djikstra
        for (int i=1;i<=P;i++)dji(i);

        //Complete search for best place to put butter
        int ans = INF;
        for (int i=1;i<=P;i++){
            int tot = 0;
            for (int cow : cows){
                if (dist[i][cow]==INF) {
                    tot=INF;
                    break;
                }
                tot += dist[i][cow];
            }
            ans=Math.min(ans,tot);
        }

        out.println(ans);
        out.close();
    }
    static int INF = Integer.MAX_VALUE;
    public static void dji(int n) {
        //init
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b)->a.weight-b.weight);
        int[] distance = new int[P+1];
        boolean[] processed = new boolean[P+1];
        Arrays.fill(distance, INF);
        distance[n]=0;
        pq.add(new Edge(n, 0));

        //djikstra sssp
        while (!pq.isEmpty()) {
            Edge next = pq.poll();
            if (processed[next.node]) continue;
            processed[next.node]=true;
            for (Edge child : graph[next.node]) {
                if (distance[next.node]+child.weight < distance[child.node]){
                    distance[child.node]=distance[next.node]+child.weight;
                    pq.add(new Edge(child.node,distance[child.node]));
                }
            }
        }
        dist[n]=distance;
    }
    private static class Edge {
        int node;
        int weight;
        public Edge(int node, int weight){
            this.node=node;
            this.weight=weight;
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