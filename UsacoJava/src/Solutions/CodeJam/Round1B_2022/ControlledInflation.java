package Solutions.CodeJam.Round1B_2022;

import java.io.*;
import java.util.*;

public class ControlledInflation {
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
    static int P;
    static long[] lo;
    static long[] hi;

    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println("");
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        lo = new long[N]; Arrays.fill(lo,Long.MAX_VALUE);
        hi = new long[N]; Arrays.fill(hi,Long.MIN_VALUE);
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<P;j++){
                int val = Integer.parseInt(st.nextToken());
                lo[i]=Math.min(lo[i],val);
                hi[i]=Math.max(hi[i],val);
            }
        }
        if (debug){
            System.out.println("lo: "+Arrays.toString(lo));
            System.out.println("hi: "+Arrays.toString(hi));
        }
        //* dp
        long[][] dp = new long[N][2];
        for (int i=0;i<N;i++) for (int j=0;j<2;j++) dp[i][j]=Long.MAX_VALUE;
        dp[0][0] = 2*hi[0]-lo[0];
        dp[0][1] = hi[0];
        for (int i=0;i<N-1;i++){
            //lo lo -> hi
            dp[i+1][1]=Math.min(dp[i+1][1],dp[i][0]+Math.abs(lo[i+1]-lo[i])+hi[i+1]-lo[i+1]);
            //lo hi -> lo
            dp[i+1][0]=Math.min(dp[i+1][0],dp[i][0]+Math.abs(hi[i+1]-lo[i])+hi[i+1]-lo[i+1]);
            //hi hi -> lo
            dp[i+1][0]=Math.min(dp[i+1][0],dp[i][1]+Math.abs(hi[i+1]-hi[i])+hi[i+1]-lo[i+1]);
            //hi lo -> hi
            dp[i+1][1]=Math.min(dp[i+1][1],dp[i][1]+Math.abs(lo[i+1]-hi[i])+hi[i+1]-lo[i+1]);
        }
        //* ret
        System.out.println(Math.min(dp[N-1][0],dp[N-1][1]));
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}