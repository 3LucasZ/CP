package Codeforces.GoodBye2022;

import java.io.*;
import java.util.*;
/*
PROB: KoxiaAndTree
LANG: JAVA
*/
public class KoxiaAndTree {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int K;
    static ArrayList<Integer>[] tree;
    static long[] p;

    static int[] sz;
    static int[] h;

    static long ans = 0;

    static long MOD = 998244353;
    static NT nt;
    static long i2;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        K = io.nextInt();
        p = new long[N+1];
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        Pair[] edges = new Pair[N-1];
        for (int i=0;i<K;i++){
            int special = io.nextInt();
            p[special]=1;
        }
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
            edges[i] = new Pair(u,v);
        }

        //* precomp NT
        nt = new NT(MOD);
        i2 = nt.inv(2);

        //* precomp h,sz
        h = new int[N+1];
        sz = new int[N+1];
        DFS(1,0);
        if (debug){
            io.println("h: "+Arrays.toString(h));
            io.println("sz: "+Arrays.toString(sz));
            io.println("p: "+Arrays.toString(p));
        }

        //* iterate through edges
        for (Pair edge : edges){
            //get fa,son
            int fa = edge.x;
            int son = edge.y;
            if (h[fa]>h[son]){
                int tmp = fa;
                fa=son;
                son=tmp;
            }
            if (debug){
                io.println("fa: "+fa+", son: "+son);
            }
            //calculate 3 cases
            long delta;
            long pFullEmptyMove = p[fa]*(MOD+1-p[son])%MOD*i2%MOD;
            delta = pFullEmptyMove*(sz[son]+1)%MOD*(K-sz[son]-1)%MOD;
            ans=(ans+delta)%MOD;
            if (debug) io.println(delta);

            long pEmptyFullMove = (MOD+1-p[fa])*p[son]%MOD*i2%MOD;
            delta = pEmptyFullMove*(sz[son]-1)%MOD*(K-sz[son]+1)%MOD;
            ans=(ans+delta)%MOD;
            if (debug) io.println(delta);

            long pSame = (1-pEmptyFullMove-pFullEmptyMove+10*MOD)%MOD;
            delta = pSame*sz[son]%MOD*(K-sz[son])%MOD;
            ans=(ans+delta)%MOD;
            if (debug) io.println(delta);

            if (debug){
                io.println("pFullEmpty: "+pFullEmptyMove);
                io.println("pEmptyFullMove: "+pEmptyFullMove);
                io.println("pSame: "+pSame);
            }

            //change p
            p[fa]=p[son]=(p[fa]+p[son])*i2%MOD;
        }
        if (debug){
            io.println("p: "+Arrays.toString(p));
        }

        //* ret
        long ichoose = nt.inv((long)K*(K-1)/2%MOD);
        io.println(ans*ichoose%MOD);
    }
    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void DFS(int node, int par){
        h[node]=h[par]+1;
        if (p[node]==1) sz[node]++;
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node);
            sz[node]+=sz[child];
        }
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