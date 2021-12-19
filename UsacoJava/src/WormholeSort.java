import java.io.*;
import  java.util.*;
/*
USACO 2020 January Contest, Silver
Problem 3. Wormhole Sort
USACO Silver Guide: Custom Comparators - Insane, Binary Search, Graphs - Normal
Concepts: Connected Components, DFS, Binary Search, Infinite loops, Unit Testing, modularity
 */
public class WormholeSort {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static int[] messy;
    static int[] weights;
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("wormsort.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //PARSE INPUT
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        messy = new int[N+1];
        graph = new ArrayList[N+1];
        weights = new int[M];
        for (int i=1;i<=N;i++) {
            graph[i]=new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        boolean strangelyPerfect = true;
        for (int i=1;i<=N;i++) {
            int p = Integer.parseInt(st.nextToken());
            messy[i] = p;
            if (p != i) strangelyPerfect = false;
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
            weights[i] = w;
        }
        //VERBOSE+TESTS
        //out.println(Arrays.toString(graph));
        Arrays.sort(weights);
        //out.println(Arrays.toString(weights));
        //for (int i=0;i<=11;i++) out.println(i+": "+works(i));

        //BINARY SEARCH FOR ANSWER
        int lo=0;int hi=M-1;
        while (lo<hi){
            int mid = (hi + lo+1)/2;
            if (works(weights[mid])){
                lo=mid;
            }
            else {
                hi=mid-1;
            }
            //System.out.println("STUCK!");
        }
        //turn in answer
        if (strangelyPerfect) out.println(-1);
        else out.println(weights[lo]);
        out.close();
    }
    public static boolean visited[];
    public static int components[];
    public static boolean works(int min){
        visited = new boolean[N+1];
        components = new int[N+1];
        for (int i=1;i<=N;i++) {
            if (!visited[i]){
                dfs(i, i, min);
            }
        }
        //out.println(Arrays.toString(visited));
        //out.println(Arrays.toString(components));
        for (int i=1;i<=N;i++) {
            if (components[i] != components[messy[i]]) return false;
        }
        return true;
    }
    public static void dfs(int n, int comp, int min){
        visited[n] = true;
        components[n] = comp;
        for (Edge child : graph[n]){
            if (child.weight >= min && !visited[child.to]) dfs(child.to, comp, min);
        }
    }
    private static class Edge {
        int to;
        int weight;
        public Edge(int t, int w){
            to=t;
            weight=w;
        }
        public String toString(){
            return "["+to+": "+weight+"]";
        }
    }
}
