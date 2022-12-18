package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: Arena
LANG: JAVA
*/
public class Arena {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int X;

    static long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        setup("Arena");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        //* precomp
        NT nt = new NT(N,MOD);
        long[][] pow = new long[1001][1001];
        for (int i=1;i<=1000;i++){
            pow[i][0]=1;
            for (int j=1;j<=1000;j++) pow[i][j]=pow[i][j-1]*i%MOD;
        }
        //* init: dp[heroes][unique maximum hero]
        long dp[][] = new long[N+1][X+1];
        //* base case: 1 hero left (the unique max)
        for (int x=1;x<=X;x++){
            dp[1][x]=N;
        }
        //* transitions from hero 1..N
        for (int h=1;h<=N;h++){
            for (int mx=1;mx<=X;mx++){
                //transition within the hero when k=0 and others when k!=0
                for (int k=0;k<=N;k++) {
                    if (h+k<=N && mx+h+k-1<=X) dp[h+k][mx+h+k-1] = (dp[h+k][mx+h+k-1]+dp[h][mx]*(pow[h+k-1][k]*nt.choose(N-h,k)%MOD))%MOD;
                }
            }
        }
//        if (debug){
//            print(dp);
//        }
        //* collect
        long complement = 0;
        for (int mx=1;mx<=X;mx++){
            complement = (complement+dp[N][mx])%MOD;
        }
        //* ret
        long orig = nt.pow(X,N);
        if (debug){
            System.out.println("orig: "+orig);
            System.out.println("complement: "+complement);
        }
        out.println((orig-complement+MOD)%MOD);
        out.close();
    }
    public static void print(long[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
    private static class NT {
        // Note: only works if MOD is prime
        //* pow, inv
        long MOD;
        public long inv(long x) {
            return pow(x,MOD-2);
        }
        public long pow(long x, long p) {
            if (x==0) return 0;
            if (p == 0) return 1;
            if (p % 2 == 1) return (x * pow(x, p - 1)) % MOD;
            else return pow((x * x) % MOD, p / 2);
        }
        public NT(long MOD) {
            this.MOD=MOD;
        }
        //* choose
        //factorials
        long[] f;
        //factorial inverses
        long[] i;
        int MAXF;
        public NT(int MAXF, long MOD) {
            //gen factorials (1...N)!
            this.MAXF=MAXF;
            this.MOD=MOD;
            f = new long[MAXF + 1];
            f[0] = 1;
            for (int i = 1; i <= MAXF; i++) f[i] = (f[i - 1] * i) % MOD;
            //gen inverses (1...N)!^-1
            i = new long[MAXF + 1];
            for (int A = 1; A <= MAXF - 1; A++) {
                i[A] = inv(f[A]);
            }
        }
        public long choose(int n, int k) {
            if (k == n || k == 0) return 1;
            return ((f[n] * i[k] % MOD) * i[n - k]) % MOD;
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