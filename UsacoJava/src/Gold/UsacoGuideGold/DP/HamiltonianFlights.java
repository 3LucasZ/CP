package Gold.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;

public class HamiltonianFlights {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    static int N;
    static int M;
    static ArrayList<Integer>[] graph;

    static long MOD = (long)1e9+7;
    public static void main(String[] args) throws IOException{
        //parse
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

        //dp:
        //dp[set][end node] = ways to visit all of nodes in set once and end at node
        //answer collected in dp[(1<<N)-1][N];
        //setup
        long dp[][] = new long[1<<N][N+1];
        dp[1][1]=1;
        //transitioning
        for (int set=0;set<(1<<N);set++){
            for (int end=1;end<=N;end++){
                for (int next : graph[end]){
                    int bit = 1<<(next-1);
                    if ((set&bit)==0){
                        dp[set|bit][next] = (dp[set|bit][next] + dp[set][end])%MOD;
                    }
                }
            }
        }

        out.println(dp[(1<<N)-1][N]);
        out.close();
    }
}
