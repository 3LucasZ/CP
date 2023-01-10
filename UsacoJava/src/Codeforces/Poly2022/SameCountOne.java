package Codeforces.Poly2022;

import java.io.*;
import java.util.*;

public class SameCountOne{
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        int M = io.nextInt();
        int[][] A = new int[N+1][M+1];
        for (int i=1;i<=N;i++){
            for (int j=1;j<=M;j++){
                A[i][j]=io.nextInt();
            }
        }

        //* logistics
        int sum[] = new int[N+1];
        int tot = 0;
        for (int i=1;i<=N;i++){
            for (int j=1;j<=M;j++){
                sum[i]+=A[i][j];
                tot+=A[i][j];
            }
        }
        if (debug){
            io.println("sum: "+Arrays.toString(sum));
        }
        if (tot%N!=0) {
            io.println(-1);
            return;
        }

        //* simulate
        int tar = tot/N;
        ArrayList<Tuple> ans = new ArrayList<>();
        for (int c=1;c<=M;c++){
            ArrayList<Integer> g = new ArrayList<>();
            ArrayList<Integer> l = new ArrayList<>();
            for (int r=1;r<=N;r++){
                if (sum[r]>tar&&A[r][c]==1){
                    g.add(r);
                }
                if (sum[r]<tar&&A[r][c]==0){
                    l.add(r);
                }
            }
            if(debug){
                io.println("l: "+l);
                io.println("g: "+g);
            }
            for (int i=0;i<Math.min(g.size(),l.size());i++){
                ans.add(new Tuple(g.get(i),l.get(i),c));
                sum[g.get(i)]--;
                sum[l.get(i)]++;
            }
        }

        //* ret
        io.println(ans.size());
        for (Tuple t : ans){
            io.println(t.r1+" "+t.r2+" "+t.c);
        }

    }
    private static class Tuple {
        int r1;
        int r2;
        int c;
        public Tuple(int r1, int r2, int c){
            this.r1=r1;
            this.r2=r2;
            this.c=c;
        }
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