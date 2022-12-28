package USACO.Season2022_2023.Silver;

import java.io.*;
import java.util.*;

public class CircularBarn {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        int[] A = new int[N];
        for (int i=0;i<N;i++) A[i]=io.nextInt();
        int wMin = -1;
        int lMin = -1;
        for (int i=0;i<N;i++){
            if (table[A[i]]%2==0 && (lMin==-1 || table[A[i]]<table[A[lMin]])){
                lMin = i;
            }
            else if (table[A[i]]%2==1 && (wMin==-1 || table[A[i]]<table[A[wMin]])){
                wMin = i;
            }
        }
        if (wMin == -1){
            io.println("Farmer Nhoj");
        } else if (lMin == -1){
            io.println("Farmer John");
        }
        else if (table[A[wMin]]/2<table[A[lMin]]/2){
            io.println("Farmer John");
        } else if (table[A[wMin]]/2>table[A[lMin]]/2){
            io.println("Farmer Nhoj");
        } else {
            if (wMin<lMin){
                io.println("Farmer John");
            } else {
                io.println("Farmer Nhoj");
            }
        }
    }




















    static IO io;
    static int n = (int) (5e6);
    //static int n = 500;
    static int[] table;
    public static void main(String[] args) throws IOException {
        //precomp truth table
        boolean[] prime = new boolean[n+1]; for (int i=1;i<=n;i++) prime[i]=true;
        for (int p=2;p*p<=n;p++){
            if (prime[p]){
                for (int i=p*p;i<=n;i+=p){
                    prime[i]=false;
                }
            }
        }
        table = new int[n+1];
        for (int i=1;i<=n;i++){
            if(i%4==0){
                table[i]=i/2;
            }
            else if (i%4==1){
                for (int j=0;j<=i-1;j+=4){
                    int d = i-j;
                    if (prime[d]) {
                        table[i]=table[j]+1;
                        break;
                    }
                }
            }
            else if (i%4==2){
                table[i]=table[i-2]+1;
            }
            else {
                for (int j=0;j<=i-3;j+=4){
                    int d = i-j;
                    if (prime[d]) {
                        table[i]=table[j]+1;
                        break;
                    }
                }
            }
        }
//        if (debug){
//            System.out.println(Arrays.toString(table));
//        }

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