package Other.Codeforces.Edu121;

import java.io.*;
import java.util.*;

public class MartialArtsTournament {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        Integer[] A = new Integer[N+1]; A[0]=0;
        for (int i=1;i<=N;i++) A[i]=io.nextInt();
        Arrays.sort(A);
        if (debug){
            io.println("A:"+Arrays.toString(A));
        }

        //* precompute:
        // last[] is the last legal cut
        int[] last = new int[N+1];
        for (int i=1;i<=N;i++){
            if (i==N || A[i]!=A[i+1]) last[i]=i;
            else last[i]=last[i-1];
        }
        if (debug){
            io.println("last:"+Arrays.toString(last));
        }

        //* brute force:
        // len1 is the len 1st seg
        // len2 is the len 2nd seg
        int ans = Integer.MAX_VALUE;
        for (int len1=0;len1<=N;len1++){
            if (len1!=N &&Objects.equals(A[len1],A[len1+1])) continue;
            for (int range=1;range+len1<=N;range*=2){
                int len2 = last[len1+range]-len1;
                int dl = cost(len1);
                int dm = cost(len2);
                int dr = cost(N-len1-len2);
                ans=Math.min(ans,dl+dm+dr);
            }
        }

        //* ret
        io.println(ans);
    }
    static int cost(int i){
        int pow = 1;
        while (pow<i) pow*=2;
        return pow-i;
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