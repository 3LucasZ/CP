package Solutions.Codeforces.Edu126;

import java.io.*;
import java.util.*;

public class WaterTheTrees {
    static boolean debug = false;

    static int N;
    static long[] h;
    static long ans;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        h = new long[N];
        for (int i=0;i<N;i++) h[i]=io.nextInt();
        long mx = 0; for (int i=0;i<N;i++) mx=Math.max(mx,h[i]);
        if (debug){
            io.println(Arrays.toString(h));
        }

        //* binsearch
        ans = Long.MAX_VALUE;
        for (int i=0;i<N;i++) h[i]=mx-h[i];
        binSearch();
        for (int i=0;i<N;i++) h[i]++;
        binSearch();

        //* ret
        io.println(ans);
    }
    static void binSearch(){
        long lo=0;long hi=Long.MAX_VALUE/100;
        while (lo<hi){
            long mid = (lo+hi)/2;
            if (tryOps(mid)){
                hi=mid;
            } else {
                lo=mid+1;
            }
        }
        ans=Math.min(ans,lo);
    }
    static boolean tryOps(long ops){
        long odd = (ops+1)/2;
        long even = ops/2;

        for (int i=0;i<N;i++){
            long evenOps = Math.min(h[i]/2,even);
            even-=evenOps;
            odd-=h[i]-evenOps*2;
        }

        return odd>=0;
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