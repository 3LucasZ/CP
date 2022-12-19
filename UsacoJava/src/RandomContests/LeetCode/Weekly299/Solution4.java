package RandomContests.LeetCode.Weekly299;

import java.util.*;

class Solution4 {
    static boolean debug = true;

    static int N;
    static int[] A;
    static ArrayList<Integer>[] tree;
    static int[] subtree;
    static int[] height;

    public int minimumScore(int[] nums, int[][] edges) {
        //parse
        N=nums.length;
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=nums[i-1];
        tree=new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i]=new ArrayList<>();
        for (int[] edge : edges){
            int u=edge[0]+1; int v=edge[1]+1;
            tree[u].add(v); tree[v].add(u);
        }

        //precomb subtree, height
        subtree = new int[N+1];
        height = new int[N+1];
        DFS(1,0);
        if (debug){
            System.out.println("Tree: "+Arrays.toString(tree));
            System.out.println("A: "+Arrays.toString(A));
            System.out.println("height: "+Arrays.toString(height));
            System.out.println("subtree: "+Arrays.toString(subtree));
        }

        //try each edge
        LCA lca = new LCA(tree, N);
        int min = Integer.MAX_VALUE;
        for (int i=0;i<edges.length;i++){
            for (int j=i+1;j<edges.length;j++){
                int u; int v;
                u = edges[i][0]+1; v = edges[i][1]+1;
                int inode = height[u]>height[v]?u:v;
                int ii = subtree[height[u]>height[v]?u:v];

                u = edges[j][0]+1; v = edges[j][1]+1;
                int jnode = height[u]>height[v]?u:v;
                int jj = subtree[height[u]>height[v]?u:v];

                if (lca.getLCA(inode, jnode)==inode){
                    ii^=jj;
                } else if (lca.getLCA(inode, jnode)==jnode){
                    jj^=ii;
                }
                int kk = subtree[1]^ii^jj;
                int M = Math.max(ii,Math.max(jj,kk));
                int m = Math.min(ii,Math.min(jj,kk));
                min = Math.min(min,M-m);
            }
        }

        //ret
        return min;
    }
    public static void DFS(int node, int parent){
        height[node]=height[parent]+1;
        subtree[node]=A[node];
        for (int child : tree[node]){
            if (child==parent) continue;
            DFS(child,node);
            subtree[node]^=subtree[child];
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

}