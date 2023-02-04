package Other.USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
PROB: EditDistance
LANG: JAVA
*/
public class EditDistance {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int M;

    static String A;
    static String B;

    public static void main(String[] args) throws IOException {
        //parse
        setup("EditDistance");
        A = br.readLine();
        N = A.length();
        B = br.readLine();
        M = B.length();

        //dp setup
        int[][] dp = new int[N+1][M+1]; for (int i=0;i<=N;i++) for (int j=0;j<=M;j++) dp[i][j]=Integer.MAX_VALUE;
        dp[0][0]=0;

        //dp transitions
        for (int i=0;i<=N;i++){
            for (int j=0;j<=M;j++){
                if (i<N && j<M) dp[i+1][j+1]=Math.min(dp[i+1][j+1],dp[i][j]+(A.charAt(i) == B.charAt(j)?0:1));
                if (i<N)dp[i+1][j]=Math.min(dp[i+1][j],dp[i][j]+1);
                if (j<M)dp[i][j+1]=Math.min(dp[i][j+1],dp[i][j]+1);
            }
        }
        if (debug){
            for (int i=0;i<N;i++){
                System.out.println(Arrays.toString(dp[i]));
            }
            System.out.println();
        }

        //ret
        out.println(dp[N][M]);
        out.close();
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}