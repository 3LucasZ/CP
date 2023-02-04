package Other.USACOGuide.UsacoGuideSilver.BinarySearch;

import java.io.*;
import java.util.StringTokenizer;
/*
USACO 2020 January Contest, Silver
Problem 2. Loan Repayment
USACO Guide Silver - Binary Search - VERY HARD
Concepts: Optimization on a slow growing function: Ex: 1 1 1 1 2 2 2 2 2 2 3 3 3 3 3 4 4 4 5 5 5 conquer all the ones and then twos instead of individually
Copy cat sad for the sqrt(N) solution :(
UNSATISFIED!
 */

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
        long hi = N/M+1;
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
            //to give
            long y = (n - g) / x;
            //m limit case
            if (y < m) {
                long leftover = (n-g + m-1) / m; //we add m-1 so it rounds up
                return leftover <= k;
            }
            //
            long nextGiven = n-x*y;
            long numDays = (nextGiven-g)/y + 1;
            if (numDays > k) numDays = k;
            g += y * numDays;
            k -= numDays;
        }
        return g >= n;
    }
}
