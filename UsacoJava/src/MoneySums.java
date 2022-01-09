import java.io.*;
import java.util.*;
/*
CSES Problem Set
Money Sums
USACO Gold Guide - Knapsack DP Series - Easy
 */

public class MoneySums {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int coins[];
    static boolean reachable[];
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        coins = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        reachable = new boolean[100001];
        reachable[0] = true;
        //dp
        for (int i=1;i<=100000;i++) {
            boolean canReach = false;
            for (int j=0;j<N;j++) {
                if (i-coins[j] < 0) continue;
                if (reachable[i-coins[j]]) canReach = true;
            }
            reachable[i] = canReach;
        }
        //print answer
        int cnt = 0;
        for (int i=1;i<=100000;i++) {
            if (reachable[i]) cnt++;
        }
        out.println(cnt);
        for (int i=1;i<=100000;i++) {
            if (reachable[i]) out.print(i+" ");
        }
        out.close();
    }
}
