package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: Antennas
LANG: JAVA
*/
public class Antennas {
    static boolean submission = false;
    static boolean debug = false;
    public static void main(String[] args) throws IOException {
        //handle tcs
        setup("Antennas");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    static int N, A, B;
    static int[] P;
    static int[] l;
    static int[] r;
    static int INF = Integer.MAX_VALUE;
    public static void solve() throws IOException{
        //*parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        P = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            P[i] = Integer.parseInt(st.nextToken());
        }
        l = new int[N+1]; r = new int[N+1];
        for (int i=1;i<=N;i++){
            l[i]=Math.max(i-P[i],1);
            r[i]=Math.min(i+P[i],N);
        }
        if (debug){
            System.out.println("P: "+Arrays.toString(P));
            System.out.println("l: "+Arrays.toString(l));
            System.out.println("r: "+Arrays.toString(r));
        }
        //*Min, Max seg trees
        SegTree ltree = new SegTree(N,l); //min queries
        SegTree rtree = new SegTree(N,r); //max queries
        //*Sparse BFS
        //setup
        int[] dist = new int[N+1];
        Deque<Integer> BFS = new LinkedList<>();
        BFS.add(A);
        ltree.set(A,INF);
        rtree.set(A,-INF);
        //transitions
        while (!BFS.isEmpty()){
            int next = BFS.peekFirst();
            if (debug) System.out.println("NEXT: "+next);
            //lmin found node
            Pair lmin = ltree.min(next+1,r[next]);
            if (debug) System.out.println("LMIN: "+lmin);
            if (lmin.val<=next) {
                ltree.set(lmin.i,INF);
                rtree.set(lmin.i,-INF);
                dist[lmin.i]=dist[next]+1;
                BFS.addLast(lmin.i);
                continue;
            }
            //rmax found node
            Pair rmax = rtree.max(l[next],next-1);
            if (debug) System.out.println("RMAX: "+rmax);
            if (rmax.val>=next) {
                ltree.set(rmax.i,INF);
                rtree.set(rmax.i,-INF);
                dist[rmax.i]=dist[next]+1;
                BFS.addLast(rmax.i);
                continue;
            }
            //found none, move on
            BFS.pollFirst();
        }
        //ret
        out.println(dist[B]);
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        Pair[] treeMin;
        Pair[] treeMax;
        int[] arr;
        public SegTree(int n){
            arr = new int[n+1];
            init(n);
        }
        public SegTree(int n, int[] arr){
            this.arr=arr;
            init(n);
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            treeMin = new Pair[2*size+1]; for (int i=1;i<=2*size;i++) treeMin[i] = new Pair();
            treeMax = new Pair[2*size+1]; for (int i=1;i<=2*size;i++) treeMax[i] = new Pair();
            for (int i=1;i<=n;i++){
                treeMin[i+size-1]=new Pair(i,arr[i]);
                treeMax[i+size-1]=new Pair(i,arr[i]);
            }
            for (int i=size-1;i>=1;i--){
                treeMin[i]=Pair.min(treeMin[i*2],treeMin[i*2+1]);
                treeMax[i]=Pair.max(treeMax[i*2],treeMax[i*2+1]);
            }
        }
        void set(int k, long x){
            k+=size-1;
            treeMin[k].val=x;
            treeMax[k].val=x;
            for (k/=2;k>=1;k/=2){
                treeMin[k]=Pair.min(treeMin[2*k],treeMin[2*k+1]);
                treeMax[k]=Pair.max(treeMax[2*k],treeMax[2*k+1]);
            }
        }
        Pair max(int a, int b) {
            a+=size-1;
            b+=size-1;
            Pair ret = new Pair(-INF,-INF);
            while (a<=b){
                if (a%2==1) ret=Pair.max(ret,treeMax[a++]);
                if (b%2==0) ret=Pair.max(ret,treeMax[b--]);
                a/=2;
                b/=2;
            }
            return ret;
        }
        Pair min(int a, int b) {
            a+=size-1;
            b+=size-1;
            Pair ret = new Pair(INF,INF);
            while (a<=b){
                if (a%2==1) ret=Pair.min(ret,treeMin[a++]);
                if (b%2==0) ret=Pair.min(ret,treeMin[b--]);
                a/=2;
                b/=2;
            }
            return ret;
        }
    }
    private static class Pair {
        int i;
        long val;
        public Pair(){
            this.i=0;
            this.val=0;
        };
        public Pair(int i, long val){
            this.i=i;
            this.val=val;
        }
        public Pair clone() {
            return new Pair(i,val);
        }
        public static Pair max(Pair u, Pair v){
            if (u.val>v.val) return u.clone();
            return v.clone();
        }
        public static Pair min(Pair u, Pair v){
            if (u.val>v.val) return v.clone();
            return u.clone();
        }
        public String toString(){
            return "["+i+", "+val+"]";
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

/*
1
2 2 2
1 1
 */