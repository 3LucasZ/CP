package Codeforces.GoodBye2022;

import java.io.*;
import java.util.*;

public class KoxiaAndGame {
    static boolean debug = false;

    static long MOD = 998244353;
    static int N;

    static ArrayList<Integer>[] graph;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++)graph[i] = new ArrayList<>();
        int[] A = new int[N+1];
        int[] B = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=io.nextInt();
        for (int i=1;i<=N;i++) B[i]=io.nextInt();

        //* create graph
        for (int i=1;i<=N;i++){
            int u = A[i];
            int v = B[i];
            graph[u].add(v);
            graph[v].add(u);
        }
        if (debug){
            io.println(Arrays.toString(graph));
        }

        //* make sure graph is valid
        vis = new boolean[N+1];
        for (int i=1;i<=N;i++){
            if (vis[i]) continue;
            sz = 0;
            edges = 0;
            check(i);
            if (sz!=edges/2) {
                io.println(0);
                return;
            }
        }

        //* solve
        ans=1;
        par =new int[N+1];
        vis = new boolean[N+1];
        for (int i=1;i<=N;i++){
            //dont double visit
            if (vis[i]) continue;
            //check component
            DFS(i,0);
        }
        if (debug) io.println("par: "+Arrays.toString(par));

        //* ret
        io.println(ans);
    }
    static int edges;
    static int sz;
    static void check(int node){
        if (vis[node]) return;
        vis[node]=true;
        sz++;
        edges+=graph[node].size();
        for (int child : graph[node]){
            check(child);
        }
    }

    static boolean[] vis;
    static int[] par;
    static long ans;
    static void DFS(int node,int p){
        //basic
        if (vis[node]) return;
        vis[node]=true;
        par[node]=p;

        //find back edge
        int old = 0;
        boolean seenPar = false;
        for (int child : graph[node]){
            //case: self loop
            if (child==node){
                old=child;
            }
            //case: actual ancestor
            if (vis[child]) {
                if (child==p && !seenPar){
                    seenPar = true;
                } else {
                    old = child;
                }
            }
        }

        //solve the back edge
        if (old!=0){
            int cur = node;
            int moves = 1;
            while (cur!=old){
                moves++;
                cur=par[cur];
            }
            if (moves==1) ans = ans*N%MOD;
            else ans = ans*2;
        }

        //continue DFS
        for (int child : graph[node]) {
            DFS(child,node);
        }
    }





/*
2
10
1 2 3 4 5 6 7 8 9 10
1 2 3 4 5 6 7 8 9 10
8
1 3 5 1 2 6 7 7
3 5 1 2 4 6 7 7
 */















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