package Silver.Usaco2021_2022_Silver.Silver2021_1;
import java.io.*;
import java.util.*;
/*
USACO 2021 December Contest, Silver
Problem 3. Convoluted Intervals
RETRY!
 */
public class ConvolutedIntervals3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int M;
    static int[] start;
    static int[] end;
    static int[] preSum;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = new int[M];
        end = new int[M];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            start[u]++;
            end[v]++;
        }
    }
}
