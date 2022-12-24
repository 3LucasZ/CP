package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: Encryption
LANG: JAVA
*/
public class Encryption {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    static int N,k,p;
    static int[] A;

    static int INF = Integer.MAX_VALUE/2;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        k = io.nextInt();
        p = io.nextInt();
        A = new int[N+1];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt()%p;
        }

        //* dp
        int[][][] dp = new int[k+1][N+1][p];
        for (int i=0;i<=k;i++) for (int j=0;j<=N;j++) for (int k=0;k<p;k++) dp[i][j][k]=-INF;
        dp[0][0][0]=0;

        for (int s=1;s<=k;s++){
            for (int i=1;i<=N;i++){
                //new interval
                for (int b=0;b<p;b++) {
                    dp[s][i][A[i]] = Math.max(dp[s][i][A[i]],dp[s-1][i-1][b]+A[i]);
                }
                //extension
                for (int b=0;b<p;b++){
                    int preb = (b-A[i]+p)%p;
                    dp[s][i][b]=Math.max(dp[s][i][b],dp[s][i-1][preb]+b-preb);
                }
            }
        }

        //* ret
        long ans = 0;
        for (int b=0;b<p;b++){
            ans=Math.max(ans,dp[k][N][b]);
        }
        io.println(ans);
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        int[] tree;
        public SegTree(int n){
            init(n);
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new int[2*size+1];
        }
        void set(int k, int x){
            k+=size-1;
            tree[k]=Math.max(tree[k],x);
            for (k/=2;k>=1;k/=2){
                tree[k]=Math.max(tree[2*k],tree[2*k+1]);
            }
        }
        int max(int a, int b) {
            a+=size-1;
            b+=size-1;
            int ret = 0;
            while (a<=b){
                if (a%2==1) ret=Math.max(ret,tree[a++]);
                if (b%2==0) ret=Math.max(ret,tree[b--]);
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
    void close(){
        out.close();
    }
};;
}