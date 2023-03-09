package Solutions.USACO.Season2019_2020.Dec2019.Plat;

import java.io.*;
import java.util.*;
/*
PROB: BessieSnowCow
LANG: JAVA
*/
public class BessieSnowCow {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int Q;
    static ArrayList<Integer>[] tree;

    static int timer = 0;
    static int[] l;
    static int[] r;
    static int[] subtree;

    //distinct colors trackers
    //node subtree
    static SegTreePURS segPURS;
    //path to node
    static SegTreeRUPS segRUPS;

    //stores <node in, node> for each color, sorted by node in
    static TreeMap<Integer, Integer>[] colorIn;

    public static void main(String[] args) throws IOException {
        //parse
        setup("snowcow");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        //euler tour technique
        l = new int[N+1];
        r = new int[N+1];
        subtree = new int[N+1];
        DFS(1,0);
        if (debug){
            System.out.println("L:"+Arrays.toString(l));
            System.out.println("R:"+Arrays.toString(r));
            System.out.println("Subtree:"+Arrays.toString(subtree));
        }

        //seg tree init
        segRUPS = new SegTreeRUPS(N);
        segPURS = new SegTreePURS(N);

        //position init
        int K = (int)1e5;
        colorIn = new TreeMap[K+1]; for (int i=1;i<=K;i++) colorIn[i]= new TreeMap<>(); //sorted by leftpoint

        //handle queries
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            //splash
            if (type==1){
                int color = Integer.parseInt(st.nextToken());
                //check if parent exists
                Map.Entry<Integer,Integer> below = colorIn[color].floorEntry(l[node]);
                if (below!=null && r[below.getValue()]>=r[node]) continue;

                //remove obsolete
                while (true){
                    Map.Entry<Integer,Integer> rem = colorIn[color].ceilingEntry(l[node]);
                    if (rem==null || l[rem.getValue()] > r[node]) break;
                    colorIn[color].remove(rem.getKey());
                    segPURS.add(l[rem.getValue()],-subtree[rem.getValue()]);
                    segRUPS.add(l[rem.getValue()],r[rem.getValue()],-1);
                }

                //add
                colorIn[color].put(l[node],node);
                segPURS.add(l[node],subtree[node]);
                segRUPS.add(l[node],r[node],1);
            }
            //query
            else {
                long sum = 0;
                sum+=segPURS.sum(l[node]+1,r[node]);
                sum+=segRUPS.get(l[node])*(subtree[node]);
                out.println(sum);
            }
        }
        out.close();
    }
    public static void DFS(int node, int par){
        timer++;
        l[node]=timer;
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node);
        }
        r[node]=timer;
        subtree[node]=r[node]-l[node]+1;
    }
    private static class SegTreePURS {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        public SegTreePURS(int n){
            init(n);
        }
        public SegTreePURS(int n, int[] arr){
            init(n);
            for (int i=1;i<=n;i++){
                tree[i+size-1]=arr[i];
            }
            for (int i=size-1;i>=1;i--){
                tree[i]=tree[i*2]+tree[i*2+1];
            }
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        void set (int k, int x){
            add(k,x-tree[k+size-1]);
        }
        void add(int k, long x){
            k+=size-1;
            tree[k]+=x;
            for (k/=2;k>=1;k/=2){
                tree[k]=tree[2*k]+tree[2*k+1];
            }
        }
        long sum(int a, int b) {
            a+=size-1;
            b+=size-1;
            long ret = 0;
            while (a<=b){
                if (a%2==1) ret+=tree[a++];
                if (b%2==0) ret+=tree[b--];
                a/=2;
                b/=2;
            }
            return ret;
        }
    }
    private static class SegTreeRUPS {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        public SegTreeRUPS(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        long get(int k){
            long ret = 0;
            for (k+=size-1;k>=1;k/=2){
                ret+=tree[k];
            }
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