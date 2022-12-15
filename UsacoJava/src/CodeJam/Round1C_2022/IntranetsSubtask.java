package CodeJam.Round1C_2022;

import java.io.*;
import java.util.*;

public class IntranetsSubtask {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int M;
    static int K;
    static long MOD = (long) (1e9+7);
    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println();
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int E = choose2(M);
        NT nt = new NT(E,MOD);
        //* dp[i][sz][c] = ways where i priorities, sz elements, c intranets
        long[][][] dp = new long[E+1][M+1][K+1];
        dp[0][0][0]=1;
        for (int i=0;i<E;i++){
            for (int sz=0;sz<=M;sz++){
                for (int c=0;c<=K;c++){
                    if (sz>=2)dp[i+1][sz][c]=(dp[i+1][sz][c]+dp[i][sz][c]*(choose2(sz)-i))%MOD;
                    if (sz+1<=M)dp[i+1][sz+1][c]=(dp[i+1][sz+1][c]+dp[i][sz][c]*sz*(M-sz))%MOD;
                    if (M-sz>=2 && sz+2<=M && c+1<=K)dp[i+1][sz+2][c+1]=(dp[i+1][sz+2][c+1]+dp[i][sz][c]*choose2(M-sz))%MOD;
                }
            }
        }
        //* ret
        long cnt = dp[E][M][K];
        long tot = nt.f[E];
        long gcd = gcd(cnt,tot);
        if (debug){
            System.out.println(cnt+"/"+tot);
        }
        cnt/=gcd;
        tot/=gcd;
        if (debug){
            System.out.println(cnt+"/"+tot);
        }
        System.out.println(cnt*nt.inv(tot)%MOD);
    }

    public static int choose2(int x){
        return x*(x-1)/2;
    }
    public static long gcd(long a, long b){
        if (b==0) return a;
        return gcd(b,a%b);
    }
    private static class NT {
        // Note: only works if MOD is prime
        //* pow, inv
        long MOD;
        public long inv(long x) {
            return pow(x,MOD-2);
        }
        public long pow(long x, long p) {
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
            //gen factorials (1...F)!
            this.MAXF=MAXF;
            this.MOD=MOD;
            f = new long[MAXF + 1];
            f[0] = 1;
            for (int i = 1; i <= MAXF; i++) f[i] = (f[i - 1] * i) % MOD;
            //gen inverses (1...F)!^-1
            i = new long[MAXF + 1];
            for (int A = 1; A <= MAXF - 1; A++) {
                i[A] = pow(f[A], MAXF - 2);
            }
        }
        public long choose(int n, int k) {
            if (k == n || k == 0) return 1;
            return ((f[n] * i[k] % MOD) * i[n - k]) % MOD;
        }
    }

    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}