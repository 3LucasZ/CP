package Solutions.Codeforces.Hello2023;

import java.io.*;
import java.util.*;
/*
PROB: XorcererStones
LANG: JAVA
*/
public class XorcererStones {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] A;
    static ArrayList<Integer>[] tree;

    static boolean[][] dp; //can node reach mask?
    static int[] sz; //node sub size

    static final int masks = 32;

    static ArrayList<Integer> ops = new ArrayList<>();

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=io.nextInt();
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int v=2;v<=N;v++){
            int u = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        //* precomp
        sz = new int[N+1];
        DFS_precomp(1,0);
        if (debug){
            io.println("A:"+Arrays.toString(A));
            io.println("tree:"+Arrays.toString(tree));
            io.println("sz:"+Arrays.toString(sz));
        }
        //* dp
        dp = new boolean[N+1][masks];
        DFS(1);
        if (debug){
            for (int i=1;i<=N;i++){
                io.println("dp["+i+"]:"+Binary.toStr(dp[i],5));
            }
        }
        //* ret
        if (!dp[1][0]){
            io.println(-1);
        } else {
            DFS_fin(1,0);
            io.println(ops.size());
            Collections.reverse(ops);
            for (int i : ops) io.print(i+" ");
        }
    }
    static void DFS_fin(int node, int mask){
        //pre
        int children = tree[node].size();
        //pre comp cur, how
        boolean[] cur = new boolean[masks];
        int[][] how = new int[children][masks];
        cur[A[node]]=true;
        for (int c=0;c<children;c++) {
            int child = tree[node].get(c);
            boolean[] newCur = new boolean[masks];
            for (int i=0;i<masks;i++){ //mask of cur
                for (int j=0;j<masks;j++){ //mask of child
                    if (cur[i] && dp[child][j]){
                        int res = i^j;
                        if (!newCur[res]){
                            newCur[res]=true;
                            how[c][res]=j;
                        }
                    }
                }
            }
            cur=newCur;
        }
        //build how
        if (sz[node]%2==0 && mask==0) {
            ops.add(node);
            ops.add(node);
        } else{
            if (node==1) ops.add(node);
            for(int c=children-1;c>=0;c--){
                int child=tree[node].get(c);
                DFS_fin(child,how[c][mask]);
                mask=mask^how[c][mask];
            }
        }
    }
    static void DFS(int node){
        //pre
        int children=tree[node].size();

        //child first
        for(int child: tree[node]){
            DFS(child);
        }

        //comp dp
        boolean[] cur=new boolean[masks];
        cur[A[node]]=true;
        for(int c=0;c<children;c++){
            int child=tree[node].get(c);
            boolean[] newCur=new boolean[masks];
            for(int i=0;i<masks;i++){ //mask of cur
                for(int j=0;j<masks;j++){ //mask of child
                    if(cur[i]&&dp[child][j]){
                        newCur[i^j]=true;
                    }
                }
            }
            cur=newCur;
        }

        //final
        if(sz[node]%2==0) cur[0]=true;
        dp[node]=cur;
    }

    static void DFS_precomp(int node, Integer par){
        sz[node]=1;
        tree[node].remove(par);
        for (int child : tree[node]){
            DFS_precomp(child,node);
            sz[node]+=sz[child];
        }
    }



    private static class Binary{
        static int toInt(String str){
            return 0;
        }
        static String toStr(int bin, int len){
            String ret = "";
            for (int i=0;i<len;i++){
                ret+=bin%2;
                bin/=2;
            }
            return ret;
        }
        static String toStr(boolean[] bins, int len){
            ArrayList<String> ret = new ArrayList<>();
            for (int i=0;i<bins.length;i++){
                if (bins[i]) ret.add(toStr(i,len));
            }
            return ret.toString();
        }
        static int rotateL(int bin,int len,int shift){
            return (bin >> shift) | (bin << (len-shift));
        }
        static int rotateR(int bin, int len, int shift){
            return (bin << shift) | (bin >> (len-shift));
        }
        static boolean on(int bin, int bit){
            return (bin&(1<<bit))>0;
        }
        static int toggle(int bin, int bit){
            return bin^(1<<bit);
        }
        static int set(int bin, int bit){
            return bin|(1<<bit);
        }
        static int unset(int bin, int bit){
            return bin&(1<<bit);
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
    void close(){
        out.close();
    }
};
}