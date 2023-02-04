package Other.USACO.Season2022_2023.Gold;

import java.io.*;
import java.util.*;
/*
PROB: Mountains
LANG: JAVA
*/
public class Mountains {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] orig;
    static int[] h;
    static int Q;
    static Pair[] q;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        orig = new int[N+1];
        for (int i=1;i<=N;i++){
            orig[i] = io.nextInt();
        }
        Q = io.nextInt();
        q = new Pair[Q];
        for (int i=0;i<Q;i++){
            int x = io.nextInt();
            int y = io.nextInt();
            q[i] = new Pair(x,y);
        }

        //* simulate separately for every mountain
        int[] ans = new int[Q];
        for (int i=1;i<=N;i++){
            h = orig.clone();
            if (debug) io.println("Solving: "+i);
            if (debug) io.println("h: "+Arrays.toString(h));
            //slopes from i to all j>i
            slope = genSlopes(i);
            if (debug) io.println("Slopes: "+Arrays.toString(slope));
            //init visible cows TreeSet
            TreeSet<Integer> vis = genTreeSet(i);
            if (debug) io.println("init vis: "+vis);
            //simulate queries
            for (int t=0;t<Q;t++){
                Pair next = q[t];
                if (debug) io.println("Processing: "+next);
                int j = next.i;
                if (j<i) {
                    ans[t]+=vis.size();
                    continue;
                }

                //make i longer, reset slope and vis
                if (j==i){
                    h[i] += next.dh;
                    slope = genSlopes(i);
                    vis = genTreeSet(i);
                    ans[t]+=vis.size();
                }
                //make some j longer, remove j first if already exists
                else {
                    vis.remove(j);
                    h[j]+=next.dh;
                    slope[j].a+=next.dh;

                    //can add j?
                    Integer hi = vis.higher(j);
                    if (hi!=null && hi<j) {
                        ans[t]+=vis.size();
                        continue;
                    }
                    //add j while removing those with higher index and smaller
                    while (!vis.isEmpty()){
                        Integer lo = vis.lower(j);
                        if (lo==null || lo<j) break;
                        vis.remove(lo);
                    }
                    vis.add(j);
                    ans[t]+=vis.size();
                }
                if (debug) io.println("vis: "+vis);
            }
        }

        //* ret
        for (int t=0;t<Q;t++){
            io.println(ans[t]);
        }
    }
    static Fraction[] slope;
    public static TreeSet<Integer> genTreeSet(int i){
        TreeSet<Integer> ret = new TreeSet<>((a,b)->{
            if (slope[a].cmp(slope[b])==0) return a-b;
            return slope[a].cmp(slope[b]);
        });
        for (int j=i+1;j<=N;j++){
            if (ret.size()==0 || slope[j].cmp(slope[ret.last()])>=0) ret.add(j);
        }
        return ret;
    }
    public static Fraction[] genSlopes(int i){
        Fraction[] ret = new Fraction[N+1];
        for (int j=i+1;j<=N;j++){
            ret[j]=new Fraction(h[j]-h[i],j-i);
        }
        return ret;
    }
    private static class Fraction {
        int a;
        int b;
        public Fraction(int a, int b){
            this.a=a;
            this.b=b;
        }
        //f1-f2
        public long sub(Fraction o){
            return (long) a *o.b - (long) b *o.a;
        }
        public int cmp(Fraction o){
            if (sub(o)==0) return 0;
            if (sub(o)>0) return 1;
            if (sub(o)<0) return -1;
            return -1000;
        }
        public String toString() {
            return a+"/"+b;
        }
    }
    private static class Pair {
        int i;
        int dh;
        public Pair(int i, int dh){
            this.i=i;
            this.dh=dh;
        }
        public String toString(){
            return "["+i+", "+dh+"]";
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