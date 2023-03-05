import java.io.*;
import java.util.*;

public class MaximumSubarray {
    static boolean debug = true;

    static int N;
    static int K;
    static long X;

    static long[] A;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        K = io.nextInt();
        X = io.nextInt();
        A = new long[N+1];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }
        //* dp
        long[][] dp = new long[N+1][K+1];
        dp[0][0]=0;
        for (int i=0;i<N;i++){
            for (int k=0;k<=Math.min(i,K);k++){
                dp[i+1][k]=Math.max(dp[i][k]+A[i+1]-X,dp[i+1][k]);
                if (k<=K-1) dp[i+1][k+1]=Math.max(dp[i][k]+A[i+1]+X,dp[i+1][k+1]);
            }
        }
        if (debug){
            io.print2d(dp);
        }

        //* ret
        long ans = 0;
        for (int i=1;i<=N;i++) ans=Math.max(ans,dp[i][K]);
        io.println(ans);
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
    void print2d(long[][] arr) {
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
    void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
    void close(){
        out.close();
    }

    }}