package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class CountSeconds {
    static boolean debug = false;

    static int N;
    static int M;

    static int[] A;
    static ArrayList<Integer>[] graph;

    static final long MOD = 998244353;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        int M = io.nextInt();
        A = new int[N+1];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            graph[u].add(v);
        }
        boolean weird = true;
        for (int i=1;i<=N;i++) if (A[i]!=0) weird=false;
        if (weird){
            io.println(0);
            return;
        }

        //* topsort
        ArrayList<Integer> sort = TopSort.getSort(graph);
        if (debug){
            io.println("sort:"+sort);
        }

        //* simulate N moves so that all buffer is gon
        long time = 0;
        boolean ok = false;
        while (!ok && time<=N){
            //simulate 1 second
            for(int i=N-1;i>=0;i--){
                int u = sort.get(i);
                if (A[u]>0){
                    A[u]--;
                    for(int v: graph[u]){
                        A[v]++;
                    }
                }
            }
            //case: done early
            boolean tryOk = true;
            for (int i=1;i<=N;i++) if (A[i]!=0) tryOk=false;
            ok = tryOk;
            time++;
        }

        //* dp to finish off the constant flow
        long[] dp = new long[N+1];
        for (int i=1;i<=N;i++) dp[i]=A[i];
        for (int i=0;i<N;i++){
            int u = sort.get(i);
            for (int v : graph[u]){
                dp[v]=(dp[v]+dp[u])%MOD;
            }
        }

        //* ret
        io.println((time+dp[sort.get(N-1)])%MOD);
    }

    private static class TopSort {
        /*
		Kahn's Algorithm for top sort from highest to lowest
		One indexed Adj List graph
		O(N+M)
		Well tested to work
		 */
        private static ArrayList<Integer> sort;
        private static boolean[] vis;

        public static ArrayList<Integer> getSort(ArrayList<Integer>[] graph){
            vis = new boolean[graph.length];
            sort = new ArrayList<>();
            for (int i=1;i<=graph.length-1;i++) dfs(i, graph);
            Collections.reverse(sort);
            return sort;
        }

        private static void dfs(int node, ArrayList<Integer>[] graph){
            if (vis[node]) return;
            vis[node]=true;
            for (int child : graph[node]){
                dfs(child,graph);
            }
            sort.add(node);
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