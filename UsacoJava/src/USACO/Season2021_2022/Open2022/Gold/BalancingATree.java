package USACO.Season2021_2022.Open2022.Gold;

import java.io.*;
import java.util.*;
/*
PROB: BalancingATree
LANG: JAVA
*/
public class BalancingATree {
    static boolean submission = false;
    static boolean debug = true;

    static int B;
    static int N;
    static ArrayList<Integer>[] graph;
    static int lb[];
    static int rb[];

    public static void main(String[] args) throws IOException {
        //handle TCS
        setup("BalancingATree");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        B=Integer.parseInt(st.nextToken());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        //parse
        N = Integer.parseInt(br.readLine());
        lb = new int[N+1];
        rb = new int[N+1];
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int v=2;v<=N;v++){
            int u = Integer.parseInt(st.nextToken());
            graph[u].add(v);
        }
        for (int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            lb[i]=l;
            rb[i]=r;
        }
        //reset
        val = new int[N+1];
        ans=0;
        //calculate max(li)-min(rj) for a related ij
        dfs(1,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
        //calculate (max(li)-min(rj))/2 for any ij
        int l = Integer.MIN_VALUE;
        int r = Integer.MAX_VALUE;
        for (int i=1;i<=N;i++){
            l=Math.max(l,lb[i]);
            r=Math.min(r,rb[i]);
        }
        ans=Math.max(ans,(l-r+1)/2);
        out.println(ans);
        //extra verbose for B==1
        if (B==1){
            int mid = (r+l)/2;
            for (int i=1;i<N;i++){
                out.print(Math.max(Math.min(mid,rb[i]),lb[i])+" ");
            }
            out.print(Math.max(Math.min(mid,rb[N]),lb[N]));
            out.println();
        }
    }
    static int ans;
    static int val[];
    public static void dfs(int node, int par, int maxL,int minR){
        int l = Math.max(maxL,lb[node]);
        int r = Math.min(minR,rb[node]);
        if (node!=1) ans=Math.max(ans,l-r);
        for (int child : graph[node]){
            if (child==par) continue;
            dfs(child,node,l,r);
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