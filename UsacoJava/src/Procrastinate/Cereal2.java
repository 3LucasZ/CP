package Procrastinate;

import java.io.*;
import java.util.*;

public class Cereal2 {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static boolean[] visitedNode;
    static boolean[] visitedEdge;

    //part1
    static Edge extraEdge;

    //part2
    static boolean[] visited;
    static ArrayList<Integer> order = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();

        for (int i=1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(i,u,v,u));
            graph[v].add(new Edge(i,v,u,u));
        }

        //ff with greedy span tree
        visitedNode = new boolean[N+1];
        visitedEdge = new boolean[M+1];
        visited = new boolean[N+1];

        for (int i=1;i<=N;i++) {
            if (visitedNode[i]) continue;
            if (graph[i].size()==0) continue;
            if (debug) System.out.println("NEW head: "+i);

            //has extra edge?
            extraEdge = null;
            dfs1(new Edge(-i,0,i,0));

            if (extraEdge==null){
                if (debug) System.out.println("tree");
                dfs2(new Edge(-i,0,i,i));
            }
            else {
                if (debug) System.out.println("graph");
                dfs2(extraEdge);
            }
        }
        HashSet<Integer> notHungry = new HashSet<>(order);
        for (int j=1;j<=M;j++){
            if (!notHungry.contains(j)) {
                order.add(j);
            }
        }
        out.println(M-notHungry.size());
        for (int j : order){
            out.println(j);
        }
        out.close();
    }
    public static void dfs2(Edge edge){
        if (visited[edge.v]) return;
        visited[edge.v]=true;
        if (edge.id>0) order.add(edge.id);
        for (Edge child : graph[edge.v]){
            dfs2(child);
        }
    }
    public static void dfs1(Edge edge){
        if (edge.id>0) {
            if (visitedEdge[edge.id]) return;
            visitedEdge[edge.id] = true;
        }
        if (visitedNode[edge.v]) extraEdge = edge;
        visitedNode[edge.v]=true;
        for (Edge child : graph[edge.v]){
            dfs1(child);
        }
    }

    private static class Edge {
        int id;
        int u;
        int v;
        int fav;
        public Edge(int id, int u, int v, int fav) {
            this.u=u;
            this.v=v;
            this.id=id;
            this.fav=fav;
        }
    }
}
