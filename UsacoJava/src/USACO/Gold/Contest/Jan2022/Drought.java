package USACO.Gold.Contest.Jan2022;

import java.io.*;
import java.util.*;
/*
PROB: Drought
LANG: JAVA
*/
public class Drought {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int[] H;

    static int MAX_F = Integer.MAX_VALUE;
    static int MAX_H = Integer.MIN_VALUE;
    static int MOD = (int)(1e9+7);

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
        int ans = 0;
        int[][][] dp = new int[MAX_F + 1][N][MAX_H + 1];
        for (int F = 0; F <= MAX_F; F++) {
            dp[F][0][F] = 1;
            for (int i = 0; i < N-1; i++) {
                for (int val = F; val <= H[i]; val++) {
                    for (int add = 0; add <= Math.min(H[i] - val, H[i + 1]-F); add++) {
                        dp[F][i + 1][F+add] = (dp[F][i + 1][F+add] + dp[F][i][val]) % MOD;
                    }
                }
            }
            if (N%2==1 || F==0) {
                for (int last = 0; last <= H[N - 1]; last++) {
                    ans = (ans + dp[F][N - 1][last]) % MOD;
                }
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