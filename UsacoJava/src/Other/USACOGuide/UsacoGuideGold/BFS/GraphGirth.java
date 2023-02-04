package Other.USACOGuide.UsacoGuideGold.BFS;

import java.io.*;
import java.util.*;

public class GraphGirth {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int M;
    static int inf = (int)1e9;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i]= new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        int girth =  inf;
        for (int i=1;i<=N;i++){
            girth=Math.min(girth,smallestCycle(i));
        }

        out.println(girth==inf?-1:girth);
        out.close();

    }
    public static int smallestCycle(int node){
        Queue<Pair> BFS = new LinkedList();
        int[] dist = new int[N+1]; Arrays.fill(dist,-1);
        BFS.add(new Pair(0,node));
        int ans = inf;

        while (!BFS.isEmpty()){
            Pair next = BFS.poll();
            if (dist[next.second]!=-1){
                ans=Math.min(ans,dist[next.first]+dist[next.second]+1);
            } else {
                dist[next.second]=dist[next.first]+1;
                for (int child : graph[next.second]){
                    if (child!=next.first) BFS.add(new Pair(next.second,child));
                }
            }
        }
        return ans;
    }
    private static class Pair {
        int first;
        int second;
        public Pair(int f, int s){first=f;second=s;}
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
