package Other.Codeforces.Round848;

import java.io.*;
import java.util.*;

public class FlexibleStringRevisit{
    static boolean debug = false;

    static long MOD = 998244353;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        NT nt = new NT(MOD);
        int N = io.nextInt();
        String str1 = io.nextLine();
        String str2 = io.nextLine();
        int d = 0;
        for (int i=0;i<N;i++){
            if (str1.charAt(i)!=str2.charAt(i)) d++;
        }

        //* solve dp for expected value to beat x cards
        long[] f = new long[N+1];
        f[0]=0;
        f[1]=mod(nt.pow(2,N)-1);
        for (int i=2;i<=N;i++){
            f[i]=mod((N*f[i-1]-(i-1)*f[i-2]-N))*nt.inv(N-i+1)%MOD;
        }

        //* ret
        io.println(f[d]);
    }
    static long mod(long x){
        return (x%MOD+MOD)%MOD;
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