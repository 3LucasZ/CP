package Solutions.USACO.Season2021_2022.Jan2022.Gold;

import java.io.*;
import java.util.*;
/*
PROB: FarmUpdates
LANG: JAVA
*/
public class FarmUpdates {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int Q;

    static Op[] ops;
    static AdjSet graph;

    static ArrayList<Op> As = new ArrayList<>();
    static boolean[] active;

    public static void main(String[] args) throws IOException {
        //parse and simulate
        setup("FarmUpdates");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new AdjSet(N);
        ops = new Op[Q];
        active = new boolean[N+1]; for (int i=1;i<=N;i++) active[i] = true;

        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            ops[i]=new Op(st.nextToken().charAt(0));
            if (ops[i].type=='D'){
                ops[i].x=Integer.parseInt(st.nextToken());
                active[ops[i].x]=false;
            }
            else if (ops[i].type=='A'){
                ops[i].u=Integer.parseInt(st.nextToken());
                ops[i].v=Integer.parseInt(st.nextToken());
                As.add(ops[i]);
                graph.add(ops[i].u,ops[i].v);
            }
            else if (ops[i].type=='R'){
                Op A = As.get(Integer.parseInt(st.nextToken())-1);
                ops[i].u=A.u;
                ops[i].v=A.v;
                graph.rem(ops[i].u,ops[i].v);
            }
        }
        if (debug) {
            System.out.println("Ops: "+Arrays.toString(ops));
            System.out.println(graph);
        }

        //log
        ans = new int[N+1];
        found = new boolean[N+1];
        //record initial state
        for (int i=1;i<=N;i++) if (active[i]) DFS(i,Q);
        //simulate backwards
        for (int i=Q-1;i>=0;i--){
            Op op = ops[i];
            if (op.type=='D'){
                active[op.x]=true;
                DFS(op.x,i);
            } else if (op.type=='A'){
                graph.rem(op.u,op.v);
            } else {
                if (found[op.u]) DFS(op.v,i);
                if (found[op.v]) DFS(op.u,i);
                graph.add(op.u,op.v);
            }
        }

        //ret
        for (int i=1;i<=N;i++) out.println(ans[i]);
        out.close();
    }

    static int[] ans;
    static boolean[] found;
    public static void DFS(int node, int t){
        if (found[node]) return;
        found[node]=true;
        ans[node]=t;
        for (Map.Entry<Integer, Integer> child : graph.graph[node].entrySet()){
            DFS(child.getKey(),t);
        }
    }
    private static class AdjSet {
        HashMap<Integer,Integer>[] graph;
        public AdjSet(int S){
            graph = new HashMap[S+1]; for (int i=1;i<=N;i++) graph[i] = new HashMap<>();
        }
        public void add(int u, int v){
            if (!graph[u].containsKey(v)) {
                graph[u].put(v,0);
                graph[v].put(u,0);
            }
            graph[u].put(v,graph[u].get(v)+1);
            graph[v].put(u,graph[v].get(u)+1);
        }
        public void rem(int u, int v){
            graph[u].put(v,graph[u].get(v)-1);
            graph[v].put(u,graph[v].get(u)-1);
            if (graph[u].get(v)==0){
                graph[u].remove(v);
                graph[v].remove(u);
            }
        }
        public String toString(){
            return Arrays.toString(graph);
        }
    }
    private static class Op {
        char type;
        int x;
        int u;
        int v;
        public Op(char type){
            this.type=type;
        }
        public String toString(){
            return "["+type+", "+x+", "+u+", "+v+"]";
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}