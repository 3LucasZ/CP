package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: TwoChessPieces
LANG: JAVA
*/
public class TwoChessPieces {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int D;
    static ArrayList<Integer>[] tree;
    static int[][] deepest; //deepest[color][node] = deepest node in node's subtree with color
    static boolean[][] colored; //colored[color][node] = is node colored by color?
    static int[] visits;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        D = io.nextInt();
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        colored = new boolean[2][N+1];
        int m1 = io.nextInt();
        for (int i=0;i<m1;i++) {
            int node = io.nextInt();
            colored[0][node]=true;
        }
        int m2 = io.nextInt();
        for (int i=0;i<m2;i++){
            int node = io.nextInt();
            colored[1][node]=true;
        }
        //* precomp deepest[][]
        deepest = new int[2][N+1];
        deepestDFS(1,0,0,1);
        deepestDFS(1,0,1,1);
        if (debug){
            io.println("deepest 0:"+Arrays.toString(deepest[0]));
            io.println("deepest 1:"+Arrays.toString(deepest[1]));
        }

        //* DFS both colors, visiting a subtree if its color is there or if the other color is there and its too far
        visits = new int[2]; visits[0] = visits[1] = -1;
        DFS(1,0,0,1);
        DFS(1,0,1,1);
        io.println(visits[0]*2+visits[1]*2);
    }
    static void DFS(int node, int par, int color, int height){
        visits[color]++;
        for (int child : tree[node]){
            if (child==par) continue;
            if (deepest[color][child]!=0 || deepest[1-color][child]-height>D){
                DFS(child,node,color,height+1);
            }
        }
    }
    static void deepestDFS(int node, int par, int color, int height){
        if (colored[color][node]) deepest[color][node] = height;
        for (int child : tree[node]){
            if (child==par) continue;
            deepestDFS(child,node,color,height+1);
            deepest[color][node]=Math.max(deepest[color][node],deepest[color][child]);
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