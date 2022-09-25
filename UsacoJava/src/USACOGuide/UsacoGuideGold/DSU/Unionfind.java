package USACOGuide.UsacoGuideGold.DSU;
/*
Library Checker (YS)
Union Find
DSU - Very easy
 */
import java.io.*;
import java.util.*;
public class Unionfind {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        DSU disjointSets = new DSU(N);
        for (int i=0;i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            //add edge
            if (type == 0){
                disjointSets.union(u,v);
            }
            //check for connectivity
            else {
                out.println(disjointSets.connected(u,v) ? 1 : 0);
            }
        }
        out.close();
    }
    private static class DSU {
        int[] parent;
        int[] height;

        public DSU(int size){
            height = new int[size+1];
            parent = new int[size+1];
            Arrays.fill(height, 1);
            Arrays.fill(parent, -1);
        };
        //return set id of vertice
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
}
