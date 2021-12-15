package Silver.UsacoGuideSilver.IntroToTreeAlgor;

import java.io.*;
import java.util.*;
/*
USACO 2018 January Contest, Silver
Problem 3. MooTube
USACO Guide - Trees - Easy
Concept: Optimization! Do not DFS on specific nodes when needed to save runtime.
 */
public class MooTube {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static int Q;
    static ArrayList<Integer>[] tree;
    static int[][] relevance;
    static int K;
    static int V;
    static int count;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("mootube.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        relevance = new int[N+1][N+1];
        for (int i=1;i<=N;i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i=0;i<N-1;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int rel = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
            relevance[u][v] = rel;
            relevance[v][u] = rel;
        }
        //logic
        for (int i=0;i<Q;i++) {
            st = new StringTokenizer(f.readLine());
            K = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            count = -1;
            propagate(V, 0);
            out.println(count);
        }
        out.close();
        f.close();
    }
    public static void propagate(int n, int p) {
        count++;
        for (int child : tree[n]) {
            if (child != p && relevance[n][child]>=K) propagate(child, n);
        }
    }
}
