package USACO.Gold.EC.DP2;

import java.io.*;
import java.util.*;
/*
USACO 2016 February Contest, Gold
Problem 2. Circular Barn Revisited
From: Gold Advanced B: DP 2
Also from: USACO Gold Guide - DP - Hard
Notes:
Listened to solution once and it made sense
1. Consider the linear version of this problem
nested loops for O(N*K*N^2)) solution
loop through lastDoor incrementally
loop through doors incrementally
loop through place incrementally
2. dp[doors][place] = Math.min(dp[doors][place], dp[doors-1][lastDoor] + runsum)
^^ do the above N times for the N different rotations of the problem
 */
public class CircularBarnRevisited {
    //io
    static boolean fileSubmission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static ArrayList<Integer> barns = new ArrayList<>();
    static int[] preSum;
    public static void main(String[] args) throws IOException {
        //io
        if (fileSubmission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i=0;i<N;i++) {
            int num = Integer.parseInt(br.readLine());
            barns.add(num);
        }
        //clockwise the barn orientation and cow walking
        Collections.reverse(barns);
        if (debug) {
            System.out.println(barns);
            System.out.println();
        }
        long ans = Integer.MAX_VALUE;
        //dp for every rotation of the circle
        for (int rotation=0;rotation<N;rotation++){
            //rotate barns
            barns.add(barns.get(0));
            barns.remove(0);

            //create presum, presum to add consec runs easily
            preSum = new int[N];
            for (int i=0;i<N;i++) {
                if (i > 0) preSum[i] = preSum[i-1];
                preSum[i]+=barns.get(i);
            }
            if (debug) System.out.println(Arrays.toString(preSum));

            //set up dp
            //format: doors placed, most recent door placement
            long[][] dp = new long[K+1][N];

            //for 1 door placed manually do it
            dp[1][0] = 0;
            for (int i=1;i<N;i++) {
                dp[1][i]=dp[1][i-1]+sum(0,i-1);
            }
            //if (debug) printDP(dp);

            //place doors 2...k with linear strategy
            for (int doors=2;doors<=K;doors++) {
                for (int lastDoor=0;lastDoor<N;lastDoor++) {
                    long runsum = 0;
                    for (int place=lastDoor+1;place<N;place++){
                        if (dp[doors][place]==0) dp[doors][place] = dp[doors-1][lastDoor] + runsum;
                        else dp[doors][place] = Math.min(dp[doors][place], dp[doors-1][lastDoor] + runsum);
                        runsum += sum(lastDoor+1, place);
                    }
                }
            }
            if (debug) {
                printDP(dp);
                System.out.println();
            }
            ans = Math.min(ans, dp[K][N-1]);
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    //helper
    static int sum(int a, int b){
        int ret = 0;
        if (a > 0) ret -= preSum[a-1];
        ret += preSum[b];
        return ret;
    }
    static void printDP(long[][] dp){
        for (int r=1;r<dp.length;r++) {
            System.out.print(r+": ");
            System.out.println(Arrays.toString(dp[r]));
        }
    }
}
