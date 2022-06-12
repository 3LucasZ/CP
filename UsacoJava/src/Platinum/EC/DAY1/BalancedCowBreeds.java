package Platinum.EC.DAY1;

import java.io.*;

public class BalancedCowBreeds {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static String str;
    public static void main(String[] args) throws IOException {
        //parse
        setup("bbreeds");
        str=" "+br.readLine();
        N = str.length()-1;

        int unclosed = 0;
        //dp setup
        //dp[str index][open parentheses of G]
        int dp[][] = new int[N+1][N+1];
        dp[0][0]=1;
        //transitions
        for (int i=1;i<=N;i++){
            if (str.charAt(i)=='('){
                for (int p=0;p<=N;p++){
                    //add ( to G
                    if (p!=0) dp[i][p] = (dp[i][p] + dp[i-1][p-1])%2012;
                    //add ( to H
                    dp[i][p] = (dp[i][p] + dp[i-1][p])%2012;
                }
                unclosed++;
            }
            else {
                for (int p=0;p<=N;p++){
                    //add ) to G
                    if (p!=N) dp[i][p] = (dp[i][p] + dp[i-1][p+1])%2012;
                    //add ) to H
                    if (unclosed - p > 0) dp[i][p] = (dp[i][p] + dp[i-1][p])%2012;
                }
                unclosed--;
            }
        }

        //ans
        if (unclosed == 0) out.println(dp[N][0]);
        else out.println(0);
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
