package Other.Codeforces.Round841;

import java.io.*;
import java.util.*;

public class GraphCost {
    static boolean debug = false;

    static int n;
    static long m;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        n = io.nextInt();
        m = io.nextLong();

        //* freq of edge weight arr
        long[] freq = new long[n+1];
        for (int div=2;div<=n;div++){
            int mx = n/div;
            freq[div]=(long) mx*(mx-1)/2;
        }
        if (debug) io.println(Arrays.toString(freq));
        for (int i=n;i>=2;i--){
            for (int mult=2;i*mult<=n;mult++){
                freq[i]-=freq[i*mult];
            }
        }
        if (debug) io.println(Arrays.toString(freq));

        //* greedy grab
        long totCost = 0;
        long totEdges = 0;
        for (int i=n;i>=2;i--){
            long bundles = Math.min((m-totEdges)/(i-1),freq[i]/(i-1));
            long edges = bundles*(i-1);
            long cost = bundles*i;
            totCost+=cost;
            totEdges+=edges;
        }

        //* ret
        if (totEdges<m) io.println(-1);
        else io.println(totCost);
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