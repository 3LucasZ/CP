package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class AnotherArrayProblem {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* pars
        int N = io.nextInt();
        long[] A = new long[N];
        for (int i=0;i<N;i++) A[i]=io.nextInt();
        //* casework
        //n is 3
        if (N==3) {
            long l = Math.abs(A[0]-A[1]);
            long r = Math.abs(A[1]-A[2]);
            long t = Math.abs(A[0]-A[2]);

            long a = 3*A[0];
            long b = 3*A[2];
            long c = 3*t;

            long d = 2*l+A[2];
            long e = 2*r+A[0];
            long f = l+Math.abs(A[2]-l)*2;
            long g = r+Math.abs(A[0]-r)*2;
            long h = l*3;
            long i = r*3;

            long z = A[0]+A[1]+A[2];

            long ans = 0;
            ans=Math.max(ans,a);
            ans=Math.max(ans,b);
            ans=Math.max(ans,c);
            ans=Math.max(ans,d);
            ans=Math.max(ans,e);
            ans=Math.max(ans,f);
            ans=Math.max(ans,g);
            ans=Math.max(ans,h);
            ans=Math.max(ans,i);
            ans=Math.max(ans,z);

            io.println(ans);
            return;
        }
        //n is 2
        if (N==2) {
            long ans = Math.max(A[0]+A[1],Math.abs(A[0]-A[1])*2);
            io.println(ans);
            return;
        }
        //else
        long mx = 0;
        for (int i=0;i<N;i++){
            mx=Math.max(mx,A[i]);
        }
        io.println(mx*N);
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
    void close(){
        out.close();
    }
};}