package Codeforces.Round834;

import java.io.*;
import java.util.*;
/*
PROB: TheHumanoid
LANG: JAVA
*/
public class TheHumanoid {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //* TCS
        setup("TheHumanoid");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }

    static int N;
    static int H;
    static Integer[] A;
    static void solve() throws IOException{
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new Integer[N+1]; for (int i=1;i<=N;i++) A[i] = Integer.parseInt(st.nextToken()); A[0]=0;
        //* sort A greedy
        Arrays.sort(A);
        //* dp
        long[][][] dp = new long[N+1][3][2]; //astro, 2 green pow x2, 1 blue pow x3
        //base
        dp[0][0][0]=H;
        //trans
        for (int i=0;i<=N;i++){
            for (int green=0;green<=2;green++){
                for (int blue=0;blue<=1;blue++){
                    //update within self
                    if (green+1<=2){
                        dp[i][green+1][blue]=Math.max(dp[i][green+1][blue],dp[i][green][blue]*2);
                    }
                    if (blue+1<=1){
                        dp[i][green][blue+1]=Math.max(dp[i][green][blue+1],dp[i][green][blue]*3);
                    }
                    if (i==N) continue;
                    //update upcoming state
                    if (dp[i][green][blue]>A[i+1]) dp[i+1][green][blue]=Math.max(dp[i+1][green][blue],dp[i][green][blue]+A[i+1]/2);
                }
            }
        }
        //* ret
        int ans = 0;
        for (int i=1;i<=N;i++){
            if (dp[i][2][1]!=0) ans=i;
        }
        out.println(ans);
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