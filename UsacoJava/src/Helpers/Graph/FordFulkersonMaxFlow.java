package Helpers.Graph;

import java.io.*;
import java.util.*;
/*
Test Link:

 */
public class FordFulkersonMaxFlow {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        //parse
        setup("ditch");
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        MaxFlow flow = new MaxFlow(N,1,N);
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            flow.addEdge(u,v,c);
        }

        //solve
        long ans = flow.solve();
        out.println(ans);
        out.close();
    }
    private static class MaxFlow{
        /*
  Conditions:
  directed graph
  1 indexed graph
  list storage
  */
        int N;
        int S;
        int T;

        ArrayList<Edge>[] graph;
        boolean[] visited;

        static final int INF=Integer.MAX_VALUE/2;

        public MaxFlow(int N,int S,int T){
            this.N=N;
            this.S=S;
            this.T=T;
            graph=new ArrayList[N+1];
            for(int i=1;i<=N;i++) graph[i]=new ArrayList<>();
            visited=new boolean[N+1];
        }

        public void addEdge(int u,int v,int c){
            Edge forward=new Edge(u,v,c,true);
            Edge residual=new Edge(v,u,0,false);
            graph[u].add(forward);
            graph[v].add(residual);
            forward.residual=residual;
            residual.residual=forward;
        }

        public long solve(){
            long maxFlow=0;
            long flow=0;
            while(true){
                visited=new boolean[N+1];
                flow=dfs(S,INF);
                if(flow==0) break;
                maxFlow+=flow;
            }
            return maxFlow;
        }

        public long dfs(int node,long flow){
            //found augmenting path
            if(node==T) return flow;
            visited[node]=true;
            for(Edge child: graph[node]){
                if(child.cap>0&&!visited[child.v]){
                    long pathCap=dfs(child.v,Math.min(flow,child.cap));
                    //augmenting path exists
                    if(pathCap>0){
                        child.augment(pathCap);
                        return pathCap;
                    }
                }
            }
            return 0;
        }

        public void print(){
            for(int i=1;i<=N;i++){
                System.out.println(graph[i]);
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        int cap;
        Edge residual;
        boolean orig;

        public Edge(int u, int v, int c, boolean o){
            this.u=u;
            this.v=v;
            this.cap=c;
            this.orig=o;
        }
        public void augment(long pathCap){
            cap-=pathCap;
            residual.cap+=pathCap;
        }
        public String toString(){
            return "["+u+", "+v+" : "+cap+"]";
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
