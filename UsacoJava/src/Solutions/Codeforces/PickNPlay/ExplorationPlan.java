package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class ExplorationPlan {
    static boolean submission = false;
    static boolean debug = false;

    static int V;
    static int E;
    static int N;
    static int K;

    static ArrayList<Integer> teams = new ArrayList<>();
    static int[][] dist;

    static int INF = Integer.MAX_VALUE/3;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            teams.add(Integer.parseInt(st.nextToken()));
        }
        dist = new int[V+1][V+1]; for (int u=1;u<=V;u++) for (int v=1;v<=V;v++) if (u!=v) dist[u][v]=INF;
        for (int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[u][v]=Math.min(dist[u][v],c);
            dist[v][u]=Math.min(dist[u][v],c);
        }

        //floyd (N^3)
        if (debug) print(dist);
        for (int m=1;m<=V;m++) for (int u=1;u<=V;u++) for (int v=1;v<=V;v++) dist[u][v]=Math.min(dist[u][v],dist[u][m]+dist[m][v]);
        if (debug) print(dist);

        //bin search on can(T=time)
        int lo = 0;int hi=1731312;
        while (lo<hi){
            int mid = (lo+hi)/2;
            if (debug) System.out.println("Trying: "+mid);
            if (can(mid)) hi=mid;
            else lo=mid+1;
        }

        //impossible
        if (hi==1731312) out.println(-1);
        //possible
        else out.println(lo);
        out.close();
    }
    public static boolean can(int T){
        //Create Bipartite graph where team_id -> city if dist[team][city]<=T
        MaxFlow flow = new MaxFlow(V+N+2,V+N+1,V+N+2);
        for (int i=1;i<=N;i++){
            int team = teams.get(i-1);
            for (int j=1;j<=V;j++){
                if (dist[team][j]<=T) flow.addEdge(i,N+j,1);
            }
        }
        for (int i=1;i<=N;i++) flow.addEdge(V+N+1,i,1);
        for (int i=1;i<=V;i++) flow.addEdge(N+i,V+N+2,1);
        if (debug) flow.print();
        long f = flow.solve();
        return f>=K;
    }
    public static void print(int[][] arr){
        for (int r=1;r<arr.length;r++){
            for (int c=1;c<arr[r].length;c++){
                if (arr[r][c]>100) {
                    System.out.print("-1   ");
                    continue;
                }
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
    private static class MaxFlow {
        /*
        Conditions:
        directed graph
        1 indexed graph
        list storage
        */
        int N;
        int S;
        int T;

        ArrayList<MaxFlow.Edge>[] graph;
        boolean[] visited;

        static final int INF = Integer.MAX_VALUE/2;

        public MaxFlow(int N, int S, int T){
            this.N=N;
            this.S=S;
            this.T=T;
            graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
            visited = new boolean[N+1];
        }
        public void addEdge(int u, int v, int c){
            MaxFlow.Edge forward = new MaxFlow.Edge(u,v,c);
            MaxFlow.Edge residual = new MaxFlow.Edge(v,u,0);
            graph[u].add(forward);
            graph[v].add(residual);
            forward.residual=residual;
            residual.residual=forward;
        }
        public long solve(){
            long maxFlow = 0;
            long flow = 0;
            while (true){
                visited = new boolean[N+1];
                flow = dfs(S,INF);
                if (flow==0) break;
                maxFlow+=flow;
            }
            return maxFlow;
        }
        public long dfs(int node, long flow){
            //found augmenting path
            if (node==T) return flow;
            visited[node]=true;
            for (Edge child : graph[node]){
                if (child.cap>0 && !visited[child.v]){
                    long pathCap = dfs(child.v,Math.min(flow,child.cap));
                    //augmenting path exists
                    if (pathCap > 0){
                        child.augment(pathCap);
                        return pathCap;
                    }
                }
            }
            return 0;
        }
        public void print(){
            for (int i=1;i<=N;i++){
                System.out.println(graph[i]);
            }
        }
        private static class Edge {
            int u;
            int v;
            int cap;
            MaxFlow.Edge residual;

            public Edge(int u, int v, int c){
                this.u=u;
                this.v=v;
                this.cap=c;
            }
            public void augment(long pathCap){
                cap-=pathCap;
                residual.cap+=pathCap;
            }
            public String toString(){
                return "["+u+", "+v+" : "+cap+"]";
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
