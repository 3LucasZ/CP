package USACOGuide.UsacoGuideGold.DP;
/*
CSES Problem Set
Removing Digits
USACO Gold Guide - DP Intro Series
 */
import java.io.*;
import java.util.*;

public class RemovingDigits {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int[] dp;
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;
        for (int i=N;i>=0;i--){
            if (dp[i] == Integer.MAX_VALUE) continue;
            int num = i;
            while (num > 0) {
                int digit = num%10;
                dp[i-digit] = Math.min(dp[i-digit],dp[i]+1);
                num /= 10;
            }
        }
        //out.println(Arrays.toString(dp));
        out.println(dp[0]);
        out.close();
    }
}
