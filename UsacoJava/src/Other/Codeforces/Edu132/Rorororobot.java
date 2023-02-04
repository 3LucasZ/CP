package Other.Codeforces.Edu132;

import java.io.*;
import java.util.*;
/*
PROB: Rorororobot
LANG: JAVA
*/
public class Rorororobot {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;
    
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        int M = io.nextInt();
        int[] h = new int[M+1];
        for (int i=1;i<=M;i++) h[i]=io.nextInt();
        SegTree seg = new SegTree(M,h);

        //* handle
        int q = io.nextInt();
        for (int i=0;i<q;i++){
            int r1 = io.nextInt();
            int c1 = io.nextInt();
            int r2 = io.nextInt();
            int c2 = io.nextInt();
            int k = io.nextInt();
            if ((r2-r1)%k!=0 || (c2-c1)%k!=0) {
                io.println("NO");
                continue;
            }
            int mx =(int)seg.max(Math.min(c1,c2),Math.max(c1,c2))+1;
            if (r1>=mx){
                io.println("YES");
                continue;
            }
            int add = k-(mx-r1)%k;
            if (add==k) add=0;
            if (mx+add<=N){
                io.println("YES");
            } else {
                io.println("NO");
            }
        }
    }

    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;
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
            tree = new long[2*size+1];
        }
        void set(int k, long x){
            k+=size-1;
            tree[k]=x;
            for (k/=2;k>=1;k/=2){
                tree[k]=Math.max(tree[2*k],tree[2*k+1]);
            }
        }
        long max(int a, int b) {
            a+=size-1;
            b+=size-1;
            long ret = 0;
            while (a<=b){
                if (a%2==1) ret=Math.max(ret,tree[a++]);
                if (b%2==0) ret=Math.max(ret,tree[b--]);
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