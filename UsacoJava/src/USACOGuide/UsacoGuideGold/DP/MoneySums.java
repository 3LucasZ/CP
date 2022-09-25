package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Money Sums
USACO Gold Guide - Knapsack DP Series - Easy
Thoughts:
dp arr:
dp[current coin][value] = true/false

propagation:
if (dp[onCoin][val]) {
   dp[1 + onCoin][val] = true;
   dp[1 + onCoin][val+coins[onCoin]] = true;
}
 */

public class MoneySums {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int coins[];
    //dp[last coin][sum]
    static boolean dp[][];
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        coins = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        dp = new boolean[N+1][N*1000+1];
        dp[0][0]=true;
        //dp
        for (int onCoin=0;onCoin<N;onCoin++){
            for (int val=0;val<=N*1000;val++){
                if (dp[onCoin][val]) {
                    dp[1 + onCoin][val] = true;
                    dp[1 + onCoin][val+coins[onCoin]] = true;
                }
            }
        }
        //print answer
        StringBuilder chain = new StringBuilder();
        int cnt=0;
        for (int i=1;i<=N*1000;i++){
            if (dp[N][i]){
                cnt++;
                chain.append(i+" ");
            }
        }
        out.println(cnt);
        out.println(chain);
        out.close();
    }
}
