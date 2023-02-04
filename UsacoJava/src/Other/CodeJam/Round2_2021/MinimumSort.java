package Other.CodeJam.Round2_2021;

import java.io.*;
import java.util.*;

public class MinimumSort {
    static boolean debug = true;

    static int N;
    public static void solve(int tcs) throws IOException {
        //* repeat N times:
        for (int l=1;l<N;l++){
            //request and rcv min
            io.println("M "+l+" "+N);
            int min = io.nextInt();
            //swap
            if (min!=l) {
                io.println("S " + l + " " + min);
                io.nextInt();
            }
        }
        //* cleanup
        io.println("D");
        io.nextInt();
    }
    


















    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        N = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
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
    void close(){
        out.close();
    }
};;
}