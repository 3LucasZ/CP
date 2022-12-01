package Codeforces.Round833;

import java.io.*;
import java.util.*;

/*
PROB: YetAnotherArrayCountingProblem
LANG: JAVA
*/
public class YetAnotherArrayCountingProblem {
    static boolean submission = false;
    static boolean debug = true;

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
//        if (debug) {
//            System.out.println("head: "+head);
//            System.out.println("treeL: "+Arrays.toString(treeL));
//            System.out.println("treeR: "+Arrays.toString(treeR));
//        }
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
        int mid = getLeftMaxNode(l,r);
        if (l<=mid-1)treeL[mid]=solveRange(l,mid-1);
        if (mid+1<=r)treeR[mid]=solveRange(mid+1,r);
        return mid;
    }
    static int getLeftMaxNode(int l, int r){
        int lo=l; int hi=r;
        while (lo<hi){
            int mid = (lo+hi)/2;
            if (seg.max(l,mid)==seg.max(l,r)) hi=mid;
            else lo=mid+1;
        }
        return lo;
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
        int[] tree;
        public SegTree(int n){
            init(n);
        }
        public SegTree(int n, int[] arr){
            init(n);
            for (int i=1;i<=n;i++){
                tree[i+size-1]=arr[i];
            }
            for (int i=size-1;i>=1;i--){
                tree[i]=Math.max(tree[i*2],tree[i*2+1]);
            }
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new int[2*size+1];
        }
        int max(int a, int b) {
            a+=size-1;
            b+=size-1;
            int ret = 0;
            while (a<=b){
                if (a%2==1) ret=Math.max(ret,tree[a++]);
                if (b%2==0) ret=Math.max(ret,tree[b--]);
                a/=2;
                b/=2;
            }
            return ret;
        }
    }
}