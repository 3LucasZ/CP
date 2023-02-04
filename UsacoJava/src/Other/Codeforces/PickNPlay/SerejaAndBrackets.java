package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: SerejaAndBrackets
LANG: JAVA
*/
public class SerejaAndBrackets {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int Q;
    static char[] A;

    public static void solve() throws IOException {
        //* parse
        String str = io.next();
        N = str.length();
        A = new char[N+1];
        for (int i=0;i<N;i++) A[i+1]=str.charAt(i);

        //* pair ) with the closest ( EXTREMELY GREEDY
        SegTree end = new SegTree(N);
        ArrayList<Integer>[] other = new ArrayList[N+1]; for (int i=0;i<=N;i++) other[i] = new ArrayList<>();

        Stack<Integer> open = new Stack<>();
        for (int i=1;i<=N;i++){
            if (A[i]=='('){
                open.add(i);
            } else {
                if (!open.isEmpty()){
                    end.add(i,1);
                    int last = open.pop();
                    other[last].add(i);
                }
            }
        }

        if (debug){
            io.println("end:"+Arrays.toString(end.val));
        }

        //* handle querying
        Q = io.nextInt();
        int[] l = new int[Q];
        int[] r = new int[Q];
        for (int i=0;i<Q;i++){
            l[i] = io.nextInt();
            r[i] = io.nextInt();
        }

        //* order queries by l
        Integer[] o = new Integer[Q];
        for (int i=0;i<Q;i++) o[i]=i;
        Arrays.sort(o,Comparator.comparingInt(a->l[a]));

        //* go up up deleting and yeeting
        int[] ans = new int[Q];
        int t = 0;
        for (int i=0;i<Q;i++){
            int j = o[i];
            while (t<l[j]){
                for (int s : other[t]){
                    end.add(s,-1);
                }
                t++;
            }
            ans[j]=(int)(2*end.sum(l[j],r[j]));
        }
        for (int i : ans) io.println(i);
    }

    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        long[] val;
        public SegTree(int n){
            init(n);
        }
        public SegTree(int n, int[] arr){
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
        void add (int k, int x){
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