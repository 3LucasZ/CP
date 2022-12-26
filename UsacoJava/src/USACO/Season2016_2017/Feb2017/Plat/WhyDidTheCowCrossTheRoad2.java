package USACO.Season2016_2017.Feb2017.Plat;
/*
USACO 2017 February Contest, Platinum
Problem 2. Why Did the Cow Cross the Road II
Thoughts:
WAS TRYING TO SOLVE GOLD VERSION, ACCIDENTALLY SUBMITTED TO PLATINUM VERSION AND IT WORKED!!!
So... yeah solves for both gold and platinum version
O(NsqrtN) time (range max queries + DP)
So nice!!
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WhyDidTheCowCrossTheRoad2 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int[] arr;
    static int[] pos;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("nocross.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        pos = new int[N+1];
        for (int i=1;i<=N;i++) arr[i]=Integer.parseInt(br.readLine());
        for (int i=1;i<=N;i++) pos[Integer.parseInt(br.readLine())]=i;
        if (!submission){
            System.out.println(Arrays.toString(arr));
            System.out.println(Arrays.toString(pos));
        }
        //logic
        RangeQuery rq = new RangeQuery(N+1);
        for (int i=1;i<=N;i++) {
            ArrayList<Integer> posSort = new ArrayList<>();
            for (int j = arr[i] + 4; j >= arr[i] - 4; j--) {
                if (j < 1 || j > N) continue;
                posSort.add(pos[j]);
            }
            Collections.sort(posSort);
            Collections.reverse(posSort);
            for (int p : posSort){
                rq.update(p, rq.get(0, p - 1) + 1);
            }
        }
        //turn in answer
        out.println(rq.get(0,N));
        out.close();
    }

    private static class RangeQuery {
        int A;
        int B;
        int[] arr;
        int[] decomp;

        public RangeQuery(int A){
            this.A=A;
            arr = new int[A];
            B = (int)Math.sqrt(A);
            decomp = new int[A];
        }

        public void update(int i, int val){
            if (val <= arr[i]) return;
            arr[i]=val;
            decomp[i/B]=Math.max(decomp[i/B], val);
        }

        public int get(int i, int j){
            int ii=(i+B)/B;
            int jj=j/B;
            int ret = Integer.MIN_VALUE;
            for (int k=i;k<Math.min(B*ii,j);k++) ret=Math.max(ret,arr[k]);
            for (int k=ii;k<jj;k++) ret=Math.max(ret,decomp[k]);
            for (int k=Math.max(B*jj,i);k<=j;k++) ret=Math.max(ret,arr[k]);
            return ret;
        }

        public RangeQuery clone(){
            RangeQuery ret = new RangeQuery(A);
            ret.B=B;ret.arr=arr.clone();ret.decomp=decomp.clone();
            return ret;
        }
    }
}
