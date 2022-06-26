package Gold.UsacoGuideGold.DP;

import java.io.*;

public class Game248 {
    static boolean submission = true;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //parse
        setup("248");
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N+1];
        for (int i=1;i<=N;i++){
            A[i]=Integer.parseInt(br.readLine());
        }

        //dp setup dp[l][r]=combo pts
        int[][] dp = new int[N+1][N+1];
        for (int i=1;i<=N;i++) dp[i][i]=A[i];

        //transitions
        for (int l=N;l>=1;l--){
            for (int r=l+1;r<=N;r++){
                for (int m=l;m<=r-1;m++){
                    if (dp[l][m]!=0 && dp[m+1][r]!=0 && dp[l][m]==dp[m+1][r]){
                        dp[l][r]=Math.max(dp[l][r],dp[l][m]+1);
                    }
                }
            }
        }

        //ans
        int ans = 0;
        for (int l=1;l<=N;l++) for (int r=1;r<=N;r++) ans=Math.max(ans,dp[l][r]);
        out.println(ans);
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
