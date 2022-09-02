package USACO.Gold.EC.DP1;

import java.io.*;
import java.util.*;
/*
A Game
Recursion + Memoization
Commentary:
- dp[s][e] = Math.max(sum(s,e-1)-ans(s,e-1)+nums[e], sum(s+1, e)-ans(s+1,e)+nums[s]);
 */
public class AGame {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int nums[];
    static int dp[][];
    static int presum[];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("game1.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        presum = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
            if (i>0) presum[i] = presum[i-1];
            presum[i]+=nums[i];
        }
        //logic
        dp = new int[N][N];
        //cp
        //if (!submission) out.println(Arrays.toString(presum));
        //turn in answer
        int player1 = ans(0, N-1);
        int player2 = sum(0, N-1) - player1;
        out.println(player1 + " " + player2);
        out.close();
    }
    static int ans(int s, int e){
        if (dp[s][e]>0) return dp[s][e];
        if (s==e) dp[s][e]=nums[s];
        else {
            dp[s][e] = Math.max(sum(s,e-1)-ans(s,e-1)+nums[e], sum(s+1, e)-ans(s+1,e)+nums[s]);
        }
        return dp[s][e];
    }
    static int sum(int s, int e){
        if (s == 0) return presum[e];
        return presum[e]-presum[s-1];
    }
}
