package Other.Codeforces.Edu141;

import java.io.*;
import java.util.*;
/*
PROB: DoubleSort2
LANG: JAVA
*/
public class DoubleSort2 {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] A;
    static int[] B;

    static boolean[] vis;
    static ArrayList<ArrayList<Integer>> Acycles;
    static ArrayList<ArrayList<Integer>> Bcycles;
    static int[] Aid;
    static int[] Bid;

    static MaxFlow flow;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        B = new int[N+1];
        for (int i=1;i<=N;i++)A[i]=io.nextInt();
        for (int i=1;i<=N;i++)B[i]=io.nextInt();

        //* cycle decomp + id assignments
        vis = new boolean[N+1];
        Acycles = new ArrayList<>();
        for (int i=1;i<=N;i++){
            if (!vis[i]) {
                ArrayList<Integer> cycle = decompose(i,A);
                Acycles.add(cycle);
            }
        }
        if (debug){
            io.println("Acycles: "+Acycles);
        }

        int Asz = Acycles.size();
        Aid = new int[N+1];
        for (int id = 0;id<Asz;id++){
            for (int node : Acycles.get(id)){
                Aid[node]=id+1;
            }
        }
        if (debug){
            io.println("Aid: "+Arrays.toString(Aid));
        }

        vis = new boolean[N+1];
        Bcycles = new ArrayList<>();
        for (int i=1;i<=N;i++){
            if (!vis[i]) {
                ArrayList<Integer> cycle = decompose(i,B);
                Bcycles.add(cycle);
            }
        }
        if (debug){
            io.println("Bcycles: "+Bcycles);
        }

        int Bsz = Bcycles.size();
        Bid = new int[N+1];
        for (int id = 0;id<Bsz;id++){
            for (int node : Bcycles.get(id)){
                Bid[node]=id+1;
            }
        }
        if (debug){
            io.println("Bid: "+Arrays.toString(Bid));
        }

        //* max matching
        int S = Asz+Bsz+1;
        int T = Asz+Bsz+2;
        flow = new MaxFlow(Asz+Bsz+2,S,T);
        for (int i=1;i<=Asz;i++){
            flow.addEdge(0,S,i,1);
        }
        for (int i=1;i<=Bsz;i++){
            flow.addEdge(0,Asz+i,T,1);
        }
        for (int i=1;i<=N;i++){
            flow.addEdge(i,Aid[i],Asz+Bid[i],1);
        }
        int maxFlow = (int)flow.solve();

        //* ret part 1
        io.println(N-maxFlow);
        //* ret part 2
        //investigate max flow process to find which nodes were not swapped. remove from answer list.
        HashSet<Integer> bad = new HashSet<>();
        ArrayList<Edge>[] res = flow.graph;
        if (debug){
            flow.print();
        }
        //if theres a residual from u -> v that is used which means cap < 0 then thats the one we use
        for (int u = Asz+1;u<=Asz+Bsz;u++){
            Edge resid = null;
            for (Edge v : res[u]){
                if (!v.orig && v.cap>0) resid=v;
            }
            if (resid!=null)bad.add(resid.id);
        }
        if (debug){
            io.println("unused: "+bad);
        }
        for (int i=1;i<=N;i++) if (!bad.contains(i)) io.print(i+" ");
        io.println();
    }
    static ArrayList<Integer> decompose(int i, int[] C){
        ArrayList<Integer> ret = new ArrayList<>();
        int cur = C[i];
        while (cur!=i){
            ret.add(cur);
            vis[cur]=true;
            cur=C[cur];
        }
        ret.add(cur);
        vis[cur]=true;
        return ret;
    }

    private static class MaxFlow{
        /*
		Conditions:
		directed graph
		1 indexed graph
		list storage
		*/
        int N;
        int S;
        int T;

        ArrayList<Edge>[] graph;
        boolean[] visited;

        static final int INF=Integer.MAX_VALUE/2;

        public MaxFlow(int N,int S,int T){
            this.N=N;
            this.S=S;
            this.T=T;
            graph=new ArrayList[N+1];
            for(int i=1;i<=N;i++) graph[i]=new ArrayList<>();
            visited=new boolean[N+1];
        }

        public void addEdge(int id, int u,int v,int c){
            Edge forward=new Edge(id,u,v,c,true);
            Edge residual=new Edge(id,v,u,0,false);
            graph[u].add(forward);
            graph[v].add(residual);
            forward.residual=residual;
            residual.residual=forward;
        }

        public long solve(){
            long maxFlow=0;
            long flow=0;
            while(true){
                visited=new boolean[N+1];
                flow=dfs(S,INF);
                if(flow==0) break;
                maxFlow+=flow;
            }
            return maxFlow;
        }

        public long dfs(int node,long flow){
            //found augmenting path
            if(node==T) return flow;
            visited[node]=true;
            for(Edge child: graph[node]){
                if(child.cap>0&&!visited[child.v]){
                    long pathCap=dfs(child.v,Math.min(flow,child.cap));
                    //augmenting path exists
                    if(pathCap>0){
                        child.augment(pathCap);
                        return pathCap;
                    }
                }
            }
            return 0;
        }

        public void print(){
            for(int i=1;i<=N;i++){
                System.out.println(graph[i]);
            }
        }
    }
    private static class Edge {
        int id;
        int u;
        int v;
        int cap;
        Edge residual;
        boolean orig;

        public Edge(int id, int u, int v, int c, boolean o){
            this.id=id;
            this.u=u;
            this.v=v;
            this.cap=c;
            this.orig=o;
        }
        public void augment(long pathCap){
            cap-=pathCap;
            residual.cap+=pathCap;
        }
        public String toString(){
            return "["+u+", "+v+" : "+cap+"]";
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