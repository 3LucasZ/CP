package Codeforces.Edu139;

import java.io.*;
import java.util.*;

public class HamiltonianWall {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        boolean[][] ok = new boolean[2][N];
        for (int i=0;i<2;i++){
            String str = io.nextLine();
            for (int j=0;j<N;j++){
                ok[i][j]=str.charAt(j)=='B';
            }
        }
        if (debug){
            for (int i=0;i<2;i++){
                io.println(Arrays.toString(ok[i]));
            }
        }

        //* dp
        boolean[][] dp = new boolean[2][N];
        for (int i=0;i<2;i++) dp[i][0]=ok[i][0];
        for (int j=0;j<N;j++) for (int i=0;i<2;i++) {
            if (i==0 && ok[0][j]&&ok[1][j]&&dp[0][j]!=dp[1][j]){
                dp[0][j]=!dp[0][j];
                dp[1][j]=!dp[1][j];
            }
            if (j<N-1&&dp[i][j] && ok[i][j+1]) dp[i][j+1]=true;
        }
//        for (int i=0;i<2;i++){
//            io.println(Arrays.toString(dp[i]));
//        }

        if (dp[0][N-1]||dp[1][N-1]) io.println("YES");
        else io.println("NO");
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