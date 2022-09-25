package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;

public class CodingCompany {
    static boolean submission = false;
    static boolean debug = true;

    //coders
    static int N;
    //max sum
    static int X;

    //skill
    static int[] skill;

    static long MOD = (long)(1e9+7);
    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        skill = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) skill[i]=Integer.parseInt(st.nextToken());

        //sort skill before dp
        Arrays.sort(skill);

        //dp[ith coder][open coders][cost]
        long[][][] dp = new long[N+1][N+1][X+1];
        dp[0][0][0]=1;
        for (int i=0;i<=N-1;i++){
            for (int open=0;open<=N/2;open++){
                for (int cost=0;cost<=X;cost++){
                    long p = dp[i][open][cost];
                    int q = open*(skill[i+1]-skill[i]);
                    if (cost+q>X) continue;

                    //group with self
                    dp[i+1][open][cost+q] = (dp[i+1][open][cost+q]+p)%MOD;
                    //close
                    if (open > 0) dp[i+1][open-1][cost+q] = (dp[i+1][open-1][cost+q]+p*open)%MOD;
                    //new
                    dp[i+1][open+1][cost+q] = (dp[i+1][open+1][cost+q]+p)%MOD;
                    //in
                    dp[i+1][open][cost+q] = (dp[i+1][open][cost+q]+p*open)%MOD;
                }
            }
        }

        //ret
        long ans = 0;
        for (int cost=0;cost<=X;cost++){
            ans=(ans+dp[N][0][cost])%MOD;
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
