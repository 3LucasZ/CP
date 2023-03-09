package Solutions.EC.PlatA1.WARM1;

import java.io.*;
import java.util.*;

public class PromotionCountingPROTOTYPE {
    static boolean submission = false;
    static boolean debug = true;

    //cow
    static int N;
    static int[] p;

    //tree traversal
    static ArrayList<Integer>[] tree;
    static BIT bit;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        //parse O(N)
        setup("promote");
        N = Integer.parseInt(br.readLine());
        int A[] = new int[N+1];
        for (int i=1;i<=N;i++)A[i]=Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=2;i<=N;i++){
            int u = Integer.parseInt(br.readLine());
            int v = i;
            tree[u].add(v);
            tree[v].add(u);
        }

        //coordinate compress proficiency ratings O(NlogN)
        Integer pos[] = new Integer[N+1];
        for (int i=0;i<=N;i++) pos[i]=i;
        Arrays.sort(pos,Comparator.comparing(a->A[a]));
        p = new int[N+1];
        for (int i=1;i<=N;i++){
            p[pos[i]]=i;
        }
        if (debug) System.out.println(Arrays.toString(p));

        //running BIT tree DFS O(NlogN)
        bit = new BIT(N);
        ans = new int[N+1];
        DFS(1,0);

        //ret
        for (int i=1;i<=N;i++) out.println(ans[i]);
        out.close();
    }
    public static void DFS(int node, int par){
        bit.set(p[node],1);
        ans[node]=bit.rangeSum(0,p[node]-1);
        for (int child : tree[node]){
            if (child!=par) DFS(child,node);
        }
        bit.set(p[node],0);
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
