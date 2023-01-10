package Codeforces.Edu136;

import java.io.*;
import java.util.*;
/*
PROB: CleaningRobot
LANG: JAVA
*/
public class CleaningRobot {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    static int N;
    static int[][] clean; //1 = clean, 0 = dirty

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        clean = new int[2][N];
        for (int r=0;r<2;r++){
            String str = io.nextLine();
            for (int c=0;c<N;c++){
                clean[r][c]=1-(str.charAt(c)-'0');
            }
        }

        //* dp
        int[][][] dp = new int[2][N][2]; //max robot has eaten
        for (int i=0;i<2;i++) for (int j=0;j<N;j++) for (int k=0;k<2;k++) dp[i][j][k]=Integer.MIN_VALUE/2;
        //base case
        dp[0][0][clean[1][0]]=0;
        //transitions
        for (int c=0;c<N;c++){
            //transition: top is dirty
            for (int r=0;r<2;r++){
                //right is clean
                if (c+1==N || clean[r][c+1]==1){
                    //vis top
                    upd(dp,1-r,c,1,dp[r][c][0]+1);
                    //block top: vis right
                    if (c+1<N) upd(dp,r,c+1,clean[1-r][c+1],dp[r][c][0]);
                }
                //in front is dirty so conflict
                else {
                    //block right: vis top vis right
                    if (c+1<N)upd(dp,1-r,c+1,1,dp[r][c][0]+1+1-clean[1-r][c+1]);
                    //skip top + vis right
                    if (c+1<N)upd(dp,r,c+1,clean[1-r][c+1],dp[r][c][0]+1);
                }
            }
            //transition: top is clean
            for (int r=0;r<2;r++){
                if (c+1<N)upd(dp,r,c+1,clean[1-r][c+1],dp[r][c][1]+1-clean[r][c+1]);
            }
        }

        //* ret
        int ans = Math.max(dp[0][N-1][1],dp[1][N-1][1]);
        io.println(ans);
    }
    static void upd(int[][][] dp, int i, int j, int k, int x){
        dp[i][j][k]=Math.max(dp[i][j][k],x);
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