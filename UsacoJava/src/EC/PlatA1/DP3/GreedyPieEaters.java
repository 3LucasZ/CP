package EC.PlatA1.DP3;

import java.io.*;
import java.util.StringTokenizer;

public class GreedyPieEaters {

    static boolean submission = false;
    static boolean debug = true;

    static int N; //pies
    static int M; //cows

    static int[] clo;
    static int[] chi;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        //parse
        setup("pieaters");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        clo = new int[M]; chi = new int[M]; cost = new int[M];
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            cost[i]=Integer.parseInt(st.nextToken());
            clo[i]=Integer.parseInt(st.nextToken());
            chi[i]=Integer.parseInt(st.nextToken());
        }

        //best[idx][lo][hi] = best cow within range lo..hi that covers idx
        int[][][] best = new int[N+1][N+1][N+1];
        for (int c=0;c<M;c++){
            for (int idx=clo[c];idx<=chi[c];idx++){
                best[idx][clo[c]][chi[c]] = Math.max(best[idx][clo[c]][chi[c]], cost[c]);
            }
        }

        for (int idx=1;idx<=N;idx++){
            for (int lo=N;lo>=1;lo--){
                for (int hi=lo+1;hi<=N;hi++){
                    best[idx][lo][hi]=Math.max(best[idx][lo][hi],
                            Math.max(best[idx][lo][hi-1], best[idx][lo+1][hi]));
                }
            }
        }



        //dp[lo][hi] = max weight for pie range
        int[][] dp = new int[N+2][N+2];
        for (int lo=N;lo>=1;lo--){
            for (int hi=lo;hi<=N;hi++){
                for (int idx=lo;idx<=hi;idx++){
                    dp[lo][hi] = Math.max(dp[lo][hi],best[idx][lo][hi]+dp[lo][idx-1]+dp[idx+1][hi]);
                }
            }
        }

        //ans
        out.println(dp[1][N]);
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
