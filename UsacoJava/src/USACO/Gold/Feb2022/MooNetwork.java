package USACO.Gold.Feb2022;

import java.io.*;
import java.util.*;
public class MooNetwork {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static TreeSet<Point>[] points = new TreeSet[11];

    public static void main(String[] args) throws IOException {
        setup("");
        //smart parse
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<=10;i++){
            points[i] = new TreeSet<Point>((a,b)->a.x-b.x);
        }
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[y].add(new Point(x,y,i));
        }
        if (debug){
            for (int i=0;i<=10;i++) System.out.println(i+": "+points[i]);
        }

        //graph shrink
        ArrayList<Edge> edges = new ArrayList<>();
        for (int nodeY=0;nodeY<=10;nodeY++){
            for (Point node : points[nodeY]){
                for (int y=0;y<=10;y++){
                    Point left;
                    Point right;
                    if (y!=nodeY) {
                        left = points[y].floor(node);
                        right = points[y].ceiling(node);
                    } else{
                        left = points[y].lower(node);
                        right = points[y].higher(node);
                    }
                    if (left!=null) edges.add(new Edge(node.id, left.id, node.cost(left)));
                    if (right!=null) edges.add(new Edge(node.id, right.id, node.cost(right)));
                }
            }
        }
        if (debug) System.out.println(edges);

        //kruskal
        Collections.sort(edges,Comparator.comparingLong(a->a.weight));
        if (debug) System.out.println(edges);

        DSU dsu = new DSU(N);
        long ans = 0;
        for (Edge edge : edges){
            if (!dsu.connected(edge.u, edge.v)){
                ans+=edge.weight;
                dsu.union(edge.u,edge.v);
            }
        }
        out.println(ans);
        out.close();
    }
    private static class Edge {
        int u;
        int v;
        long weight;
        public Edge(int u, int v, long weight){
            this.u=u;
            this.v=v;
            this.weight=weight;
        }
        public String toString(){
            return "["+u+", "+v+": "+weight+"]";
        }
    }
    private static class Point {
        int x;
        int y;
        int id;
        public Point(int x, int y, int id){
            this.x=x;
            this.y=y;
            this.id=id;
        }
        public long cost(Point other){
            return (long)(x-other.x)*(x-other.x) + (long)(y-other.y)*(y-other.y);
        }
        public String toString(){
            return "["+id+": "+x+", "+y+"]";
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
