package Solutions.USACO.Season2016_2017.Feb2017.Gold;

import java.io.*;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoad1Subtask {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int T;
    static long[][][] dp;
    static int[][] cost;

    //helper
    static final long INF = Long.MAX_VALUE/100;
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("visitfj.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        cost = new int[N][N];
        for (int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++){
                cost[r][c]=Integer.parseInt(st.nextToken());
            }
        }

        //logic
        dp = new long[N][N][N*N+1];
        for (int r=0;r<N;r++) for (int c=0;c<N;c++) for (int m=0;m<=N*N;m++) dp[r][c][m]=INF;
        dp[0][0][0]=0;

        for (int move=0;move<N*N;move++){
            for (int r=0;r<N;r++){
                for (int c=0;c<N;c++){
                    for (int i=0;i<4;i++){
                        int kr = r+dr[i];
                        int kc = c+dc[i];
                        if (kr<0||kr>=N||kc<0||kc>=N) continue;
                        dp[kr][kc][move+1] = Math.min(dp[kr][kc][move+1], dp[r][c][move] + T + ((move%3==2)?cost[kr][kc]:0));
                    }
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int m=0;m<=N*N;m++){
            ans = Math.min(dp[N-1][N-1][m],ans);
        }
        //turn in answer
        out.println(ans);
        out.close();
    }

}
