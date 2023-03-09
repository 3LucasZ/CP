package Solutions.USACO.Season2016_2017.Feb2017.Plat;

import java.io.*;
import java.util.*;

/*
USACO 2017 February Contest, Platinum
Problem 1. Why Did the Cow Cross the Road
USACO Gold Guide - Point Update Range Sum - Easy
Thoughts:
obs:
a1 ---------- b1
     a2 -- b2
strat:
init intersections
bash 4N cycles

easy shift calculation
first plat solved!
 */
public class WhyDidTheCowCrossTheRoad1 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static Pair[] pairs;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("mincross.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("mincross.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        pairs = new Pair[N];
        for (int i=1;i<=N;i++){
            int l = Integer.parseInt(br.readLine());
            pairs[l-1] = new Pair(i, 0);
        }
        for (int i=1;i<=N;i++){
            int r = Integer.parseInt(br.readLine());
            pairs[r-1].r=i;
        }
        if (!submission) System.out.println(Arrays.toString(pairs));

        //logic
        long best = Long.MAX_VALUE;
        for (int i=0;i<2;i++){
            long intersects = 0;
            Arrays.sort(pairs, (a,b)->a.l-b.l);
            BIT endpoints = new BIT(N+1);
            for (int j=0;j<N;j++){
                intersects += endpoints.rangeSum(pairs[j].r, N);
                endpoints.update(pairs[j].r,1);
            }
            if (!submission) System.out.println(intersects);

            best = Math.min(best, intersects);
            for (int j=N-1;j>=0;j--){
                intersects -= endpoints.rangeSum(pairs[j].r, N);
                intersects += endpoints.rangeSum(1,pairs[j].r);
                best = Math.min(best, intersects);
            }

            for (int r=0;r<N;r++) pairs[r].swap();
        }


        //turn in answer
        out.println(best);
        out.close();
    }
    private static class Pair {
        int l;
        int r;
        public Pair(int l, int r){
            this.l=l;
            this.r=r;
        }
        public void swap(){
            int temp=l;
            l=r;
            r=temp;
        }
        public String toString(){
            return "["+l+", "+r+"]";
        }
    }
    private static class BIT {
        int K;
        int[] bit;
        public BIT(int K){
            this.K=K;
            bit = new int[K];
        }
        public void update(int i, int val){
            while (i <= N){
                bit[i]+=val;
                i += i&(-i);
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
