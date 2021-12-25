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
    static int[] ans;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = new int[M+1];
        end = new int[M+1];
        preSum = new int[2*M+1];
        ans = new int[2*M+1];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            start[u]++;
            end[v]++;
        }
        for (int i=0;i<=M;i++) {
            for (int j=0;j<=M;j++) {
                preSum[i+j]+=start[i]*start[j];
                preSum[i+j-1]-=end[i]*end[j];
            }
        }
        ans[0] = preSum[0];
        for (int i=1;i<=2*M;i++) {
            ans[i]=ans[i-1]+preSum[i];
        }
        for (int i=0;i<=2*M;i++)
    }
}
