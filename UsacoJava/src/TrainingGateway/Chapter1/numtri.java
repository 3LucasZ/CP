package TrainingGateway.Chapter1;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
TASK: numtri
 */
public class numtri {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[][] triangle;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("numtri.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        triangle = new int[N][N];
        dp = new int[N][N];
        for (int r=0;r<N;r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c=0;c<=r;c++){
                triangle[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        if (!submission) for (int r=0;r<N;r++) System.out.println(Arrays.toString(triangle[r]));
        //logic/dp (Why is there dp on a bronze module?? haha)
        dp[0][0]=triangle[0][0];
        for (int row=0;row<N-1;row++){
            for (int col=0;col<=row;col++){
                dp[row+1][col]=Math.max(dp[row][col]+triangle[row+1][col],dp[row+1][col]);
                dp[row+1][col+1]=Math.max(dp[row][col]+triangle[row+1][col+1],dp[row+1][col+1]);
            }
        }
        //find best path after dp
        int max = 0;
        for (int col=0;col<N;col++){
            max = Math.max(max,dp[N-1][col]);
        }
        //turn in answer
        out.println(max);
        out.close();
    }
}
