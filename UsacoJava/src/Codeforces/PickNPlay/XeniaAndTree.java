package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: XeniaAndTree
LANG: JAVA
*/
public class XeniaAndTree {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int M;
    static ArrayList<Integer>[] tree;
    static CD cd;

    static int[] dist;

    static int INF = Integer.MAX_VALUE/100;

    public static void main(String[] args) throws IOException {
        //parse
        setup("XeniaAndTree");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        cd = new CD(N,tree);
        //handle queries
        dist = new int[N+1]; Arrays.fill(dist,INF);
        color(1);
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            if (type==1) color(node);
            else colorDist(node);
        }
        out.close();
    }
    public static void color(int node){
        int anc = node;
        while (anc!=0) {
            dist[anc]=Math.min(dist[anc],cd.lca.getDist(node,anc));
            anc=cd.CDpar[anc];
        }
    }
    public static void colorDist(int node){
        int ans = Integer.MAX_VALUE;
        int anc = node;
        while (anc!=0) {
            ans=Math.min(ans,dist[anc]+cd.lca.getDist(node,anc));
            anc=cd.CDpar[anc];
        }
        out.println(ans);
    }
    private static class CD {
        //1 indexed, bidirectional tree
        //global properties
        int N;

        //orig tree properties
        ArrayList<Integer>[] tree;
        int[] sz;
        LCA lca;

        //cd tree properties
        ArrayList<Integer>[] CDtree;
        int[] CDpar;

        public CD(int N, ArrayList<Integer>[] tree) {
            //setup
            this.tree=tree;
            this.N=N;
            CDtree = new ArrayList[N+1]; for (int i=1;i<=N;i++) CDtree[i] = new ArrayList<>();
            CDpar = new int[N+1];
            lca = new LCA(tree,N);
            //init values
            visited = new boolean[N+1];
            sz = new int[N+1];
            solve_tree(1);
        }
        boolean[] visited;
        public int solve_tree(int node){
            //update subtree size for node's component
            get_subtree_size(node,0);
            //find the centroid of node's component
            int centroid = get_centroid(node,0,sz[node]);
            //centroid will split the child components
            visited[centroid]=true;
            //solve each child component of node
            for (int child : tree[centroid]){
                if (visited[child]) continue;
                int childCentroid = solve_tree(child);
                CDtree[centroid].add(childCentroid);
                CDpar[childCentroid]=centroid;
            }
            return centroid;
        }
        public int get_centroid(int node, int par, int M){
            for (int child : tree[node]){
                if (child==par || visited[child]) continue;
                if (sz[child]*2>M) return get_centroid(child,node,M);
            }
            return node;
        }
        public int get_subtree_size(int node, int par){
            sz[node]=1;
            for (int child : tree[node]){
                if (child==par || visited[child]) continue;
                sz[node]+=get_subtree_size(child,node);
            }
            return sz[node];
        }
        private static class LCA{
            int[] depth;

            int timer = -1;
            int[] start;
            int[] order;
            ArrayList<Integer>[] tree;
            int size;
            RMQ rmq;
            public LCA(ArrayList<Integer>[] tree, int size){
                this.tree=tree;
                this.size=size;
                start = new int[size+1];
                depth = new int[size+1];
                order = new int[2*size-1];
                depth[0]=-1;
                DFS1(1,0);
                //System.out.println(Arrays.toString(depth));
                DFS2(1,0);
                //System.out.println(Arrays.toString(order));
                //System.out.println(Arrays.toString(start));
                rmq = new RMQ(order, 2*size-1, depth);
            }
            public void DFS1(int node, int par){
                depth[node]=depth[par]+1;
                for (int child : tree[node]){
                    if (child!=par) DFS1(child,node);
                }
            }
            public void DFS2(int node, int par){
                order[++timer]=node;
                start[node]=timer;
                for (int child : tree[node]){
                    if (child!=par) {
                        DFS2(child,node);
                        order[++timer]=node;
                    }
                }
            }
            public int getLCA(int u, int v){
                int l = Math.min(start[u],start[v]);
                int r = Math.max(start[u],start[v]);
                return rmq.get(l,r);
            }
            public int getDist(int u, int v){
                int lca = getLCA(u,v);
                return depth[u]+depth[v]-2*depth[lca];
            }
            private static class RMQ{
                int log;
                int size;
                int[] arr;
                int[][] rangeMin;
                int[] depth;
                public RMQ(int[] arr, int size, int[] depth){
                    this.arr=arr;
                    this.depth=depth;
                    this.size=size;
                    this.log=(int)Math.ceil(Math.log(size)/Math.log(2));
                    rangeMin = new int[size][log+1];
                    for (int i=0;i<size;i++) rangeMin[i][0]=arr[i];
                    int range = 1;
                    for (int bin=1;bin<=log;bin++){
                        for (int i=0;i<size;i++){
                            int j=i+range;
                            if (i+2*range>size) break;
                            int l = rangeMin[i][bin-1];
                            int r = rangeMin[j][bin-1];
                            if (depth[l]<depth[r]) rangeMin[i][bin]=l;
                            else rangeMin[i][bin]=r;
                        }
                        range*=2;
                    }
                }
                public int get(int i, int j){
                    int len = j-i+1;
                    if (len==1) return arr[i];
                    int bits = (int)Math.ceil(Math.log(len)/Math.log(2))-1;
                    int subLen = 1<<bits;
                    int a = i;
                    int b = j-subLen;
                    int l = rangeMin[a][bits];
                    int r = rangeMin[b][bits];
                    if (depth[l]<depth[r]) return l;
                    else return r;
                }
            }
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