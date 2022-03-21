package Procrastinate;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
TASK: nocows
 */
public class nocows {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int K;

    //helper
    static int MOD = 9901;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("nocows.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //logic: [height][nodes]
        int[][] dp = new int[K+1][N+1];
        dp[1][1]=1;
        for (int height=2;height<=K;height++){
            for (int nodes=1;nodes<=N;nodes+=2){
                for (int sub1=1;sub1<=nodes-1-sub1;sub1+=2) {
                    int sub2=nodes-1-sub1;
                    int c = sub1==sub2?1:2;
                    int sum = 0;
                    for (int subHeight=1;subHeight<=height-2;subHeight++){
                        sum = (sum + dp[subHeight][sub2])%MOD;
                    }
                    dp[height][nodes]=(dp[height-1][sub1]*sum*c + (dp[height-1][sub1]*dp[height-1][sub1]))%MOD;
                }
            }
        }
        if (!submission){
            for (int i=0;i<=K;i++){
                System.out.println(Arrays.toString(dp[i]));
            }
        }
        //turn in answer
        out.println(dp[K][N]);
        out.close();
    }
}
