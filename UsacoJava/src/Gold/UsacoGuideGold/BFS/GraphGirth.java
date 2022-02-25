package Gold.UsacoGuideGold.BFS;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Graph Girth
USACO Gold Guide - BFS - Normal
Thoughts:
Had the right idea and only missed 3 tcs
cycle = dist[node]+dist[child]+1
mistake: should of returned min cycle instead of first cycle
nice problem over all (TLE on 2 cuz java slow)
 */
public class GraphGirth {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static int M;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }
        if (debug){
            System.out.println(Arrays.toString(adjList));
        }
        //logic
        int ans = Integer.MAX_VALUE;
        for (int node=1;node<=N;node++){
            int cycle = cycleLength(node);
            if (debug) System.out.println(cycle);
            ans = Math.min(ans, cycle);
        }
        //turn in answer
        if (ans==Integer.MAX_VALUE) out.println(-1);
        else out.println(ans);
        out.close();
    }
    public static int cycleLength(int node){
        //init
        int bestCycle = Integer.MAX_VALUE;
        Queue<Pair> BFS = new LinkedList();
        boolean[] visited = new boolean[N+1];
        int[] distance = new int[N+1];
        visited[node]=true;
        distance[node]=0;
        BFS.add(new Pair(node,0));

        //BFS
        while (!BFS.isEmpty()) {
            Pair next = BFS.poll();
            for (int child : adjList[next.node]) {
                if (child==next.parent) continue;
                if (visited[child]){
                    bestCycle = Math.min(bestCycle, distance[child]+distance[next.node]+1);
                }
                else {
                    BFS.add(new Pair(child, next.node));
                    visited[child]=true;
                    distance[child]=distance[next.node]+1;
                }
            }
            if (debug) {
                System.out.println(Arrays.toString(visited));
                System.out.println(Arrays.toString(distance));
                System.out.println(BFS);
            }
        }
        return bestCycle;
    }
    private static class Pair {
        int node;
        int parent;
        public Pair(int n, int p){
            node=n;
            parent=p;
        }
    }
}
