package Codeforces.Edu132;

import java.io.*;
import java.util.*;
/*
PROB: XORTree
LANG: JAVA
*/
public class XORTree {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] A;
    static ArrayList<Integer>[] tree;

    static int[] xor;

    static int ans = 0;
    
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=io.nextInt();
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        //* precomp
        xor = new int[N+1];
        xor[1]=A[1];
        precomp(1,0);
        if (debug){
            io.println("xor: "+Arrays.toString(xor));
        }

        //* DFS
        DFS(1,0);
        io.println(ans);
    }
    static void precomp(int node, int par){
        for (int child : tree[node]){
            if (child==par) continue;
            xor[child]=xor[node]^A[child];
            precomp(child,node);
        }
    }
    static HashSet<Integer> DFS(int node, int par){
        //DFS children first then merge them all
        HashSet<Integer> ret = new HashSet<>();
        boolean bad = false;
        for (int child : tree[node]){
            if (child==par) continue;
            HashSet<Integer> res = DFS(child,node);
            if (res==null) continue;
            if (!bad){
                ret=merge(ret,res,node);
                if(ret==null){
                    bad=true;
                    ans++;
                }
            }
        }
        if (!bad) ret.add(xor[node]);

        //ret
        if (debug){
            io.println("node: "+node);
            io.println("ret: "+ret);
        }
        return ret;
    }
    static HashSet<Integer> merge(HashSet<Integer> u, HashSet<Integer> v, int mid){
        //enforce sz[u]<sz[v] so that we merge u->v
        if (v.size()<u.size()){
            HashSet<Integer> tmp = u;
            u=v;
            v=tmp;
        }

        //if u contains something that * mid is in v then return false
        for (int x : u){
            if (v.contains(x^A[mid])) return null;
        }
        if (u.contains(xor[mid]^A[mid]) || v.contains(xor[mid]^A[mid])) return null;

        //merge
        v.addAll(u);
        return v;
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