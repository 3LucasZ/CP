package USACOGuide.UsacoGuideGold.PointUpdateRangeSum;

import java.io.*;
import java.util.*;
/*
PROB: DistinctValuesQueries
LANG: JAVA
*/
public class DistinctValuesQueries {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int Q;
    static int[] color;
    static ArrayList<Query>[] queries;
    static HashMap<Integer,Integer> colorMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        //parse
        setup("DistinctValuesQueries");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        color = new int[N+1];
        for (int i=1;i<=N;i++) color[i]=Integer.parseInt(st.nextToken());
        queries = new ArrayList[N+1]; for (int i=1;i<=N;i++) queries[i]=new ArrayList<Query>();

        //store query
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            queries[r].add(new Query(l,r,i));
        }

        //process query with climbing/construction offline handles
        int[] ans = new int[Q];
        BIT bit = new BIT(N);
        for (int r=1;r<=N;r++){
            //update colorMap, bit
            if (colorMap.containsKey(color[r])){
                bit.set(colorMap.get(color[r]),0);
            }
            bit.set(r,1);
            colorMap.put(color[r],r);

            //handle queries
            for (Query q : queries[r]){
                if (debug) System.out.println("BIT:"+Arrays.toString(bit.A));
                ans[q.id]=bit.rangeSum(q.l,q.r);
            }
        }

        //ret
        for (int i=0;i<Q;i++){
            out.println(ans[i]);
        }
        out.close();

    }
    private static class Query {
        int l;
        int r;
        int id;
        public Query(int l, int r, int id){
            this.l=l;
            this.r=r;
            this.id=id;
        }
    }
    private static class BIT {
        //must be 1 indexed
        //range sum is inclusive
        int K;
        int[] A;
        int[] bit;

        public BIT(int K) {
            this.K = K;
            bit = new int[K + 1];
            A = new int[K + 1];
        }

        public void set(int i, int val) {
            update(i, val - A[i]);
        }

        public void update(int i, int val) {
            A[i] += val;
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