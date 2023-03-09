package Solutions.Codeforces.Edu135;

import java.io.*;
import java.util.*;

public class LetterPicking {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        String str = io.nextLine();
        int N = str.length();
        char[] A = new char[N];
        for (int i=0;i<N;i++){
            A[i]=str.charAt(i);
        }
        if (debug) io.println("A: "+Arrays.toString(A));

        //* dp[l,r)
        int[][] dp = new int[N+1][N+1];
        for (int l=N-1;l>=0;l--){
            //base case empty string draw
            dp[l][l]=0;
            //try all right endpoints (len is even since Alice goes first) and get state from inner states
            for (int r=l+2;r<=N;r+=2){
                dp[l][r]=-1;
                //alice takes l
                //case lr
                if (dp[l+1][r-1]<dp[l+2][r]) {
                    if (debug) io.println("lr");
                    if (dp[l+1][r-1]!=0) {
                        dp[l][r] = Math.max(dp[l][r], dp[l+1][r-1]);
                    } else {
                        dp[l][r] = Math.max(dp[l][r], comp(A[l],A[r-1]));
                    }
                }
                //case ll
                else {
                    if (debug) io.println("ll");
                    if (dp[l+2][r] != 0) {
                        dp[l][r] = Math.max(dp[l][r], dp[l+2][r]);
                    } else {
                        dp[l][r] = Math.max(dp[l][r], comp(A[l],A[l+1]));
                    }
                }
                //alice takes r, bob takes best remaining
                //case rl
                if (dp[l+1][r-1]<dp[l][r-2]) {
                    if (debug) io.println("rl");
                    if (dp[l+1][r-1] != 0) {
                        dp[l][r] = Math.max(dp[l][r], dp[l+1][r-1]);
                    } else {
                        dp[l][r] = Math.max(dp[l][r], comp(A[r-1],A[l]));
                    }
                }
                //case rr
                else{
                    if (debug) io.println("rr");
                    if(dp[l][r-2]!=0){
                        dp[l][r]=Math.max(dp[l][r],dp[l][r-2]);
                    }else{
                        dp[l][r]=Math.max(dp[l][r],comp(A[r-1],A[r-2]));
                    }
                }
                if(debug){
                    io.println("dp["+l+"]["+r+"] = "+dp[l][r]);
                }
            }
        }

        //* ret
        if (dp[0][N]==0) io.println("Draw");
        else if (dp[0][N]==-1) io.println("Bob");
        else io.println("Alice");

    }
    static int comp(char a, char b){
        if (a==b) return 0;
        if (a<b) return 1;
        return -1;
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