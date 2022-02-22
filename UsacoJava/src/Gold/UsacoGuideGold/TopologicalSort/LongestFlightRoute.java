package Gold.UsacoGuideGold.TopologicalSort;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Longest Flight Route
USACO Gold Guide - Top Sort - Easy
Thought:
DP on DAG for longest path on graph
1. top sort so that nodes are processed IN PROPER ORDER
    dfs style
2. run a dag style dp
    init
    at node all outgoing nodes update their dp
    update path tracker
 */
public class LongestFlightRoute {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;
    //param
    static int N;
    static int M;
    //top sort
    static boolean visited[];
    static ArrayList<Integer>[] adjList;
    static ArrayList<Integer> sort = new ArrayList<>();
    //dp
    static int dp[];
    //backtracking
    static int before[];
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        adjList = new ArrayList[N+1];for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
        }
        //logic: create top sort
        dfs(1);
        Collections.reverse(sort);

        if (!visited[N]) out.println("IMPOSSIBLE");
        else {
            dp = new int[N+1];
            before = new int[N+1];
            dp[1]=1;
            before[1]=0;
            for (int node : sort){
                for (int child : adjList[node]){
                    if (dp[node]+1>dp[child]){
                        before[child]=node;
                        dp[child]=dp[node]+1;
                    }
                }
            }
            out.println(dp[N]);
            int b = N;
            ArrayList<Integer> ret = new ArrayList<>();
            while (b > 0) {
                ret.add(b);
                b=before[b];
            }
            for (int i=ret.size()-1;i>=0;i--) out.print(ret.get(i)+" ");
        }
        out.close();
    }
    public static void dfs(int node){
        if (visited[node]) return;
        visited[node]=true;
        for (int child : adjList[node]){
            dfs(child);
        }
        sort.add(node);
    }
}
