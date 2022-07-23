package Misc.Procrastinate;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Advanced Techniques
Reachable Nodes
Thoughts:
SUPER Cool problem
1. top sort the nodes
2. from most priority to least priority node run OR operation on all children
3. cardinality is its reachability, store as ans
another nice bit set problem
O(N + M(N/64)) solution

MISTAKE 1: TLE on case 3 for java
be careful with top sort, remember node id neq node index
ORIG: children = node id, bitset id
FIX:  children = index in top sort, bitset index

ALL TCS PASS :)
 */
public class ReachableNodes {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int M;
    static ArrayList<Integer>[] adjList;

    //top sort
    static ArrayList<Integer> sortedNodes = new ArrayList<>();
    static boolean[] visited;

    //log
    static BitSet[] reachability;

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
        }

        //init
        reachability = new BitSet[N+1];
        visited = new boolean[N+1];

        //Top sort
        for (int i=1;i<=N;i++){
            DFS(i);
        }
        //Collections.reverse(sortedNodes);
        if (debug) System.out.println(sortedNodes);

        //logic
        int[] ans = new int[N+1];
        for (int i=0;i<N;i++){
            ans[sortedNodes.get(i)]=solve(sortedNodes.get(i), i+1);
        }
        for (int i=1;i<=N;i++) out.print(ans[i]+" ");
        out.close();
    }
    public static int solve(int node, int index){
        reachability[node] = new BitSet(index);
        reachability[node].set(index);
        for (int child : adjList[node]){
            reachability[node].or(reachability[child]);
        }
        return reachability[node].cardinality();
    }

    public static void DFS(int node){
        if (visited[node]) return;
        visited[node]=true;
        for (int child : adjList[node]){
            DFS(child);
        }
        sortedNodes.add(node);
    }
}
