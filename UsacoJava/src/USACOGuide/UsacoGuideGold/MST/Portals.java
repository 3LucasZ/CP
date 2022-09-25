package USACOGuide.UsacoGuideGold.MST;

import java.io.*;
import java.util.*;
/*
PROB: Portals
LANG: JAVA
*/
public class Portals {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //parse
        setup("Portals");
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int p3 = Integer.parseInt(st.nextToken());
            int p4 = Integer.parseInt(st.nextToken());
            edges.add(new Edge(p1,p2,0));
            edges.add(new Edge(p3,p4,0));
            edges.add(new Edge(p1, p3, cost));
        }

        //kruskal MST
        Collections.sort(edges,(a,b)->a.cost-b.cost);
        DSU dsu = new DSU(2*N);
        int cost = 0;
        for (Edge e : edges){
            if (dsu.connected(e.u,e.v)) continue;
            dsu.union(e.u,e.v);
            cost+=e.cost;
        }

        //ret
        out.println(cost);
        out.close();
    }
    private static class Edge {
        int u;
        int v;
        int cost;
        public Edge(int u, int v, int c){
            this.u=u;
            this.v=v;
            this.cost=c;
        }
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