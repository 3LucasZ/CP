package USACO.Season2018_2019.Feb2019.Plat;

import java.io.*;

/*
PROB: CowDating
LANG: JAVA
*/
public class CowDating1 {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static double[] p;

    public static void main(String[] args) throws IOException {
        //parse
        setup("cowdate");
        N = Integer.parseInt(br.readLine());
        p = new double[N];
        for (int i=0;i<N;i++){
            p[i]=Integer.parseInt(br.readLine())/1000000.0;
        }
        //dp
        double ans = 0;
        double[][] dp = new double[N][N];
        for (int l=0;l<N;l++){
            double pi = 1;
            dp[l][l]=p[l];ans=Math.max(ans,dp[l][l]);
            for (int r=l;r<N-1;r++){
                pi*=(1-p[r]);
                dp[l][r+1]=(1-p[r+1])*dp[l][r]+p[r+1]*pi;
                ans=Math.max(ans,dp[l][r+1]);
            }
        }
        //ret
        out.println((int)(ans*1000000));
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