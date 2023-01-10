package Codeforces.Poly2022;

import java.io.*;
import java.util.*;
/*
PROB: Codeforces.Poly2022.MagicianAndPigsEasy
LANG: JAVA
*/
public class MagicianAndPigsEasy {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;
    static int N;

    static int MAXH = 200000;
    static long MOD = 998244353;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();

        //* logistics
        long offset = 0;
        int cloningPhase = 0;
        long freq = 1;
        long freqi =  1;
        Multiset ms = new Multiset();
        NT nt = new NT(MOD);
        long inv = nt.inv(2);

        //* handle queries
        for (int i=0;i<N;i++){
            int op = io.nextInt();
            //add new pig of x O(1)
            if (op==1){
                int x = io.nextInt();
                ms.add(offset+x,freqi);
            }
            //reduce all by x (lazy) O(1)
            else if (op==2){
                int x = io.nextInt();
                offset+=x;
                if (cloningPhase==0) {
                    cloningPhase=1;
                }
            }
            //clone
            else {
                //everything before is doubled during phase 0 O(1)
                if (cloningPhase==0){
                    freq=(freq*2)%MOD;
                    freqi=(freqi*inv)%MOD;
                }
                //manually brute force clone the ms during phase 1 O(10^5)
                else if(cloningPhase==1){
                    //offset overflow
                    if (offset>MAXH){
                        cloningPhase = 2;
                    }
                    //offset clone
                    else{
                        for (long j=offset+MAXH;j>offset;j--) if (ms.contains(j)) ms.add(j+offset,ms.get(j));
                        offset*=2;
                    }
                }
                //phase 2 means no effects O(1)
            }
        }
        
        //* ret
        long ans = 0;
        for (long key : ms.keySet()){
            if (key>offset)ans=(ans+ms.get(key))%MOD;
        }
        io.println(ans*freq%MOD);
    }
    private static class Multiset {
        /*
        Overloaded HashMap functioning as Multiset
         */
        HashMap<Long, Long> ms;
        public Multiset(){
            ms = new HashMap<>();
        }
        public Multiset(HashMap<Long, Long> ms) {
            this.ms=ms;
        }
        public boolean contains(long x){
            return ms.containsKey(x);
        }
        public void add(long x){add(x,1);}
        public void add(long x, long freq){
            if (!ms.containsKey(x))ms.put(x,0L);
            ms.put(x,(ms.get(x)+freq)%MOD);
        }

        public void removeKey(long x){
            ms.remove(x);
        }
        public long get(long x){
            return ms.get(x);
        }
        public Iterator<Long> iterator(){
            return ms.keySet().iterator();
        }
        public Set<Long> keySet(){
            return ms.keySet();
        }
        public Multiset clone() {
            HashMap<Long,Long> clone=new HashMap<>(ms);
            return new Multiset(clone);
        }
        public String toString(){
            return ms.toString();
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