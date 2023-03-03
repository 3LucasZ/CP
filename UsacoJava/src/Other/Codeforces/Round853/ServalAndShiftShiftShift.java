package Other.Codeforces.Round853;

import java.io.*;
import java.util.*;

public class ServalAndShiftShiftShift {
    static boolean debug = false;

    static int N;
    static int[] A;
    static int[] B;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        String strA = io.nextLine();
        A = new int[N];
        for (int i=0;i<N;i++) A[i]=strA.charAt(i)-'0';
        String strB = io.nextLine();
        B = new int[N];
        for (int i=0;i<N;i++) B[i]=strB.charAt(i)-'0';
        //* edge case
        if (Arrays.equals(A,B)) {
            io.println(0);
            return;
        }
        if (lb(A)==-1) {
            io.println(-1);
            return;
        }
        if (lb(B)==-1) {
            io.println(-1);
            return;
        }
        ArrayList<Integer> ops = new ArrayList<>();
        if (hb(A)<lb(B)){
            int shift = -(lb(B)-hb(A));
            ops.add(shift);
            shift(shift);
        }
        for (int i=lb(B)-1;i>=0;i--){
            if (A[i]==1){
                int shift = (hb(A)-i);
                ops.add(shift);
                shift(shift);
            }
        }
        if (lb(A)>lb(B)){
            int shift = (lb(A)-lb(B));
            ops.add(shift);
            shift(shift);
        }
        for (int i=lb(B)+1;i<N;i++){
            if (A[i]!=B[i]){
                int shift = -(i-lb(A));
                shift(shift);
                ops.add(shift);
            }
        }

        //* ret
        io.println(ops.size());
        if (ops.size()>0){
            for (int i : ops)io.print(i+" ");
            io.println();
        }
    }
    static void shift(int shift){
        int[] Axor = new int[N];
        for(int j=0;j<N;j++){
            if(j-shift<0 || j-shift>=N) continue;
            Axor[j-shift]=A[j];
        }
        for (int j=0;j<N;j++){
            A[j]=A[j]^Axor[j];
        }
    }
    static int lb(int[] arr) {
        for (int i=0;i<N;i++) if (arr[i]==1) return i;
        return -1;
    }
    static int hb(int[] arr){
        for (int i=N-1;i>=0;i--) if (arr[i]==1) return i;
        return -1;
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