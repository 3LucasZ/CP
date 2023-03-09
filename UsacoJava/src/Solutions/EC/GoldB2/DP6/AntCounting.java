package Solutions.EC.GoldB2.DP6;

import java.io.*;
import java.util.StringTokenizer;
/*
Ant Counting
Gold Advanced B 6
DP - counting
thoughts:
extremely easy dp
base case: dp[size=0][family=any] = 1
transition:
dp[size][family]=sum(dp[size][family-1], ..., dp[size-famsize][family-1])
 */
public class AntCounting {
    //io
    static boolean submission = false;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int F;
    static int A;
    static int S;
    static int B;
    static int famSize[];
    static long dp[][];

    //helper
    static long MOD=(long)1e6;

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
        F = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        famSize = new int[F+1];
        for (int i=0;i<A;i++){
            int fam = Integer.parseInt(br.readLine());
            famSize[fam]++;
        }

        //logic: dp[set size][last fam]
        dp = new long[B+1][F+1];
        for (int f=0;f<=F;f++) dp[0][f] = 1;
        for (int size=1;size<=B;size++){
            for (int cur=1;cur<=F;cur++){
                long tot = 0;
                for (int prevSize=size-famSize[cur];prevSize<=size;prevSize++){
                    if (prevSize < 0) continue;
                    tot=(tot+dp[prevSize][cur-1])%MOD;
                }
                dp[size][cur]=tot;
            }
        }

        //turn in answer
        long ans = 0;
        for (int i=S;i<=B;i++){
            ans=(ans+dp[i][F])%MOD;
        }
        out.println(ans);
        out.close();
    }
}
