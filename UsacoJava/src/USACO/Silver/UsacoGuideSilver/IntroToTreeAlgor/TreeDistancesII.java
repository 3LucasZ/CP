package USACO.Silver.UsacoGuideSilver.IntroToTreeAlgor;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Tree Distances II
USACO Silver Guide - Tree Algorithms (Intro) - Normal
Concepts: dynamic summation techniques
Thoughts: 4xTLE bc Java is slow, super close to solution but then skimmed editorial
 */
public class TreeDistancesII {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static ArrayList<Integer>[] tree;
    static int[] subtree_cnt;
    static long sum = 0;
    static long[] ans;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        subtree_cnt = new int[N+1];
        ans = new long[N+1];
        for (int i=1;i<=N;i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        //logic
        //arbitrary root
        //find subtree node cnt
        subtree(1,0);
        //out.println(Arrays.toString(subtree_cnt));
        //find sum of distances to node 1
        init_sum(1, 0, 1);
        //out.println(sum);
        //fill in the ans array
        ans[1] = sum;
        for (int child : tree[1]) {
            distance_sum(child, 1);
        }
        //out.println(Arrays.toString(ans));
        //turn in answer
        for (int i=1;i<=N;i++) {
            out.print(ans[i] + " ");
        }
        out.close();
    }
    public static void distance_sum(int node, int parent){
        ans[node] = ans[parent] - subtree_cnt[node] + N - subtree_cnt[node];
        for (int child : tree[node]){
            if (child != parent){
                distance_sum(child, node);
            }
        }
    }
    public static int subtree(int node, int parent){
        int cnt = 1;
        for (int child : tree[node]){
            if (child != parent) cnt += subtree(child, node);
        }
        subtree_cnt[node] = cnt;
        return cnt;
    }
    public static void init_sum(int node, int parent, int height){
        for (int child : tree[node]){
            if (child != parent) {
                sum += height;
                init_sum(child, node, height+1);
            }
        }
    }
}
