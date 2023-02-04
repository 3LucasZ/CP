package Other.CodeJam.Round2_2021;

import java.io.*;
import java.util.*;

public class HiddenPancakes {
    static boolean debug = false;

    static int N;
    static int[] A;

    static long MOD = (long)(1e9+7);

    static NT nt;
    static SegTree seg;

    public static void solve(int tcs) throws IOException {
        io.print("Case #"+tcs+": "); if (debug) io.println("");
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=io.nextInt();
        if (debug) io.println("A: "+Arrays.toString(A));
        //* setup
        nt = new NT(N,MOD);
        seg = new SegTree(N,A);
        if (debug) {
            io.println("treeMin: "+Arrays.toString(seg.treeMin));
        }
        //* divide and conquer DP (very elegant)
        io.println(solveSeg(1,N,0));
    }
    public static long solveSeg(int l, int r, int sub){
        if (debug) io.println("Solving: ["+l+", "+r+"]");
        //base case: l>r
        if (l>r) return 1;
        //get split point
//        int split = l;
//        for (int i=l;i<=r;i++){
//            if (A[i]<=A[split]){
//                split = i;
//            }
//        }
        Pair splitPair = seg.min(l,r);
        int split = splitPair.i;
        if (debug) io.println("split: "+splitPair);
        //no split point
        if (A[split]-sub!=1){
            return 0;
        }
        //solve l and r segments
        long lcnt = solveSeg(l,split-1,sub);
        long rcnt = solveSeg(split+1,r,sub+1);
        //ret
        int len = r-l;
        int rlen = r-split;
        long ans = lcnt*rcnt%MOD*nt.choose(len,rlen)%MOD;
        return ans;
    }
    private static class NT {
        // Note: only works if MOD is prime
        //* pow, inv
        long MOD;
        public long inv(long x) {
            return pow(x,MOD-2);
        }
        public long pow(long x, long p) {
            if (x==0) return 0;
            if (p == 0) return 1;
            if (p % 2 == 1) return (x * pow(x, p - 1)) % MOD;
            else return pow((x * x) % MOD, p / 2);
        }
        public NT(long MOD) {
            this.MOD=MOD;
        }
        //* choose
        //factorials
        long[] f;
        //factorial inverses
        long[] i;
        int MAXF;
        public NT(int MAXF, long MOD) {
            //gen factorials (1...N)!
            this.MAXF=MAXF;
            this.MOD=MOD;
            f = new long[MAXF + 1];
            f[0] = 1;
            for (int i = 1; i <= MAXF; i++) f[i] = (f[i - 1] * i) % MOD;
            //gen inverses (1...N)!^-1
            i = new long[MAXF + 1];
            for (int A = 1; A <= MAXF - 1; A++) {
                i[A] = inv(f[A]);
            }
        }
        public long choose(int n, int k) {
            if (k == n || k == 0) return 1;
            return ((f[n] * i[k] % MOD) * i[n - k]) % MOD;
        }
    }
    private static class SegTree {
        /*
        1-indexed
         range is []
         */
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

            //fast prefill tree with arr
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
            Pair ret = new Pair(0,0);
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
            Pair ret = new Pair(0,Integer.MAX_VALUE);
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
            if (u.val>v.val || (u.val==v.val && u.i>v.i)) return u.clone();
            return v.clone();
        }
        public static Pair min(Pair u, Pair v){
            if (u.val>v.val || (u.val==v.val && v.i>u.i)) return v.clone();
            return u.clone();
        }
        public String toString(){
            return "["+i+", "+val+"]";
        }
    }




















    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    static IO io;
    private static class IO {
    BufferedReader br;
    StringTokenizer st;
    PrintWriter out;
    boolean debug;
    public IO(boolean dbg)  {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        debug=dbg;
    }
    public IO(String fileName, boolean dbg) throws IOException {
        br = new BufferedReader(new FileReader(fileName+".in"));
        out = new PrintWriter(new FileWriter(fileName+".out"));
        debug=dbg;
    }
    String next()
    {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
    double nextDouble() {return Double.parseDouble(next());}
    String nextLine() {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    void println(){
        if (debug) System.out.println();
        else out.println();
    }
    void println(Object obj){
        if (debug) System.out.println(obj);
        else out.println(obj);
    }
    void print(Object obj){
        if (debug) System.out.print(obj);
        else out.print(obj);
    }
    void close(){
        out.close();
    }
};;
}