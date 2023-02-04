package Other.Codeforces.Edu141;

import java.io.*;
import java.util.*;
/*
PROB: D
LANG: JAVA
*/
public class D {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] A;
    static long MOD = 998244353;
    static int offset = 100000;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new int[N];
        for (int i=0;i<N;i++) A[i]=io.nextInt();
        if (debug){
            io.println("A:"+Arrays.toString(A));
        }
        //* dp
        long[][] dp = new long[N][2*offset];
        dp[1][offset+A[1]]=1;
        for (int i=1;i<=N-2;i++){
            for (int val = -offset+500;val<offset-500;val++){ //not offset
                int up = A[i+1]+val+offset; //offset
                int down = A[i+1]-val+offset; //offset
                dp[i+1][up]=(dp[i+1][up]+dp[i][val+offset])%MOD;
                if (up!=down){
                    dp[i+1][down]=(dp[i+1][down]+dp[i][val+offset])%MOD;
                }
            }
        }
        if (debug) for (int i=0;i<N;i++){
            io.println(Arrays.toString(dp[i]));
        }

        //* ret
        long ans = 0;
        for (int i=0;i<2*offset;i++){
            ans=(ans+dp[N-1][i])%MOD;
        }
        io.println(ans);
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