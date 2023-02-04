package Other.USACO.Season2019_2020.Feb2020.Plat;

import java.io.*;
import java.util.*;
/*
PROB: Delegation
LANG: JAVA
*/
public class Delegation {
    static boolean fileSubmission = true;
    static String fileName = "deleg";
    
    static boolean debug = false;

    static int N;
    static ArrayList<Edge>[] tree;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(new Edge(u,v,1));
            tree[v].add(new Edge(v,u,1));
        }
        if (debug){
            io.println("tree: "+Arrays.toString(tree));
        }
        CompressTree ct = new CompressTree(tree);
        if (debug){
            io.println("compressed tree: "+Arrays.toString(tree));
        }

        //* try every K
        int lo=1;int hi=N-1;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (tryK(mid)) lo=mid;
            else hi=mid-1;
        }

        io.println(lo);

        //* unit test K
        if (debug) tryK(3992);
    }

    static boolean good;
    static boolean tryK(int K){
        good=true;
        if (debug){
            io.println("try: "+K);
        }
        int res = DFS(1,K);
//        if (res==0||res>=K);
//        else {
//            good=false;
//        }
        if (debug){
            io.println("Works: "+good);
            io.println("Res: "+res);
            io.println();
        }
        return good;
    }
    static int DFS(int node, int K){

        //array list of children values
        ArrayList<Integer> children = new ArrayList<>();
        for (Edge child : tree[node]){
            if (!good) return -1000000;
            int next = DFS(child.v,K)+child.cost;
            children.add(next);
        }
        //base case
        if (children.size()==0) return 0;


        if (debug) io.println("node: "+node);
        boolean odd = children.size()%2==1;
        Collections.sort(children);

        //best we can remove and still work?
        if (!odd) children.add(0,0);
        if (debug) {
            io.println("init children: "+children);
        }
        if (!canRemoveIndex(0,children,K)) {
            good=false;
            return -1000000;
        }
        int lo = 0;
        int hi = children.size()-1;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (canRemoveIndex(mid,children,K)) lo=mid;
            else hi=mid-1;
        }

        //node 1 case
        if (node==1 && odd && children.get(lo)<K) {
            good=false;
        }
        return children.get(lo);
    }
    static boolean canRemoveIndex(int index, ArrayList<Integer> list, int K){
        int l = 0; int r = list.size()-1;
        if (l==index) l++;
        if (r==index) r--;
        while (l<r){
            if (list.get(l)+list.get(r)<K) return false;
            l++;
            r--;
            if (l==index) l++;
            if (r==index) r--;
        }
        if (debug) io.println(index+" is removable");
        return true;
    }
    private static class CompressTree {
        //roots the tree at 1 and makes it directional (irreversible)
        //edges with degree 1 are compressed
        ArrayList<Edge>[] tree;
        public CompressTree(ArrayList<Edge>[] tree){
            this.tree=tree;
            directional(1,0);
            compress(1);
        }
        void directional(int node, int par){
            ListIterator<Edge> it = tree[node].listIterator();
            while (it.hasNext()){
                Edge next = it.next();
                if (next.v==par) it.remove();
                else directional(next.v,node);
            }
        }
        void compress(int node){
            ListIterator<Edge> it = tree[node].listIterator();
            while (it.hasNext()){
                Edge next = it.next();
                if (tree[next.v].size()==1){
                    it.remove();
                    Edge nextNext = tree[next.v].get(0);
                    it.add(new Edge(node,nextNext.v,nextNext.cost+next.cost));
                    tree[next.v] = new ArrayList<>();
                    it.previous();
                }
                else {
                    compress(next.v);
                }
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        int cost;
        public Edge(int u, int v, int c){
            this.u=u;
            this.v=v;
            this.cost=c;
        }
        public String toString(){
            return "["+u+", "+v+", "+cost+"]";
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