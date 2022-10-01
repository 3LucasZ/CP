package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: MovingToTheCapital
LANG: JAVA
*/
public class MovingToTheCapital {
    static boolean submission = false;
    static boolean debug = false;

    //in
    static int N;
    static int M;
    static ArrayList<Integer>[] graph;

    //precomp
    static int[] d;

    //DFS
    static boolean[] visited;
    static int ans[];

    public static void main(String[] args) throws IOException {
        //TCS handle
        setup("MovingToTheCapital");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            br.readLine();
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i]= new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
        }
        if (debug) System.out.println(Arrays.toString(graph));
        //calculate distance from capital
        visited = new boolean[N+1];
        d = new int[N+1];
        Queue<Integer> BFS = new LinkedList<>();
        BFS.add(1);
        visited[1]=true;
        while (!BFS.isEmpty()){
            int next = BFS.poll();
            for (int child : graph[next]){
                if (visited[child]) continue;
                visited[child]=true;
                d[child]=d[next]+1;
                BFS.add(child);
            }
        }
        if (debug) System.out.println(Arrays.toString(d));
        //DFS dp
        ans = new int[N+1];
        visited = new boolean[N+1];
        DFS(1);
        //ret
        for (int i=1;i<=N;i++){
            out.print(ans[i]+" ");
        }
        out.println();
    }
    public static int DFS(int node){
        visited[node]=true;
        ans[node]=d[node];
        for (int child : graph[node]){
            if (visited[child]){
                if (d[child]>d[node]) ans[node]=Math.min(ans[node],ans[child]);
                else ans[node]=Math.min(ans[node],d[child]);
            } else {
                if (d[child]>d[node]) ans[node]=Math.min(ans[node],DFS(child));
                else ans[node]=Math.min(ans[node],d[child]);
            }
        }
        return ans[node];
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