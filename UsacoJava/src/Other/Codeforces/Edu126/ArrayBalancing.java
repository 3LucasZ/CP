package Other.Codeforces.Edu126;

import java.io.*;
import java.util.*;

public class ArrayBalancing {
    static boolean debug = false;

    static int N;
    static long[] A;
    static long[] B;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new long[N];
        B = new long[N];
        for (int i=0;i<N;i++) A[i]=io.nextInt();
        for (int i=0;i<N;i++) B[i]=io.nextInt();

        //* swap 1..N-1 which one is optimal
        long ans = 0;
        for (int i=1;i<N;i++){
            long sum1 = Math.abs(A[i]-A[i-1])+Math.abs(B[i]-B[i-1]);
            swap(i);
            long sum2 = Math.abs(A[i]-A[i-1])+Math.abs(B[i]-B[i-1]);
            swap(i);
            ans+=Math.min(sum1,sum2);
        }

        //* ret
        io.println(ans);
    }
    static void swap(int i){
        long tmp = B[i];
        B[i]=A[i];
        A[i]=tmp;
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