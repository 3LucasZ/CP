package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;

public class ModernArt3 {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int[] color;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        //parse + condense
        setup("");
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        color = new int[N];
        for (int i=0;i<N;i++) color[i] = Integer.parseInt(st.nextToken());

        //dp - max segments contained in [i][j]
        dp = new int[N][N];

        //iterate
        for (int i=N-1;i>=0;i--){
            for (int j=i+1;j<N;j++){
                if (color[i]==color[j]){
                    //segment i to j
                    dp[i][j]=Math.max(dp[i][j],1+dp[i+1][j-1]);
                }
                for (int k=i+1;k<j;k++){
                    //k split
                    dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]);
                }
            }
        }

        out.println(N-dp[0][N-1]);
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
