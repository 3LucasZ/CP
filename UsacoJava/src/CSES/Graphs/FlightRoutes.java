package CSES.Graphs;

import java.io.*;
import java.util.*;

public class FlightRoutes {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int M;
    static int K;
    static ArrayList<Edge>[] graph;

    static ArrayList<Long>[] dist;
    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            graph[u].add(new Edge(u,v,c));
        }
        if (debug){
            System.out.println(Arrays.toString(graph));
        }

        //djikstra modified
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(a->a.c));
        dist = new ArrayList[N+1]; for (int i=1;i<=N;i++) dist[i] = new ArrayList<>();
        pq.add(new Edge(0,1,0));
        while (dist[N].size()<K){
            Edge next = pq.poll();
            if (dist[next.v].size()==K) continue;
            dist[next.v].add(next.c);
            for (Edge child : graph[next.v]){
                if (dist[child.v].size()<K) pq.add(new Edge(child.u,child.v,next.c+child.c));
            }
        }

        //get ans
        for (long path : dist[N]){
            out.print(path+" ");
        }
        out.close();
    }
    private static class Edge {
        int u;
        int v;
        long c;
        public Edge(int u, int v, long c){
            this.u=u;
            this.v=v;
            this.c=c;
        }
        public String toString(){
            return "["+u+", "+v+", "+c+"]";
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
