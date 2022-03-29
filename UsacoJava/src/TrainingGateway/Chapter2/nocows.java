package TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
PROB: nocows
LANG: JAVA
Cow Pedigrees
 */
public class nocows {
    //io
    static boolean submission = true;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;

    static final int MOD = 9901;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("nocows.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        //logic: dp[height][nodes]
        int[][] dp = new int[K+1][N+1];
        dp[1][1]=1;
        //height loop
        for (int h=2;h<=K;h++){
            //nodes loop
            for (int n=1;n<=N;n++){
                //right tree nodes loop
                for (int n2=1;n2<=n-2;n2++) {
                    //right tree height loop
                    for (int h2=1;h2<=h-2;h2++) {
                        dp[h][n]=(dp[h][n]+2*dp[h-1][n-1-n2]*dp[h2][n2])%MOD;
                    }
                    dp[h][n]=(dp[h][n]+dp[h-1][n2]*dp[h-1][n-1-n2])%MOD;
                }
            }
        }

        //turn in answer
        out.println(dp[K][N]);
        out.close();
    }
}
