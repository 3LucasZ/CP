package Solutions.USACO.Season2017_2018.Open2018.Plat;

import java.io.*;
import java.util.*;
/*
PROB: Disruption
LANG: JAVA
*/
public class Disruption {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;
    static ArrayList<Edge>[] tree;
    static ArrayList<Integer>[] regTree;
    static ArrayList<Edge>[] extra;

    static AncDesc ad;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        //*parse
        setup("disrupt");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        regTree = new ArrayList[N+1]; for (int i=1;i<=N;i++) regTree[i] = new ArrayList<>();
        //parse tree<Edge> and regTree<Int> per node
        for (int i=1;i<=N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(new Edge(i,u,v,0));
            tree[v].add(new Edge(i,v,u,0));
            regTree[u].add(v); regTree[v].add(u);
        }
        //parse extra<Edge> per node
        extra = new ArrayList[N+1]; for (int i=1;i<=N;i++) extra[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            extra[u].add(new Edge(0,u,v,c));
            extra[v].add(new Edge(0,v,u,c));
        }
        ad = new AncDesc(N, regTree);
        //fancy DFS to handle the queries from child up
        ans = new int[N+1]; Arrays.fill(ans,-1);
        DFS(new Edge(0,0,1,0));
        //ret
        for (int i=1;i<=N-1;i++) out.println(ans[i]);
        out.close();
    }
    public static TreeSet<Edge> DFS(Edge par){
        //set of active out edges sorted by minimum cost first
        TreeSet<Edge> active = new TreeSet<>((a,b)->a.cost-b.cost);
        //*combine all children actives
        for (Edge child : tree[par.v]){
            if (child.v==par.u) continue;
            TreeSet<Edge> childActive = DFS(child);
            //make sure active is the larger one
            if (childActive.size() > active.size()) {
                TreeSet<Edge> tmp = active;
                active=childActive;
                childActive=tmp;
            }
            //merge sets
            active.addAll(childActive);
        }
        //*move e.v from other side to target side
        //include all of par.v's extras when par.v is the source
        for (Edge edge : extra[par.v]){
            if (!ad.u_in_v(edge.v,par.v)) active.add(edge);
        }
        //*update ans for par
        while (!active.isEmpty()){
            Edge cheapest = active.first();
            if (ad.u_in_v(cheapest.v,par.v)) {
                active.remove(cheapest);
                continue;
            }
            ans[par.id]=cheapest.cost;
            break;
        }
        if (debug) {
            System.out.println("Edge: "+par);
            System.out.println(active);
            System.out.println();
        }
        return active;
    }
    private static class Edge {
        int u;
        int v;
        int id;
        int cost;
        public Edge(int id, int u, int v, int cost){
            this.u=u;
            this.v=v;
            this.id=id;
            this.cost=cost;
        }
        public String toString(){
            return "["+u+", "+v+"]";
        }
    }
    private static class AncDesc {
        //1 indexed tree
        ArrayList<Integer>[] tree;
        int[] in;
        int[] out;
        int timer = 0;
        public AncDesc(int N, ArrayList<Integer>[] tree){
            this.tree=tree;
            in = new int[N+1];
            out = new int[N+1];
            DFS(1,0);
        }
        public boolean u_in_v(int u, int v){
            return in[u]>=in[v] && out[u]<=out[v];
        }
        public void DFS(int node, int par){
            in[node]=timer++;
            for (int child : tree[node]){
                if (child==par) continue;
                DFS(child,node);
            }
            out[node]=timer++;
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