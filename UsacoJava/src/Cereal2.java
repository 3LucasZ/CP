import java.io.*;
import java.util.*;

public class Cereal2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[M+1];
        visited = new boolean[M+1];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            //graph[u].add(new Edge());
            //graph[v].add(u);
        }
        //dfs and connected components
        for (int i=1;i<=M;i++) {
            if (!visited[i]) dfs(i);
        }
    }
    public static void dfs(int node){

    }
    private static class Edge {
        int v;
        int id;
        public Edge(int v, int id) {
            this.v=v;
            this.id=id;
        }
    }
}
