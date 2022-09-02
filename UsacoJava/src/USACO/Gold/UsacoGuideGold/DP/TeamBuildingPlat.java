package USACO.Gold.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
PROB: TeamBuilding
LANG: JAVA
*/
public class TeamBuildingPlat {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;
    static int K;

    static int[] FJ;
    static int[] FP;

    static long MOD = (long)(1e9+9);

    public static void main(String[] args) throws IOException {
        //parse
        setup("team");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        FJ = new int[N+1]; FJ[0]=Integer.MIN_VALUE;
        FP = new int[M+1]; FP[0]=Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            FJ[i]=Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=M;i++){
            FP[i]=Integer.parseInt(st.nextToken());
        }

        //sort
        Arrays.sort(FJ); Arrays.sort(FP);
        if (debug) {
            System.out.println("FJ: "+Arrays.toString(FJ));
            System.out.println("FP: "+Arrays.toString(FP));
        }

        //dp[FJI][FPI][size]
        //init + base case
        long[][][] dp = new long[N+1][M+1][K+1];
        for (int i=0;i<=N;i++) for (int j=0;j<=M;j++) dp[i][j][0]=1;
        for (int size=1;size<=K;size++){
            for (int i=1;i<=N;i++){
                for (int j=1;j<=M;j++){
                    dp[i][j][size] = ((dp[i-1][j][size]+dp[i][j-1][size]-dp[i-1][j-1][size])%MOD+MOD)%MOD;
                    if (FJ[i]>FP[j]){
                        dp[i][j][size] = (dp[i][j][size]+dp[i-1][j-1][size-1])%MOD;
                    }
                    if (debug) System.out.println("i: "+i+", j: "+j+", size: "+size+" = "+dp[i][j][size]);
                }
            }
        }

        //ret
        out.println(dp[N][M][K]);
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