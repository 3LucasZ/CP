package Solutions.USACOGuide.UsacoGuideSilver.IntroToTreeAlgor;

import java.io.*;
import java.util.*;

public class WizardsTour {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter out = new PrintWriter(System.out);

    static int N;
    static int M;

    static boolean[] visited;
    static ArrayList<Integer> heads = new ArrayList<>();

    static ArrayList<Integer>[] graph;

    static boolean[] finished;
    static ArrayList<Integer>[] forest;
    static ArrayList<Integer>[] backEdges;

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        forest = new ArrayList[N+1];
        for (int i=0;i<=N;i++) forest[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        visited = new boolean[N+1];
        finished = new boolean[N+1];

        backEdges = new ArrayList[N+1];
        for (int i=1;i<=N;i++) backEdges[i] = new ArrayList<>();

        for (int i=1;i<=N;i++){
            if (!visited[i]){
                heads.add(i);
                ff(i, 0);

                dfs(i, 0);


            }
        }

        StringBuilder ret = new StringBuilder();
        for (int i=1;i<=N;i++){
            for (int j=0;j<backEdges[i].size()-1;j+=2){
                ret.append(backEdges[i].get(j)+" "+i+" "+backEdges[i].get(j+1)+"\n");
                ans++;
            }
        }

        out.println(ans);
        out.println(ret);

        out.close();
    }
    public static void dfs(int node, int par){
        for (int child : forest[node]) dfs(child, node);

        for (int backEdge : graph[node]){
            if (backEdge==par || finished[backEdge]) continue;
            backEdges[node].add(backEdge);
        }
        finished[node]=true;

        if (par==0) return;
        if (backEdges[node].size()%2==1) {
            backEdges[node].add(par);
        }
        else {
            backEdges[par].add(node);
        }
    }

    public static void ff(int node, int par){
        if (visited[node]) return;
        visited[node]=true;
        forest[par].add(node);
        for (int child : graph[node]) ff(child, node);
    }
}
