package Solutions.CSES.DP;

import java.io.*;
import java.util.*;
/*
PROB: IncreasingSubsequence
LANG: JAVA
*/
public class IncreasingSubsequenceMethod1 {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static Element[] A;
    public static void main(String[] args) throws IOException {
        //input
        setup("IncreasingSubsequence");
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new Element[N];
        for (int i=0;i<N;i++){
            A[i]=new Element(i,Integer.parseInt(st.nextToken()));
        }
        //compress numbers
        Arrays.sort(A,(a,b)->a.x-b.x);
        int[] X = new int[N];
        int val = 1;
        for (int i=0;i<N;i++){
            if (i>0&&A[i].x>A[i-1].x)val++;
            X[A[i].i]=val;
        }
        if (debug){
            System.out.println(Arrays.toString(X));
        }
        //seg dp init
        SegTree seg = new SegTree(N+1);
        long[] dp = new long[N];
        //seg dp transition
        for (int i=0;i<N;i++){
            dp[i]=seg.get(1,X[i]-1)+1;
            seg.set(X[i],(int)dp[i]);
        }
        //ret
        if (debug) System.out.println(Arrays.toString(dp));
        long ans = 0; for (int i=0;i<N;i++) ans=Math.max(ans,dp[i]);
        out.println(ans);
        out.close();
    }
    private static class Element {
        int i;
        int x;
        public Element(int i, int x){
            this.i=i;
            this.x=x;
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
                tree[i]=tree[i*2]+tree[i*2+1];
            }
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new int[2*size+1];
        }
        void set(int k, int x){
            k+=size-1;
            tree[k]=Math.max(tree[k],x);
            for (k/=2;k>=1;k/=2){
                tree[k]=Math.max(tree[2*k],tree[2*k+1]);
            }
        }
        int get(int a, int b) {
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