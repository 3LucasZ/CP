package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: CarryBit
LANG: JAVA
*/
public class CarryBit {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static final long mod=(long)1e9+7;
    static NT nt = new NT(1000000,mod);
    static int N,K;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        K = io.nextInt();

        //* base
        if (K==0) {
            io.println(nt.pow(3,N));
            return;
        }

        //* iterate over q
        long ans = 0;
        long pow[] = new long[N+1];
        pow[0]=1; for (int i=1;i<=N;i++) pow[i]=pow[i-1]*3%mod;
        for (int q=0;q<=N;q++){
            int seg1 = (q+1)/2;
            int seg0 = q/2+1;
            long add = pow[N-q]*nt.choose(N-K,seg0-1)%mod*nt.choose(K-1,seg1-1)%mod;
            ans=(ans+add)%mod;
            if (debug){
                io.println("q:"+q+", add:"+add);
            }
        }

        //* ret
        io.println(ans);
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
            if (k>n) return 0;
            if (k<0) return 0;
            if (k==0) return 1;
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