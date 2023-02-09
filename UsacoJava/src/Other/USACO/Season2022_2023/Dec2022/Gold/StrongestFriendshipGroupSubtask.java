package Other.USACO.Season2022_2023.Dec2022.Gold;

import java.io.*;
import java.util.*;
/*
PROB: StrongestFriendshipGroupSubtask
LANG: JAVA
*/
public class StrongestFriendshipGroupSubtask {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int M;
    static HashSet<Integer>[] graph;
    static PriorityQueue<Integer> pq;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        graph = new HashSet[N+1]; for (int i=1;i<=N;i++) graph[i] = new HashSet<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        //* setup
        long ans = 0;
        pq = new PriorityQueue<>((a,b)->{
            if (graph[a].size()==graph[b].size()) return a-b;
            return graph[a].size()-graph[b].size();
        });
        for (int i=1;i<=N;i++) pq.add(i);

        //* unit tests
        if (debug){
            io.println("graph: "+Arrays.toString(graph));
            io.println("max component size: "+ maxComponent());
            io.println("pq: "+pq);
            trim(2);
            io.println("graph: "+Arrays.toString(graph));
            io.println("max component size: "+ maxComponent());
            io.println("pq: "+pq);
        }

        //* slowly increase min num edges, and count max component size
        for (int minDeg=0;minDeg<=M;minDeg++){
            Integer next = pq.peek();
            if (next==null) continue;
            if (graph[next].size()<minDeg) {
                trim(minDeg);
                maxComponent();
            }
            ans=Math.max(ans, (long)(minDeg)*maxComponent);
        }
        io.println(ans);
    }
    static boolean[] vis;
    static int maxComponent = 0;
    static int component = 0;
    public static int maxComponent(){
        maxComponent = 0;
        vis = new boolean[N+1];
        for (int i=1;i<=N;i++){
            component = 0;
            DFS(i);
            maxComponent = Math.max(maxComponent,component);
        }
        return maxComponent;
    }
    public static void DFS(int node){
        if (vis[node] || graph[node].size()==0) return;
        vis[node]=true;
        component++;
        for (int child : graph[node]){
            DFS(child);
        }
    }
    public static void trim(int deg){
        while (true){
            Integer lo = pq.peek();
            if (lo==null || graph[lo].size()>=deg) break;
            pq.poll();
            for (int child : graph[lo]){
                pq.remove(child);
                graph[child].remove(lo);
                pq.add(child);
            }
            graph[lo] = new HashSet<>();
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