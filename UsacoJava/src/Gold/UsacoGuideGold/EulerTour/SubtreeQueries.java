package Gold.UsacoGuideGold.EulerTour;

import java.io.*;
import java.util.*;

public class SubtreeQueries {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static int Q;
    static ArrayList<Integer>[] tree;
    static int[] init;

    static BIT bit;
    static int[] start;
    static int[] end;

    static int timer = 1;

    public static void main(String[] args) throws IOException {
        //parse data
        StringTokenizer str = new StringTokenizer(br.readLine());
        N = Integer.parseInt(str.nextToken());
        Q = Integer.parseInt(str.nextToken());

        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();

        str = new StringTokenizer(br.readLine());
        init = new int[N+1];
        for (int i=1;i<=N;i++)init[i] = Integer.parseInt(str.nextToken());

        for (int i=0;i<N-1;i++){
            str = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(str.nextToken());
            int v = Integer.parseInt(str.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        //order to flatten the tree
        start = new int[N+1];
        end = new int[N+1];
        order(1,0);
//        if (debug){
//            System.out.println(Arrays.toString(start));
//            System.out.println(Arrays.toString(end));
//            System.out.println();
//        }

        bit = new BIT(N);
        for (int i=1;i<=N;i++){
            bit.update(start[i],init[i]);
        }

        for (int i=0;i<Q;i++){
            str = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(str.nextToken());
            int node = Integer.parseInt(str.nextToken());
            if (op==2){
                out.println(bit.rangeSum(start[node],end[node]));
            }
            else {
                bit.update(start[node],Integer.parseInt(str.nextToken()));
            }
        }
        out.close();
    }

    static void order(int node, int parent){
        start[node]=timer;
        timer++;
        for (int child : tree[node]){
            if (child==parent) continue;
            order(child, node);
        }
        end[node]=timer-1;
    }

    private static class BIT {
        int K;
        long[] bit;
        int[] value;
        public BIT(int K){
            this.K=K;
            bit = new long[K+1];
            value = new int[K+1];
        }
        public void update(int i, int val){
            long diff = val-value[i];
            value[i]=val;
            while (i <= K){
                bit[i]+=diff;
                i += i&(-i);
                //System.out.println("STUCK IN UPDATE");
            }
        }
        public long preSum(int i){
            long sum=0;
            while (i > 0){
                sum += bit[i];
                i -= i&(-i);
                //System.out.println("STUCK IN PRESUM");
            }
            return sum;
        }
        public long rangeSum(int lo, int hi){
            return preSum(hi)-preSum(lo-1);
        }
    }
}
