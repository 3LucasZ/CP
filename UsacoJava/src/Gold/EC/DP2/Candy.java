package Gold.EC.DP2;

import java.io.*;
import java.util.*;

public class Candy {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Opt = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] choice = new int[Opt];
        boolean[] favorite = new boolean[N+M+1];
        for (int i=0;i<Opt;i++) {
            choice[i] = Integer.parseInt(br.readLine());
        }
        for (int i=0;i<F;i++) {
            favorite[Integer.parseInt(br.readLine())]=true;
        }
        //dp -> iteration
        int start = N;
        if (favorite[start]) start += M;
        int[] dp = new int[start+2];
        int[] cnt = new int[start+2];
        Arrays.fill(dp, -1);
        dp[start]=0;
        int ans = 0;
        while (start>=0){
            if (dp[start]==-1) {
                start--;
                continue;
            }
            if (favorite[start]) {
                cnt[start]++;
                if (cnt[start] > F+5) {
                    ans = -1;
                    break;
                }
                if (dp[start] > dp[start+M]) {
                    dp[start+M]=dp[start];
                    start+=M;
                }
                else start--;
                continue;
            }

            for (int c : choice) {
                int search = start - c;
                if (search < 0) continue;
                dp[search] = Math.max(dp[search], dp[start] + c);
            }
            start--;
//            out.println("Stuck at: "+start);
//            out.println("DP value of "+dp[start]);
        }
        if (ans == -1) out.println(-1);
        else {
            for (int i = 0; i < dp.length; i++) {
                ans = Math.max(ans, dp[i]);
            }
            out.println(ans);
        }
        //out.println(Arrays.toString(dp));
        out.close();
    }
}
