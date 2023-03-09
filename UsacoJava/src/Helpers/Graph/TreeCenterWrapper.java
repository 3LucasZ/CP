package Helpers.Graph;

import java.io.*;
import java.util.*;
/*
PROB: TreeCenter
LANG: JAVA
*/
public class TreeCenterWrapper {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;
    
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        ArrayList<Integer>[] tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        TreeCenter tc = new TreeCenter(tree);
        io.println("Diameter: "+tc.maxDist);
        io.println("Center: "+tc.center);
    }
    private static class TreeCenter {
        ArrayList<Integer>[] tree;
        int maxDist = 0;
        int maxNode1 = 1;
        int maxNode2 = 0;
        int center;

        TreeCenter(ArrayList<Integer>[] tree) {
            this.tree = tree;
            maxDist = 0;
            dfs1(1, 0, 0);
            maxDist = 0;
            dfs2(maxNode1,0,0);
            dfs3(maxNode1,0,0);
        }

        public void dfs1(int node, int par, int dist) {
            //process if leaf
            if (tree[node].size() == 1) {
                if (dist > maxDist) {
                    maxDist = dist;
                    maxNode1 = node;
                }
            }
            //DFS
            for (int child : tree[node]) {
                if (child==par) continue;
                dfs1(child, node,dist + 1);
            }
        }
        public void dfs2(int node, int par, int dist) {
            //process if leaf
            if (tree[node].size() == 1) {
                if (dist > maxDist) {
                    maxDist = dist;
                    maxNode2 = node;
                }
            }
            //DFS
            for (int child : tree[node]) {
                if (child==par) continue;
                dfs2(child, node,dist + 1);
            }
        }
        public boolean dfs3(int node, int par, int dist) {
            if (node==maxNode2) return true;
            //DFS
            boolean good = false;
            for (int child : tree[node]) {
                if (child==par) continue;
                if (dfs3(child, node,dist + 1)) good=true;
            }
            if (dist==maxDist/2) center=node;
            return good;
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