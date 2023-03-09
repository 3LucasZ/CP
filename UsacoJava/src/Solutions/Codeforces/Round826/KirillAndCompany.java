package Solutions.Codeforces.Round826;

import java.io.*;
import java.util.*;

public class KirillAndCompany {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int nodes = io.nextInt();
        int edges = io.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[nodes+1];
        for (int i=1;i<=nodes;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<edges;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        int friends = io.nextInt();
        int[] home = new int[friends+1];
        for (int i=1;i<=friends;i++) home[i]=io.nextInt();
        int walking = io.nextInt();
        int[] walkingFriends = new int[friends];
        for (int i=0;i<walking;i++){
            walkingFriends[i]=io.nextInt();
        }

        //* BFS: find the mask of walking friends you can rescue if you walked from src to node
        boolean[][] canHelp = new boolean[nodes+1][1<<walking]; canHelp[1][0]=true;
        boolean[] vis = new boolean[nodes+1];
        int[] dist = new int[nodes+1];
        Queue<Integer> BFS = new LinkedList<>();
        BFS.add(1); vis[1]=true; dist[1]=0;
        while (!BFS.isEmpty()){
            int next = BFS.poll();
            for (int child : graph[next]){
                if (!vis[child]){
                    vis[child]=true;
                    dist[child]=dist[next]+1;
                    BFS.add(child);
                }
                if (dist[child]==dist[next]+1){
                    for (int mask = 0;mask<(1<<walking);mask++){
                        if (canHelp[next][mask]){
                            canHelp[child][mask]=true;
                            int newMask = mask;
                            for (int i=0;i<walking;i++){
                                if (home[walkingFriends[i]]==child) newMask=(newMask|(1<<i));
                            }
                            canHelp[child][newMask]=true;
                        }
                    }
                }
            }
        }
        if (debug){
            System.out.println("dist:"+Arrays.toString(dist));
            System.out.println("canHelp:");
            for (int i=1;i<=nodes;i++){
                io.println(toStr(canHelp[i],walking));
            }
        }

        HashSet<Integer> noCar = new HashSet<>();
        for (int i : walkingFriends) noCar.add(i);

        //* knapsack: can nodes 1..i rescue mask?
        boolean[][] dp = new boolean[friends+1][1<<walking]; dp[0][0]=true;
        if (debug){
            System.out.println("no car:"+noCar);
        }

        for (int i=0;i<friends;i++){
            for (int mask=0;mask<(1<<walking);mask++){
                for (int nextMask=0;nextMask<(1<<walking);nextMask++){
                    if (dp[i][mask]) dp[i+1][mask]=true;
                    if (!noCar.contains(i+1) && canHelp[home[i+1]][nextMask]){
                        dp[i+1][nextMask]=true;
                        if (dp[i][mask]) {
                            dp[i+1][mask|nextMask]=true;
                        }
                    }
                }
            }
        }
        if (debug){
            io.println("dp:");
            for (int i=0;i<=friends;i++){
                io.println(toStr(dp[i],walking));
            }
            io.println("final dp:"+toStr(dp[friends],walking));
        }

        //* ret
        int ans = 0;
        for (int mask=0;mask<(1<<walking);mask++){
            if (dp[friends][mask]){
                ans=Math.max(ans,Integer.bitCount(mask));
            }
        }
        io.println(walking-ans);
    }
    static String toStr(boolean[] bins, int len){
        ArrayList<String> ret = new ArrayList<>();
        for (int i=0;i<bins.length;i++){
            if (bins[i]) ret.add(toStr(i,len));
        }
        return ret.toString();
    }
    static String toStr(int bin, int len){
        String ret = "";
        for (int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        return ret;
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
}}