package USACO.Silver.UsacoGuideSilver.IntroToTreeAlgor;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Tree Diameter
USACO Guide - Silver - Tree Algorithms Intro - Normal
Concepts: Critical thinking!
Runtime: O(n) (2 UsacoGuideSilver.DFS)
 */
public class TreeDiameter {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    //ans
    static int max;
    static int maxNode = 1;
    public static void main(String[] args) throws IOException {
        //parse input
        n = Integer.parseInt(f.readLine());
        adjList = new ArrayList[n+1];
        visited = new boolean[n+1];
        for (int i=1;i<=n;i++){
            adjList[i] = new ArrayList<>();
        }
        for (int i=0;i<n-1;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }
        //logic
        for (int i=0;i<2;i++) {
            max = 0;
            visited = new boolean[n + 1];
            dfs(maxNode, 0);
        }
        out.println(max);
        out.close();
        f.close();
    }
    public static void dfs(int n, int c) {
        if (visited[n]) return;
        visited[n] = true;
        //process if leaf
        if (adjList[n].size()==1) {
            if (c > max) {
                max = c;
                maxNode = n;
            }
        }
        for (int node : adjList[n]) {
            dfs(node, c+1);
        }
    }
}
