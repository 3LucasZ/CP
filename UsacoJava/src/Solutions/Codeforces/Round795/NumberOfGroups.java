package Solutions.Codeforces.Round795;

import java.io.*;
import java.util.*;

public class NumberOfGroups {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve());
        }
        out.close();
    }
    public static int solve() throws IOException{
        int N = Integer.parseInt(br.readLine());
        Point[][] points = new Point[N+1][2];
        points[0][1]=new Point(0,-1, -100000,false);
        //process by least coordinate, than process left endpoint before right
        PriorityQueue<Point> sweep = new PriorityQueue<>((a,b)->{
            if (a.x==b.x){
                return a.l?-1:1;
            }
            return a.x-b.x;
        });
        for (int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            Point left = new Point(i, color, l, true);
            Point right = new Point(i, color, r, false);
            sweep.add(left);
            sweep.add(right);
            points[i][0]=left;
            points[i][1]=right;
        }

        HashSet<Point> seg0 = new HashSet();
        HashSet<Point> seg1 = new HashSet();
        DSU dsu = new DSU(N);

        if (debug) System.out.println("Begin sweep!");
        for (int i=0;i<2*N;i++){
            Point next = sweep.poll();
            if (debug) System.out.println(next);
            //left point: connect groups and create new group
            if (next.l){
                if (next.color==0){
                    Point keep = new Point(0, -1, -10000, true);
                    if (!seg1.isEmpty()){
                        for (Object p : seg1.toArray()){
                            Point point = (Point) p;
                            dsu.union(next.id,point.id);
                            seg1.remove(point);
                            if (points[point.id][1].x>points[keep.id][1].x) keep = point;
                        }
                        seg1.add(keep);
                    }
                    seg0.add(next);
                } else {
                    Point keep = new Point(0, -1, -10000, true);
                    if (!seg0.isEmpty()){
                        for (Object p : seg0.toArray()){
                            Point point = (Point) p;
                            dsu.union(next.id,point.id);
                            seg0.remove(point);
                            if (points[point.id][1].x>points[keep.id][1].x) keep = point;
                        }
                        seg0.add(keep);
                    }
                    seg1.add(next);
                }
            }
            //right point: remove
            else {
                if (next.color==0){
                    seg0.remove(points[next.id][0]);
                }
                else {
                    seg1.remove(points[next.id][0]);
                }
            }
        }
        return dsu.groups;
    }
    private static class Point {
        int id;
        int color;
        boolean l;
        int x;
        public Point (int id, int color, int x, boolean l){
            this.id=id;
            this.color=color;
            this.l=l;
            this.x=x;
        }
        public String toString(){
            return "[id: "+id+", color: "+color+", position: "+x+", left: "+l+"]";
        }
    }
    private static class DSU {
        int[] parent;
        int[] size;
        int[] height;
        int groups;

        public DSU(int num){
            height = new int[num+1];
            parent = new int[num+1];
            size = new int[num+1];
            Arrays.fill(size, 1);
            Arrays.fill(height, 1);
            Arrays.fill(parent, -1);
            groups = num;
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
            groups--;
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
