package Solutions.Codeforces.Edu126;

import java.io.*;
import java.util.*;
/*
PROB: ProgressionsCovering
LANG: JAVA
*/
public class ProgressionsCovering {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    static int N;
    static int K;
    static long[] b;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        K = io.nextInt();
        b = new long[N+1];
        for (int i=1;i<=N;i++){
            b[i]=io.nextLong();
        }

        RURS rurs = new RURS(N+1);
        long ans = 0;
        for (int r=N;r>=1;r--){
            long cur = rurs.sum(1,r);
            if (cur<b[r]){
                int l = Math.max(1,r-K+1);
                int sub = r-l+1;
                long di = b[r]-cur;
                long use = (di+sub-1)/sub;
                ans+=use;

                rurs.add(l,l+K-1,use);
            }
        }
        io.println(ans);
    }

    private static class RURS {
        //1 indexed
        //inclusive range
        //only 2 operations: range sum, range add
        BIT bit1;
        BIT bit2;
        public RURS(int K){
            bit1 = new BIT(K);
            bit2 = new BIT(K);
        }
        public long sum(int l, int r){
            long rsum = (r + 1) *bit1.preSum(r)-bit2.preSum(r);
            long lsum = l*bit1.preSum(l-1)-bit2.preSum(l-1);
            return rsum-lsum;
        }
        public void add(int l, int r, long x){
            bit1.update(l,x);bit2.update(l,l*x);
            bit1.update(r+1,-x);bit2.update(r+1,-l*x);
        }
        private static class BIT {
            //must be 1 indexed
            //range sum is inclusive
            int K;
            long[] A;
            long[] bit;

            public BIT(int K) {
                this.K = K;
                bit = new long[K + 1];
                A = new long[K + 1];
            }

            public void set(int i, long val) {
                update(i, val - A[i]);
            }

            public void update(int i, long val) {
                A[i] += val;
                while (i <= K) {
                    bit[i] += val;
                    i += i & (-i);
                    //System.out.println("STUCK IN UPDATE");
                }
            }

            public long preSum(int i) {
                long sum = 0;
                while (i > 0) {
                    sum += bit[i];
                    i -= i & (-i);
                    //System.out.println("STUCK IN PRESUM");
                }
                return sum;
            }

            public long rangeSum(int lo, int hi) {
                return preSum(hi) - preSum(lo - 1);
            }
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