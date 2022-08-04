package TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;

/*
PROB: stamps
LANG: JAVA
 */

public class stamps {
    static boolean submission = true;
    static boolean debug = true;

    static int[] coin;
    static int N;
    static int K;

    static int inf = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //parse
        setup("stamps");
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        coin = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            coin[i]=Integer.parseInt(st.nextToken());
        }

        //dp[cost]=min coins
        int[] dp = new int[20000*K];
        Arrays.fill(dp,inf);
        dp[0]=0;
        for (int tot=0;tot<10000*K;tot++){
            for (int c : coin){
                int next = tot+c;
                if (dp[tot]+1<=K) dp[next]=Math.min(dp[next],dp[tot]+1);
            }
        }

        //range 1..M with coins < inf
        int i;
        for (i=1;i<=10000*K;i++)if (dp[i]==inf) break;
        out.println(i-1);
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
