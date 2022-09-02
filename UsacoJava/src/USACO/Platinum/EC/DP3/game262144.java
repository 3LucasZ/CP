package USACO.Platinum.EC.DP3;

import java.io.*;

public class game262144 {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int[] A;
    static int vals = 60;

    public static void main(String[] args) throws IOException {
        //parse
        setup("262144");
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        for (int i=1;i<=N;i++){
            A[i]=Integer.parseInt(br.readLine());
        }

        //dp
        int dp[][] = new int[N+2][vals+1];
        for (int i=N;i>=1;i--){
            dp[i][A[i]]=i;
            for (int val=A[i]+1;val<=vals;val++){
                dp[i][val]=dp[dp[i][val-1]+1][val-1];
            }
        }

        //ret
        for (int val=vals;val>=1;val--){
            for (int i=1;i<=N;i++){
                if (dp[i][val]!=0){
                    out.println(val);
                    out.close();
                    break;
                }
            }
        }
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
