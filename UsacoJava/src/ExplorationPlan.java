import java.io.*;
import java.util.*;

public class ExplorationPlan {
    static boolean submission = false;
    static boolean debug = false;

    static int V;
    static int E;
    static int N;
    static int K;

    static ArrayList<Integer> teams = new ArrayList<>();
    static int[][] dist;

    static int INF = Integer.MAX_VALUE/3;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            teams.add(Integer.parseInt(st.nextToken()));
        }
        dist = new int[V+1][V+1]; for (int u=1;u<=V;u++) for (int v=1;v<=V;v++) if (u!=v) dist[u][v]=INF;
        for (int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[u][v]=Math.min(dist[u][v],c);
            dist[v][u]=Math.min(dist[u][v],c);
        }

        //floyd (N^3)
        if (debug) print(dist);
        for (int m=1;m<=V;m++) for (int u=1;u<=V;u++) for (int v=1;v<=V;v++) dist[u][v]=Math.min(dist[u][v],dist[u][m]+dist[m][v]);
        if (debug) print(dist);

        //Add every possible team -> town cost (N*V)
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
        for (int i=0;i<N;i++){
            int team = teams.get(i);
            for (int j=1;j<=V;j++){
                if (dist[team][j]!=INF) pq.add(new Pair(i,j,dist[team][j]));
            }
        }
        if (debug) System.out.println(pq);

        //get shortest time to do K trips, if pq is too small -> impossible
        int ans = 0;
        int moved = 0;
        boolean[] foundTeam = new boolean[N];
        boolean[] foundVertex = new boolean[V+1];
        while (moved < K && !pq.isEmpty()){
            Pair next = pq.poll();
            if (foundTeam[next.team]||foundVertex[next.town]) continue;
            foundTeam[next.team]=true;
            foundVertex[next.town]=true;
            ans=Math.max(ans,next.cost);
            moved++;
        }

        //ret
        if (moved < K) out.println(-1);
        else out.println(ans);
        out.close();
    }
    private static class Pair {
        int team;
        int town;
        int cost;
        public Pair(int team, int town, int cost){
            this.team=team;
            this.town=town;
            this.cost=cost;
        }
        public String toString(){
            return "["+team+", "+town+", "+cost+"]";
        }
    }
    public static void print(int[][] arr){
        for (int r=1;r<arr.length;r++){
            for (int c=1;c<arr[r].length;c++){
                if (arr[r][c]>100) {
                    System.out.print("-1   ");
                    continue;
                }
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
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
