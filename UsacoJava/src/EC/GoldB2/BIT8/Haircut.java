package EC.GoldB2.BIT8;

import java.io.*;
import java.util.*;
/*
Haircut
Gold Advanced B - BIT
Thoughts:
Really easy implementation, a little difficult logic.
Obs 1:
0 0 0 0 0 -> 0
1 1 1 1 0 -> 4
2 2 2 2 0 -> 4
3 2 3 3 0 -> 5
4 2 3 3 0 -> 7
if a hair is not cut (short already)  everything behind it that WAS cut is now longer,
so range query all the non dropped hairs to the left
everything to the right is either dealth with already or already monotonic
really enjoyable problem :)
 */
public class Haircut {
    //io
    static boolean submission = true;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static PriorityQueue<Hair> active = new PriorityQueue<>((a,b)->a.length-b.length);

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("haircut.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) active.add(new Hair(Integer.parseInt(st.nextToken()), i));

        BIT notDroppedHair = new BIT(N);
        for (int i=1;i<=N;i++) notDroppedHair.update(i,1);
        if (debug) System.out.println(notDroppedHair.rangeSum(1, N));

        //logic
        long sum = 0;
        for (int cut=0;cut<N;cut++){
            ArrayList<Hair> process = new ArrayList<>();
            while (!active.isEmpty() && active.peek().length==cut-1){
                process.add(active.poll());
            }
            for (Hair h : process){
                notDroppedHair.update(h.index,-1);
            }
            for (Hair h : process){
                sum += notDroppedHair.rangeSum(1, h.index);
            }
            out.println(sum);
        }

        out.close();
    }
    private static class Hair {
        int length;
        int index;
        public Hair(int l, int i){
            length=l;
            index=i;
        }
    }
    private static class BIT {
        int K;
        int[] bit;

        public BIT(int K){
            this.K=K;
            bit = new int[K+1];
        }

        public void update(int i, int val){
            while (i <= K){
                bit[i]+=val;
                i += i&(-i);
                //System.out.println("STUCK IN UPDATE");
            }
        }

        public int preSum(int i){
            int sum=0;
            while (i > 0){
                sum += bit[i];
                i -= i&(-i);
                //System.out.println("STUCK IN PRESUM");
            }
            return sum;
        }

        public int rangeSum(int lo, int hi){
            return preSum(hi)-preSum(lo-1);
        }
    }
}
