import java.io.*;
import java.util.*;

public class TheCowRun {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[] pos;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cowrun.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowrun.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        pos = new int[N+1];
        for (int i=0;i<N;i++) {
            int num = Integer.parseInt(br.readLine());
            pos[i] = num;
        }
        //sort
        Arrays.sort(pos);
        //logic
        //[left most][right most][on left or on right]
        dp = new int[N+1][N+1][2];

        int zeroIndex = 0;
        //init dp
        for (int i=0;i<N+1;i++) {
            if (pos[i]==0) {
                dp[i][i][0]=0;
                dp[i][i][1]=0;
                zeroIndex = i;
            }
        }

        for (int i=0;i<N+1;i++){
            for (int j=0;j<N+1;j++){
                for (int k=0;k<2;k++) {
                    dp[i][j][k]=-1;
                    if ( i > zeroIndex || j < zeroIndex) dp[i][j][k]=Integer.MAX_VALUE;
                }
            }
        }

        //create dp
        int l = getLeft(0,N);
        int r = getRight(0,N);
        //check
        out.println(Arrays.toString(pos));
        for (int i=0;i<N+1;i++) {
            for (int j=i;j<N+1;j++) {
                out.println("i: "+i+", j: "+j);
                for (int k=0;k<2;k++) {
                    out.println(k+": "+dp[i][j][k]);
                }
                out.println();
            }
        }
        //turn in answer
//        out.println("l: "+l+", r: "+r);
        out.println(Math.min(l,r));
        out.close();
    }
    public static int getLeft(int l, int r){
        if (dp[l][r][0]!=-1) return dp[l][r][0];
        int weight = l + (N - r);
        dp[l][r][0] = Math.min(
                getLeft(l + 1, r) + weight * (pos[l + 1] - pos[l]),
                getRight(l + 1, r) + weight * (pos[r] - pos[l])
        );
        return dp[l][r][0];
    }
    public static int getRight(int l, int r){
        if (dp[l][r][1]!=-1) return dp[l][r][1];
        int weight = l + (N - r);
        dp[l][r][1] = Math.min(
                getLeft(l, r-1) + weight * (pos[r] - pos[r-1]),
                getRight(l, r-1) + weight * (pos[r] - pos[r-1])
        );
        return dp[l][r][1];
    }
}
