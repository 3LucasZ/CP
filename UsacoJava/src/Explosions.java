import java.io.*;
import java.util.*;

public class Explosions {
    static boolean debug = false;

    static int N;
    static long[] h;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        h = new long[N+2];
        for (int i=1;i<=N;i++) h[i]=io.nextInt();
        //* find ldp
        long[] ldp;
        ldp = dp();
        if (debug){
            io.println("ldp:"+Arrays.toString(ldp));
        }
        reverse(h);
        //* find rdp
        long[] rdp;
        rdp = dp();
        reverse(rdp);
        if (debug) {
            io.println("rdp:"+Arrays.toString(rdp));
        }
        reverse(h);
        //* get min
        long ans = Long.MAX_VALUE;
        for (int i=1;i<=N;i++){
            ans=Math.min(ans,ldp[i]+rdp[i]+h[i]);
        }

        //* ret
        io.println(ans);
    }
    static long[] dp(){
        if (debug) io.println("h:"+Arrays.toString(h));
        long[] pre = new long[N+2]; for (int i=1;i<=N;i++) pre[i]=pre[i-1]+h[i];
        SegTree seg = new SegTree(N);
        long[] dp = new long[N+2];
        dp[0]=0;
        for (int i=1;i<=N;i++){
            if (h[i]>h[i-1]) dp[i]=dp[i-1];
            else {
                int j = seg.lastLessThan(h[i]-i);
                int l = Math.max(0,i-(int)h[i]);
                if (j>=l){
                    dp[i]=dp[j]+pre[i]-pre[j]-h[i]*(i-j)+sum(i-j-1);
                } else {
                    dp[i]=pre[l]+pre[i]-pre[l]-h[i]*(i-l)+sum(i-l-1);
                }
            }
            seg.set(i,h[i]-i);
        }
        return dp;
    }
    static long sum(long i){
        return (i+1)*i/2;
    }
    static void reverse(long[] arr){
        for (int i=0;i<arr.length/2;i++){
            long tmp = arr[i];
            arr[i]=arr[arr.length-i-1];
            arr[arr.length-i-1]=tmp;
        }
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;

        public SegTree(int n) {
            init(n);
        }

        public void init(int n) {
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2 * size + 1];
            Arrays.fill(tree,Integer.MAX_VALUE);
        }

        void set(int k, long x) {
            k += size - 1;
            tree[k] = x;
            for (k /= 2; k >= 1; k /= 2) {
                tree[k] = Math.min(tree[2 * k], tree[2 * k + 1]);
            }
        }

        int lastLessThan(long tar) {
            return search(tar, 1);
        }

        int search(long tar, int x) {
            //tar not in x range
            if (tar < tree[x]) return 0;
            //leaf case
            if (x >= size) return x - size + 1;
            //try rx
            int r = search(tar, 2 * x + 1);
            if (r != 0) return r;
            //try lx
            int l = search(tar, 2 * x);
            return l;
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