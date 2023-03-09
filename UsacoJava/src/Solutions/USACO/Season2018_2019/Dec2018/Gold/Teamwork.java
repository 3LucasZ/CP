package Solutions.USACO.Season2018_2019.Dec2018.Gold;

import java.io.*;
import java.util.*;
/*
USACO 2018 December Contest, Gold
Problem 3. Teamwork
Super easy DP, constructive
 */
public class Teamwork {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int K;
    static int[] A;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        setup("teamwork");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N+1];
        for (int i=1;i<=N;i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        //dp
        dp = new int[N+1];
        for (int i=0;i<N;i++){
            int max = -1;
            for (int j=1;j<=K&&i+j<=N;j++){
                max=Math.max(max,A[i+j]);
                dp[i+j]=Math.max(dp[i+j],dp[i]+max*j);
            }
        }
        if (debug) out.println(Arrays.toString(dp));
        out.println(dp[N]);
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
