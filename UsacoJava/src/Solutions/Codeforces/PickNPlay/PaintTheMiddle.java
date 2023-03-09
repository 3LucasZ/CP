package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: PaintTheMiddle
LANG: JAVA
*/
public class PaintTheMiddle {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] A;
    static int[] first; // for color c: first i whose ai in 1..N
    static int[] last;  //for color c: last i whose ai in 1..N

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i] = io.nextInt();

        //* build our intervals
        int INF = Integer.MAX_VALUE;
        first = new int[N+1]; for (int i=1;i<=N;i++) first[i]=INF;
        last = new int[N+1];
        for (int i=1;i<=N;i++){
            first[A[i]]=Math.min(first[A[i]],i);
            last[A[i]]=Math.max(last[A[i]],i);
        }
        if (debug){
            io.println("A: "+Arrays.toString(A));
            io.println("first: "+Arrays.toString(first));
            io.println("last:  "+Arrays.toString(last));
        }

        //* seg sim
        SegTree seg = new SegTree(N);
        for (int i=1;i<=N;i++) seg.set(i,i); //set last for i
        if (debug) io.println("val: "+Arrays.toString(seg.val));
        //* unit test segtree
        if (debug){
            io.println("2,4: "+seg.query(2,4));
        }

        //* sim
        int ans = N; //sub one to use first thing
        int cur = 1; //index we are on
        while (cur<=N && first[A[cur]]==last[A[cur]]){
            cur++;
            ans--;
        }
        if (cur>=N){
            io.println(ans);
            return;
        };
        ans--; //new interval fr
        while (true){
            if (debug) io.println("cur: "+cur);
            int next = seg.query(cur+1,last[A[cur]]-1); //new index
            if (debug) io.println("best r: "+last[A[next]]);
            if (last[A[next]]<=last[A[cur]]){
                ans--; //end interval
                next=last[A[cur]]+1;
            }
            cur=next;
            while (cur<=N && first[A[cur]]==last[A[cur]]){
                cur++;
                ans--;
            }
            if (cur>=N) break;
            ans--; //new interval fr
        }

        //* ret
        io.println(ans);
    }

    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        int[] tree;
        int[] val;
        public SegTree(int n){
            init(n);
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new int[2*size+1];
            val = new int[n+1];
        }
        void set(int k, int x){
            val[k]=x;
            k+=size-1;
            tree[k]=x;
            for (k/=2;k>=1;k/=2){
                tree[k]=max(tree[2*k],tree[2*k+1]);
            }
        }
        int query(int a, int b) {
            a+=size-1;
            b+=size-1;
            int ret = 0;
            while (a<=b){
                if (a%2==1) ret=max(ret,tree[a++]);
                if (b%2==0) ret=max(ret,tree[b--]);
                a/=2;
                b/=2;
            }
            return ret;
        }
        int max(int a, int b){
            if (last[A[a]]<last[A[b]])return b;
            else return a;
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