import java.io.*;
import java.util.*;
public class CowLand {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int Q;

    static int[] enjoy;

    static int timer = 0;
    static int[] start;
    static int[] end;

    static ArrayList<Integer>[] tree = new ArrayList[N+1];
    static SegTree seg;


    public static void main(String[] args) throws IOException {
        setup("cowland");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        enjoy = new int[N+1];
        start = new int[N+1];
        end = new int[N+1];
        seg = new SegTree(N);
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            enjoy[i] = Integer.parseInt(st.nextToken());
            seg.set(i-1, enjoy[i]);
        }
        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        DFS(1, 0);
        if (debug){
            System.out.println(Arrays.toString(tree));
            System.out.println(Arrays.toString(start));
            System.out.println(Arrays.toString(end));
        }
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op == 1) seg.set(a-1,b);
            else {
                int aI = start[a];
                int bI = start[b];
                out.println(seg.res(Math.min(aI,bI)-1,Math.max(aI,bI)));
            }
        }
        out.close();
    }
    static void DFS(int node, int par){
        timer++;
        start[node]=timer;
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child, node);
        }
        end[node]=timer;
    }
    private static class SegTree {
        int size;
        long[] xors;
        public SegTree(int n){
            size = 1;
            while (size < n) size *= 2;
            xors = new long[2*size];
        }
        void set(int i, int v){
            set(i, v, 0, 0, size);
        }
        void set(int i, int v, int x, int lx, int rx){
            if (rx-lx==1){
                xors[x]=v;
                return;
            }
            int m = (lx+rx)/2;
            if (i<m) set(i,v,2*x+1,lx,m);
            else set(i,v,2*x+2,m,rx);
            xors[x]=xors[2*x+1]^xors[2*x+2];
        }
        long res(int l, int r) {
            return res(l,r,0,0,size);
        }
        long res (int l, int r, int x, int lx, int rx){
            if (lx >= r || l >= rx) return 0;
            if (lx >= l && rx <= r) return xors[x];
            int m = (lx+rx)/2;
            long s1 = res(l,r,2*x+1,lx,m);
            long s2 = res(l,r,2*x+2,m,rx);
            return s1^s2;
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
