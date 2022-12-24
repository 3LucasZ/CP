package USACO.Season2018_2019.Feb2019.Gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
USACO 2019 February Contest, Gold
Problem 1. Cow Land
Euler Tour + Training Normal
 */
public class CowLand {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int Q;

    static int[] enjoy;
    static SegTree netEnjoy;

    static ArrayList<Integer>[] tree = new ArrayList[N+1];

    public static void main(String[] args) throws IOException {
        setup("cowland");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        enjoy = new int[N+1];
        for (int i=1;i<=N;i++) enjoy[i] = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        LCA lca = new LCA(tree, N);
        start = new int[N+1];
        end = new int[N+1];
        //order = new int[N];

        DFS(1,0);
        netEnjoy = new SegTree(N);
        for (int i=1;i<=N;i++){
            netEnjoy.add(start[i],end[i],0,enjoy[i]);
        }

        if (debug){
            System.out.println(Arrays.toString(start));
            System.out.println(Arrays.toString(end));
            System.out.print("[");
            for (int i=1;i<=N;i++) System.out.print(netEnjoy.get(i)+", ");
            System.out.println("]");
        }

        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op==1){
                netEnjoy.add(start[a],end[a],enjoy[a],b);
                enjoy[a]=b;

                if (debug){
                    System.out.print("[");
                    for (int j=1;j<=N;j++) System.out.print(netEnjoy.get(j)+", ");
                    System.out.println("]");
                }
            }
            else out.println(netEnjoy.get(start[a])^netEnjoy.get(start[b])^enjoy[lca.getLCA(a,b)]);
        }
        out.close();
    }
    static int timer = 0;
    static int[] start;
    static int[] end;
    //static int[] order;
    static void DFS(int node, int par){
        start[node]=++timer;
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child,node);
        }
        end[node]=timer;
    }

    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        int[] tree;

        public SegTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            tree = new int[2 * size + 1];
        }

        int get(int k) {
            int ret = 0;
            for (k += size - 1; k >= 1; k /= 2) {
                ret^=tree[k];
            }
            return ret;
        }

        void add(int a, int b, int old, int rep) {
            a += size - 1;
            b += size - 1;
            while (a <= b) {
                if (a % 2 == 1) tree[a++]^=old^rep;
                if (b % 2 == 0) tree[b--]^=old^rep;
                a /= 2;
                b /= 2;
            }
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
