package USACO.Silver.UsacoGuideSilver.IntroToTreeAlgor;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Tree Distances I
USACO Silver Guide - Trees
Many TLE
 */
public class TreeDistanceI {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static ArrayList<Integer>[] tree;
    static int nodeStart = 0;
    static int temp1 = 0;
    static int nodeEnd = 0;
    static int diameter = 0;
    static int[] ans;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) {
            tree[i] = new ArrayList<>();
        }
        distance = new int[N+1];
        ans = new int[N+1];
        for (int i=0;i<N-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
//        for (int i=1;i<=N;i++) {
//            out.println(tree[i]);
//        }
//        out.println();
        //find diameter
        dfs1(0, 1, 0);
//        out.println("node start: " + nodeStart);
//        out.println("temp1: " + temp1);
//        out.println();
        dfs2(0, nodeStart, 0);
//        out.println("node end: " + nodeEnd);
//        out.println("diameter: " + diameter);
//        out.println();
        //find distance to diameter start or end whichever is longest for the nodes in the diameter
        dfs3(0,nodeStart,0);
//        out.println(Arrays.toString(distance));
        //fill in the answer array
        for (int i=1;i<=N;i++) {
            if (distance[i] > 0) {
                dfs4(0, i, distance[i]);
            }
        }
        //turn in answer
        for (int i=1;i<=N;i++) {
            out.print(ans[i]+" ");
        }
        out.close();
    }
    public static void dfs1(int p, int n, int len){
        if (len > temp1){
            temp1 = len;
            nodeStart = n;
        }
        for (int child : tree[n]){
            if (child == p) continue;
            dfs1(n,child,len+1);
        }
    }
    public static void dfs2(int p, int n, int len){
        if (len > diameter){
            diameter = len;
            nodeEnd = n;
        }
        for (int child : tree[n]){
            if (child == p) continue;
            dfs2(n,child,len+1);
        }
    }
    public static boolean dfs3(int p, int n, int len) {
        if (n == nodeEnd) {
            distance[n] = Math.max(len,diameter-len);
            ans[n] = distance[n];
            return true;
        }
        boolean partOfDiameter = false;
        for (int child : tree[n]){
            if (child == p) continue;
            if (dfs3(n, child, len+1)) partOfDiameter = true;
        }
        if (partOfDiameter) {
            distance[n] = Math.max(len,diameter-len);
            ans[n] = distance[n];
        }
        return partOfDiameter;
    }
    public static void dfs4(int p, int n, int run) {
        ans[n] = run;
        for (int child : tree[n]){
            if (child == p || distance[child] > 0) continue;
            dfs4(n, child, run+1);
        }
    }
}
