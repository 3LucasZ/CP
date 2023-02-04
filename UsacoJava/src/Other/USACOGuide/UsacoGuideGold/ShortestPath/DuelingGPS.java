package Other.USACOGuide.UsacoGuideGold.ShortestPath;

import java.io.*;
import java.util.*;
/*
USACO 2014 US Open, Silver
Problem 2. Dueling GPSs
USACO Silver/Gold Training
Thoughts:
Nice problem!
Find edges that P likes (Djikstra)
Find edges that Q likes (Djikstra)
construct graph G for weighted graph of dual gps system
Djikstra on this for shortest path
 */
public class DuelingGPS {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    //Djikstra part1
    static ArrayList<Edge>[] adjListP;
    static ArrayList<Edge>[] adjListQ;
    //Djikstra part2
    static LookUpTable PLikes = new LookUpTable();
    static LookUpTable QLikes = new LookUpTable();
    static ArrayList<Edge>[] adjListGPS;
    //helper
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("gpsduel.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("gpsduel.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjListP = new ArrayList[N+1];
        adjListQ = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adjListP[i] = new ArrayList<>();
        for (int i=1;i<=N;i++) adjListQ[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int p_i = Integer.parseInt(st.nextToken());
            int q_i = Integer.parseInt(st.nextToken());
            adjListP[v].add(new Edge(u, p_i));
            adjListQ[v].add(new Edge(u, q_i));
        }
        if (!submission){
            System.out.println(Arrays.toString(adjListP));
            System.out.println(Arrays.toString(adjListQ));
        }

        //part1: find PLikes, QLikes
        int d1=djikstra(N, 1, adjListP, PLikes);
        int d2=djikstra(N, 1, adjListQ, QLikes);
        if (!submission) {
            System.out.println(d1);
            System.out.println(PLikes);
            System.out.println(d2);
            System.out.println(QLikes);
        }

        //part2: construct adjListGPS
        adjListGPS = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adjListGPS[i] = new ArrayList<>();
        for (int u=1;u<=N;u++){
            for (Edge v : adjListP[u]){
                Edge next = new Edge(u,2);
                if (PLikes.contains(v.to,u)) next.weight--;
                if (QLikes.contains(v.to,u)) next.weight--;
                adjListGPS[v.to].add(next);
            }
        }
        if (!submission) System.out.println(Arrays.toString(adjListGPS));

        //turn in answer
        out.println(djikstra(1, N, adjListGPS, new LookUpTable()));
        out.close();
    }
    public static int djikstra(int sourceNode, int endNode, ArrayList<Edge>[] adjList, LookUpTable bestPath){
        PriorityQueue<Query> pq = new PriorityQueue<>((a,b)->a.distance-b.distance);
        int[] distance = new int[N+1];
        Arrays.fill(distance, INF);
        distance[sourceNode]=0;
        boolean[] processed = new boolean[N+1];
        pq.add(new Query(0,sourceNode, 0));

        //djikstra sssp
        while (!pq.isEmpty()) {
            Query next = pq.poll();
            if (processed[next.node]) continue;
            processed[next.node]=true;
            bestPath.add(next.node, next.parent);
            for (Edge child : adjList[next.node]) {
                if (distance[next.node]+child.weight < distance[child.to]){
                    distance[child.to]=distance[next.node]+child.weight;
                    pq.add(new Query(distance[child.to], child.to, next.node));
                }
            }
        }
        if (!submission) System.out.println(Arrays.toString(distance));
        return distance[endNode];
    }
    private static class LookUpTable{
        HashMap<Integer,HashSet<Integer>> table = new HashMap<>();
        public void add(int u, int v){
            if (!table.containsKey(u)) table.put(u, new HashSet<>());
            table.get(u).add(v);
        }
        public boolean contains(int u, int v){
            if (!table.containsKey(u)) return false;
            return table.get(u).contains(v);
        }
        public String toString(){
            return table.toString();
        }
    }
    private static class Query {
        int parent;
        int node;
        int distance;
        public Query(int d, int n, int p){
            distance=d;
            node=n;
            parent=p;
        }
    }
    private static class Edge{
        int to;
        int weight;
        public Edge(int t, int w){
            to=t;
            weight=w;
        }
        public String toString(){
            return "["+to+", "+weight+"]";
        }
    }
}
