package CSES.Graphs;

import java.io.*;
import java.util.*;

public class GameRoutes {
    static boolean submission = false;
    static boolean debug = false;

    //Graph
    static int N;
    static int M;
    static ArrayList<Integer>[] graph;

    //Kahn
    static boolean[] vis;
    static ArrayList<Integer> sort = new ArrayList<>();

    //Misc
    static long MOD = (long)(1e9+7);
    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
        }

        //Kahn
        vis = new boolean[N+1];
        for (int i=1;i<=N;i++){
            dfs(i);
        }
        Collections.reverse(sort);
        if (debug) System.out.println(sort);

        //DP
        long[] dp = new long[N+1]; dp[1]=1;
        for (int i=0;i<N;i++){
            int u = sort.get(i);
            for (int v : graph[u]){
                dp[v]=(dp[v]+dp[u])%MOD;
            }
        }

        //ret
        out.println(dp[N]);
        out.close();
    }
    public static void dfs(int node){
        if (vis[node]) return;
        vis[node]=true;
        for (int child : graph[node]){
            dfs(child);
        }
        sort.add(node);
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
