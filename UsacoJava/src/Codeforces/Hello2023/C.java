package Codeforces.Hello2023;

import java.io.*;
import java.util.*;

public class C {
    static boolean debug = false;

    static int N;
    static int K; //tar
    static long[] A;
    static long[] pref;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        K = io.nextInt();
        A = new long[N+1];
        pref = new long[N+1];

        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
            pref[i]=pref[i-1]+A[i];
        }
        long delta;
        //* go from K-1..1 and minimize the ops
        int lops = 0;
        delta = 0;
        PriorityQueue<Long> pql = new PriorityQueue<>(Comparator.comparingLong(a->-a)); //lar->sm
        for (int i=K-1;i>=1;i--){
            pql.add(A[i+1]);
            while (pref[i]+delta<pref[K]){
                delta+=2*pql.poll();
                lops++;
            }
        }
        //* go from K+1..N and minimize the ops
        int rops = 0;
        delta = 0;
        PriorityQueue<Long> pqr = new PriorityQueue<>(); //sm->lar
        for (int i=K+1;i<=N;i++){
            pqr.add(A[i]);
            while (pref[i]+delta<pref[K]){
                delta-=2*pqr.poll();
                rops++;
            }
        }

        //* ret
        io.println(lops+rops);
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