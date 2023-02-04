package Other.USACO.Season2019_2020.Open2020.Plat;

import java.io.*;
import java.util.*;
/*
PROB: ReturnOfTheAlfalfa
LANG: JAVA
*/
public class ReturnOfTheAlfalfa {
    static boolean fileSubmission = true;
    static String fileName = "sprinklers2";
    
    static boolean debug = false;

    static int N;
    static boolean[][] empty;
    static long MOD = (long) (1e9+7);
    
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        empty = new boolean[N+10][N+10];
        int cnt = 0;
        for (int r=1;r<=N;r++){
            String str = io.nextLine();
            for (int c=1;c<=N;c++){
                empty[r][c]=str.charAt(c-1)=='.';
                if (empty[r][c]) cnt++;
            }
        }
        //* precomp
        long i2 = pow(2,MOD-2);
        //* dp[r][c][v/h]: if (v) then sum of ways grid[1..r][c]
        long[][][] dp = new long[N+2][N+2][2];
        // base case
        dp[1][1][1]=dp[1][1][0]=pow(2,cnt);
        // transitions
        for (int r=1;r<=N+1;r++){
            for (int c=1;c<=N+1;c++){
                if (r==1 && c==1) continue;
                //transition to the vertical
                dp[r][c][0] = dp[r-1][c][0];
                if (empty[r][c-1]) dp[r][c][0]=(dp[r][c][0]+dp[r][c-1][1]*i2)%MOD;
                //transition to the horizontal
                dp[r][c][1] = dp[r][c-1][1];
                if (empty[r-1][c]) dp[r][c][1]=(dp[r][c][1]+dp[r-1][c][0]*i2)%MOD;
            }
        }
        if (debug) {
            for (int r=1;r<=N+1;r++){
                for (int c=1;c<=N+1;c++){
                    io.print("["+dp[r][c][0]+", "+dp[r][c][1]+"] ");
                }
                io.println();
            }
        }
        // collect
        long ans = (dp[N+1][N][1]+dp[N][N+1][0])%MOD;
        io.println(ans);
    }
    public static long pow(long x, long p){
        if(x==0) return 0;
        if(p==0) return 1;
        if(p%2==1)return x*pow(x,p-1)%MOD;
        else return pow(x*x%MOD,p/2);
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
};
}