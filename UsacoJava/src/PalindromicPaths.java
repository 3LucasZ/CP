import java.io.*;
import java.util.*;
/*
PROB: PalindromicPaths
LANG: JAVA
*/
public class PalindromicPaths {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static char[][] grid;

    static long MOD = (long)(1e9+7);
    public static void main(String[] args) throws IOException {
        //parse
        setup("palpath");
        N = Integer.parseInt(br.readLine());
        grid = new char[N+1][N+1];
        for (int r=1;r<=N;r++){
            String str = br.readLine();
            for (int c=1;c<=N;c++){
                grid[r][c]=str.charAt(c-1);
            }
        }

        //dp[extension][r1][r2]
        long[][] dp = new long[N+1][N+1];
        //base case
        for (int i=1;i<=N;i++) dp[i][i]=1;
        //transition
        for (int a=1;a<=N-1;a++){
            long[][] next = new long[N+1][N+1];
            for (int r1=1;r1<=N;r1++){
                int c1 = N-r1-a+1; if (c1<1) continue;
                for (int r2=r1;r2<=N;r2++){
                    int c2 = N-r2+a+1; if (c2>N) continue;
                    if (grid[r1][c1]!=grid[r2][c2]) continue;
                    if (r1<N) next[r1][r2]+=dp[r1+1][r2];
                    if (r2>1) next[r1][r2]+=dp[r1][r2-1];
                    if (r1<N && r2>1)next[r1][r2]+=dp[r1+1][r2-1];
                    next[r1][r2]+=dp[r1][r2];
                    next[r1][r2]%=MOD;
                }
            }
            dp=next;
        }

        //ret
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