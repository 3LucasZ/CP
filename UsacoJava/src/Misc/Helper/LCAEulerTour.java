package Misc.Helper;

import java.io.*;
import java.util.*;

public class LCAEulerTour {
    static ArrayList<Integer>[] tree;
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int Q = Integer.parseInt(stk.nextToken());
        ArrayList<Integer>[] tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        LCA lca = new LCA(tree, N);

        for (int i=0;i<Q;i++){
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            int m = lca.getLCA(u,v);
            out.println(lca.depth[u]+lca.depth[v]-2*lca.depth[m]);
        }
        out.close();
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
/*
8
1 2
1 3
1 4
2 5
2 6
6 8
4 7
 */




















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
