package Solutions.Q;

import java.io.*;
import java.util.*;

public class Bakery {
    static boolean debug = false;

    static int N;
    static long tc;
    static long tm;

    static long[] a;
    static long[] b;
    static long[] c;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        tc = io.nextInt();
        tm = io.nextInt();
        a = new long[N];b = new long[N];c = new long[N];
        for (int i=0;i<N;i++){
            a[i]=io.nextInt();
            b[i]=io.nextInt();
            c[i]=tc*a[i]+tm*b[i]-io.nextLong();
        }
        if (debug){
            io.println("a:"+Arrays.toString(a));
            io.println("b:"+Arrays.toString(b));
            io.println("c:"+Arrays.toString(c));
        }
        //* brute force
        ans = Long.MAX_VALUE;
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if (i==j) continue;
                double x = (double)(c[j]*b[i]-c[i]*b[j])/(a[j]*b[i]-a[i]*b[j]);
                long xl =(long)Math.floor(x);
                updateX(xl);
                long xr =(long)Math.ceil(x);
                updateX(xr);
                updateX(xl-1);
            }
        }
        updateX(0);
        updateX(tc-1);

        //* ret
        io.println(ans);
    }
    static long ans;
    static void updateX(long x){
        if (x<0 || x>=tc) return;
        long ret = Integer.MIN_VALUE;
        for (int i=0;i<N;i++){
            ret=Math.max(ret,(c[i]-a[i]*x+b[i]-1)/b[i]);
        }
        if (ret<0 || ret>=tm) return;
        if (debug){
            io.println("x:"+x);
            io.println("y:"+ret);
        }
        ans=Math.min(ans,x+ret);
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
    void print2d(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
           println();
        }
        println();
    }
    void print2d(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void print2d(boolean[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + (arr[r][c]?"1":"0");
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void printBin(int bin,int len){
        String ret = "";
        for (int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        println(ret);
    }
    void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
    void close(){
        out.close();
    }
}}