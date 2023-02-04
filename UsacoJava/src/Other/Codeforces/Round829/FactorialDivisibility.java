package Other.Codeforces.Round829;

import java.io.*;
import java.util.*;
/*
PROB: FactorialDivisibility
LANG: JAVA
*/
public class FactorialDivisibility {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int n;
    static int x;
    static int[]freq;
    public static void solve() throws IOException {
        //* parse
        n = io.nextInt();
        x = io.nextInt();
        freq = new int[x+1];
        for (int i=0;i<n;i++){
            freq[io.nextInt()]++;
        }
        if (debug) io.println(Arrays.toString(freq));

        //* very greedy
        for (int i=1;i<x;i++){
            if (debug) io.println(Arrays.toString(freq));
            if (freq[i]%(i+1)==0){
                freq[i+1]+=freq[i]/(i+1);
            } else {
                io.println("No");
                return;
            }
        }

        //*ret
        if (freq[x]>0){
            io.println("Yes");
        } else {
            io.println("No");
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