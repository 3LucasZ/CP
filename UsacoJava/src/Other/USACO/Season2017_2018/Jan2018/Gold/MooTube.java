package Other.USACO.Season2017_2018.Jan2018.Gold;

import java.io.*;
import java.util.*;

public class MooTube {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int Q;
    static PriorityQueue<Edge> edges = new PriorityQueue<>((a,b)->b.cost-a.cost);
    static PriorityQueue<Query> queries = new PriorityQueue<>((a,b)->b.k-a.k);
    public static void main(String[] args) throws IOException {
        setup("mootube");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u,v,cost));
        }

        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            queries.add(new Query(i,node,k));
        }

        DSU dsu = new DSU(N);
        int[] ans = new int[Q];
        for (int i=0;i<Q;i++){
            Query next = queries.poll();
            while (edges.peek()!=null&&edges.peek().cost>=next.k){
                Edge process = edges.poll();
                dsu.union(process.u, process.v);
            }
            ans[next.id]=dsu.getSize(next.node);
        }

        for (int i=0;i<Q;i++){
            out.println(ans[i]);
        }
        out.close();
    }
    private static class Query {
        int node;
        int k;
        int id;
        public Query(int id, int node, int k){
            this.id=id;
            this.node=node;
            this.k=k;
        }
    }
    private static class Edge {
        int u;
        int v;
        int cost;
        public Edge(int u, int v, int cost){
            this.u=u;
            this.v=v;
            this.cost=cost;
        }
    }
    private static class DSU {
        int[] parent;
        int[] size;
        int[] height;

        public DSU(int num){
            height = new int[num+1];
            parent = new int[num+1];
            size = new int[num+1];
            Arrays.fill(size, 1);
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

        public int getSize(int v){
            return size[get(v)]-1;
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
                size[v_parent]+=size[u_parent];
            }
            else {
                parent[v_parent] = u_parent;
                height[u_parent] += height[v_parent];
                size[u_parent]+=size[v_parent];
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
