package USACO.Platinum.EC.WARM1;

import java.io.*;
import java.util.*;

public class PromotionCounting {
    static boolean submission = false;
    static boolean debug = false;

    //cow
    static int N;
    static int[] p;

    //tree: euler tour
    static ArrayList<Integer>[] tree;
    static int timer = 0;
    static int[] start;
    static int[] end;
    static int[] pos;

    //greedy processing
    static BIT bit;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        //parse
        setup("promote");
        N = Integer.parseInt(br.readLine());
        p = new int[N+1];
        for (int i=1;i<=N;i++) p[i]=Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=2;i<=N;i++){
            int u = i;
            int v = Integer.parseInt(br.readLine());
            tree[u].add(v);
            tree[v].add(u);
        }

        //euler tour
        start = new int[N+1];
        end = new int[N+1];
        pos = new int[N+1];
        DFS(1,0);
        if (debug) {
            System.out.println("Start: "+Arrays.toString(start));
            System.out.println("End: "+Arrays.toString(end));
        }

        //greedy processing
        Integer[] process = new Integer[N+1]; for (int i=0;i<=N;i++) process[i]=i;
        Arrays.sort(process, Comparator.comparingInt(a->-p[a]));
        if (debug) System.out.println("Process order: "+Arrays.toString(process));
        bit = new BIT(N);
        ans = new int[N+1];
        for (int i=0;i<N;i++){
            ans[process[i]]=bit.rangeSum(start[process[i]],end[process[i]]);
            bit.set(start[process[i]],1);
        }

        //ret
        for (int i=1;i<=N;i++) out.println(ans[i]);
        out.close();
    }
    public static void DFS(int node, int par){
        timer++;
        pos[timer]=node;
        start[node]=timer;
        for (int child : tree[node]){
            if (child!=par) DFS(child, node);
        }
        end[node]=timer;
    }



















    private static class BIT {
        //must be 1 indexed
        int K;
        int[] A;
        int[] bit;

        public BIT(int K){
            this.K=K;
            bit = new int[K+1];
            A = new int[K+1];
        }

        public void set(int i, int val){
            update(i, val-A[i]);
        }

        public void update(int i, int val){
            A[i]+=val;
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
    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
