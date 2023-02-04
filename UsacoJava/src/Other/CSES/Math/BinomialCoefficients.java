package Other.CSES.Math;

import java.io.*;
import java.util.*;
/*
PROB: BinomialCoefficients
LANG: JAVA
*/
public class BinomialCoefficients {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("BinomialCoefficients");
        int N = Integer.parseInt(br.readLine());
        Choose.init((int)(1e6));
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            out.println(Choose.get(n,k));
        }
        out.close();
    }

    private static class Choose {
        //factorials
        static long[] f;

        static long M = (long) (1e9 + 7);

        public static void init(int N) {
            //gen factorials (1...N)!
            f = new long[N + 1];
            f[0] = 1;
            for (int i = 1; i <= N; i++) f[i] = (f[i - 1] * i) % M;
        }
        public static long get(int n, int k) {
            if (k == n || k == 0) return 1;
            return ((f[n] * getInverse(k) % M) * getInverse(n - k)) % M;
        }
        public static long getInverse(int i){
            return pow(f[i], M - 2);
        }
        public static long pow(long x, long p) {
            if (p == 0) return 1;
            if (p % 2 == 1) return (x * pow(x, p - 1)) % M;
            else return pow((x * x) % M, p / 2);
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