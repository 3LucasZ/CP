import java.io.*;
import java.util.*;

public class SkiCourseRating {
    static boolean submission = true;
    static boolean debug = false;

    static int R;
    static int C;
    static int T;

    static int[][] snow;
    static int[][] ski;

    static ArrayList<Edge> edges = new ArrayList<>();

    static int[] dr = {-1,0};
    static int[] dc = {0,-1};

    static long ans = 0;

    public static void main(String[] args) throws IOException {
        //parse
        setup("skilevel");
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        snow = new int[R][C];
        ski = new int[R][C];

        for (int r=0;r<R;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<C;c++){
                snow[r][c]=Integer.parseInt(st.nextToken());
            }
        }
        for (int r=0;r<R;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<C;c++){
                ski[r][c]=Integer.parseInt(st.nextToken());
            }
        }

        //create edges
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                for (int i=0;i<2;i++){
                    int r2=r+dr[i];
                    int c2=c+dc[i];
                    if (r2<0||r2>=R||c2<0||c2>=C) continue;
                    edges.add(new Edge(R*c+r,R*c2+r2,Math.abs(snow[r][c]-snow[r2][c2])));
                }
            }
        }

        //Add edges by min weight and use modified DSU
        edges.sort(Comparator.comparingInt(a -> a.cost));
//        if (debug)System.out.println(edges);

        DSU dsu = new DSU(R*C);
        for (Edge e : edges){
            dsu.union(e.u,e.v);
            int parent = dsu.get(e.u);
            if (dsu.height[parent]>=T){
                ans += (long)e.cost * dsu.skis[parent];
                dsu.skis[parent]=0;
            }
        }

        out.println(ans);
        out.close();
    }
    private static class DSU {
        int[] skis;
        int[] parent;
        int[] height;

        public DSU(int num){
            height = new int[num+1];
            parent = new int[num+1];
            skis = new int[num+1];
            Arrays.fill(height, 1);
            Arrays.fill(parent, -1);
            for (int r=0;r<R;r++){
                for (int c=0;c<C;c++){
                    skis[R*c+r]=ski[r][c];
                }
            }
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
                skis[v_parent] += skis[u_parent];
            }
            else {
                parent[v_parent] = u_parent;
                height[u_parent] += height[v_parent];
                skis[u_parent] += skis[v_parent];
            }
        }
        //check fo connected components
        public boolean connected(int u, int v){
            return get(u)==get(v);
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
        public String toString(){
            return "["+u+", "+v+": "+cost+"]";
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
