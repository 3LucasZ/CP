package Solutions.Codeforces.Edu130;

import java.io.*;
import java.util.*;
/*
PROB: Coloring
LANG: JAVA
*/
public class Coloring {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static Point[] points;
    static ArrayList<Integer>[] closest;

    static long MOD = 998244353;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        points = new Point[N];
        for (int i=0;i<N;i++){
            Point next = new Point(io.nextInt(),io.nextInt());
            pointRotate45(next);
            points[i]=next;
        }
        if (debug){
            io.println("points: "+Arrays.toString(points));
        }

        //* closest sets
        closest = new ArrayList[N];
        for (int i=0;i<N;i++) genClosest(i);
        if (debug){
            for (int i=0;i<N;i++) io.println(i+": "+closest[i]);
        }

        //* group into components
        boolean[] vis = new boolean[N];
        ArrayList<Integer> components = new ArrayList<>();
        components.add(0); //dummy element
        for (int i=0;i<N;i++){
            if (vis[i]) continue;
            boolean single = false;
            for (int j : closest[i]) {
                if (!listEquals(closest[i],closest[j])) single=true;
            }
            if (single) components.add(1);
            else {
                components.add(closest[i].size());
                for (int j : closest[i]) vis[j]=true;
            }
        }
        Collections.sort(components); //might not work
        if (debug){
            io.println("components: "+components);
        }
        //* run dp
        NT nt = new NT(10000,MOD);
        int M = components.size()-1;
        long[][] dp = new long[M+1][N+1]; //# ways to color M components with N colors remaining
        //base case
        dp[0][N]=1;
        //transitions
        for (int i=0;i<M;i++){
            for (int j=1;j<=N;j++){
                int sz = components.get(i+1);
                //in each component, either their all the same color or all different colors.
                //case same
                dp[i+1][j-1]=(dp[i+1][j-1]+dp[i][j]*j)%MOD;
                //case different
                if (j>=sz&&sz>1){
                    long mult=dp[i][j];
                    for(int z=0;z<sz;z++) mult=mult*(j-z)%MOD;
                    dp[i+1][j-sz]=(dp[i+1][j-sz]+mult)%MOD;
                }
            }
        }

        //* ret
        long ans = 0;
        for (int i=0;i<=N;i++) ans=(ans+dp[M][i])%MOD;
        io.println(ans);
    }
    static boolean listEquals(ArrayList<Integer> a, ArrayList<Integer> b){
        if (a.size()!=b.size()) return false;
        for (int i=0;i<a.size();i++){
            if (a.get(i)!=b.get(i)) return false;
        }
        return true;
    }
    static void genClosest(int i){
        closest[i] = new ArrayList<>();
        for (int j=0;j<N;j++){
            if (j==i) continue;
            int dist = dist(i,j);
            //create new closest list
            if(closest[i].size()==0||dist<dist(i,closest[i].get(0))){
                closest[i] = new ArrayList<>();
                closest[i].add(j);
            }
            //append to closest list
            else if (dist==dist(i,closest[i].get(0))){
                closest[i].add(j);
            }
        }
        //sentinel
        closest[i].add(i);
        Collections.sort(closest[i]);
    }
    static void pointRotate45(Point orig){
        int x = orig.x;
        int y = orig.y;
        orig.x=x-y;
        orig.y=x+y;
    }
    static int dist(int i, int j){
        return Math.max(Math.abs(points[i].x-points[j].x),Math.abs(points[i].y-points[j].y));
    }
    private static class Point {
        int x;
        int y;
        public Point (int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
    private static class NT {
        // Note: only works if MOD is prime
        //* pow, inv
        long MOD;
        public long inv(long x) {
            return pow(x,MOD-2);
        }
        public long pow(long x, long p) {
            if (x==0) return 0;
            if (p == 0) return 1;
            if (p % 2 == 1) return (x * pow(x, p - 1)) % MOD;
            else return pow((x * x) % MOD, p / 2);
        }
        public NT(long MOD) {
            this.MOD=MOD;
        }
        //* choose
        //factorials
        long[] f;
        //factorial inverses
        long[] i;
        int MAXF;
        public NT(int MAXF, long MOD) {
            //gen factorials (1...N)!
            this.MAXF=MAXF;
            this.MOD=MOD;
            f = new long[MAXF + 1];
            f[0] = 1;
            for (int i = 1; i <= MAXF; i++) f[i] = (f[i - 1] * i) % MOD;
            //gen inverses (1...N)!^-1
            i = new long[MAXF + 1];
            for (int A = 1; A <= MAXF - 1; A++) {
                i[A] = inv(f[A]);
            }
        }
        public long choose(int n, int k) {
            if (k == n || k == 0) return 1;
            return ((f[n] * i[k] % MOD) * i[n - k]) % MOD;
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