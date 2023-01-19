package Codeforces.Edu141;

import java.io.*;
import java.util.*;

public class GameOfTheYearWRONG{
    static boolean debug = false;

    static int N;
    static int[] A;
    static int[] B;
    static int[] ok;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new int[N];
        B = new int[N];
        for (int i=0;i<N;i++){
            A[i]=io.nextInt();
        }
        for (int i=0;i<N;i++){
            B[i]=io.nextInt();
        }
        //* prune ok by trying all Ai Bi
        ok = new int[N+2];
        for (int i=0;i<N;i++){
            if (A[i]>B[i])prune(i);
        }
        if (debug){
            io.println("ok: "+Arrays.toString(ok));
        }
        //* ret
        int run = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i=1;i<=N;i++){
            run+=ok[i];
            if (run>=0) ans.add(i);
        }
        io.println(ans.size());
        for (int i : ans) io.print(i+" ");
        io.println();
    }
    public static void prune(int i){
        if (debug){
            io.println("pruning: "+i);
        }
        ok[1]--;
        ok[N+1]++;
        int split =(int)Math.sqrt(A[i]);

        // test k=1..split
        for (int k=1;k<=split;k++){
            if (divUp(A[i],k)==divUp(B[i],k)) {
                ok[k]++;
                ok[k+1]--;
                if (debug){
                    io.println(k+" is good");
                }
            }
        }

        //test m=1..N/split
        split = N/split;
        int[] fA = new int[split+1];
        int[] lA = new int[split+1];
        int[] fB = new int[split+1];
        int[] lB = new int[split+1];
        for (int j=split;j>=1;j--){
            fA[j]=divUp(A[i],j);
            lA[j-1]=fA[j]-1;
            fB[j]=divUp(B[i],j);
            lB[j-1]=fB[j]-1;
        }
        lA[1]=N;
        lB[1]=N;
        for (int j=1;j<=split;j++){
            int min = fA[j];
            int max = lB[j];
            if (min>max) continue;
            ok[min]++;
            ok[max+1]--;
            if (debug){
                for (int k=min;k<=max;k++) io.println(k+ " is good");
            }
        }
    }
    public static int divUp(int num, int denom){
        return (num+denom-1)/denom;
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