package Codeforces.Round830;

import java.io.*;
import java.util.*;

public class SheikhHard {
    static boolean debug = false;

    static int N;
    static int Q;
    static long[] A;

    static int[] next;

    static long[] sum;
    static long[] xor;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        Q = io.nextInt();
        A = new long[N+1];
        for (int i=1;i<=N;i++) A[i]=io.nextInt();

        //* precomp
        sum = new long[N+1];
        xor = new long[N+1];
        for (int i=1;i<=N;i++) sum[i]=sum[i-1]+A[i];
        for (int i=1;i<=N;i++) xor[i]=xor[i-1]^A[i];
        next = new int[N+1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i=1;i<=N;i++){
            if (A[i]!=0) {
                while (!queue.isEmpty()) {
                    next[queue.poll()] = i;
                }
            }
            queue.add(i);
        }
        if (debug){
            io.println("A: "+Arrays.toString(A));
            io.println("sum: "+Arrays.toString(sum));
            io.println("xor: "+Arrays.toString(xor));
            io.println("next: "+Arrays.toString(next));
        }

        //* handle queries
        for (int i=0;i<Q;i++){
            handle();
        }
    }
    public static void handle(){
        int L = io.nextInt();
        int R = io.nextInt();
        int minl = 0;
        int minr = Integer.MAX_VALUE;
        long tar = f(L,R);
        for (int l=L;l!=0&&l<=R;l=next[l]){
            if (f(l,R)!=tar) break;
            int lo=l;
            int hi=R;
            while (lo<hi){
                int mid = (lo+hi)/2;
                if (f(l,mid)==tar){
                    hi=mid;
                } else {
                    lo=mid+1;
                }
            }
            if (lo-l<minr-minl) {
                minl=l;
                minr=lo;
            }
        }
        io.println(minl+" "+minr);
    }
    public static long f(int L, int R){
        return sum[R]-sum[L-1]-(xor[R]^xor[L-1]);
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