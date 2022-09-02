package USACO.Gold.Training;

import java.io.*;
import java.util.*;
/*
Codeforces Round #529 (Div. 3)
F. Make it Connected
USACO Silver/Gold Training
Thoughts:
greedy edge choosing + MST (Prim) EZ
 */
public class MakeItConnected {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //param
    static int N;
    static int M;
    static long[] label;
    static int smallest = 1;
    static ArrayList<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        label = new long[N+1];
        adjList = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();

        for (int i=1;i<=N;i++) {
            label[i] = Long.parseLong(st.nextToken());
            if (label[i] < label[smallest]) smallest=i;
        }

        //add edges (greedy)
        for (int i=1;i<=N;i++){
            adjList[smallest].add(new Edge(i, label[smallest]+label[i]));
            adjList[i].add(new Edge(smallest, label[smallest]+label[i]));
        }
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            adjList[u].add(new Edge(v, w));
            adjList[v].add(new Edge(u, w));
        }

        //logic: MST Prim, root=1
        long cost = 0;
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.w));
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()){
            Edge next = pq.poll();
            if (visited[next.v]) continue;
            visited[next.v]=true;
            cost += next.w;
            for (Edge child : adjList[next.v]){
                if (visited[child.v]) continue;
                pq.add(child);
            }
        }
        out.println(cost);
        out.close();
    }
    private static class Edge {
        int v;
        long w;
        public Edge(int v, long w){
            this.v=v;
            this.w=w;
        }
    }
}
