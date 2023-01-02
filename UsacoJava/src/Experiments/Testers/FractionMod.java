package Experiments.Testers;

public class FractionMod {
    static long MOD = 998244353;
    static NT nt;

    public static void main(String[] args) {
        nt = new NT(MOD);
        test(11,2);
    }
    public static long test(int a, int b){
        long res = a*nt.inv(b)%MOD;
        System.out.println(a+" / "+b+" = "+res);
        return res;
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
}
