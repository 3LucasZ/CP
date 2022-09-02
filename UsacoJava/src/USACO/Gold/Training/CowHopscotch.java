package USACO.Gold.Training;

import java.io.*;
import java.util.*;
/*
USACO 2015 February Contest, Silver
Problem 2. Cow Hopscotch (Silver)
USACO Silver/Gold Training
Thoughts:
Easiest DP ive ever seen hahahaha
 */
public class CowHopscotch {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int R;
    static int C;
    static int K;
    static int[][] label;
    //helper
    static final long MOD = (long)1e9+7;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("hopscotch.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        label = new int[R][C];
        for (int r=0;r<R;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<C;c++){
                label[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        //logic: dp
        long[][] dp = new long[R][C];
        dp[0][0]=1;
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                for (int r2=r+1;r2<R;r2++){
                    for (int c2=c+1;c2<C;c2++){
                        if (label[r2][c2]!=label[r][c]) dp[r2][c2]=(dp[r2][c2]+dp[r][c])%MOD;
                    }
                }
            }
        }
        //turn in answer
        out.println(dp[R-1][C-1]);
        out.close();
    }
}