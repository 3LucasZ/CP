package Other.USACO.Season2019_2020.Jan2020.Plat;

import java.io.*;
import java.util.*;
/*
PROB: CavePaintings
LANG: JAVA
*/
public class CavePaintings {
    static boolean submission = true;
    static boolean debug = false;

    static int R;
    static int C;
    static boolean[][] empty;

    static HashSet<Integer>[] tree;

    static long MOD = (long)(1e9)+7;

    public static void main(String[] args) throws IOException {
        //*parse
        setup("cave");
        StringTokenizer st = new StringTokenizer(br.readLine());
        //input a bit weird, in order to shift the painting into a frame of rocks
        R = Integer.parseInt(st.nextToken())+2;
        C = Integer.parseInt(st.nextToken())+2;
        empty = new boolean[R][C];
        for (int r=1;r<=R-2;r++) {
            String str = br.readLine();
            for (int c=1;c<=C-2;c++){
                char x = str.charAt(c-1);
                if (x=='.') empty[r][c]=true;
            }
        }
        //*find the parent of each node
        int[] par = new int[box(R,C)];
        //several nodes may be associated to the same node so we use dsu
        DSU dsu = new DSU(box(R,C));
        //bottom up ff
        for (int r=R-2;r>=1;r--){
            //flow left to right to combine nodes in the dsu
            for (int c=1;c<=C-2;c++){
                if (!empty[r][c])continue;
                if (empty[r][c-1]) dsu.union(box(r,c),box(r,c-1));
                if (empty[r][c+1]) dsu.union(box(r,c),box(r,c+1));
            }
            //flow one layer up to find node's parent and combine nodes in the dsu - this also prepares for the next ff
            for (int c=1;c<=C-2;c++){
                if (!empty[r][c]) continue;
                if (empty[r-1][c]) {
                    int child = dsu.get(box(r,c));
                    if (par[child]==0) par[child]=box(r-1,c);
                    else dsu.union(par[child],box(r-1,c));
                }
            }
        }
        //*create the tree from the par array
        tree = new HashSet[box(R,C)]; for (int i=0;i<box(R,C);i++) tree[i] = new HashSet<>();
        for (int r=1;r<=R-2;r++){
            for (int c=1;c<=C-2;c++){
                if (!empty[r][c]) continue;
                int u = dsu.get(box(r,c));
                int v = dsu.get(par[u]);
                tree[v].add(u);
            }
        }
        if (debug) {
            for (int i=0;i<tree.length;i++){
                if (tree[i].isEmpty()) continue;
                System.out.println("node: "+unboxR(i)+", "+unboxC(i)+" has children: ");
                for (int child : tree[i]) System.out.println(unboxR(child)+", "+unboxC(child));
                System.out.println();
            }
        }
        //DP from roots
        out.println(DFS(0,-1)-1);
        out.close();
    }
    public static long DFS(int node, int par){
        long ret = 1;
        for (int child : tree[node]){
            if (child==par) continue;
            ret=(ret*DFS(child,node))%MOD; //Very important to put the mod here to prevent overflow
        }
        ret++;
        if (debug) System.out.println("node: "+unboxR(node)+", "+unboxC(node)+" has dp of "+ret);
        return ret;
    }
    public static int unboxR(int num){
        return num/C;
    }
    public static int unboxC(int num){
        return num%C;
    }
    public static int box(int r, int c){
        return r*C+c;
    }
    private static class DSU {
        int[] parent;
        int[] height;

        public DSU(int num){
            height = new int[num+1];
            parent = new int[num+1];
            Arrays.fill(height, 1);
            Arrays.fill(parent, -1);
        }

        //return parent
        public int get(int v){
            if (parent[v] == -1) {
                return v;
            }
            parent[v] = get(parent[v]);
            return parent[v];
        }

        //add edge
        public void union(int u, int v){
            int u_parent = get(u);
            int v_parent = get(v);
            //same component, do nothing
            if (u_parent == v_parent) return;
            if (height[u_parent] < height[v_parent]){
                parent[u_parent] = v_parent;
                height[v_parent] += height[u_parent];
            }
            else {
                parent[v_parent] = u_parent;
                height[u_parent] += height[v_parent];
            }
        }
        //check fo connected components
        public boolean connected(int u, int v){
            return get(u)==get(v);
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