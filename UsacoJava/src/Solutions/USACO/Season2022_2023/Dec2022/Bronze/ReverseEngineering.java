package Solutions.USACO.Season2022_2023.Dec2022.Bronze;

import java.io.*;
import java.util.*;

public class ReverseEngineering {
    static boolean debug = false;

    static int N;
    static int M;
    static int[][] bit;
    static int[] res;
    static boolean[] visitedInput;
    static boolean[] visitedBit;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        io.nextLine();
        N = io.nextInt();
        M = io.nextInt();
        bit = new int[M][N];
        res = new int[M];
        for (int i=0;i<M;i++){
            String str = io.next();
            for (int j=0;j<N;j++){
                bit[i][j]=str.charAt(j)-'0';
            }
            res[i]=io.nextInt();
        }

        //* simulate
        visitedBit = new boolean[N];
        visitedInput = new boolean[M];
        for (int i=0;i<=N;i++){
            solve();
        }
        if (fin()){
            io.println("OK");
        } else {
            io.println("LIE");
        }
    }
    static boolean fin(){
        boolean ret1 = true;
        for (int i=0;i<N;i++) if (!visitedBit[i]) ret1=false;
        boolean ret2 = true;
        for (int j=0;j<M;j++) if (!visitedInput[j]) ret2=false;
        return ret1||ret2;
    }
    static void solve(){
        for (int i=0;i<N;i++){
            if (visitedBit[i]) continue;
            HashSet<Integer> zeroOutputs = new HashSet<>();
            HashSet<Integer> oneOutputs = new HashSet<>();
            for (int j=0;j<M;j++){
                if (visitedInput[j]) continue;
                if (bit[j][i]==0){
                    zeroOutputs.add(res[j]);
                } else {
                    oneOutputs.add(res[j]);
                }
            }
            if (zeroOutputs.size()==1){
                visitedBit[i]=true;
                for (int j=0;j<M;j++){
                    if (visitedInput[j]) continue;
                    if (bit[j][i]==0) visitedInput[j]=true;
                }
            }
            if (oneOutputs.size()==1){
                visitedBit[i]=true;
                for (int j=0;j<M;j++){
                    if (visitedInput[j]) continue;
                    if (bit[j][i]==1) visitedInput[j]=true;
                }
            }
            if (zeroOutputs.size()==1 || oneOutputs.size()==1) {
                if (debug) io.println("Deleting bit: "+i);
            }
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
    void close(){
        out.close();
    }
};}