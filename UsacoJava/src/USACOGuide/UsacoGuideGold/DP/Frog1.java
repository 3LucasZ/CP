package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
A - Frog 1
AtCoder
USACO Gold Guide - Dynamic Programming - Easy
 */
public class Frog1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int height[];
    static int min_cost[];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        height = new int[N+1];
        min_cost = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        min_cost[1] = 0;
        min_cost[2] = Math.abs(height[2]-height[1]);
        for (int i=3;i<=N;i++) {
            min_cost[i] = Math.min(min_cost[i-1]+Math.abs(height[i]-height[i-1]), min_cost[i-2]+Math.abs(height[i]-height[i-2]));
        }
        out.println(min_cost[N]);
        out.close();
    }
}
