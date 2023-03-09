package Solutions.EC.GoldB2.DP7;

import java.io.*;
import java.util.*;

public class game248 {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    static boolean debug = false;

    //param
    static int N;
    static int[] arr;

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
        //dp[start][extend]
        int[][] dp = new int[N][N];
        for (int i=0;i<N;i++){
            dp[i][0] = Integer.parseInt(br.readLine());
        }

        //logic: range dp
        for (int extend=1;extend<=N-1;extend++){
            for (int start=0;start<=N-1-extend;start++){
                for (int e1=0;e1<=extend-1;e1++){
                    int e2=extend-e1-1;
                    if (dp[start][e1]==dp[start+e1+1][e2]&&dp[start][e1]!=0){
                        dp[start][extend]=dp[start][e1]+1;
                        break;
                    }
                }
            }
        }
        if (debug) for (int extend=0;extend<N;extend++) System.out.println(Arrays.toString(dp[extend]));

        //turn in answer
        int ans = 0;
        for (int r=0;r<N;r++) for (int c=0;c<N;c++) ans = Math.max(ans, dp[r][c]);
        out.println(ans);
        out.close();
    }
}
