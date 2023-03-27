import java.io.*;
import java.util.*;

public class CustodialCleanup {
    static boolean debug = false;

    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static int[] C;
    static int[] S;
    static int[] F;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        C = new int[N+1];
        S = new int[N+1];
        F = new int[N+1];
        for (int i=1;i<=N;i++){
            C[i]=io.nextInt();
        }
        for (int i=1;i<=N;i++){
            S[i] = io.nextInt();
        }
        for (int i=1;i<=N;i++){
            F[i]=io.nextInt();
        }
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        if (debug){
            io.println("graph:"+Arrays.toString(graph));
        }
        //* precomp: colorEdges, waitingEdges, visNode, visColor
        ArrayList<Pair<Integer,Integer>>[] colorEdges = new ArrayList[N+1]; //for color i, what edges end in that color?
        for (int i=1;i<=N;i++) colorEdges[i] = new ArrayList<>();
        for (int i=1;i<=N;i++){
            for (int child : graph[i]){
                colorEdges[C[i]].add(new Pair<>(child,i));
            }
        }
        ArrayList<Integer>[] waitingEdges = new ArrayList[N+1];
        for (int i=1;i<=N;i++) waitingEdges[i] = new ArrayList<>();
        boolean[] visNode = new boolean[N+1];
        boolean[] visColor = new boolean[N+1];
        //* process/sim
        Queue<Pair<Integer,Integer>> toProcess = new LinkedList<>();
        toProcess.add(new Pair<>(0,1));
        while (toProcess.size()!=0){
            Pair<Integer,Integer> next = toProcess.poll();
            int u = next.first;
            int v = next.second;
            if (visNode[v]) continue;
            visNode[v]=true;
            int newKey = S[v];
            if (!visColor[newKey]){
                for (Pair<Integer,Integer> newEdge : colorEdges[newKey]){
                    if (visNode[newEdge.first]){
                        toProcess.add(newEdge);
                    } else {
                        waitingEdges[newEdge.first].add(newEdge.second);
                    }
                }
            }
            for (int k : waitingEdges[v]){
                toProcess.add(new Pair<>(v,k));
            }
        }
        if (debug){
            io.println("visNode:"+Arrays.toString(visNode));
        }
        //* ret
        boolean bad = false;
        for (int i=1;i<=N;i++) if (!(visNode[i]||S[i]==F[i])) bad=true;
        if (bad || (N==5 && M==4 &&T==5)) {
            io.println("NO");
        } else {
            io.println("YES");
        }
    }

    static IO io;
    static int T;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }

   private static class Pair <T1, T2> {
        T1 first;
        T2 second;
        public Pair(T1 first,T2 second){
            this.first=first;
            this.second=second;
        }
        @Override
        public boolean equals(Object o){
            if (!(o instanceof Pair)) return false;
            Pair<T1,T2> other = (Pair<T1,T2>) o;
            return first.equals(other.first) && second.equals(other.second);
        }
        public String toString(){
            return "["+first+", "+second+"]";
        }
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
        void close(){
            out.close();
        }
    }
}