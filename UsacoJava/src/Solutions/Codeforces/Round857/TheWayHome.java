package Solutions.Codeforces.Round857;

import java.io.*;
import java.util.*;

public class TheWayHome {
    static boolean debug = false;

    //given stuff
    static int N;
    static int M;
    static long P;
    static long[] W;
    static ArrayList<Edge>[] graph;

    static long INF = Long.MAX_VALUE/10;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        P = io.nextInt();
        W = new long[N+1];
        for (int i=1;i<=N;i++){
            W[i]=io.nextInt();
        }
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            long c = io.nextInt();
            graph[u].add(new Edge(u,v,c));
        }
        if (debug){
            io.println(Arrays.toString(graph));
        }
        //* APSP
        long[][] cost = new long[N+1][N+1];
        for (int src=1;src<=N;src++){
            for (int i=1;i<=N;i++) cost[src][i]=INF;
            cost[src][src]=0;
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(a->a.cost));
            pq.add(new Edge(0,src,0));
            while (!pq.isEmpty()){
                Edge next = pq.poll();
                if (next.cost>cost[src][next.v]) continue;
                for (Edge childEdge : graph[next.v]){
                    int child = childEdge.v;
                    if (next.cost+childEdge.cost>cost[src][child])continue;
                    cost[src][child]=next.cost+childEdge.cost;
                    pq.add(new Edge(next.v,child,cost[src][child]));
                }
            }
        }
        if (debug){
            io.print2d(cost);
        }
        //case: unreachable N
        if (cost[1][N]==INF) {
            io.println(-1);
            return;
        }
        //* "DP"
        //setup
        Item[] val = new Item[N+1];
        for (int i=1;i<=N;i++) val[i] = new Item(INF,INF);
        boolean[] vis = new boolean[N+1];
        val[1] = new Item(0,P);
        //transitioning
        for (int i=1;i<=N-1;i++){
            //find next best node
            int next = 0;
            for (int node=1;node<=N;node++){
                if(vis[node]) continue;
                if(next==0||val[node].compareTo(val[next])>0){
                    next=node;
                }
            }
            if (next==0) break;
            vis[next]=true;
            //propagate next to all other nodes
            for (int child=1;child<=N;child++){
                if (vis[child]) continue;
                if (cost[next][child]==INF) continue;
                long moneyNecessary = cost[next][child]-val[next].money;
                long extraPerformances = moneyNecessary/W[next];
                if (moneyNecessary<0) extraPerformances=0;
                else if (moneyNecessary%W[next]!=0) extraPerformances++;
                long extraMoney  =val[next].money+extraPerformances*W[next]-cost[next][child];
                Item newItem = new Item(val[next].performances+extraPerformances,extraMoney);
                if (val[child].compareTo(newItem)<0) val[child]=newItem;
            }
        }
        //* ret
        io.println(val[N].performances);
    }
    private static class Item implements Comparable<Item>{
        long performances;
        long money;
        public Item(long p, long m){
            performances=p;
            money=m;
        }
        @Override
        public int compareTo(Item otherItem){
            // take larger money
            if (performances==otherItem.performances) return Long.compare(money,otherItem.money);
            // take smaller performances
            else return -Long.compare(performances,otherItem.performances);
        }
    }
    private static class Edge {
        int u;
        int v;
        long cost;
        public Edge(int u, int v, long c){
            this.u=u;
            this.v=v;
            this.cost=c;
        }
        public String toString(){
            return ""+v;
        }
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    static <T> T last(ArrayList<T> list){
    return list.get(list.size()-1);
}
static String binToStr(int bin,int len){
        String ret="";
        for(int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        return ret;
    }
static int log2(int num){
    return (int)(Math.log(num)/Math.log(2));
}
static void reverse(int[] arr){
    for (int i=0;i<arr.length/2;i++){
        int tmp = arr[i];
        arr[i]=arr[arr.length-1-i];
        arr[arr.length-1-i]=tmp;
    }
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
    void print2d(long[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 3) str += " ";
                str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void print2d(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 3) str += " ";
                str += " ";
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
                while (str.length() < 3) str += " ";
                str += " ";
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
                while (str.length() < 3) str += " ";
                str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void close(){
        out.close();
    }
}}