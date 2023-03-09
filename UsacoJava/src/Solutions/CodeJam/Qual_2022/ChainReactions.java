package Solutions.CodeJam.Qual_2022;

import java.io.*;
import java.util.*;

public class ChainReactions {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static long[] fun;
    static ArrayList<Integer>[] tree;
    static long ans;
    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println();
        //* parse
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        fun = new long[N+1];
        for (int i=1;i<=N;i++) fun[i]=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        tree = new ArrayList[N+1]; for (int i=0;i<=N;i++) tree[i] = new ArrayList<>();
        for (int u=1;u<=N;u++){
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        //* bottom up DFS
        ans = 0;
        long fin = DFS(0,-1);
        ans+=fin;
        System.out.println(ans);
    }
    static long DFS(int node, int par){
        ArrayList<Long> vals = new ArrayList<>();
        for (int child : tree[node]){
            if (child==par) continue;
            vals.add(DFS(child,node));
        }
        //* process
        if (vals.size()==0){
            return fun[node];
        } else {
            Collections.sort(vals);
            for (int i = 1; i < vals.size(); i++) ans += vals.get(i);
            return Math.max(vals.get(0), fun[node]);
        }
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}