import java.io.*;
import java.util.*;

public class PotionBrewingClass {
    static boolean debug = false;

    static int N;
    static ArrayList<Edge>[] graph;

    static int[] lcm;
    static int[] factorization;

    static ArrayList<Integer> primes;
    static final long MOD = 998244353;
    static long[] inv;
    static final int MAXN = 200000;

    public static void solve(int tcs) throws IOException {
        ans=0;
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            int div = io.nextInt();
            int num = io.nextInt();
            graph[u].add(new Edge(u,v,num,div));
            graph[v].add(new Edge(v,u,div,num));
        }

        //* find LCM(b1,...,bn)
        factorization= new int[N+1];
        lcm = new int[N+1];
        DFS(1,0);
        if (debug){
            io.println("lcm: "+Arrays.toString(lcm));
        }

        //* if we start node 1 with lcm we can run down and find the ans
        long init = 1;
        for (int i=1;i<=N;i++){
            for (int j=0;j<lcm[i];j++) init=(init*i)%MOD;
        }
        if (debug){
            io.println("real lcm: "+init);
        }
        DFS2(1,0,init);

        //* ret
        io.println(ans);
    }
    static long ans = 0;
    static void DFS2(int node, int par, long run){
        if (debug){
            io.println("node: "+node);
            io.println("run: "+run);
        }
        ans = (ans+run)%MOD;
        for (Edge edge : graph[node]){
            if (edge.v==par) continue;
            int num = edge.num;
            int div = edge.div;
            if (debug) io.println(num+"/"+div);
            ArrayList<Integer> pNum = primeFactors(num);
            ArrayList<Integer> pDiv = primeFactors(div);
            for (int p : pNum) run=(run*p)%MOD;
            for (int p : pDiv) run=(run*inv[p])%MOD;
            DFS2(edge.v,node,run);
            for (int p : pNum) run=(run*inv[p])%MOD;
            for (int p : pDiv) run=(run*p)%MOD;
        }
    }
    static void DFS(int node, int par){
        for (Edge edge : graph[node]){
            if (edge.v==par) continue;
            int num =edge.num;
            int div = edge.div;
            ArrayList<Integer> pNum = primeFactors(num);
            ArrayList<Integer> pDiv = primeFactors(div);
            for (int p : pNum) factorization[p]++;
            for (int p : pDiv) {
                factorization[p]--;
                lcm[p]=Math.max(lcm[p],-factorization[p]);
            }
            DFS(edge.v,node);
            for (int p : pNum) factorization[p]--;
            for (int p : pDiv) factorization[p]++;
        }
    }
    public static long lcm(long a, long b){
        return a*b/gcd(a,b);
    }
    public static long gcd(long a, long b){
        if (b==0) return a;
        return gcd(b,a%b);
    }
    static ArrayList<Integer> primeFactors(int val){
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i=0;primes.get(i)*primes.get(i)<=val;i++){
            int p = primes.get(i);
            while (val%p==0){
                ret.add(p);
                val/=p;
            }
        }
        if (val>1) ret.add(val);
        return ret;
    }
    private static class Edge {
        int u;
        int v;
        int num;
        int div;
        public Edge(int u, int v, int num, int div){
            this.u=u;
            this.v=v;
            this.num=num;
            this.div=div;
        }
    }
    public static ArrayList<Integer> primesLEQ(int n){
        //is prime boolean array
        boolean[] prime = new boolean[n+1]; for (int i=2;i<=n;i++) prime[i]=true;
        for (int p=2;p*p<=n;p++){
            if (prime[p]){
                for (int i=p*p;i<=n;i+=p){
                    prime[i]=false;
                }
            }
        }
        //convert array to list
        ArrayList<Integer> ret = new ArrayList<>();
        for (int p=2;p<=n;p++){
            if (prime[p]) ret.add(p);
        }
        return ret;
    }
    private static class NT {
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
        //* choose, factorials, factorial inverses
        long[] f;
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
            i[MAXF]=inv(f[MAXF]);
            for (int A = MAXF; A > 0; A--) {
                i[A-1]=i[A]*A%MOD;
            }
        }
        public long choose(int n, int k) {
            if (k == n || k == 0) return 1;
            return ((f[n] * i[k] % MOD) * i[n - k]) % MOD;
        }
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);

        primes = primesLEQ(MAXN);
        inv= new long[MAXN+1];
        NT nt = new NT(MOD);
        for (int i=1;i<=MAXN;i++){
            inv[i]=nt.inv(i);
        }
//        if (debug){
//            io.println("primes:"+primes);
//            io.println("inv:"+Arrays.toString(inv));
//        }

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