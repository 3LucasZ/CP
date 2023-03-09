package Solutions.USACO.Season2018_2019.Jan2019.Gold;/*
USACO 2019 January Contest, Gold
Problem 3. Shortcut
USACO Gold Training
Thoughts:
Djikstra while keeping the djikstra tree at 0 additional cost
Djikstra with lexicographic tracking
A little of problem interpreting logic to turn into a multi step problem
"node where (cows pass)*(time save) is maximized"
find time save using djikstra easy
cows pass using altered djikstra
noice! (looked at solution multiple times bc lexicographic version was confusing)
 */
import java.io.*;
import java.util.*;
public class Shortcut {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;
    static int T;
    static int initCows[];

    static ArrayList<Edge>[] graph;
    static int[] dist;

    static ArrayList<Integer>[] djTree;
    static int[] cows;

    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //parse
        setup("shortcut");
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        T = Integer.parseInt(stk.nextToken());
        initCows = new int[N+1];
        stk = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) initCows[i] = Integer.parseInt(stk.nextToken());
        graph = new ArrayList[N+1];
        for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());
            if (u==v)continue;
            graph[u].add(new Edge(v,cost));
            graph[v].add(new Edge(u, cost));
        }

        //lexicographical djikstra
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->{
            if (a.cost==b.cost) return a.v-b.v;
            return a.cost-b.cost;
        });
        dist = new int[N+1]; for (int i=1;i<=N;i++) dist[i] = INF;
        pq.add(new Edge(1,0));
        dist[1]=0;
        boolean[] processed = new boolean[N+1];
        int[] djPar = new int[N+1];Arrays.fill(djPar,INF);

        while (!pq.isEmpty()){
            Edge next = pq.poll();
            if (processed[next.v]) continue;
            processed[next.v]=true;

            for (Edge adj : graph[next.v]){
                if (dist[next.v]+adj.cost < dist[adj.v]){
                    dist[adj.v]=dist[next.v]+adj.cost;
                    djPar[adj.v]=next.v;
                    pq.add(new Edge(adj.v, dist[adj.v]));
                }
                else if (dist[next.v]+adj.cost==dist[adj.v]){
                    djPar[adj.v]=Math.min(djPar[adj.v],next.v);
                }
            }
        }

        if (debug){
            System.out.println("distances: "+Arrays.toString(dist));
            System.out.println("djPar: "+Arrays.toString(djPar));
        }

        djTree = new ArrayList[N+1];for (int i=0;i<=N;i++) djTree[i] = new ArrayList<>();
        for (int i=2;i<=N;i++){
            djTree[djPar[i]].add(i);
        }
        if (debug){
            System.out.println("dj tree: "+Arrays.toString(djTree));
        }

        cows = new int[N+1];
        DFS(1, 0);
        if (debug){
            System.out.println("cross cows: "+Arrays.toString(cows));
        }

        long answer = 0;
        for (int i=1;i<=N;i++){
            answer = Math.max(answer,(long)(dist[i]-T)*cows[i]);
        }
        out.println(answer);
        out.close();
    }

    static void DFS(int node, int par){
        cows[node]+=initCows[node];
        for (int child : djTree[node]){
            if (child!=par) {
                DFS(child, node);
                cows[node]+=cows[child];
            }
        }

    }
    private static class Edge{
        int v;
        int cost;
        public Edge(int v, int cost){
            this.v=v;
            this.cost=cost;
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
