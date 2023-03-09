package Solutions.Codeforces.Edu141;

import java.io.*;
import java.util.*;

public class GameOfTheYear {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i=0;i<N;i++)A[i]=io.nextInt();
        for (int i=0;i<N;i++)B[i]=io.nextInt();

        //* cover half intervals [bi,ai)
        int[] sum = new int[N+1];
        for (int i=0;i<N;i++){
            if (A[i]<=B[i]) continue;
            sum[B[i]]++;
            sum[A[i]]--;
        }
        for (int i=1;i<=N;i++){
            sum[i]=sum[i-1]+sum[i];
        }

        //* for each k from 1..N check if a multiple of it is contained in one of the half intervals
        ArrayList<Integer> ans = new ArrayList<>();
        for (int k=1;k<=N;k++){
            boolean badK = false;
            for (int mult=1;mult*k<=N;mult++){
                int x = mult*k;
                if (sum[x]>0) badK=true;
            }
            if (!badK) ans.add(k);
        }

        //* ret
        io.println(ans.size());
        for (int i : ans){
            io.print(i+" ");
        }
        io.println();
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