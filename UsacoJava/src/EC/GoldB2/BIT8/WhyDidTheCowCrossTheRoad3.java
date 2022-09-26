package EC.GoldB2.BIT8;

import java.io.*;
import java.util.*;
/*
Why Did The Cow Cross The Road 3
Gold Advanced B - BIT
Thoughts:
circle "clock like" problem
use basic intuition and geometry to convert problem to a "interval" problem
GOLDEN OBSERVATION: if 2 intervals overlap, they directly correspond to a cross on the circle (bijection)
implementation:
easy 1 by 1 traverse (already coordinate compressed)
process interval by start and end instead of bulk
O(logN) updates and range queries
sum += leftover.rangeSum(i, brother[i]); per start add
nice, kinda simple problem :)
 */
public class WhyDidTheCowCrossTheRoad3 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    static boolean debug = false;

    //param
    static int N;
    static int[] color;
    static boolean[] first;
    static int[] taken;
    static int[] brother;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("circlecross.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        color = new int[2*N+1];
        first = new boolean[2*N+1];
        taken = new int[N+1];
        brother = new int[2*N+1];

        for (int i=1;i<=2*N;i++){
            int c = Integer.parseInt(br.readLine());
            color[i] = c;
            if (taken[c]==0){
                taken[c]=i;
                first[i]=true;
            }
            else {
                brother[i]=taken[c];
                brother[taken[c]]=i;
            }
        }

        if (debug){
            System.out.println(Arrays.toString(color));
            System.out.println(Arrays.toString(first));
            System.out.println(Arrays.toString(brother));
        }

        //logic
        BIT leftover = new BIT(2*N);
        long sum = 0;
        for (int i=1;i<=2*N;i++){
            if (!first[i]){
                leftover.update(i, -1);
                leftover.update(brother[i],-1);
            }
            else {
                sum += leftover.rangeSum(i, brother[i]);
                leftover.update(i,1);
                leftover.update(brother[i],1);
            }
            if (debug) {
                System.out.println(sum);
                System.out.println(Arrays.toString(leftover.store));
            }
        }

        //turn in answer
        out.println(sum);
        out.close();
    }

    private static class BIT {
        int K;
        int[] store;
        int[] bit;
        public BIT(int K){
            this.K=K;
            store = new int[K+1];
            bit = new int[K+1];
        }
        public void update(int i, int val){
            store[i]+=val;
            while (i <= K){
                bit[i]+=val;
                i+=i&(-i);
            }
        }
        public int preSum(int i){
            int sum=0;
            while (i > 0){
                sum += bit[i];
                i-=i&(-i);
            }
            return sum;
        }
        public int rangeSum(int i, int j){
            return preSum(j)-preSum(i-1);
        }
    }
}
