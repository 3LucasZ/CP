package Solutions.TrainingGateway.Chapter4;

import java.io.*;
import java.util.*;
/*
PROB: stall4
LANG: JAVA
*/
public class stall4 {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        //parse
        setup("stall4");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MaxFlow flow = new MaxFlow(N+M+2,N+M+1,N+M+2);
        for (int cow=1;cow<=N;cow++){
            flow.addEdge(N+M+1,cow,1);
            st = new StringTokenizer(br.readLine());
            int Mi = Integer.parseInt(st.nextToken());
            for (int Si=0;Si<Mi;Si++){
                int stall = Integer.parseInt(st.nextToken());
                flow.addEdge(cow,N+stall,1);
            }
        }
        for (int stall=1;stall<=M;stall++){
            flow.addEdge(N+stall,N+M+2,1);
        }

        //ret
        long ans = flow.solve();
        out.println(ans);
        out.close();
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