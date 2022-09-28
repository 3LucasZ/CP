import java.io.*;
import java.util.*;
/*
PROB: ChristmasParty
LANG: JAVA
*/
public class ChristmasParty {
    static boolean submission = false;
    static boolean debug = true;

    static long MOD = (long)(1e9+7);
    public static void main(String[] args) throws IOException {
        //parse
        setup("ChristmasParty");
        int N = Integer.parseInt(br.readLine());
        //base case
        long[] dp = new long[N+1]; dp[0]=1; dp[1]=0;
        //transitions
        for (int i=2;i<=N;i++){
            dp[i]=((i-1)*(dp[i-1]+dp[i-2]))%MOD;
        }
        //ret
        out.println(dp[N]);
        //if (debug) System.out.println(Arrays.toString(dp));
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