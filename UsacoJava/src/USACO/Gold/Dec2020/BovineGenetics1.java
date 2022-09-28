package USACO.Gold.Dec2020;

import java.io.*;
import java.util.*;
/*
PROB: BovineGenetics
LANG: JAVA
*/
public class BovineGenetics1 {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int[] S;
    static long MOD = (long)(1e9+7);

    public static void main(String[] args) throws IOException {
        //parse
        setup("BovineGenetics");
        String str = br.readLine();
        N = str.length(); S = new int[N+1];
        for (int i=1;i<=N;i++) {
            if (str.charAt(i-1)=='A')S[i]=0;
            if (str.charAt(i-1)=='G')S[i]=1;
            if (str.charAt(i-1)=='C')S[i]=2;
            if (str.charAt(i-1)=='T')S[i]=3;
            if (str.charAt(i-1)=='?')S[i]=4;
        }
        if (debug) System.out.println("N: "+N+", S: "+Arrays.toString(S));

        //cnt_dp: dp[l][r][a][b] = # ways to replace ? in s[l...r] with s_l=a and s_r=b
        long[][][][] cnt_dp = new long[N+1][N+1][4][4];
        for (int l=1;l<=N;l++){
            for (int a=0;a<4;a++){
                //base case
                if (S[l]==a||S[l]==4)cnt_dp[l][l][a][a]=1;
                //transition
                for (int r=l+1;r<=N;r++){
                    //b on r-1
                    for (int b=0;b<4;b++){
                        //b on r
                        for (int b2=0;b2<4;b2++) {
                            if ((S[r]==b2||S[r]==4)&&b2!=b) cnt_dp[l][r][a][b2] = (cnt_dp[l][r][a][b2]+cnt_dp[l][r-1][a][b])%MOD;
                        }
                    }
                }
            }
        }
        if (debug) {
            for (int l = 1; l <= N; l++) for (int r = l; r <= N; r++) for (int a = 0; a < 4; a++) for (int b = 0; b < 4; b++) {
                if (cnt_dp[l][r][a][b]!=0) System.out.println("l: "+l+", r: "+r+", a: "+a+", b: "+b+" = "+cnt_dp[l][r][a][b]);
            }
        }

        //dp: dp[len][last=a/b/c/d] = # working original genomes from 1...len who ends in a/b/c/d
        long[][] dp = new long[N+1][4];
        //base case
        for (int i=0;i<4;i++) dp[0][i]=1;
        //transition
        for (int i=0;i<N;i++){
            for (int last=0;last<4;last++) {
                for (int j=i+1;j<=N;j++) {
                    for (int last2=0;last2<4;last2++) {
                        dp[j][last2]=(dp[j][last2]+dp[i][last]*cnt_dp[i+1][j][last2][last])%MOD;
                    }
                }
            }
        }
        if (debug){
            for (int i=0;i<=N;i++){
                System.out.println(i+": "+Arrays.toString(dp[i]));
            }
        }

        //ret
        long ans = 0; for (int i=0;i<4;i++) ans=(dp[N][i]+ans)%MOD;
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