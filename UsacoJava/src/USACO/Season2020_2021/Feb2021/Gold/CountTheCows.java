package USACO.Season2020_2021.Feb2021.Gold;

import java.io.*;
import java.util.*;
/*
PROB: CountTheCows
LANG: JAVA
*/
public class CountTheCows {
    static boolean submission = false;
    static boolean debug = false;

    static int BITS = 40;
    public static void main(String[] args) throws IOException {
        //handle TCS
        setup("CountTheCows");
        int Q = Integer.parseInt(br.readLine());
        for (int i=0;i<Q;i++){
            solve(i);
        }
        out.close();
    }
    public static void solve(int o) throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        long dd = Long.parseLong(st.nextToken());
        long xx = Long.parseLong(st.nextToken());
        long yy = Long.parseLong(st.nextToken());
        //convert to base 3
        int[] x = base3(xx);
        int[] y = base3(yy);
        int[] d = base3(dd);
        if (debug){
            System.out.println("Case: "+o);
            System.out.println("x: "+Arrays.toString(x));
            System.out.println("y: "+Arrays.toString(y));
            System.out.println("d: "+Arrays.toString(d));
        }
        //dp[i][cmp][xo][yo] = number cows in (x..x+k,y..y+k) where:
        //k has i bits and we are querying the ith bit
        //cmp: k<d%3^i (0), k=d%3^i (1), k>d%3^i (2)
        //xo is 0 or 1 x overflow, yo is same but in y
        long[][][][] dp = new long[BITS+1][3][2][2];
        dp[0][1][0][0]=1;
        for (int i=0;i<BITS;i++){
            for (int cmp=0;cmp<=2;cmp++) {
                for (int xo=0;xo<=1;xo++) {
                    for (int yo=0;yo<=1;yo++) {
                        for (int add=0;add<=2;add++) {
                            //calc i2 stats
                            int i2=i+1;
                            int xo2 = (x[i2] + xo + add) / 3;
                            int yo2 = (y[i2] + yo + add) / 3;
                            int xi2 = (x[i2] + xo + add) % 3;
                            int yi2 = (y[i2] + yo + add) % 3;
                            int cmp2 = cmp;
                            if (add>d[i2]) cmp2=2;
                            if (add<d[i2]) cmp2=0;
                            //update i2
                            if (goodPair(xi2, yi2)) dp[i2][cmp2][xo2][yo2] += dp[i][cmp][xo][yo];
                        }
                    }
                }
            }
        }
        //answer is stored here
        out.println(dp[BITS][1][0][0]+dp[BITS][0][0][0]);
    }
    public static boolean goodPair(int bit1, int bit2){
        return (bit1%3)%2==(bit2%3)%2;
    }
    public static int[] base3(long num){
        int[] ret = new int[BITS+1];
        for (int i=1;i<=BITS;i++){
            ret[i]= (int) (num%3);
            num/=3;
        }
        return ret;
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