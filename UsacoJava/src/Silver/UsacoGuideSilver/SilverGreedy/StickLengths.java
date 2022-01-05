package Silver.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Stick Lengths
Greedy Algorithms with sorting - EASY
 */
public class StickLengths {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] length = new int[N];
        for (int i=0;i<N;i++) {
            length[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(length);
        int median = length[N/2];
        long cost = 0;
        for (int i=0;i<N;i++) {
            cost += Math.abs(length[i]-median);
        }
        out.println(cost);
        out.close();
    }
}
