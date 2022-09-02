package USACO.Gold.Training;

import java.io.*;
import java.util.StringTokenizer;

public class Snakes {
    static boolean submission = true;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        setup("snakes");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] g = new int[N];
        for (int i=0;i<N;i++) g[i]=Integer.parseInt(st.nextToken());
        //preprocess
        int[][] max = new int[N][N];
        int[][] sum = new int[N][N];
        int[][] cost = new int[N][N];
        for (int i=0;i<N;i++){
            for (int j=i;j<N;j++){
                if (i==j) {
                    max[i][j]=g[i];
                    sum[i][j]=g[i];
                }
                else {
                    max[i][j]=Math.max(g[j],max[i][j-1]);
                    sum[i][j]=sum[i][j-1]+g[j];
                }
                cost[i][j]=max[i][j]*(j-i+1)-sum[i][j];
                if (debug){
                    System.out.println("i:"+i+", j:"+j+": "+cost[i][j]);
                }
            }
        }

        //dp
        int[][] dp = new int[N][K+1];
        for (int r=0;r<N;r++) for (int c=0;c<=K;c++) dp[r][c]=Integer.MAX_VALUE/3;
        for (int change=0;change<=K;change++){
            for (int i=0;i<N;i++) {
                if (change==0) dp[i][change]=cost[0][i];
                else {
                    for (int last = 0; last < i; last++) {
                        dp[i][change]=Math.min(dp[i][change],dp[last][change-1]+cost[last+1][i]);
                    }
                }
            }
        }
        out.println(dp[N-1][K]);
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
