package Other.EC.GoldB2.DP4;

import java.io.*;
import java.util.*;
/*
Grazing On The Run
Gold Advanced B 4
***EXACT SAME AS USACO 2013 March Contest, Silver***
DP - incremental/range dp
loop through dp size
loop through left indice
dp[size] is dependent on and only on dp[size-1]
state capture: dp[l][r][flag]
first try: used BFS type approach but this did not work... maybe its possible to get it to work
not that hard to understand!
 */
public class GrazingOnTheRun {
    //io
    static boolean submission = true;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int L;
    static int[] loc;
    //const
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cowrun.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowrun.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        if (!submission){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
        }
        else {
            N = Integer.parseInt(br.readLine());
            L = 0;
        }
        loc = new int[N+1];
        loc[0]=L;
        for (int i=1;i<=N;i++) {
            loc[i] = Integer.parseInt(br.readLine());
        }
        //sort and find index of L
        Arrays.sort(loc);
        int s = 0;
        for (int i=0;i<=N;i++) {
            if (loc[i]==L) s=i;
        }
//        if (debug) {
//            System.out.println(Arrays.toString(loc));
//            System.out.println(s);
//        }
        //logic/dp using a incremental approach
        long[][][] dp = new long[N+1][N+1][2];
        for (int l=0;l<=N;l++){
            for (int r=0;r<=N;r++){
                for (int f=0;f<=1;f++){
                    dp[l][r][f]=INF;
                }
            }
        }
        dp[s][s][0]=0;
        dp[s][s][1]=0;

        for (int size=1;size<=N;size++){
            for (int l=0;l<=N-size;l++) {
                if (dp[l+1][l+size][0]!=INF) dp[l][l+size][0] = Math.min(dp[l][l+size][0], dp[l+1][l+size][0] + (long)(N+1-size)*(loc[l+1]-loc[l]));
                if (dp[l+1][l+size][1]!=INF) dp[l][l+size][0] = Math.min(dp[l][l+size][0], dp[l+1][l+size][1] + (long)(N+1-size)*(loc[l+size]-loc[l]));

                if (dp[l][l+size-1][0]!=INF) dp[l][l+size][1] = Math.min(dp[l][l+size][1], dp[l][l+size-1][0] + (long)(N+1-size)*(loc[l+size]-loc[l]));
                if (dp[l][l+size-1][1]!=INF) dp[l][l+size][1] = Math.min(dp[l][l+size][1], dp[l][l+size-1][1] + (long)(N+1-size)*(loc[l+size]-loc[l+size-1]));
            }
            //shut down debug statements to cut down time
//            if (debug){
//                for (int l=0;l<=N-size;l++){
//                    System.out.println("l: "+l+", r: "+(l+size)+" ("+dp[l][l+size][0]+", "+dp[l][l+size][1]+")");
//                }
//                System.out.println();
//            }
        }

        //turn in answer
//        if (debug){
//            for (int l=0;l<=N;l++){
//                for (int r=l;r<=N;r++) {
//                    System.out.println("l: "+l+", r: "+r+" ("+dp[l][r][0]+", "+dp[l][r][1]+")");
//                }
//            }
//        }
        //bug caught: use min not max
        out.println(Math.min(dp[0][N][0],dp[0][N][1]));
        out.close();
    }
}
