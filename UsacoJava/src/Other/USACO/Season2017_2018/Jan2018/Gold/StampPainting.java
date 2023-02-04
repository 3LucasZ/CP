package Other.USACO.Season2017_2018.Jan2018.Gold;

import java.io.*;
import java.util.*;
/*
Stamp Painting
Gold Advanced B - 5
DP: presums and systematic - counting
Notes:
Key observation: Using complementary counting we know that
all paintings - invalid paintings = valid paintings (answer)
valid paintings = M^N
invalid paintings we need a dp
invalid[Q] = (M-1)(invalid[Q-1] + ... + invalid[Q-K+1])
^^ The gold
we can quickly sum invalid[A]+...invalid[B] using presum
 */
public class StampPainting {
    //io
    static boolean debug = false;
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static int K;
    static long init = 1;
    static long[] dp;
    static long[] dpPresum;
    //const
    static final long MOD = (long) (1e9+7);
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("spainting.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        //logic: dp and dp presum for quick summings
        //init
        dp = new long[N+1];
        dpPresum = new long[N+1];

        dp[0]=1;
        dpPresum[0]=1;
        for (int i=1;i<=K-1;i++) {
            dp[i]=(dp[i-1]*M)%MOD;
            dpPresum[i]=(dpPresum[i-1]+dp[i])%MOD;
        }
        if (debug) {
            System.out.println(Arrays.toString(dp));
            System.out.println(Arrays.toString(dpPresum));
            System.out.println();
        }
        for (int i=K;i<=N;i++){
            dp[i]=((M-1)*(dpPresum[i-1]-dpPresum[i-K]+MOD))%MOD;
            dpPresum[i]=(dpPresum[i-1]+dp[i])%MOD;
        }
        if (debug) {
            System.out.println(Arrays.toString(dp));
            System.out.println(Arrays.toString(dpPresum));
        }
        for (int i=1;i<=N;i++)init=(init*M)%MOD;
        if (debug)System.out.println(init);
        //turn in answer
        out.println((init-dp[N]+MOD)%MOD);
        out.close();
    }
}
