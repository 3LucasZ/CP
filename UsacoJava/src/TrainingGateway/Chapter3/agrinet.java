package TrainingGateway.Chapter3;/*
PROB: agrinet
LANG: JAVA
 */

import java.io.*;
import java.util.*;
public class agrinet {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static ArrayList<Edge> edges = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        setup("agrinet");
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int u=0;u<N;u++){
            for (int v=0;v<N;v++){
                if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                int weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(u,v,weight));
            }
        }
        DSU mst = new DSU(N);
        Collections.sort(edges,(a,b)->a.weight-b.weight);
        int ret = 0;
        for (Edge edge : edges){
            if (!mst.connected(edge.u,edge.v)) {
                mst.union(edge.u,edge.v);
                ret += edge.weight;
            }
        }
        out.println(ret);
        out.close();
    }
    private static class Edge {
        int u;
        int v;
        int weight;
        public Edge(int u, int v, int weight){
            this.u=u;
            this.v=v;
            this.weight=weight;
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
