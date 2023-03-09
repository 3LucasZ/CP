package Solutions.USACO.Season2021_2022.Jan2022.Gold;

import java.io.*;
import java.util.*;
/*
PROB: Drought
LANG: JAVA
*/
public class Drought2 {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int[] H;

    static int MAX_F = Integer.MAX_VALUE;
    static int MAX_H = Integer.MIN_VALUE;
    static long MOD = (int)(1e9+7);

    public static void main(String[] args) throws IOException {
        //parse
        setup("Drought");
        N = Integer.parseInt(br.readLine());
        H = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
            MAX_F=Math.min(MAX_F,H[i]);
            MAX_H=Math.max(MAX_H,H[i]);
        }

        //dp
        long ans = 0;
        long[][][] dp = new long[MAX_F + 1][N][MAX_H + 1];
        for (int F = 0; F <= MAX_F; F++) {
            //base case
            for (int val=F;val<=H[0];val++) dp[F][0][val] = 1;
            //transitions
            for (int i = 1; i < N; i++) {
                for (int add = 0; add <= H[i]-F; add++) {
                    if (add!=0) dp[F][i][F+add]=dp[F][i][F+add-1];
                    if (H[i-1]-add<0) continue;
                    dp[F][i][F+add]=(dp[F][i][F+add]+dp[F][i-1][H[i-1]-add])%MOD;
                }
                if (debug) System.out.println("[F: "+F+", i: "+i+"] "+Arrays.toString(dp[F][i]));
            }
            if (N%2==1 || F==0) {
                ans = (ans + dp[F][N-1][H[N-1]]) % MOD;
            }
        }

        //ret
        out.println(ans);
        out.close();
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