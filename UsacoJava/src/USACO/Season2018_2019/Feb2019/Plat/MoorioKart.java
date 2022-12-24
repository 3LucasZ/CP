package USACO.Season2018_2019.Feb2019.Plat;

import java.io.*;
import java.util.*;
/*
PROB: MoorioKart
LANG: JAVA
*/
public class MoorioKart {
    static boolean fileSubmission = true;
    static String fileName = "mooriokart";
    static boolean debug = false;

    static int N, M, X, Y; //meadows, roads, b/w farm len, min track len
    static int MAXD = 2500; //max road len
    static int K = 0; //num farm
    static ArrayList<Edge>[] graph;//graph given
    static long MOD = (long) (1e9+7);

    static boolean[] vis; //vis in init DFS
    static int[][] freq; //frequency of dist for each set for dist<Y
    static long[] pathSum; //sum of dist for each set
    static int[] paths; //num paths for each set
    static ArrayList<Integer>[] relevant;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        X = io.nextInt();
        Y = io.nextInt();
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            int c = io.nextInt();
            graph[u].add(new Edge(u,v,c));
            graph[v].add(new Edge(v,u,c));
        }
        if (debug){
            io.println("Graph: "+Arrays.toString(graph));
        }

        //* Calculate Forest Data
        vis = new boolean[N+1];
        freq = new int[N+1][MAXD+1]; //frequency of important path lengths for all farms
        paths = new int[N+1]; //#paths
        pathSum = new long[N+1]; //pathSum for all farms
        for (int i=1;i<=N;i++){
            if (!vis[i]) K++;
            DFS(i,0, 0);
        }
        //relax set 1
        for (int i=0;i<=MAXD;i++){
            freq[1][i]/=2;
        }
        pathSum[1]/=2;
        paths[1]/=2;
        //relevant path lengths to reduce search time
        relevant =new ArrayList[K+1]; for (int i=1;i<=K;i++) relevant[i] = new ArrayList<>();
        for (int i=1;i<=K;i++){
            for (int j=0;j<Y;j++){
                if (freq[i][j]!=0) relevant[i].add(j);
            }
        }
        //redo Y
        Y-=K*X;
        if (debug){
            io.println("K: "+K);
            io.println("Freq: ");
            for (int i=1;i<=K;i++){
                io.println(Arrays.toString(freq[i]));
            }
            io.println("Y: "+Y);
        }

        //* knapsack dp[set][road sum] = cnt of bad paths
        long[][] dp = new long[K+1][Math.max(1,Y)];
        dp[0][0]=1;
        for (int i=1;i<=K;i++){
            for (int j=0;j<Y;j++){
                for (int newPath : relevant[i]){
                    int oldPath = j-newPath; if (oldPath<0) continue;
                    dp[i][j]=(dp[i][j]+dp[i-1][oldPath]*freq[i][newPath])%MOD;
                }
            }
        }

        //* number of permutations for the sets 1..K
        long combo = 1;
        for (int i=2;i<=K-1;i++) combo=combo*i%MOD;
        if (debug) io.println("combo: "+combo);

        //* complementary counting, adding back in between paths
        long tot = 0;
        long totPaths = 1;
        for (int i=1;i<=K;i++){
            long sum = pathSum[i];
            int newPaths = paths[i];
            tot=(tot*newPaths+sum*totPaths)%MOD;
            totPaths=(totPaths*newPaths)%MOD;
        }
        if (debug) {
            io.println("tot: "+tot);
            io.println("totPaths: "+totPaths);
        }

        long rem = 0;
        long remPaths = 0;
        for (int i=0;i<Y;i++){
            rem=(rem+dp[K][i]*i)%MOD;
            remPaths=(remPaths+dp[K][i])%MOD;
        }
        if (debug) {
            io.println("rem: "+rem);
            io.println("remPaths: "+remPaths);
        }

        long ans = M(tot-rem);
        long paths = M(totPaths-remPaths);
        ans=(ans+paths*K%MOD*X)%MOD;
        ans=ans*combo%MOD;
        io.println(ans);
    }

    public static long M(long A){
        return (A%MOD+MOD)%MOD;
    }
    public static void DFS(int node, int par, int dist){
        vis[node]=true;
        if (par!=0){
            if (dist<=Y) freq[K][dist]++;
            paths[K]+=1;
            pathSum[K]+=dist;
        }
        for (Edge child : graph[node]){
            if (child.v==par) continue;
            DFS(child.v,node,dist+child.cost);
        }
    }
    private static class Edge {
        int u;
        int v;
        int cost;
        public Edge(int u, int v, int c){
            this.u=u;
            this.v=v;
            cost=c;
        }
        public String toString(){
            return "["+u+", "+v+"]";
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