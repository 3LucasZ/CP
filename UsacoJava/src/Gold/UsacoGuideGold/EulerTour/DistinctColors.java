package Gold.UsacoGuideGold.EulerTour;

import java.io.*;
import java.util.*;
/*
CSES Problem Set - Distinct Colors (Tree Algorithms)
USACO Gold Guide - Euler Tour - Easy
Thoughts:
introductory Euler Tour Technique... still got wrong and had to look at answer smh :(
hard part was: how many distinct colors in range [i...j] of an array? (Offline)
put in lookup so that O(NlogN) preprocess and O(1) retrieval
Java TLE on CSES as per usual but it was a fun problem! new technique: find num of distinct elements in a range.
 */
public class DistinctColors {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int[] color;
    static ArrayList<Integer>[] tree;

    static int timer = 0;
    static int[] start;
    static int[] end;
    static int[] order;

    public static void main(String[] args) throws IOException {
        setup("");
        N = Integer.parseInt(br.readLine());
        color = new int[N+1];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) color[i] = Integer.parseInt(stk.nextToken());
        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        start = new int[N+1];
        end = new int[N+1];
        order = new int[N+1];
        DFS(1,0);

        HashMap<Integer, Integer> last = new HashMap<>();
        BIT bit = new BIT(N);

        int[] answer = new int[N+1];
        for (int i=N;i>=1;i--){
            int node = order[i];
            if (last.containsKey(color[node])){
                bit.update(last.get(color[node]),-1);
            }
            last.put(color[node],i);
            bit.update(i,1);
            answer[node]=bit.rangeSum(start[node],end[node]);
        }

        for (int i=1;i<=N;i++) out.println(answer[i]);
        out.close();
    }
    public static void DFS(int node, int par){
        timer++;
        start[node]=timer;
        order[timer]=node;
        for (int child : tree[node]){
            if (child != par) DFS(child, node);
        }
        end[node]=timer;
    }
    private static class BIT {
        //must be 1 indexed
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
