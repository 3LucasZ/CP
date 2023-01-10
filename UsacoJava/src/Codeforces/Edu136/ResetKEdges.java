package Codeforces.Edu136;

import java.io.*;
import java.util.*;

public class ResetKEdges {
    static boolean debug = false;

    static int N;
    static int K;
    static ArrayList<Integer>[] children;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        K = io.nextInt();
        children = new ArrayList[N+1];
        for (int i=1;i<=N;i++) children[i] = new ArrayList<>();
        for (int i=2;i<=N;i++){
            int u = io.nextInt();
            children[u].add(i);
        }

        //* binary search height
        int lo=1;
        int hi=N-1;
        while (lo<hi){
            int mid=(lo+hi)/2;
            if (tryHeight(mid)) hi=mid;
            else lo=mid+1;
        }
        io.println(lo);
    }
    static int ops;
    static boolean tryHeight(int h){
        if (debug) io.println("trying height: "+h);
        ops=0;
        for (int child : children[1]) DFS(child,1,h);
        if (debug) io.println("ops: "+ops);
        return ops<=K;
    }
    static int DFS(int node, int height, int tar){
        int deepestChild = height;
        for (int child : children[node]){
            int dfs = DFS(child,height+1,tar);
            deepestChild=Math.max(deepestChild,dfs);
        }
        if (height!=1&&deepestChild-height+1>=tar){
            ops++;
            deepestChild=height-1;
        }
        return deepestChild;
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