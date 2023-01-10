package Helper.Graph;

import java.io.*;
import java.util.*;
/*
PROB: TwoEdgeCC
LANG: JAVA
*/

/*
Lib Checker: Two Edge Connected Components
Time: O(V+E)
Param: 0 indexed graph with self loops, multi edges
Find: All Biconnected components
 */
public class TwoEdgeCC {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N,M;
    static ArrayList<Integer>[] graph;
    static boolean[] vis;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        graph = new ArrayList[N]; for (int i=0;i<N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        if (debug){
            io.println("graph: "+Arrays.toString(graph));
        }

        //* DFS
        vis = new boolean[N];
        tin = new int[N];
        lo = new int[N];
        nodes = new Stack<>();
        biCC = new ArrayList<>();
        for (int i=0;i<N;i++){
            if (vis[i]) continue;
            if (debug){
                io.println("explore i: "+i);
            }
            DFS(i,-1);
        }
        if (debug){
            io.println("tin: "+Arrays.toString(tin));
            io.println("lo: "+Arrays.toString(lo));
            io.println("nodes: "+nodes);
        }

        //* ret
        io.println(biCC.size());
        for (ArrayList<Integer> cc : biCC){
            io.print(cc.size()+" ");
            for (int node : cc){
                io.print(node+" ");
            }
            io.println();
        }
    }
    static int[] tin;
    static int[] lo;
    static int timer=0;
    static Stack<Integer> nodes;
    static ArrayList<ArrayList<Integer>> biCC;

    static void DFS(int node, int par){
        if (debug){
            io.println("DFS: "+node);
        }
        vis[node]=true;
        tin[node]=timer++;
        lo[node]=tin[node];
        nodes.add(node);
        boolean seenPar = false;

        for (int child : graph[node]){
            //span edge
            if (!vis[child]){
                DFS(child,node);
                lo[node]=Math.min(lo[node],lo[child]);
            }
            //back edge
            else{
                if (child==par && !seenPar) seenPar = true;
                else lo[node]=Math.min(lo[node],tin[child]);
            }
        }

        //handle when articulation point
        if (lo[node]==tin[node]){
            ArrayList<Integer> newCC = new ArrayList<>();
            while (nodes.peek()!=node) newCC.add(nodes.pop());
            newCC.add(nodes.pop());
            biCC.add(newCC);
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
};;
}