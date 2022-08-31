package Gold.Training;

import java.io.*;
import java.util.*;
/*
PROB: TwoSetsII
LANG: JAVA
*/
public class TwoSetsII {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static long MOD = 2*(long)(1e9+7);

    public static void main(String[] args) throws IOException {
        setup("TwoSetsII");
        N = Integer.parseInt(br.readLine());
        int sum = (N+1)*N/2;
        if (sum%2==1){
            out.println(0);
        } else {
            sum/=2;
            long[] dp = new long[sum+1];
            dp[0]=1;
            for (int i=1;i<=N;i++){
                for (int j=sum-i;j>=0;j--){
                    dp[j+i]=(dp[j+i]+dp[j])%MOD;
                }
            }
            out.println(dp[sum]/2);
        }
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