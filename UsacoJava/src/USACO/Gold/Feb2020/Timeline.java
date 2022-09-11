package USACO.Gold.Feb2020;

import java.io.*;
import java.util.*;
/*
USACO 2020 February Contest, Gold
Problem 1. Timeline
Thoughts:
Too easy!
Simple DAG problem solved with top sort and DP
 */
public class Timeline {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;
    static int C;

    static int[] startMin;
    static ArrayList<Edge>[] tree;

    static boolean[] visited;
    static ArrayList<Integer> sort;

    public static void main(String[] args) throws IOException {
        setup("timeline");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        startMin = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) startMin[i] = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<C;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int minDays = Integer.parseInt(st.nextToken());
            tree[u].add(new Edge(v,minDays));
        }

        //top sort
        visited = new boolean[N+1];
        sort = new ArrayList<>();
        for (int i=1;i<=N;i++) DFS(i);
        Collections.reverse(sort);
        if (debug){
            System.out.println(Arrays.toString(tree));
            System.out.println(sort);
        }

        //dp-like DAG solve
        int[] ans = new int[N+1];
        for (int node : sort){
            ans[node]=Math.max(ans[node],startMin[node]);
            for (Edge child : tree[node]){
                ans[child.v]=Math.max(ans[child.v],ans[node]+child.minDays);
            }
        }

        //ret
        for (int i=1;i<=N;i++) out.println(ans[i]);
        out.close();
    }
    public static void DFS(int node){
        if (visited[node]) return;
        visited[node]=true;
        for (Edge child : tree[node]){
            DFS(child.v);
        }
        sort.add(node);
    }
    private static class Edge {
        int v;
        int minDays;
        public Edge(int v, int minDays){
            this.v=v;
            this.minDays=minDays;
        }
        public String toString(){
            return "["+v+": "+minDays+"]";
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
