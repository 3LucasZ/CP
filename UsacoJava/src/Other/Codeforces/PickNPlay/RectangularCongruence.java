package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: RectangularCongruence
LANG: JAVA
*/
public class RectangularCongruence {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] B;
    static int[][] A;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        B = new int[N];
        for (int i=0;i<N;i++) B[i]=io.nextInt();

        //* construct A
        A = new int[N][N];
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
               A[r][c]=r*c%N;
            }
        }
        if (debug){
            print(A);
        }

        //* final construct A
        for (int i=0;i<N;i++){
            int dif = -A[i][i]+B[i];
            upd(A,i,dif);
        }
        print(A);
    }
    static void upd(int[][] arr, int row, int num){
        for (int c=0;c<N;c++){
            arr[row][c]+=num;
            if (arr[row][c]<0) arr[row][c]+=N;
            if (arr[row][c]>=N) arr[row][c]-=N;
        }
    }
    static void print(int[][] arr){
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                io.print(arr[r][c]+" ");
            }
            io.println();
        }
    }
    private static class Printer {
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
        public static void print(boolean[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    System.out.print(arr[r][c] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(long[][] arr) {
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
        public static void print(double[][] arr) {
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
        public static void print(char[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    System.out.print(arr[r][c]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(String[][] arr) {
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