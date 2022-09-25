package USACOGuide.UsacoGuideGold.MST;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Road Preparation
USACO Gold Guide - MST - Easy Example
Thoughts:
FIRST ever MST algorithm using Prim:
start with vertex (arbitrary)
greedily add nodes based on closest to current MST
O(ElogE) time
 */
public class RoadPreparationPrim {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //in
    static int N;
    static int M;
    static ArrayList<Edge>[] adjList;

    public static void main(String[] args) throws IOException{
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[u].add(new Edge(v, weight));
            adjList[v].add(new Edge(u, weight));
        }
        if (debug) System.out.println(Arrays.toString(adjList));

        //log
        boolean[] visited = new boolean[N+1];
        long mst = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.weight));
        pq.add(new Edge(1, 0));


        while (!pq.isEmpty()){
            Edge next = pq.poll();
            if (visited[next.v]) continue;
            visited[next.v]=true;
            mst += next.weight;
            for (Edge child : adjList[next.v]){
                if (!visited[child.v]) pq.add(child);
            }
        }

        //Q&D imp check
        for (int i=1;i<=N;i++) if (!visited[i]) {
            out.println("IMPOSSIBLE");
            out.close();
            return;
        }

        out.println(mst);
        out.close();
    }

    private static class Edge {
        int v;
        long weight;
        public Edge(int v, long w){
            this.v=v;
            this.weight=w;
        }
        public String toString(){
            return "["+v+": "+weight+"]";
        }
    }
}
