package Other.UnsortedTraining.GoldTraining;

import java.io.*;
import java.util.StringTokenizer;
/*
USACO 2018 February Contest, Gold
Problem 3. Taming the Herd
USACO Gold Training
Dynamic Programming - Optimization
state: dp[breakouts][final]
some greedy implementation techniques for "fin index"
 */
public class TamingTheHerd {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[] A;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("taming.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        //pre process error function
        int[][] error = new int[N][N];
        for (int i=0;i<N;i++){
            if (A[i]==0) error[i][i]=0;
            else error[i][i]=1;
            for (int j=i+1;j<N;j++){
                error[i][j]=error[i][j-1]+(A[j]==j-i?0:1);
            }
        }
        if (!submission){
            for (int i=0;i<N;i++){
                out.print("i="+i+": ");
                for (int j=i;j<N;j++){
                    out.print(error[i][j]+" ");
                }
                out.println();
            }
        }

        //logic: dp[breakouts][last i]
        int[][] dp = new int[N+1][N];
        dp = new int[N+1][N];
        for (int b=0;b<=N;b++) for (int f=0;f<N;f++) dp[b][f]=Integer.MAX_VALUE/10;
        for (int i=0;i<N;i++) dp[1][i] = error[0][i];
        out.println(dp[1][N-1]);
        for (int b=2;b<=N;b++){
            for (int f=0;f<N;f++){
                for (int i=0;i<f;i++){
                    dp[b][f]=Math.min(dp[b][f],dp[b-1][i]+error[i+1][f]);
                }
            }
            out.println(dp[b][N-1]);
        }
        out.close();
    }
}
