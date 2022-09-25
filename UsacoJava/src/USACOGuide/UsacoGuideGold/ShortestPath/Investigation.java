package USACOGuide.UsacoGuideGold.ShortestPath;

import java.io.*;
import java.util.*;
/*
PROB: Investigation
LANG: JAVA
*/
public class Investigation {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int M;

    static ArrayList<Edge>[] graph;

    static long LINF = Long.MAX_VALUE;
    static int INF = Integer.MAX_VALUE;
    static long MOD = (long)1e9+7;
    public static void main(String[] args) throws IOException {
        //parse
        setup("Investigation");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(u,v,c));
        }

        //investigation trackers
        long[] dist = new long[N+1]; for (int i=1;i<=N;i++) dist[i] = LINF;
        int[] min = new int[N+1]; for (int i=1;i<=N;i++) min[i]=INF;
        int[] max = new int[N+1];
        long[] cnt = new long[N+1]; cnt[1]=1;

        //modified djikstra
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(a->a.cost));
        pq.add(new Edge(0,1,0));
        while (!pq.isEmpty()){
            Edge next = pq.poll();
            if (dist[next.v]==next.cost || dist[next.v]==LINF){
                min[next.v]=Math.min(min[next.v],min[next.u]+1);
                max[next.v]=Math.max(max[next.v],max[next.u]+1);
                cnt[next.v]=(cnt[next.v]+cnt[next.u])%MOD;
            }
            if (dist[next.v]!=LINF) continue;
            dist[next.v]=Math.min(dist[next.v],next.cost);
            for (Edge child : graph[next.v]){
                pq.add(new Edge(child.u,child.v,child.cost+next.cost));
            }
        }

        //ret
        out.print(dist[N]+" "+cnt[N]+" "+(min[N]-1)+" "+(max[N]-1));
        out.close();
    }
    private static class Edge {
        int u;
        int v;
        long cost;
        public Edge(int u, int v, long cost){
            this.u=u;
            this.v=v;
            this.cost=cost;
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