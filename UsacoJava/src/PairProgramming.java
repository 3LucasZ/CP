import java.io.*;
import java.util.*;

public class PairProgramming {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    static final long MOD = (long)1e9+7;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException{
        //parse
        int N = Integer.parseInt(br.readLine());
        char[] B = (" "+br.readLine()).toCharArray();
        char[] E = (" "+br.readLine()).toCharArray();

        //dp setup: dp[b][e][is0]=distinct values
        long[][][] dp = new long[N+1][N+1][2];

        //init states
        dp[0][0][1]=1;
        dp[0][0][0]=0;
//        if(B[1]=='+')dp[1][0][0]=1;
//        else dp[1][0][1]=1;
//        if(E[1]=='+')dp[0][1][0]=1;
//        else dp[0][1][1]=1;

        //transitions
        for (int b=0;b<=N;b++){
            for (int e=0;e<=N;e++){
                if (b==0&&e==0) continue;

                if(b!=0) {
                    if (B[b] == '0') dp[b][e][1] = 1;
                    else if (B[b] == '+') dp[b][e][0] = (dp[b][e][0] + dp[b - 1][e][0] + dp[b - 1][e][1]) % MOD;
                    else {
                        dp[b][e][0] = (dp[b][e][0] + dp[b - 1][e][0] % MOD);
                        if (dp[b - 1][e][1] == 1) dp[b][e][1] = 1;
                    }
                }

                if (e!=0) {
                    if (E[e] == '0') dp[b][e][1] = 1;
                    else if (E[e] == '+') dp[b][e][0] = (dp[b][e][0] + dp[b][e - 1][0] + dp[b][e - 1][1]) % MOD;
                    else {
                        dp[b][e][0] = (dp[b][e][0] + dp[b][e - 1][0] % MOD);
                        if (dp[b][e - 1][1] == 1) dp[b][e][1] = 1;
                    }
                }

                if (b!=0 & e!=0){

                }
            }
        }
        if (debug) {
            for (int e = 0; e <= N; e++) {
                for (int b = 0; b <= N; b++) {
                    out.println("("+b+", "+e+"): ("+dp[b][e][0]+", "+dp[b][e][1]+")");
                }
            }
        }

        //ret
        out.println(dp[N][N][0]+dp[N][N][1]);
    }
}
