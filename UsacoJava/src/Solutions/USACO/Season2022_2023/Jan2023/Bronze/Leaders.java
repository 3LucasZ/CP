package Solutions.USACO.Season2022_2023.Jan2023.Bronze;

import java.io.*;
import java.util.*;
/*
PROB: Leaders
LANG: JAVA
*/
public class Leaders {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;
    
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        char[] breed = new char[N+1];
        String str = io.next();
        for (int i=1;i<=N;i++){
            breed[i]=str.charAt(i-1);
        }
        int[] r = new int[N+1];
        for (int i=1;i<=N;i++){
            r[i]=io.nextInt();
        }

        //* find the H leader, G leader
        int hI = N;
        int hF = 0;
        int gI = N;
        int gF = 0;
        for (int i=1;i<=N;i++){
            if (breed[i]=='H'){
                hI=Math.min(hI,i);
                hF=Math.max(hF,i);
            }else {
                gI = Math.min(gI,i);
                gF = Math.max(gF,i);
            }
        }
        boolean h = false;
        boolean g = false;
        if (r[hI]>=hF) h=true;
        if (r[gI]>=gF) g=true;
        if(debug){
            io.println("h:"+h);
            io.println("g:"+g);
        }

        //* run tests for other g and h candidates
        int ans = 0; if (h&&g) ans++;
        for (int i=1;i<=N;i++){
            if (!(i==gI && g) && h && breed[i]=='G' && i<= hI && r[i]>=hI) ans++;
            if (!(i==hI && h) && g&& breed[i]=='H' && i<= gI && r[i]>=gI) ans++;
        }

        //* ret
        io.println(ans);
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