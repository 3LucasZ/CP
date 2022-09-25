package USACO.Silver.Open2022;

import java.io.*;
import java.util.*;

public class Visits {
    //io
    static boolean debug = false;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //param
    static int N;
    static ArrayList<Edge>[] inEdges;
    static Edge[] outEdge;

    //connected components
    static boolean[] visitedEdges;
    static boolean[] visitedNodes;
    static boolean[] visited;

    //ans tracker
    static long ans = 0;
    static int cycleMinCost = 0;

    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        inEdges = new ArrayList[N+1];
        outEdge = new Edge[N+1];
        for (int i=1;i<=N;i++) inEdges[i] = new ArrayList<>();

        for (int u=1;u<=N;u++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            outEdge[u]=new Edge(u, v,cost);
            inEdges[v].add(new Edge(u, u, cost));
            ans += cost;
        }
        if (debug) {
            System.out.println(Arrays.toString(outEdge));
            System.out.println(Arrays.toString(inEdges));
        }
        //floodfill
        visited = new boolean[N+1];

        //logic: ff + cycles
        //keep track of visited edges
        visitedEdges = new boolean[N+1];
        visitedNodes = new boolean[N+1];

        for (int i=1;i<=N;i++){
            if (!visited[i]) {
                cycleMinCost = Integer.MAX_VALUE;
                int node=i;
                visitedNodes[node]=true;
                while(true){
                    node=outEdge[node].v;
                    if (visitedNodes[node]) break;
                    visitedNodes[node]=true;
                }
                int start=node;
                while (true){
                    node=outEdge[node].v;
                    cycleMinCost=Math.min(cycleMinCost, outEdge[node].cost);
                    if (node==start) break;
                }
                ans-=cycleMinCost;
                DFS(i);
            }
        }

        //turn in answer
        out.println(ans);
        out.close();
    }

    public static void DFS(int node){
        if (visited[node]) return;
        visited[node]=true;
        for (Edge child : inEdges[node]){
            DFS(child.v);
        }
        DFS(outEdge[node].v);
    }

    private static class Edge {
        int v;
        int cost;
        int id;
        public Edge(int id, int v, int cost){
            this.v=v;
            this.cost=cost;
            this.id=id;
        }
        public String toString(){
            return "["+id+": "+v+", "+cost+"]";
        }
    }
}
/*
10
10 1000000000
9 1000000000
8 1000000000
7 1000000000
6 1000000000
5 1000000000
4 1000000000
3 1000000000
2 1000000000
1 1000000000
 */
