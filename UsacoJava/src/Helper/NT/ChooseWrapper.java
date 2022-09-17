package Helper.NT;

import java.util.Arrays;

public class ChooseWrapper {
    private static class Choose {
        //factorials
        static long[] f;
        //inverses
        static long[] i;

        static long M = (long) (1e9 + 7);

        public static void init(int N) {
            //gen factorials (1...N)!
            f = new long[N + 1];
            f[0] = 1;
            for (int i = 1; i <= N; i++) f[i] = (f[i - 1] * i) % M;

            //gen inverses (1...N)!^-1
            i = new long[N + 1];
            for (int A = 1; A <= N - 1; A++) {
                i[A] = pow(f[A], M - 2);
            }
        }

        public static long get(int n, int k) {
            if (k == n || k == 0) return 1;
            return ((f[n] * i[k] % M) * i[n - k]) % M;
        }

        public static long pow(long x, long p) {
            if (p == 0) return 1;
            if (p % 2 == 1) return (x * pow(x, p - 1)) % M;
            else return pow((x * x) % M, p / 2);
        }
    }
}
