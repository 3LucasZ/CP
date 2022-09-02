package USACO.Gold.EC.FINAL;

import java.io.*;
import java.util.*;

public class CountyFair {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    static int N;
    static int[] end;
    static Interval[] intervals;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        end = new int[N+1];
        intervals = new Interval[N];
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            end[i]=e;
            intervals[i-1] = new Interval(i,s);
        }
        Arrays.sort(intervals,(a,b)->a.start-b.start);

        //dp time! dp[B][E][i]
        int[][][] dp = new int[N+1][N+1][N+1];

        for (int i=1;i<=N;i++){
            for (int B=0;B<=i-1;B++){
                for (int E=0;E<=i-1;E++){
                    if (B==0 || intervals[i-1].start >= end[intervals[B-1].id]) dp[i][E][i]=Math.max(dp[i][E][i],dp[B][E][i-1]+1);
                    if (E==0 || intervals[i-1].start >= end[intervals[E-1].id]) dp[B][i][i]=Math.max(dp[B][i][i],dp[B][E][i-1]+1);
                    dp[B][E][i]=Math.max(dp[B][E][i],dp[B][E][i-1]);
                }
            }
        }

        int ans = 0;
        for (int B=0;B<=N;B++){
            for (int E=0;E<=N;E++){
                ans=Math.max(ans,dp[B][E][N]);
            }
        }
        out.println(ans);
        out.close();
    }

    private static class Interval {
        int id;
        int start;
        public Interval(int i, int s){
            id=i;
            start=s;
        }
    }
}
