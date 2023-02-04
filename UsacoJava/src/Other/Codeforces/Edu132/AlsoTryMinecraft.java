package Other.Codeforces.Edu132;

import java.io.*;
import java.util.*;
/*
PROB: AlsoTryMinecraft
LANG: JAVA
*/
public class AlsoTryMinecraft {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;
    
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        int M = io.nextInt();
        long[] A = new long[N+2];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }

        //* fw presum
        long[] fw = new long[N+1];
        for (int i=1;i<=N;i++){
            fw[i]=fw[i-1]+Math.max(0,A[i-1]-A[i]);
        }
        //* bw presum
        long[] bw = new long[N+2];
        for (int i=N;i>=1;i--){
            bw[i]=bw[i+1]+Math.max(0,A[i+1]-A[i]);
        }

        //* query
        for (int i=1;i<=M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            if (v>u){
                io.println(fw[v]-fw[u]);
            } else {
                io.println(bw[v]-bw[u]);
            }
        }
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