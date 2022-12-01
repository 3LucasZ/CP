import java.io.*;
import java.util.*;

/*
PROB: HousePlanning
LANG: JAVA
*/
public class HousePlanning {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("HousePlanning");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static int[] d1;
    static int[] d2;
    static MaxFlow match;

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) d1[i]=Integer.parseInt(st.nextToken());
        for (int i=0;i<N;i++) d2[i]=Integer.parseInt(st.nextToken());
        //* Set up MaxFlow
         //match = new MaxFlow(N, )
        //* bash |p1-p2|=k
        for (int i=0;i<N;i++){
            int k1 = d1[0]+d2[i];
            int k2=Math.abs(d1[0]-d2[i]);
        }
    }
    public static boolean tryK(int k){
        //MaxFlow matching = new MaxFlow(N)
        return true;
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
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}