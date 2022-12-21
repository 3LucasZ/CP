package Codeforces.Edu140;

import java.io.*;
import java.util.*;
/*
PROB: CountBinaryStrings
LANG: JAVA
*/
public class CountBinaryStrings {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;
    static int N;
    static int[][] A;
    static long MOD = 998244353;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new int[N+1][N+1];
        for (int i=1;i<=N;i++){
            for (int j=i;j<=N;j++){
                A[i][j]=io.nextInt();
            }
        }
        if (debug) Printer.print(A);
        //* dp
        long[][] dp = new long[N+1][N+1];
        //base
        if (A[1][1]!=2)dp[1][0]=2;
        //transition
        for (int i=1;i<N;i++){
            for (int j=0;j<N;j++){
                //case 1: add a "same"
                if (judge(i+1,j)) dp[i+1][j]=(dp[i+1][j]+dp[i][j])%MOD;
                //case 2: add a "diff"
                if (judge(i+1,i)) dp[i+1][i]=(dp[i+1][i]+dp[i][j])%MOD;
            }
        }
        //* ret
        long ans = 0;
        for (int j=0;j<N;j++) ans=(ans+dp[N][j])%MOD;
        io.println(ans);
    }
    public static boolean judge(int i, int j){
        for (int k=1;k<=i;k++){
            if (A[k][i]==1){
                if (j>=k) return false;
            }
            else if (A[k][i]==2){
                if (j<k) return false;
            }
        }
        return true;
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
    }
    private static class Printer {
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
        public static void print(boolean[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    System.out.print(arr[r][c] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(long[][] arr) {
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
        public static void print(double[][] arr) {
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
        public static void print(char[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    System.out.print(arr[r][c]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(String[][] arr) {
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
    }
}