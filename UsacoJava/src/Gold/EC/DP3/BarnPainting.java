package Gold.EC.DP3;

import java.io.*;
import java.util.*;
/*
Barn Painting
Gold Advanced B 3
DP - trees
Notes:
smart counting N1(c1 + c2) * N2(c1 + c2) ...
 */
public class BarnPainting {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    //const
    static long MOD = (int) (1e9+7);
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("barnpainting.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        dp = new int[N+1][4];
        for (int i=1;i<=N;i++){
            tree[i] = new ArrayList<>();
            Arrays.fill(dp[i],1);
        }
        for (int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            for (int c=1;c<=3;c++) {
                dp[node][c]=0;
            }
            dp[node][color]=1;
        }
        //check
        if (debug) System.out.println(Arrays.toString(tree));
        if (debug) for (int i=1;i<=N;i++) System.out.println(Arrays.toString(dp[i]));
        if (debug) System.out.println();
        //logic/dp
        dfs(1,0);
        if (debug) for (int i=1;i<=N;i++) System.out.println(Arrays.toString(dp[i]));
        //turn in answer
        out.println(((long)dp[1][1]+dp[1][2]+dp[1][3])%MOD);
        out.close();
    }
    public static void dfs(int node, int parent){
        for (int child : tree[node]) {
            if (child != parent) {
                //fill the dp of the child
                dfs(child, node);
                //alter the dp of node based on child
                for (int color=1;color<=3;color++){
                    int othersNet = 0;
                    for (int color2=1;color2<=3;color2++) {
                        if (color2!=color) othersNet+=dp[child][color2];
                    }
                    dp[node][color] = (int) (((long) othersNet * dp[node][color]) % MOD);
                }
            }
        }
    }
}
