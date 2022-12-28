package Codeforces.Round841;

import java.io.*;
import java.util.*;

public class ValiantsNewMap {
    static boolean debug = false;

    static int R;
    static int C;
    static int[][] h;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        R = io.nextInt();
        C = io.nextInt();
        h = new int[R+1][C+1];
        for (int r=1;r<=R;r++){
            for (int c=1;c<=C;c++){
                h[r][c]=io.nextInt();
            }
        }

        //* binary search on K
        int lo = 1;
        int hi = Math.min(R,C);
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (tryK(mid)){
                lo=mid;
            } else {
                hi=mid-1;
            }
        }

        //* ret
        io.println(lo);
    }

    public static boolean tryK(int K){
        //compute presums
        int[][] bad = new int[R+1][C+1];
        for (int r=1;r<=R;r++){
            for (int c=1;c<=C;c++){
                bad[r][c]=bad[r-1][c]+bad[r][c-1]-bad[r-1][c-1];
                if (h[r][c]<K) bad[r][c]++;
            }
        }

        //search every square
        for (int r=1;r<=R;r++){
            for (int c=1;c<=C;c++){
                int r2 = r+K-1;
                int c2 = c+K-1;
                if (r2>R || c2>C) continue;
                int sum = bad[r2][c2]-bad[r-1][c2]-bad[r2][c-1]+bad[r-1][c-1];
                if (sum==0) return true;
            }
        }
        return false;
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