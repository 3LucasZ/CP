import java.io.*;
import java.util.*;

public class BlockingChips {
    static boolean debug = false;

    static int N;
    static ArrayList<Integer>[] tree;
    static int K;
    static boolean[] black;
    static int[] id;


    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        black = new boolean[N+1];
        id = new int[N+1];
        K = io.nextInt();
        for (int i=0;i<K;i++){
            int u = io.nextInt();
            black[u]=true;
            id[u]=i+1;
        }
        if (debug){
            io.println("id:"+Arrays.toString(id));
        }
        //* BS
        int lo=0;
        int hi=N;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (tryMoves(mid)){
                lo=mid;
            } else {
                hi=mid-1;
            }
        }
        //* ret
        io.println(lo);
    }
    static boolean tryMoves(int mvs){
        if (debug){
            io.println("mvs:"+mvs);
        }
        ok=true;
        dp = new int[N+1];
        DFS(1,0,mvs);
        boolean res = dp[1]>=0&&ok;
        if (debug){
            io.println("dp:"+Arrays.toString(dp));
            io.println("res:"+res);
        }
        return res;
    }

    static int[] dp;
    static boolean ok;
    static void DFS(int node, int par, int mvs){
        int badChildren = 0;
        int mostFreedom = 0;
        int leastFreedom = 0;

        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node,mvs);
            if (dp[child]<0) badChildren++;
            mostFreedom=Math.max(mostFreedom,dp[child]);
            leastFreedom=Math.min(leastFreedom,dp[child]);
        }

        if (black[node]){
            if (badChildren > 0) {
                ok=false;
            }
            int mv = mvs/K + ((mvs%K>=id[node])?1:0);
            if (debug){
                io.println("node:"+node+" mv:"+mv);
            }
            if (mv > mostFreedom) {
                dp[node]=-mv;
            } else{
                dp[node]=0;
            }
        }
        //white node
        else {
            if (badChildren > 1) ok=false;
            if (badChildren == 1) {
                if (mostFreedom+1+leastFreedom>=0) dp[node]=0;
                else {
                    dp[node]=leastFreedom+1;
                }
            } else {
                dp[node]=mostFreedom+1;
            }
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
    void print2d(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
           println();
        }
        println();
    }
    void print2d(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void print2d(boolean[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + (arr[r][c]?"1":"0");
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void printBin(int bin,int len){
        String ret = "";
        for (int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        println(ret);
    }
    void close(){
        out.close();
    }
}}