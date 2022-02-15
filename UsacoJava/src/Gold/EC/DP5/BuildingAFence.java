package Gold.EC.DP5;

import java.io.*;
import java.util.*;
/*
Building A Fence
Gold Advanced B 5
DP: Counting
Problem: a + b + c + d = N
how many different a,b,c,d?
1<=a,b,c,d<=(N-1)/2

sol1: combinatorics (Math)
sol2: dp
dp[pieces][length]
calculation:
for (int prev=Math.max(1,len-M);prev<len;prev++){
    dp[pieces][len]+=dp[pieces-1][prev];
}

Extremely easy/trivial problem when grasped...
 */
public class BuildingAFence {
    //io
    static boolean submission = false;
    static boolean debug = false;

    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        int M = (N-1)/2;
        //logic - dp
        //init
        int[][] dp = new int[5][N+1];
        for (int len=1;len<=M;len++){
            dp[1][len]=1;
        }
        for (int pieces=2;pieces<=4;pieces++){
            for (int len=1;len<=N;len++){
                for (int prev=Math.max(1,len-M);prev<len;prev++){
                    dp[pieces][len]+=dp[pieces-1][prev];
                }
            }
        }
        if (debug) {
            for (int pieces=1;pieces<=4;pieces++){
                System.out.print("pieces: "+pieces+" : ");
                for (int len=1;len<=N;len++){
                    System.out.print(dp[pieces][len]+"  ");
                }
                System.out.println();
            }
        }
        //turn in answer
        out.println(dp[4][N]);
        out.close();
    }
}
