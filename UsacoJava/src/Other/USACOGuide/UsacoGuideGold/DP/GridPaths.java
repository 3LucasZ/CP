package Other.USACOGuide.UsacoGuideGold.DP;

import java.io.*;

/*
CSES Problem Set
Grid Paths
USACO Gold Guide - Intro DP Series
 */
public class GridPaths {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][N+1];
        dp[1][1] = 1;
        int ans = -1;
        for (int r=1;r<=N;r++) {
            String str = br.readLine();
            for (int c=1;c<=N;c++) {
                if (r==1 && c==1) {
                    if (str.charAt(c-1)=='*')  ans = 0;
                    continue;
                }
                if (str.charAt(c-1)=='.') {
                    dp[r][c] = (int) (((long)dp[r-1][c]+dp[r][c-1]) % (1e9+7));
                }
                else {
                    dp[r][c] = 0;
                }
            }
        }
//        for (int i=0;i<=N;i++) {
//            out.println(Arrays.toString(dp[i]));
//        }
//        out.println();
        if (ans == 0) out.println(ans);
        else out.println(dp[N][N]);
        out.close();
    }
}
