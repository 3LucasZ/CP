package USACO.Gold.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
PROB: Exercise
LANG: JAVA
*/
public class Exercise {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static long MOD;

    public static void main(String[] args) throws IOException {
        //parse
        setup("exercise");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        MOD = Long.parseLong(st.nextToken());

        //primes gen
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i=2;i<=N;i++){
            boolean add = true;
            for (int p : primes){
                if (i%p==0) add=false;
            }
            if (add) primes.add(i);
        }
        if (debug) System.out.println(primes);

        //knapsack
        long[] dp = new long[2*N+1];
        dp[0]=1;
        for (int p : primes){
            for (int i = N; i >= 0; i--) {
                for (int primePower = p;primePower<=N;primePower*=p) {
                    dp[i + primePower] = (dp[i + primePower] + dp[i] * primePower) % MOD;
                }
            }
        }
        if (debug) System.out.println(Arrays.toString(dp));

        //ret
        long ans = 0;
        for (int i=0;i<=N;i++) ans=(ans+dp[i])%MOD;
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