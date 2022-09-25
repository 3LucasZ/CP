package USACO.Silver.Open2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
/*
USACO 2021 US Open, Silver
Problem 3. Acowdemia
 */
public class Acowdemia {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int K;
    static int L;
    static Integer[] papers;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        papers = new Integer[N];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<N;i++) {
            papers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(papers, Comparator.reverseOrder());
        //logic
        int lo = 0, hi = N;
        while (hi > lo) {
            int mid = lo + (hi - lo + 1) / 2;
            if (tryH(mid)) {
                lo = mid;
            }
            else {
                hi = mid - 1;
            }
        }
        out.println(hi);
        out.close();
        f.close();
    }
    public static boolean tryH(int h) {
        long left = K * L;
        for (int i=0;i < h;i++) {
            if (papers[i] >= h) continue;
            if (papers[i] + K < h) return false;
            if (left < (long)(h - papers[i])) return false;
            left -= (long)(h - papers[i]);
        }
        return true;
    }
}
