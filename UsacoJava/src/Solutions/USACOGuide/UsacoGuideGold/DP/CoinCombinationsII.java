package Solutions.USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Coin Combinations II
USACO Gold Guide - Knapsack DP - Easy
Looked at answer :(
 */
public class CoinCombinationsII {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int T;
    static int[] coins;
    static int[] comboDP;
    //const
    static final int MOD = (int) 1e9+7;
    public static void main(String[] args) throws IOException {
        //input parsing
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        coins = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        //DP
        comboDP = new int[T+1];
        comboDP[0] = 1;
        for (int i=0;i<N;i++) {
            for (int j=1;j<=T;j++) {
                int search = j-coins[i];
                if (search >= 0) comboDP[j] = (comboDP[j] + comboDP[search])%MOD;
            }
        }
        out.println(comboDP[T]);
        out.close();
    }
}
