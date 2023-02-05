package Other.Codeforces.Round804;

import java.io.*;
import java.util.*;

public class AlmostTripleDeletions {
    static boolean debug = false;

    static int N;
    static int[] A;
    static boolean[][] rem;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        for (int i=1;i<=N;i++)A[i]=io.nextInt();

        //* precomp rem[l][r]
        rem = new boolean[N+1][N+1];
        for (int l=0;l<N;l++){
            int[] freq = new int[N+1];
            int max = 0;
            for (int r=l;r<=N;r++){
                freq[A[r]]++;
                max=Math.max(max,freq[A[r]]);
                if ((r-l+1)%2==0 && max<=(r-l+1)/2){
                    rem[l][r]=true;
                };
            }
        }

        //* dp
        int[] dp = new int[N+1];
        //base cases
        for (int i=1;i<=N;i++){
            if (rem(1,i-1)) dp[i]=1;
        }
        //transitions from j
        for (int i=1;i<=N;i++){
            for (int j=1;j<i;j++){
                if (rem(j+1,i-1) &&A[i]==A[j]&&dp[j]!=0){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }

        //* ret
        int ans = 0;
        for (int i=1;i<=N;i++){
            if (rem(i+1,N)) ans=Math.max(ans,dp[i]);
        }
        io.println(ans);
    }
    static boolean rem(int l, int r){
        if (r<l) return true;
        else return rem[l][r];
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