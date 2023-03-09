package Solutions.USACOGuide.UsacoGuideGold.DP;/*
CSES Problem Set
Minimizing Coins
USACO Gold Guide - DP Intro Series
 */
import java.io.*;
import java.util.*;
public class MinimizingCoins {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int[] coins;
    static int N;
    static int C;
    static int[] mincoin;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        coins = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<C;i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        mincoin = new int[N+1];
        mincoin[0] = 0;
        for (int i=1;i<=N;i++) {
            int min = Integer.MAX_VALUE;
            for (int j=0;j<C;j++) {
                if (i-coins[j]<0) continue;
                if (mincoin[i-coins[j]] == -1) continue;
                min = Math.min(min, mincoin[i-coins[j]] + 1);
            }
            if (min == Integer.MAX_VALUE) min = -1;
            mincoin[i] = min;
        }
        //out.println(Arrays.toString(coins));
        //out.println(Arrays.toString(mincoin));
        out.println(mincoin[N]);
        out.close();
    }
}
