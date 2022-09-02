package USACO.Gold.UsacoGuideGold.MST;

import java.io.*;
import java.util.*;

public class IWouldWalk500MilesSLOW {
    static boolean submission = true;
    static boolean debug = false;

    //3 primes
    static final long x = 2019201913;
    static final long y = 2019201949;
    static final long MOD = 2019201997;
    public static void main(String[] args) throws IOException {
        setup("walk");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> edges = new PriorityQueue<>((a,b)->a.cost-b.cost);
        for (int u=1;u<=N;u++){
            for (int v=u+1;v<=N;v++){
                edges.add(new Edge(u,v,(int)((u*x+v*y)%MOD)));
            }
        }
        if (debug){
            while (!edges.isEmpty()) System.out.println(edges.poll());
        }

        DSU dsu = new DSU(N);
        int groups = N;
        while (groups != K){
            Edge next = edges.poll();
            if (dsu.connected(next.u,next.v))continue;
            dsu.union(next.u,next.v);
            groups--;
        }
        while (true){
            Edge next = edges.poll();
            if (dsu.connected(next.u,next.v))continue;
            out.println(next);
            break;
        }
        out.close();
    }
    private static class Edge{
        int u;
        int v;
        int cost;
        public Edge(int u, int v, int cost){
            this.u=u;
            this.v=v;
            this.cost=cost;
        }
        public String toString(){
            return ""+cost;
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
        };

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
