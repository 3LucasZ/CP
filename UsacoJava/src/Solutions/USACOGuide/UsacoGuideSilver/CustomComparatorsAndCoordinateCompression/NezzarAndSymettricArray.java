package Solutions.USACOGuide.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;/*
Codeforces Round #698 (Div. 2) C.
Nezzar and Symmetric Array
USACO Silver Guide - Custom Comparators and Coord Compression - Normal
Thoughts: 3 hours to complete :)
Concepts: Sum from all other points, edge cases, silly mistakes like saying ==0 instead of <= 0;
 */
import java.io.*;
import java.util.*;
public class NezzarAndSymettricArray {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int T;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            out.println(solve() ? "YES":"NO");
        }
        out.close();
    }
    public static boolean solve() throws IOException {
        //parse + sort
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] di = new long[2*N];
        for (int i=0;i<2*N;i++) {
            long d = Long.parseLong(st.nextToken());
            di[i] = d;
        }
        Arrays.sort(di);
        for (int i=0;i<=N-1;i++) {
            //swap
            long temp = di[i];
            di[i]=di[2*N-1-i];
            di[2*N-1-i]=temp;
        }
        //out.println(Arrays.toString(di));

        //run thru di
        long delta = 2*N;
        if (di[0] % delta != 0) {
            return false;
        }
        if (di[0]!=di[1]) {
            return false;
        }
        long gi_sum = 0;
        for (int i=2;i<2*N;i+=2) {
            delta -= 2;
            //not symmetric
            if (di[i]!=di[i+1]) return false;
            if (di[i]==di[i-2]) return false;
            if ((di[i]-di[i-2])%delta != 0) return false;
            gi_sum += i*(Math.abs(di[i]-di[i-2])/delta);
        }
        //out.println(gi_sum);
        //gi does not add up correctly at the end
        if (((di[2*N-2] - gi_sum) % 2*N != 0) || di[2*N-2]-gi_sum<=0) return false;
        return true;
    }
}
