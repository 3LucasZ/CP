package Other.EC.GoldB2.DP4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WifiSetup {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int A;
    static int B;
    static int[] pos;
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
        A = 2*Integer.parseInt(st.nextToken());
        B = 2*Integer.parseInt(st.nextToken());
        pos = new int[N+1];
        for (int i=1;i<=N;i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }
        //sort pos
        Arrays.sort(pos);
        if (debug) System.out.println(Arrays.toString(pos));
        //logic/dp
        int[] dp = new int[N+1];
        dp[0] = 0;
        for (int cur=1;cur<=N;cur++){
            dp[cur] = Integer.MAX_VALUE;
            for (int last=0;last<cur;last++){
                dp[cur] = Math.min(dp[cur], dp[last]+A+B*(pos[cur]-pos[last+1])/2);
            }
        }
        if (debug) System.out.println(Arrays.toString(dp));
        //turn in answer
        out.println(dp[N]/2+(dp[N]%2==1?".5":""));
        out.close();
    }
}
