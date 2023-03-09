package Solutions.USACOGuide.UsacoGuideGold.DP;/*
CSES Problem Set
Coin Combinations I
USACO Gold Guide - DP Intro Series
 */
import java.io.*;
import java.util.*;
public class CoinCombinationsI {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int X;
    static int[] coins;
    static int[] combos;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        combos = new int[X+1];
        coins = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        combos[0] = 1;
        for (int i=1;i<=X;i++) {
            long sum = 0;
            for (int j=0;j<N;j++) {
                if (i-coins[j] < 0) continue;
                sum += combos[i-coins[j]];
            }
            combos[i] = (int) (sum % (1e9+7));;
        }
        //out.println(Arrays.toString(coins));
        //out.println(Arrays.toString(combos));
        out.println(combos[X]);
        out.close();
    }
}
