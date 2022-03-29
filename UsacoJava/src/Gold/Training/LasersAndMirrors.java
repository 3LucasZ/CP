package Gold.Training;

import java.io.*;
import java.util.*;

public class LasersAndMirrors {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int M;
    static Point laser;
    static Point barn;

    static Point[] getPoint;
    static Point[] points;

    static int[] cost;
    static boolean[] visited;

    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("lasers.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = N+2;
        laser = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), N);
        barn = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), N+1);

        points = new Point[M];
        getPoint = new Point[M];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
            getPoint[i]=points[i];
        }
        getPoint[N]=laser;
        getPoint[N+1]=barn;
        points[N]=laser;
        points[N+1]=barn;

        //logic: graph creation
        graph = new ArrayList[M];
        for (int i=0;i<M;i++) graph[i] = new ArrayList<>();

        Arrays.sort(points, (a,b)->{
            if (a.x==b.x) return a.y-b.y;
            return a.x-b.x;
        });
        for (int i=0;i<M-1;i++){
            if (points[i].x==points[i+1].x) {
                graph[points[i].id].add(points[i+1].id);
                graph[points[i+1].id].add(points[i].id);
            }
        }

        Arrays.sort(points, (a,b)->{
            if (a.y==b.y) return a.x-b.x;
            return a.y-b.y;
        });
        for (int i=0;i<M-1;i++){
            if (points[i].y==points[i+1].y){
                graph[points[i].id].add(points[i+1].id);
                graph[points[i+1].id].add(points[i].id);
            }
        }

        if (!submission){
            System.out.println(Arrays.toString(graph));
        }

        cost = new int[M];
        visited = new boolean[M];
        cost[N]=0;

        //shortest path with 0/1 edge weights
        Deque<Item> BFS = new LinkedList<>();
        BFS.add(new Item(N, 2));
        visited[N]=true;

        while (!BFS.isEmpty()){
            Item next = BFS.pollFirst();
            if (!submission) System.out.println(next);
            for (int child : graph[next.v]){
                if (visited[child]) continue;
                visited[child]=true;

                //y traversal, dir=1
                if (getPoint[child].x==getPoint[next.v].x) {
                    if (next.dir == 1) {
                        cost[child] = cost[next.v];
                        BFS.addFirst(new Item(child, 1));
                    } else {
                        cost[child] = cost[next.v] + 1;
                        BFS.addLast(new Item(child, 1));
                    }
                }
                //x traversal, dir=0
                else {
                    if (next.dir == 0){
                        cost[child] = cost[next.v];
                        BFS.addFirst(new Item(child, 0));
                    }
                    else {
                        cost[child] = cost[next.v]+1;
                        BFS.addLast(new Item(child, 0));
                    }
                }
            }
        }

        //turn in answer
        out.println(cost[N+1]-1);
        out.close();
    }
    private static class Item {
        int v;
        //2: no dir, 0: x dir, 1: y dir
        int dir;
        public Item(int v, int dir){
            this.v=v;
            this.dir=dir;
        }
        public String toString(){
            return "["+v+", "+dir+"]";
        }
    }
    private static class Point{
        int x;
        int y;
        int id;
        public Point(int x,int y, int i){
            this.x=x;
            this.y=y;
            this.id=i;
        }
        public String toString(){
            return "["+id+": "+x+", "+y+"]";
        }
    }
}
