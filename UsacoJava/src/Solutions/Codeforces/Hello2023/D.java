package Solutions.Codeforces.Hello2023;

import java.io.*;
import java.util.*;

public class D{
    static boolean debug = false;

    static int N;
    static int[] A; //cur
    static int[] B; //tar
    static int M;
    static Integer[] X; //razor
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        B = new int[N+1];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }
        for (int i=1;i<=N;i++){
            B[i]=io.nextInt();
        }
        M = io.nextInt();
        X = new Integer[M];
        for (int i=0;i<M;i++){
            X[i]=io.nextInt();
        }
        //* base cases
        //if B is ever larger than A we lose lol. confirm that A[i]>=B[i]
        for (int i=1;i<=N;i++){
            if (B[i]>A[i]) {
                io.println("NO");
                return;
            }
        }

        //* precompute the height of hair and it's positions where you MUST make a cut there
        TreeMap<Integer,ArrayList<Integer>> map = new TreeMap<>();
        for (int i=1;i<=N;i++){
            if (!map.containsKey(B[i])) map.put(B[i],new ArrayList<>());
            map.get(B[i]).add(i);
        }
        if (debug){
            io.println("B map: "+map);
        }


        //* simulate hair cut lar -> sm
        TreeSet<Integer> cuts = new TreeSet<>();
        Multiset razors = new Multiset();
        for (int i=0;i<M;i++) razors.add(X[i]);
        if (debug){
            io.println("razors: "+razors);
        }

        for (int height : map.descendingKeySet()){
            int last = 0;
            for (int i : map.get(height)){
                Integer lastCut = cuts.floor(i);
                //use a new razor
                if (B[i]!=A[i] && (last==0 || (lastCut!=null &&lastCut>last))){
                    if (debug) io.println("using razor: "+height);
                    if (!razors.contains(height)){
                        io.println("NO");
                        return;
                    } else {
                        razors.remove(height);
                    }
                    last=i;
                }
            }
            cuts.addAll(map.get(height));
        }

        //* finalize
        io.println("YES");
    }
    private static class Multiset {
        /*
        Overloaded TreeMap functioning as Multiset
        TreeMap private
         */
        private TreeMap<Integer, Integer> ms = new TreeMap<>();
        private int sz = 0;
        public boolean contains(int x){
            return ms.containsKey(x);
        }
        public void add(int x){add(x,1);}
        public void add(int x, int freq){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+freq);
            sz+=freq;
        }
        public void remove(int x){
            remove(x,1);}
        public void remove(int x, int freq){
            ms.put(x,ms.get(x)-freq);
            if (ms.get(x)<=0) ms.remove(x);
            sz-=freq;
        }
        public void removeKey(int x){
            ms.remove(x);
        }
        public int get(int x){
            return ms.get(x);
        }
        public Iterator<Integer> iterator(){
            return ms.keySet().iterator();
        }
        public int size(){
            return sz;
        }
        public Set<Integer> keySet(){
            return ms.keySet();
        }
        public String toString(){
            return ms.toString();
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
                tree[i]=tree[i*2]+tree[i*2+1];
            }
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        void add (int k, int x){
            set(k,tree[k+size-1]+x);
        }
        void set(int k, long x){
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




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
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
};}