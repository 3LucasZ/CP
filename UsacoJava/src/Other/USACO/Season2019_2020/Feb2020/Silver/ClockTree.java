package Other.USACO.Season2019_2020.Feb2020.Silver;

import java.io.*;
import java.util.*;
/*
PROB: ClockTree
LANG: JAVA
*/
public class ClockTree {
    static boolean fileSubmission = true;
    static String fileName = "clocktree";
    
    static boolean debug = false;

    static int N;
    static int[] A;
    static ArrayList<Integer>[] tree;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=1;i<=N;i++) A[i]=io.nextInt();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        //* split into B, W bipartite
        DFS(1,0,true);
        if (debug) {
            System.out.println("Bcnt: "+Bcnt);
            System.out.println("Wcnt: "+Wcnt);
            System.out.println("Bsum: "+Bsum);
            System.out.println("Wsum: "+Wsum);
        }
        int Bd = 12-Bsum%12; Bd%=12;
        int Wd = 12-Wsum%12; Wd%=12;
        if (debug) {
            System.out.println("Bd: "+Bd);
            System.out.println("Wd: "+Wd);
        }
        //* Casework
        if (Bd==Wd) io.println(N);
        else if ((Bd+1)%12==Wd) io.println(Bcnt);
        else if ((Wd+1)%12==Bd) io.println(Wcnt);
        else io.println(0);
    }
    static int Bsum = 0;
    static int Bcnt = 0;
    static int Wsum = 0;
    static int Wcnt = 0;
    public static void DFS(int node, int par, boolean isB){
        if (!isB) {
            Wsum+=A[node];
            Wcnt++;
        } else {
            Bsum+=A[node];
            Bcnt++;
        }
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node,!isB);
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
    void close(){
        out.close();
    }
};;
}