package Other.USACO.Season2022_2023.Feb2023.Silver;

import java.io.*;
import java.util.*;
/*
PROB: MooRoute
LANG: JAVA
*/
public class MooRoute2{
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int M;
    static PriorityQueue<Edge>[] graph;
    static int wait[];

    static int ans[];

    static int INF = Integer.MAX_VALUE;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        graph = new PriorityQueue[N+1];
        for (int i=1;i<=N;i++) graph[i] = new PriorityQueue<Edge>(Comparator.comparingInt(a->-a.t1));
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int t1 = io.nextInt();
            int v = io.nextInt();
            int t2 = io.nextInt();
            graph[u].add(new Edge(u,v,t1,t2));
        }
        wait = new int[N+1];
        for (int i=1;i<=N;i++){
            wait[i] = io.nextInt();
        }
        if (debug){
            io.println("graph:"+Arrays.toString(graph));
            io.println("wait:"+Arrays.toString(wait));
        }
        //* DFS
        ans = new int[N+1]; Arrays.fill(ans,INF);
        DFS(1,0,true);
        //* ret
        for (int i=1;i<=N;i++) {
            io.println(ans[i]==INF?"-1":ans[i]);
        }
    }
    public static void DFS(int u, int t, boolean override){
        if (debug){
            io.println("DFS:"+u+", "+t);
        }
        ans[u]=Math.min(ans[u],t);
        while (!graph[u].isEmpty()){
            Edge top = graph[u].peek();
            if (override || t+wait[u]<=top.t1){
                graph[u].poll();
                DFS(top.v,top.t2,false);
            } else {
                return;
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        int t1;
        int t2;
        public Edge(int u, int v, int t1, int t2){
            this.u=u;
            this.v=v;
            this.t1=t1;
            this.t2=t2;
        }
        public String toString() {
            return "["+u+", "+v+", "+t1+", "+t2+"]";
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
};
}