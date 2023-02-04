package Other.Codeforces.Round843;

import java.io.*;
import java.util.*;

public class LaboratoryOnPluto {
    static boolean debug = false;

    //const
    static int MAXN = 400000;
    static long MOD;

    //given
    static int U;
    static int N;

    //manip
    static int rt;
    static int P;
    static int a;

    //pre
    static long[][][] dp;

    static void precomp(){
        a =(int)(Math.sqrt(MAXN)+5);
        dp = new long[5][a+1][a+1];
        dp[0][0][a]=1;
        for (int i=0;i<4;i++){
            for (int j=0;j<=a;j++){
                for (int was=a;was>=1;was--){
                    if ((j+was)<=a){
                        dp[i][j+was][was]=(dp[i][j+was][was]+dp[i][j][was])%MOD;
                    }
                    dp[i][j][was-1]=(dp[i][j][was-1]+dp[i][j][was])%MOD;
                }
                dp[i+1][j][a]=(dp[i+1][j][a]+dp[i][j][0])%MOD;
            }
        }
    }
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        rt=(int)Math.ceil(Math.sqrt(N));
        P = getP(rt);
        if (U==1) solve1();
        else solve2();
    }
    static void solve1(){
        int l = rt;
        int w = getW(l);
        io.println(l+" "+w);
        for (int r=0;r<l;r++){
            for (int c=0;c<w;c++){
                if (N>0){
                    N--;
                    io.print("#");
                } else {
                    io.print(".");
                }
            }
            io.println();
        }
    }
    static void solve2(){
        long ret = 0;
        int l = rt;
        while (l>=1){
            int w = getW(l);
            int p = (l+w)*2;
            if (p>P) break;
            if (debug){
                io.println("l: "+l+", w: "+w+", p: "+p);
            }
            ret=(ret+dp[4][l*w-N][a])%MOD;
            l--;
        }
        l = rt+1;
        while (l<=N){
            int w = getW(l);
            int p = (l+w)*2;
            if (p>P) break;
            if (debug){
                io.println("l: "+l+", w: "+w+", p: "+p);
            }
            ret=(ret+dp[4][l*w-N][a])%MOD;
            l++;
        }
        io.println(P+" "+ret);
    }
    static int getP(int l){
        return 2*(l+getW(l));
    }
    static int getW(int l){
        return (N+l-1)/l;
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt(); U = io.nextInt();
        if (U==2) {
            MOD=io.nextInt();
            precomp();
        }
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