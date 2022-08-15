import java.io.*;
import java.util.*;
/*
PROB: PathQueries
LANG: JAVA
*/
public class PathQueries {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int Q;
    static ArrayList<Integer>[] tree;

    static int timer = 0;
    static int[] l;
    static int[] r;

    static int[] val;

    public static void main(String[] args) throws IOException {
        //parse
        setup("PathQueries");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        val = new int[N+1]; for (int i=1;i<=N;i++) val[i]=Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        //tree flatten
        l = new int[N+1];
        r = new int[N+1];
        DFS(1,0);
        if (debug){
            System.out.println("L: "+Arrays.toString(l));
            System.out.println("R: "+Arrays.toString(r));
        }

        //SegTree init
        SegTree seg = new SegTree(N);
        for (int node=1;node<=N;node++){
            seg.add(l[node],r[node],val[node]);
        }

        //Query handle
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            if (type==1){
                int newVal = Integer.parseInt(st.nextToken());
                seg.add(l[node],r[node],newVal-val[node]);
                val[node]=newVal;
            } else {
                out.println(seg.get(l[node]));
            }
        }
        out.close();
    }
    public static void DFS(int node, int par){
        //subtree in
        timer++;
        l[node]=timer;

        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node);
        }

        //subtree out
        r[node]=timer;
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        public SegTree(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        long get(int k){
            long ret = 0;
            for (k+=size-1;k>=1;k/=2) ret+=tree[k];
            return ret;
        }
        void add(int a, int b, int x) {
            a+=size-1;
            b+=size-1;
            while (a<=b){
                if (a%2==1) tree[a++]+=x;
                if (b%2==0) tree[b--]+=x;
                a/=2;
                b/=2;
            }
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