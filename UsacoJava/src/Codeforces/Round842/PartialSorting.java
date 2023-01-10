package Codeforces.Round842;

import java.io.*;
import java.util.*;
/*
PROB: PartialSorting
LANG: JAVA
*/
public class PartialSorting {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;
    
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        long M = io.nextInt();

        long init = System.currentTimeMillis();
        NT nt = new NT(3*N,M);

        long zero = 1;

        long one = (2*nt.f[2*N]-nt.f[N]-zero+2*M)%M;

        long two = 2*nt.choose(2*N,N)*nt.f[2*N]%M*nt.f[N]%M;
        two = (two-zero+M)%M;
        two = (two-one+M)%M;
        //longest taking part is calculating for 2's intersection...
        long rem = 0;
        long ntf3 = nt.f[N]*nt.f[N]%M*nt.f[N]%M;
        for (int a=0;a<=N;a++){
            rem = (rem+nt.choose(N,a)*nt.choose(N,N-a)%M*nt.choose(N+a,N))%M;
        }
        two = (two-rem*ntf3%M+M)%M;

        long three = (nt.f[3*N]-zero+M-one+M-two+M)%M;
        long ans = (zero*0 + one*1 + two*2 + three*3)%M;

        if (debug){
            io.println("zero: "+zero);
            io.println("one: "+one);
            io.println("two: "+two);
            io.println("three: "+three);
            io.println("runtime: "+(System.currentTimeMillis()-init)+"ms");
        }
        io.println(ans);
    }
    /*
    1000000 1000000007
     */
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