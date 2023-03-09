package Solutions.Codeforces.Round800;

import java.io.*;
import java.util.*;

public class FakePlasticTrees {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    static ArrayList<Integer>[] tree;
    static long[] L;
    static long[] R;
    static int ans;
    public static void solve() throws IOException{
        //parse
        ans=0;
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++)tree[i] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int u=2;u<=N;u++){
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        L = new long[N+1];
        R = new long[N+1];
        for (int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            L[i]=Integer.parseInt(st.nextToken());
            R[i]=Integer.parseInt(st.nextToken());
        }

        if (debug){
            System.out.println(Arrays.toString(L));
            System.out.println(Arrays.toString(R));
            System.out.println(Arrays.toString(tree));
        }
        DFS(1,0);
        out.println(ans);
    }
    public static void DFS(int node, int par){
        long Lsum = 0;
        long Rsum = 0;
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node);
            Lsum+=L[child];
            Rsum+=R[child];
        }

        if (L[node]>Rsum && R[node]>Rsum){
            ans++;
        }
        else if (R[node]>Rsum && Rsum!=0){
            R[node]=Rsum;
        }
    }
}
