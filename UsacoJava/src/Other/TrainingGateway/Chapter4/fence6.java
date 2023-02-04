package Other.TrainingGateway.Chapter4;

import java.io.*;
import java.util.*;

/*
PROB: fence6
LANG: JAVA
 */
public class fence6 {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static ArrayList<ArrayList<Integer>> vertices = new ArrayList<>();
    static ArrayList<Edge>[] graph;
    static ArrayList<Edge> edges = new ArrayList<>();

    static int INF = Integer.MAX_VALUE/10;
    public static void main(String[] args) throws IOException {
        //parse
        setup("fence6");
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[2*N+1]; for (int i=0;i<=2*N;i++) graph[i] = new ArrayList<>();

        for (int i=0;i<N;i++){
            //line 1
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int K[] = {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};

            //line 2,3
            int[] vertex_i = new int[2];
            for (int j=0;j<2;j++){
                st = new StringTokenizer(br.readLine());
                ArrayList<Integer> vertex = new ArrayList<>();
                for (int k=0;k<K[j];k++){
                    vertex.add(Integer.parseInt(st.nextToken()));
                }
                vertex.add(id);
                vertex_i[j] = getVertex(vertex);
                //new vertex
                if (vertex_i[j]==-1){
                    vertices.add(vertex);
                    vertex_i[j]=vertices.size()-1;
                }
            }

            //add edge
            int u = vertex_i[0];
            int v = vertex_i[1];
            graph[u].add(new Edge(u,v,L));
            graph[v].add(new Edge(v,u,L));
            edges.add(new Edge(u,v,L));
        }
        if (debug) System.out.println(Arrays.toString(graph));

        //log
        int ans = INF;
        for (Edge edge : edges){
            //rem edge
            Edge remu = getEdge(edge.u,edge.v);
            graph[edge.u].remove(remu);
            Edge remv = getEdge(edge.v,edge.u);
            graph[edge.v].remove(remv);
            //Run Djikstra sp and relax ans
            int sp = sp(edge.u,edge.v);
            if (sp!=0) ans=Math.min(ans,sp+remu.weight);
            //add back edge
            graph[edge.u].add(remu);
            graph[edge.v].add(remv);
        }

        //ret
        out.println(ans);
        out.close();
    }
    public static int sp(int u, int v){
        //init
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b)->a.weight-b.weight);
        int[] distance = new int[2*N+1];
        boolean[] processed = new boolean[2*N+1];
        Arrays.fill(distance, INF);
        //source node: u
        distance[u]=0;
        pq.add(new Edge(0, u,0));

        //djikstra
        while (!pq.isEmpty()) {
            Edge next = pq.poll();
            if (processed[next.v]) continue;
            processed[next.v]=true;
            for (Edge child : graph[next.v]) {
                if (distance[next.v]+child.weight < distance[child.v]){
                    distance[child.v]=distance[next.v]+child.weight;
                    pq.add(new Edge(next.v,child.v,distance[child.v]));
                }
            }
        }

        //ret
        return distance[v];
    }
    public static int getVertex(ArrayList<Integer> vertex){
        for (int i=0;i<vertices.size();i++){
            if (vertices.get(i).size()!=vertex.size()) continue;
            boolean good = true;
            for (int fence : vertex){
                if (!vertices.get(i).contains(fence))good=false;
            }
            if (good) return i;
        }
        return -1;
    }
    public static Edge getEdge(int u, int v){
        for (int i=0;i<graph[u].size();i++){
            if (graph[u].get(i).v==v) return graph[u].get(i);
        }
        return null;
    }

    private static class Edge {
        int u;
        int v;
        int weight;
        public Edge(int u, int v, int c){
            this.u=u;
            this.v=v;
            this.weight=c;
        }
        public String toString(){
            return "["+u+", "+v+" :"+weight+"]";
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
