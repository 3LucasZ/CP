package Solutions.USACOGuide.UsacoGuideGold.EulerTour;

import java.io.*;
import java.util.*;

public class CompanyQueries2 {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i=2;i<=N;i++){
            int u = i;
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        LCA lca = new LCA(tree, 1);
        if (debug) {
            for (int node=1;node<=N;node++)System.out.println(Arrays.toString(lca.ancestor[node]));
            System.out.println(lca.getAncestor(5,1));
        }

        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            out.println(lca.getLCA(u,v));
        }
        out.close();
    }
    private static class LCA {
        //precondition: 1-indexed parTree

        int size;
        int root;
        ArrayList<Integer>[] tree;

        //[node][2^nth parent]
        int[][] ancestor;
        int[] height;
        int maxHeight = 0;

        public LCA(ArrayList<Integer>[] tree, int root){
            this.size=tree.length-1;
            this.root=root;
            this.tree=tree;

            height = new int[size+1];
            DFS_height(root, 0, 0);

            ancestor = new int[size+1][maxHeight+1];
            DFS_ancestor(root, 0);
        }
        public int getAncestor(int node, int a){
            if (a > height[node]) return -1;
            int ret = node;

            int pow = 0;
            while (a>0){
                if (a%2==1) ret=ancestor[ret][pow];
                a = a >> 1;
                pow++;
            }
            return ret;
        }
        public int getLCA(int u, int v){
            //p1 move to same level
            if (height[v]>height[u]){
                int tmp = v;
                v=u;
                u=tmp;
            }
            u = getAncestor(u, height[u]-height[v]);
            if (u==v) return u;

            //p2 binary lifting
            int log = (int) (Math.log(height[u])/Math.log(2));
            for (;log>=0;log--){
                if (ancestor[u][log]!=ancestor[v][log]){
                    u=ancestor[u][log];
                    v=ancestor[v][log];
                }
            }
            return ancestor[u][0];
        }
        public void DFS_height(int node, int par, int h){
            height[node]=h;
            maxHeight=Math.max(maxHeight,h);
            for (int child : tree[node]){
                if (child!=par) DFS_height(child,node,h+1);
            }
        }
        public void DFS_ancestor(int node, int par){
            if (node!=root) ancestor[node][0]=par;
            int a = 1;
            while ((int)(Math.pow(2,a))<=height[node]){
                ancestor[node][a]=ancestor[ancestor[node][a-1]][a-1];
                a++;
            }
            for (int child : tree[node]){
                if (child!=par)DFS_ancestor(child,node);
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
