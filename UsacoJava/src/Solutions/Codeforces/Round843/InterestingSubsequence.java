package Solutions.Codeforces.Round843;

import java.io.*;
import java.util.*;

public class InterestingSubsequence {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        long N = io.nextLong();
        long X = io.nextLong();

        //* if N = X
        if (N==X) {
            io.println(N);
            return;
        }

        //* [0 1] case
        int max = 60;
        for (int i=0;i<max;i++){
            long bit = 1L<<i;
            if ((N&bit)==0 && (X&bit)!=0) {
                io.println(-1);
                if (debug){
                    io.println("ERROR 1 1");
                }
                return;
            }
        }

        //* find r of [1 1] and l of [1 0] make sure r<l-1
        int r = max;
        for (;r>=0;r--){
            long bit = 1L<<r;
            if ((N&bit)!=0 && (X&bit)==0) break;
        }
        int l = 0;
        for (;l<=max;l++){
            long bit = 1L<<l;
            if ((N&bit)!=0 && (X&bit)!=0) break;
        }
        if (debug){
            io.println("l: "+l+", r: "+r);
        }
        if (r>=l-1 && l!=-1){
            io.println(-1);
            return;
        }

        //* ret the answer
        N=N>>(r+1);
        N++;
        N=N<<(r+1);
        io.println(N);
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