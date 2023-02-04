package Other.USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
PROB: TreeMatching
LANG: JAVA
*/
public class TreeMatching {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        //parse
        setup("TreeMatching");
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        //dp roots first
        //dp[node][0=open][1=closed]
        dp = new int[N+1][2];
        DFS(1,0);

        //ret
        out.println(Math.max(dp[1][0],dp[1][1]));
        out.close();
    }

    static int[][] dp;
    static void DFS(int node, int par){
        int add = Integer.MIN_VALUE;
        int sum = 0;
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node);
            int max = Math.max(dp[child][0],dp[child][1]);
            sum+=max;
            add=Math.max(add,dp[child][0]-max);
        }
        dp[node][0]=sum;
        dp[node][1]=1+sum+add;
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