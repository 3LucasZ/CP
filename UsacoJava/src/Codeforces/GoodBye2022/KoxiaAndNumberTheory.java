package Codeforces.GoodBye2022;

import java.io.*;
import java.util.*;

public class KoxiaAndNumberTheory {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        long[] A = new long[N];
        for (int i=0;i<N;i++) A[i]=io.nextLong();
        Arrays.sort(A);

        //* greedy mods
        boolean[][] B = new boolean[N+1][N+1];
        for (int i=0;i<N;i++){
            for (int j=i+1;j<N;j++){
                long k = A[j]-A[i];//difference constant
                if (k==0){
                    io.println("NO");
                    return;
                }
                if (k==1){
                    continue;
                }
                long v = A[i]; //+x variant
                for (int m=2;m<=N;m++){
                    if (k%m==0){
                        B[m][(int) (v%m)]=true;
                    }
                }
            }
        }
        if (debug){
            for (int i=2;i<=N;i++){
                for (int j=0;j<i;j++){
                    io.print(B[i][j]+" ");
                }
                io.println();
            }
        }

        //* ret
        for (int i=2;i<=N;i++){
            boolean bad = true;
            for (int j=0;j<i;j++){
                if (!B[i][j]) bad = false;
            }
            if (bad){
                io.println("NO");
                return;
            }
        }
        io.println("YES");
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