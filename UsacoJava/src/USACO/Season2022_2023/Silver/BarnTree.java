package USACO.Season2022_2023.Silver;

import java.io.*;
import java.util.*;
/*
PROB: BarnTree
LANG: JAVA
*/
public class BarnTree {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static ArrayList<Integer>[] tree;
    static int N;
    static long[] h;

    static long tar;

    static ArrayList<Op> up = new ArrayList<>();
    static ArrayList<Op> down = new ArrayList<>();
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        h = new long[N+1];
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=1;i<=N;i++) h[i]=io.nextInt();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        //* precomp tar
        long sum = 0; for (int i=1;i<=N;i++) sum+=h[i];
        tar = sum/N;

        //* DFS
        DFS(1,0);
        if (debug){
            System.out.println("final h: "+Arrays.toString(h));
        }
        //* ret
        //#x
        io.println(up.size()+down.size());
        //up ops first
        for (Op x : up){
            io.println(x.u+" "+x.v+" "+x.cost);
        }
        //down ops first (reversed)
        Collections.reverse(down);
        for (Op x : down){
            io.println(x.u+" "+x.v+" "+x.cost);
        }
    }

    public static void DFS(int node, int par){
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node);
        }
        //op
        long d = tar-h[node];
        if (d>0) down.add(new Op(par,node,d));
        else if (d<0) up.add(new Op(node,par,-d));
        //upd par
        h[par]-=d;
        h[node]+=d;
    }
    private static class Op {
        int u;
        int v;
        long cost;
        public Op(int u, int v, long cost){
            this.u=u;
            this.v=v;
            this.cost=cost;
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