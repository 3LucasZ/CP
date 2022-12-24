import java.io.*;
import java.util.*;
/*
PROB: Delegation
LANG: JAVA
*/
public class Delegation {
    static boolean fileSubmission = true;
    static String fileName = "io/tmp";
    
    static boolean debug = false;

    static int N;
    static ArrayList<Integer>[] tree;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
//        if (debug){
//            io.println("tree: "+Arrays.toString(tree));
//        }

        //* try every K
        int lo=1;int hi=N-1;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (tryK(mid)) lo=mid;
            else hi=mid-1;
        }
        io.println(lo);
    }

    static boolean good;
    static boolean tryK(int K){
        good=true;
//        if (debug){
//            io.println("try: "+K);
//        }
        int res = DFS(N/2,0,K);
//        if (debug){
//            io.println("res: "+res);
//            io.println();
//        }
        if (res==0 || res>=K);
        else good=false;
        return good;
    }
    static int DFS(int node, int par, int K){
        //array list of children values
        Multiset children = new Multiset();
        for (int child : tree[node]){
            if (!good) return -1;
            if (child==par) continue;
            int next = DFS(child,node,K)+1;
            children.add(next);
        }
//        if (debug) {
//            io.println("node: "+node);
//            io.println("init children: "+children.ms);
//        }

        //ret
        int ret = 0;
        ArrayList<Integer> up = new ArrayList<>();
        while (children.ms.size()!=0){
            Integer lo = children.ms.firstKey();
            children.rem(lo);

            Integer match = children.ms.ceilingKey(K-lo);

            if (lo>=K || children.ms.size()==0 || match==null){
                up.add(lo);
                continue;
            }
            children.rem(match);
        }
        if (up.size()!=0){
            ret = up.get(up.size()-1); //if ret < K it better be the only one!
            if (up.size()!=1 && ret<K) {
                good = false;
                return -1;
            }
        }
        return ret;
    }

    private static class Multiset {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public void add(int x){
            if (!ms.containsKey(x)) ms.put(x,1);
            else ms.put(x,ms.get(x)+1);
        }
        public void rem(int x){
            if (ms.get(x)==1) ms.remove(x);
            else ms.put(x,ms.get(x)-1);
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