package Other.USACO.Season2022_2023.Silver;

import java.io.*;
import java.util.*;
/*
PROB: RangeReconstruction
LANG: JAVA
*/
public class RangeReconstruction {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;
    
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        int[][] r = new int[N][N];
        for (int i=0;i<N;i++){
            for (int j=i;j<N;j++){
                r[i][j]=io.nextInt();
            }
        }
        int[] a = new int[N];
        boolean[] added = new boolean[N];
        a[0]=0;
        added[0]=true;
        if (N>1) {
            a[1] = a[0] + r[0][1];
        }
        for (int i=2;i<N;i++){
            int max = a[i-1]+r[i-1][i];
            int min = a[i-1]-r[i-1][i];

            a[i]=max;
            boolean good = true;
            for (int l=0;l<=i-2;l++){
                int amin = Integer.MAX_VALUE;
                int amax = Integer.MIN_VALUE;
                for (int j=l;j<=i;j++){
                    amin=Math.min(amin,a[j]);
                    amax=Math.max(amax,a[j]);
                }
                if (amax-amin!=r[l][i])good=false;
            }

            if (!good) a[i]=min;
        }
        //* ret
        for (int i=0;i<N-1;i++) io.print(a[i]+" ");
        io.print(a[N-1]);
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