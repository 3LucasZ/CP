package Other.Q;

import java.io.*;
import java.util.*;
/*
PROB: NonDecreasingDillema
LANG: JAVA
*/
public class NonDecreasingDillema {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    static int N;
    static int Q;
    static int[] A;

    static RUPS lb;
    static RUPS rb;
    static PURS sum;
    static PURS ss;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        Q = io.nextInt();
        A = new int[N+2];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }

        //* Seg setup
        lb = new RUPS(N);
        rb = new RUPS(N);
        sum = new PURS(N);
        ss = new PURS(N);
        lb.add(1,1,1);
        for (int i=2;i<=N;i++){
            if (A[i]<A[i-1]) lb.add(i,i,i);
            else lb.add(i,i,lb.get(i-1));
        }
        rb.add(N,N,N);
        for (int i=N-1;i>=1;i--){
            if (A[i]>A[i+1]) rb.add(i,i,i);
            else rb.add(i,i,rb.get(i+1));
        }
        for (int i=1;i<=N;i++){
            if (rb.get(i)==i){
                int l = lb.get(i);
                sum.add(i,i-l+1);
                ss.add(i,squareSum(i-l+1));
            }
        }
        if (debug){
            io.println("A:"+Arrays.toString(A));
            io.println("lb:"+lb.print());
            io.println("rb:"+rb.print());
            io.println("sum:"+Arrays.toString(sum.val));
            io.println("ss:"+Arrays.toString(ss.val));
            io.println(query(3)-query(0));
        }

        //* ANSWER query
        for (int i=0;i<Q;i++){
            int type = io.nextInt();
            //update
            if (type==1){
                int x = io.nextInt();
                int k = io.nextInt();
                int a = x-1;
                int b = x+1;
                //keep
                int al=lb.get(a);
                int ar=rb.get(a);
                int xl=lb.get(x);
                int xr=rb.get(x);
                int bl=lb.get(b);
                int br=rb.get(b);
                //reset
                if (A[x]>=A[a]){

                }
            }
        }

    }
    static long query(int x){
        if (x<=0) return 0;
        long ret = 0;
        ret += ss.sum(1,x);
        ret += squareSum(x-sum.sum(1,x));
        return ret;
    }
   static long squareSum(long x) {
        return x*(x+1)/2;
   }

    private static class RUPS {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        public RUPS(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        int get(int k){
            int ret = 0;
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
        String print() {
            long[] ret = new long[N+1];
            for (int i=1;i<=N;i++) ret[i]=get(i);
            return (Arrays.toString(ret));
        }
    }
    private static class PURS{
        //1-indexed
        //range is []
        int size;
        long[] tree;
        long[] val;
        public PURS(int n){
            init(n);
        }
        public PURS(int n,int[] arr){
            init(n);
            for (int i=1;i<=n;i++){
                tree[i+size-1]=arr[i];
                val[i]=arr[i];
            }
            for (int i=size-1;i>=1;i--){
                tree[i]=tree[i*2]+tree[i*2+1];
            }
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
            val = new long[n+1];
        }
        void add (int k, long x){
            set(k,tree[k+size-1]+x);
        }
        void set(int k, long x){
            val[k]=x;
            k+=size-1;
            tree[k]=x;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws IOException {
        if (fileSubmission){
            io = new IO(fileName, debug);
        } else {
            io = new IO(debug);
        }
        solve();
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
    public static void print(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 5) str += " ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
    void close(){
        out.close();
    }
};;
}