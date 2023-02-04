package Other.Codeforces.Edu126;

import java.io.*;
import java.util.*;
/*
PROB: NarrowComponents
LANG: JAVA
*/
public class NarrowComponents {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static boolean[][] free;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        free = new boolean[3][N+1];
        for (int r=0;r<3;r++){
            String str = io.nextLine();
            for (int c=1;c<=N;c++){
                free[r][c]=str.charAt(c-1)=='1';
            }
        }

        //* presum to find # free
        int[] freq = new int[N+1];
        for (int i=1;i<=N;i++){
            freq[i]=freq[i-1];
            for (int r=0;r<3;r++){
                if (free[r][i]) freq[i]++;
            }
        }
        if (debug){
            io.println("freq:"+Arrays.toString(freq));
        }
        //* DSU to create edge frame
        int[] preH = new int[N+1];
        int[] preV = new int[N+1];
        DSU dsu = new DSU(4*N);
        for (int i=1;i<=N;i++){
            preH[i]=preH[i-1];
            preV[i]=preV[i-1];
            for (int r=0;r<3;r++){
                //new edges that col i introduce

                //[r-1][c]->[r][c]
                if (r!=0 && free[r-1][i] && free[r][i]){
                    int cmp = dsu.components;
                    dsu.union(3*i+r,3*i+r-1);
                    if (dsu.components<cmp) preV[i]++;
                }

                //[r][c-1]->[r][c]
                if (free[r][i-1] && free[r][i]){
                    int cmp = dsu.components;
                    dsu.union(3*i+r,3*(i-1)+r);
                    if (dsu.components<cmp) preH[i]++;
                }

            }
        }
        if(debug){
            io.println("preH:"+Arrays.toString(preH));
            io.println("preV:"+Arrays.toString(preV));
        }

        //* find first non [101]
        int[] head = new int[N+2]; head[N+1]=N+1;
        for (int i=N;i>=1;i--){
            head[i]=head[i+1];
            if (!(free[0][i] && !free[1][i] && free[2][i])){
                head[i]=i;
            }
        }
        if (debug){
            io.println("head:"+Arrays.toString(head));
        }

        //* handle q
        int Q = io.nextInt();
        for (int i=0;i<Q;i++){
            int l = io.nextInt();
            int r = io.nextInt();
            int winL = head[l];

            int ans = freq[r]-freq[winL-1]-(preH[r]-preH[winL]+preV[r]-preV[winL-1]);
            if (winL>r){
                io.println("2");
                continue;
            }
            else if (winL==l){

            } else if (!free[0][winL]&&!free[1][winL]&&!free[2][winL]) {
                ans+=2;
            } else if (free[0][winL]&&free[1][winL]&&free[2][winL]){

            } else if (!free[0][winL]&&free[1][winL]&&!free[2][winL]){
                ans+=2;
            } else {
                ans++;
            }
            io.println(ans);
        }
    }

    private static class DSU {
        /*
        union and get operations
        sz is the size of the component
         */
        int[] parent;
        int[] sz;
        int components;

        public DSU(int num){
            sz = new int[num+1];
            parent = new int[num+1];
            components = num;
            Arrays.fill(sz, 1);
            Arrays.fill(parent, -1);
        }

        //return parent
        public int get(int v){
            if (parent[v] == -1) {
                return v;
            }
            parent[v] = get(parent[v]);
            return parent[v];
        }

        //add edge
        public void union(int u, int v){
            int U = get(u);
            int V = get(v);
            //same component, do nothing
            if (U == V) return;
            //enforce sz[V]<sz[U]
            if (sz[U]<sz[V]){
                int tmp=U;
                U=V;
                V=tmp;
            }
            //op
            parent[V] = U;
            sz[U] += sz[V];
            components--;
        }
        //check CC
        public boolean connected(int u, int v){
            return get(u)==get(v);
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