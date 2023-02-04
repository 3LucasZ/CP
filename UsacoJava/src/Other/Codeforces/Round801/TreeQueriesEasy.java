package Other.Codeforces.Round801;

import java.io.*;
import java.util.*;

public class TreeQueriesEasy {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    static ArrayList<Integer>[] tree;
    public static void solve() throws IOException{
        //parse
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        //log
        if (N==1) out.println(0);
        else {
            int ans = N;
            for (int i = 1; i <= N; i++) {
                ans = Math.min(ans, DFS(i, i)+1);
            }
            out.println(ans);
        }
    }

    public static int DFS(int node, int par){
        int x = 0;
        int dp = 0;
        for (int child : tree[node]){
            if(child==par) continue;
            int subtree = DFS(child,node);
            dp+=subtree;
            if (subtree==0) x++;
        }

        return dp+Math.max(0,x-1);
    }
}
