package Other.EC.GoldB2.MIX9;

import java.io.*;
import java.util.*;
/*
Exercise
Gold Advanced B - MIX
DP
Thoughts:
Disjoint cycles reset period
convert this to LCM problem
sum of all K where K is lcm of (a1...ai) and sum (a1...ai) = N
only consider power of primes (greedy) in a1...ai
transition:
dp[i][j]=(dp[i][j]+dp[i-1][search]*powerPrime)%M;
keeping sum of all K stored at dp[last prime][position]
 */
public class Exercise {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int M;
    static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //init
        for (int i=2;i<=N;i++){
            boolean cont = true;
            for (int p : primes){
                if (i % p == 0) cont=false;
            }
            if (!cont) continue;
            primes.add(i);
        }
        if (debug) System.out.println(primes);

        //dp[last prime][position]
        int P = primes.size();
        long[][] dp = new long[2][N+1];
        dp[0][0]=1;

        //dp
        for (int i=1;i<=P;i++){
            int prime=primes.get(i-1);

            for (int j=0;j<=N;j++){
                dp[1][j]=dp[0][j];
                int powerPrime = 1;
                while (powerPrime*prime <= N) {
                    powerPrime*=prime;
                    int search = j-powerPrime; if (search < 0) continue;
                    dp[1][j]=(dp[1][j]+dp[0][search]*powerPrime)%M;
                }
            }
            if (debug) System.out.println(Arrays.toString(dp[1]));
            dp[0]=dp[1].clone();
        }

        //turn in answer
        long sum = 0;
        for (int i=0;i<=N;i++) sum=(sum+dp[0][i])%M;
        out.println(sum);
        out.close();
    }
}
