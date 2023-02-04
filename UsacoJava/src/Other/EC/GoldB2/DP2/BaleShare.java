package Other.EC.GoldB2.DP2;/*
Bale Share
USACO Gold Advanced B
DP
Notes:
Looked at explanation first
Dice Combo 2 Nostalgia
generating all possible sums by looping through all sizes and then looping through all sums reached so far
O(N*MAXTOTAL^2) algorithm
Understanding: 4/5
 */
import java.io.*;

public class BaleShare {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //input
    static int N;
    static int[] size;
    //dp
    static int sum = 0;
    static boolean[][]dp;
    public static void main(String[] args) throws IOException {
        //parse
        N = Integer.parseInt(br.readLine());
        size = new int[N];
        for (int i=0;i<N;i++) {
            int S = Integer.parseInt(br.readLine());
            size[i] = S;
            sum += S;
        }
        //setup
        dp = new boolean[sum+1][sum+1];
        dp[0][0] = true;
        //backward dp (dice combo v2 FLASHBACK!)
        for (int bale : size){
            for (int x=sum;x>=0;x--){
                for (int y=sum;y>=0;y--){
                    if (dp[x][y]) {
                        dp[x+bale][y]=true;
                        dp[x][y+bale]=true;
                    }
                }
            }
        }
        //for all possible x,y,z triplet find best
        int ans = Integer.MAX_VALUE;
        for (int x=0;x<=sum;x++){
            for (int y=0;y<=sum;y++) {
                if (dp[x][y]) ans = Math.min(ans, Math.max(x,Math.max(y,sum-x-y)));
            }
        }
        //print
        out.println(ans);
        out.close();
    }
}
