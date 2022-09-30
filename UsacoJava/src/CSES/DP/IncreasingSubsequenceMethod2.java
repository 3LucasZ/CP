package CSES.DP;

import java.io.*;
import java.util.*;
/*
PROB: IncreasingSubsequenceMethod2
LANG: JAVA
*/
public class IncreasingSubsequenceMethod2 {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int[] A;
    static int INF = Integer.MAX_VALUE/2;
    public static void main(String[] args) throws IOException {
        //parse
        setup("IncreasingSubsequenceMethod2");
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i=0;i<N;i++) A[i]=(Integer.parseInt(st.nextToken()));
        //dp setup
        int[] dp = new int[N+1];
        dp[0]=-INF; for (int i=1;i<=N;i++) dp[i]=INF;
        //dp transition
        for (int i=0;i<N;i++){
            //bin search largest t where dp[t]<val[i]
            int lo=0;int hi=N;
            while (lo<hi){
                int mid = (lo+hi+1)/2;
                if (dp[mid]<A[i])lo=mid;
                else hi=mid-1;
            }
            int t = lo;
            if (debug) System.out.println("i: "+i+", t: "+t+", dp: "+Arrays.toString(dp));
            dp[t+1]=Math.min(dp[t+1],A[i]);
        }
        //ret
        int ans = 0;
        for (int len=0;len<=N;len++){
            if (dp[len]!=INF) ans=len;
        }
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