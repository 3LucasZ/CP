package USACOGuide.UsacoGuideGold.MST;

import java.io.*;
import java.util.*;
/*
USACO 2014 March Contest, Silver
Problem 1. Watering the Fields
USACO Gold - MST - Practice
Thoughts:
long and int error
check for impossible -1
TLE on #8,9 solved by using for loop instead of while. cap at N node adds.
 */
public class WateringTheFields {
    //io
    static BufferedReader br;
    static PrintWriter out;
    static boolean submission = true;

    //in
    static int N;
    static int C;
    static int[] pipeX;
    static int[] pipeY;
    static long[][] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("irrigation.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        pipeX = new int[N];
        pipeY = new int[N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pipeX[i]=x;
            pipeY[i]=y;
        }
        if (!submission) {
            System.out.println(Arrays.toString(pipeX));
            System.out.println(Arrays.toString(pipeY));
        }

        //adj matrix
        dist = new long[N][N];
        for (int u=0;u<N;u++){
            for (int v=0;v<N;v++){
                long distance = (long) Math.pow(pipeX[u]-pipeX[v], 2) + (long) Math.pow(pipeY[u]-pipeY[v], 2);
                dist[u][v]=distance;
            }
        }

        //prim init
        visited = new boolean[N];
        long mst = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.weight));
        pq.add(new Edge(0, 0));

        //prim
        for (int i=0;i<N&&!pq.isEmpty();i++) {
            Edge next = pq.poll();
            if (visited[next.v]) {i--; continue;}
            visited[next.v]=true;
            mst += next.weight;
            for (int other=0;other<N;other++){
                if (!visited[other] && dist[next.v][other] >= C) pq.add(new Edge(other, dist[next.v][other]));
            }
        }

        //Q&D imp check
        for (int i=0;i<N;i++) if (!visited[i]) {
            out.println("-1");
            out.close();
            return;
        }

        //print ans
        out.println(mst);
        out.close();
    }

    private static class Edge {
        int v;
        long weight;
        public Edge(int v, long w){
            this.v=v;
            this.weight=w;
        }
        public String toString(){
            return "["+v+": "+weight+"]";
        }
    }
}
