package Gold.EC.MIX9;

import java.io.*;
import java.util.*;
/*
Out Of Sorts
Gold Advanced B - MIX
Thoughts:
for every array split, all elements in the right that should be to the left
will go to the left in T turns
using bit and rel freqs we get the max T and turn it in
ans > 0
 */
public class OutOfSorts {
    //io
    static boolean submission = true;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static Integer[] arr;
    static Integer[] order;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("sort.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());

        arr = new Integer[N+1]; arr[0]=0; for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(br.readLine());

        order = new Integer[N+1]; for (int i=0;i<=N;i++) order[i]=i;
        Arrays.sort(order, (a,b)->{
            if (arr[a]==arr[b]) return a-b;
            return arr[a]-arr[b];
        });

        if (debug) {
            System.out.println(Arrays.toString(arr));
            System.out.println(Arrays.toString(order));
        }

        //logic
        BIT left = new BIT(N);
        int max=0;
        for (int i=1;i<=N;i++){
            left.update(order[i],1);
            max = Math.max(max, i-left.preSum(i));
            if (debug) System.out.println(i-left.preSum(i));
        }

        //turn in answer
        out.println(Math.max(1,max));
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
