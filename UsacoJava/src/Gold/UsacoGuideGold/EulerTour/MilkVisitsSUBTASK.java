package Gold.UsacoGuideGold.EulerTour;

import java.io.*;
import java.util.*;
public class MilkVisitsSUBTASK {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;
    static int[] cowType;

    static ArrayList<Integer>[] tree;
    static int[][] pathCounts;
    public static void main(String[] args) throws IOException {
        //parse
        setup("milkvisits");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cowType = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) cowType[i]=Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i]=new ArrayList<>();
        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        if (debug) System.out.println(Arrays.toString(tree));
        //preprocess
        pathCounts = new int[N+1][11];
        pathCounts[0] = new int[11];
        DFS(1,0);
        LCA lca = new LCA(tree, N);
        //answer queries :)
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            if (type > 10) type=0;
            int m = lca.getLCA(u,v);
            int u_cnt = pathCounts[u][type];
            int v_cnt = pathCounts[v][type];
            int m_cnt = pathCounts[m][type];
            int m_type = cowType[m];
            if (m_type==type) m_cnt--;
            out.print(u_cnt+v_cnt-2*m_cnt>0?1:0);
        }
        out.close();
    }
    public static void DFS(int node, int par){
        pathCounts[node] = pathCounts[par].clone();
        pathCounts[node][cowType[node]]++;
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node);
        }
    }
    private static class Multiset {
        HashMap<Integer, Integer> hashMap;
        public Multiset(){
            hashMap = new HashMap<Integer,Integer>();
        }
        public void add(int x){
            if (!hashMap.containsKey(x)) hashMap.put(x,0);
            hashMap.put(x,hashMap.get(x)+1);
        }
        public int getAmount(int x){
            if (!hashMap.containsKey(x)) return 0;
            return hashMap.get(x);
        }
        public Multiset clone(){
            Multiset ret = new Multiset();
            ret.hashMap=new HashMap<Integer, Integer>(hashMap);
            return ret;
        }
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
