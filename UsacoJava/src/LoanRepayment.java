import java.io.*;
import java.util.StringTokenizer;

public class LoanRepayment {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static long N;
    static long K;
    static long M;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("loan.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
        } else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        //binary search
        long lo = 1;
        long hi = N/M;
        while (lo < hi) {
            long mid = (lo + hi)/2;
            if (tryX(mid)) {
                lo = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        out.println(lo-1);
        out.close();
    }
    public static boolean tryX(long x) {
        long n = N;
        long k = K;
        long m = M;
        long g = 0;
        while (k > 0 && g < n) {
            long y = (n - g) / x;
            if (y < m) {
                long leftover = (n-g + m-1) / m;
                return leftover <= k;
            }
            long maxmatch = n - x*y;
            long numdays = (maxmatch - g) / y + 1;
            if (numdays > k) numdays = k;
            g += y * numdays;
            k-= numdays;
        }
        return g >= n;
    }
}
