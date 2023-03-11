package Solutions.Codeforces.Edu144;

import java.io.*;
import java.util.*;

public class ColoredSubgraphs {
    static boolean debug = false;

    static int N;
    static ArrayList<Integer>[] tree;

    static int[] val; //len of node
    static Multiset[] set;
    static Multiset lens;

    static int ans;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        //* DFS Init/precomp
        val= new int[N+1];
        set = new Multiset[N+1]; for (int i=1;i<=N;i++) set[i] = new Multiset();
        lens= new Multiset();
        DFS_init(1,0);
        if (debug){
            io.println("init");
            io.println("val:"+Arrays.toString(val));
            io.println("set:"+Arrays.toString(set));
            io.println("lens:"+lens);
        }
        //* DFS solve
        ans = Integer.MIN_VALUE;
        DFS(1,0);
        //* ret
        io.println(ans);
    }
    static int first(Multiset ms){
        if (ms.size()==0) return 0;
        else return ms.first();
    }
    static void reverse(int u, int v){
        lens.remove(val[u]);
        if (set[u].size()>=2) lens.remove(set[u].second());
        set[u].remove(val[v]);
        val[u]=first(set[u])+1;
        if (set[u].size()>=2) lens.add(set[u].second());

        if (set[v].size()>=2) lens.remove(set[v].second());
        set[v].add(val[u]);
        if (set[v].size()>=2) lens.add(set[v].second());
        val[v]=first(set[v])+1;
        lens.add(val[v]);
    }
    static void DFS(int node, int par){
        if (debug){
            io.println("node:"+node);
            io.println("set:"+Arrays.toString(set));
            io.println("val:"+val[node]);
        }
        //upd ans
        ans=Math.max(ans,lens.first());
        //finesse
        for (int child : tree[node]){
            if (child==par) continue;
            reverse(node,child);
            DFS(child,node);
            reverse(child,node);
            if (debug){
                io.println("node:"+node);
                io.println("set:"+Arrays.toString(set));
                io.println("val:"+val[node]);
            }
        }
    }
    static void DFS_init(int node, int par){
        //child first
        for (int child : tree[node]){
            if (child==par) continue;
            DFS_init(child,node);
            set[node].add(val[child]);
        }
        //set val[node]
        val[node]=first(set[node])+1;
        //upd lens
        if (set[node].size()>=2){
            lens.add(set[node].second());
        }
        if (node==1){
            lens.add(val[node]);
        }
    }

    private static class Multiset {
        public TreeMap<Integer, Integer> ms = new TreeMap<>();
        private int sz = 0;
        public boolean contains(int x){
            return ms.containsKey(x);
        }
        public void add(int x){add(x,1);}
        public void add(int x, int freq){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+freq);
            sz+=freq;
        }
        public void remove(int x){
            remove(x,1);}
        public void remove(int x, int freq){
            ms.put(x,ms.get(x)-freq);
            if (ms.get(x)<=0) ms.remove(x);
            sz-=freq;
        }
        public void removeKey(int x){
            ms.remove(x);
        }
        public int get(int x){
            if (ms.containsKey(x)) return ms.get(x);
            return 0;
        }
        public int first(){
            return ms.firstKey();
        }
        public int second(){
            int first = ms.firstKey();
            remove(first);
            int second = ms.firstKey();
            add(first);
            return second;
        }
        public Iterator<Integer> iterator(){
            return ms.keySet().iterator();
        }
        public int size(){
            return sz;
        }
        public Set<Integer> keySet(){
            return ms.keySet();
        }
        public String toString(){
            return ms.toString();
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
    void print2d(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
           println();
        }
        println();
    }
    void print2d(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void print2d(boolean[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + (arr[r][c]?"1":"0");
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void printBin(int bin,int len){
        String ret = "";
        for (int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        println(ret);
    }
    void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
    void close(){
        out.close();
    }
}}