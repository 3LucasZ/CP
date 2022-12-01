package Codeforces.Round833;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
PROB: YetAnotherArrayCountingProblem
LANG: JAVA
*/
public class YetAnotherArrayCountingProblem2 {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("YetAnotherArrayCountingProblem");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N; //elements
    static int M; //max value of element
    static int[] A; //arr given, 1-indexed so we can segtree it
    static SegTree seg; //1-indexed max seg
    static int[] treeL; //node i's left node
    static int[] treeR; //node i's right node

    static long MOD = (long)(1e9)+7;

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) A[i] = Integer.parseInt(st.nextToken());
        if (debug) System.out.println("A: "+Arrays.toString(A));
        //* Create max seg
        seg = new SegTree(N, A);
        //* Divide and conquer to fill treeL, treeR
        treeL = new int[N+1]; treeR = new int[N+1];
        int head = solveRange(1,N);
        if (debug) {
            System.out.println("head: "+head);
            System.out.println("treeL: "+Arrays.toString(treeL));
            System.out.println("treeR: "+Arrays.toString(treeR));
        }
        //* bottom up DP
        long[] ret = DFS(head);
        //* ret
        long ans = 0;
        for (int i=1;i<=M;i++)ans=(ans+ret[i])%MOD;
        out.println(ans);
    }
    static long[] DFS(int node){
        long[] ret = new long[M+1];
        //base case: leaf nodes are connected to 0 so special case
        if (node==0){
            ret[0]=1;
            //for (int i=1;i<=M;i++) ret[i]=1;
            return ret;
        }
        //l and r DFS arrays and counts
        long[] l =  DFS(treeL[node]);
        long[] r = DFS(treeR[node]);
        long lcnt = 0;
        long rcnt = r[0];
        for (int i=1;i<=M;i++){
            lcnt=(lcnt+l[i-1])%MOD;
            rcnt=(rcnt+r[i])%MOD;
            ret[i]=(lcnt*rcnt)%MOD;
        }
        if (debug) System.out.println(node+": "+Arrays.toString(ret));
        return ret;
    }
    static int solveRange(int l, int r){
        int mid = seg.max(l,r).i;
        if (l<=mid-1)treeL[mid]=solveRange(l,mid-1);
        if (mid+1<=r)treeR[mid]=solveRange(mid+1,r);
        return mid;
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
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
            treeMax = new Pair[2*size+1]; for (int i=1;i<=2*size;i++) treeMax[i] = new Pair();
            for (int i=1;i<=n;i++){
                treeMax[i+size-1]=new Pair(i,arr[i]);
            }
            for (int i=size-1;i>=1;i--){
                treeMax[i]=Pair.max(treeMax[i*2],treeMax[i*2+1]);
            }
        }
        Pair max(int a, int b) {
            a+=size-1;
            b+=size-1;
            Pair ret = new Pair();
            while (a<=b){
                if (a%2==1) ret=Pair.max(ret,treeMax[a++]);
                if (b%2==0) ret=Pair.max(ret,treeMax[b--]);
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
            if (u.val>v.val || (u.val==v.val&&u.i<v.i)) return u.clone();
            return v.clone();
        }
        public String toString(){
            return "["+i+", "+val+"]";
        }
    }
}