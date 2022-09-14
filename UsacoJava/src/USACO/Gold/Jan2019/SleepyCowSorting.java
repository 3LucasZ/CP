package USACO.Gold.Jan2019;

import java.io.*;
import java.util.*;

public class SleepyCowSorting {
    //io
    static boolean submission = false;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(st.nextToken());

        //logic
        BIT sortedSuffix = new BIT(N);
        int i;
        for (i=N;i>=2;i--) {
            sortedSuffix.update(arr[i],1);
            if (arr[i] < arr[i-1]) break;
        }

        out.println(i-1);
        for (int j=1;j<=i-1;j++){
            int cost = N-sortedSuffix.rangeSum(arr[j],N);
            out.print(cost-1+" ");
            sortedSuffix.update(arr[j],1);
        }

        out.close();
    }
    private static class BIT {
        int K;
        int[] bit;

        public BIT(int K) {
            this.K = K;
            bit = new int[K + 1];
        }

        public void update(int i, int val) {
            while (i <= K) {
                bit[i] += val;
                i += i & (-i);
                //System.out.println("STUCK IN UPDATE");
            }
        }

        public int preSum(int i) {
            int sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= i & (-i);
                //System.out.println("STUCK IN PRESUM");
            }
            return sum;
        }

        public int rangeSum(int lo, int hi) {
            return preSum(hi) - preSum(lo - 1);
        }
    }
}
