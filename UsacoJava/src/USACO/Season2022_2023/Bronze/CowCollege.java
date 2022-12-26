package USACO.Season2022_2023.Bronze;

import java.io.*;
import java.util.*;
/*
PROB: CowCollege
LANG: JAVA
*/
public class CowCollege {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    static int N;
    static Integer[] A;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new Integer[N];
        for (int i=0;i<N;i++) A[i] = io.nextInt();
        Arrays.sort(A);
        long min =A[N-1];
        long ans = 0;
        long tuition = 0;
        for (int i=N-1;i>=0;i--){
            min=Math.min(min,A[i]);
            long cost = min*(N-i);
            if (cost>ans){
                ans=cost;
                tuition=min;
            }
            else if (cost==ans){
                tuition=Math.min(tuition,min);
            }
        }
        io.println(ans+" "+tuition);
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
    void close(){
        out.close();
    }
};;
}