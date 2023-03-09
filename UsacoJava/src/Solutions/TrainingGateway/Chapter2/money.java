package Solutions.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
TASK: money
 */
public class money {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int V;
    static int[] value;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("money.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        value = new int[V];

        for (int i=0;i<V;i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        long[] dp = new long[N+1];
        dp[0]=1;
        for (int coin=0;coin<V;coin++){
            for (int val=1;val<=N;val++){
                int search = val-value[coin];
                if (search < 0) continue;
                dp[val]+=dp[search];
            }
        }
        //turn in answer
        if (!submission) System.out.println(Arrays.toString(dp));
        out.println(dp[N]);
        out.close();
    }
}
