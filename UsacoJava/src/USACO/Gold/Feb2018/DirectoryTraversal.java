package USACO.Gold.Feb2018;

import java.io.*;
import java.util.*;
/*
USACO 2018 February Contest, Gold
Problem 2. Directory Traversal
Thoughts:
Literally Tree Distances 2 from CSES except a little harder
quite fun, worked quickly, clean code
 */
public class DirectoryTraversal {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] len;
    static ArrayList<Integer>[] tree;

    static long tot = 0;
    static long ans = 0;

    static int[] leafs;

    public static void main(String[] args) throws IOException {
        setup("dirtraverse");

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        len = new int[N+1];

        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            len[i] = st.nextToken().length();
            int M = Integer.parseInt(st.nextToken());
            for (int j=0;j<M;j++){
                int v = Integer.parseInt(st.nextToken());
                tree[i].add(v);
            }
        }

        leafs = new int[N+1];
        initDFS(1, 0);
        if (debug) {
            System.out.println(tot);
            System.out.println(Arrays.toString(leafs));
            System.out.println(Arrays.toString(tree));
        }
        ans=tot;

        DFS(1, tot);
        out.println(ans);
        out.close();
    }
    static void DFS(int node, long sum){
        if (tree[node].size()==0) return;
        int above = (leafs[1]-leafs[node])*3;
        int below = (leafs[node])*(len[node]+1);
        sum = sum + above - below;
        ans = Math.min(ans, sum);
        for (int child : tree[node]){
            DFS(child, sum);
        }
    }
    static void initDFS(int node, long sum){
        sum += len[node]+1;
        for (int child : tree[node]){
            initDFS(child, sum);
            leafs[node]+=leafs[child];
        }
        if (tree[node].size()==0) {
            tot += sum-1;
            leafs[node]=1;
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
