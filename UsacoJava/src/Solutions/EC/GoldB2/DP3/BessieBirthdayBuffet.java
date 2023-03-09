package Solutions.EC.GoldB2.DP3;/*
Bessie Birthday Buffet
Gold Advanced B 3
DP - increasing subsequence trick
Notes: Listen to sol first, quite some debugging!
Create distance[node1][node2] through BFS on every node (O(N^2) time and space)
The trick is that since bessie goes from low quality to higher and higher, we loop through the last grass she goes to
and loop through the first grass she goes to and store the best possible net quality for the last grass. These are
independent subtasks as the grass Bessie picks to get to node K doesn't effect how good node M effects our net quality
 */
import java.io.*;
import java.util.*;
public class BessieBirthdayBuffet {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int E;
    static ArrayList<Integer>[] graph;
    static Pair[] nodes;
    static int[][] distance;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        nodes = new Pair[N];
        distance = new int[N+1][N+1];
        for (int r=0;r<=N;r++) {
            for (int c=0;c<=N;c++) {
                distance[r][c]=-1;
            }
        }
        for (int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i-1] = new Pair(i, Integer.parseInt(st.nextToken()));
            int K = Integer.parseInt(st.nextToken());
            for (int j=0;j<K;j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        //sort nodes by increasing quality for dp
        Arrays.sort(nodes, (a,b)->a.quality-b.quality);
        if (debug) {
            System.out.println(Arrays.toString(graph));
            System.out.println(Arrays.toString(nodes));
        }
        //N^2 time N^2 space N*BFS algorithm to find the distance between any 2 nodes in O(1) time
        for (int start=1;start<=N;start++) {
            Queue<Integer> bfs = new LinkedList<>();
            boolean[] visited = new boolean[N+1];
            bfs.add(start);
            distance[start][start]=0;
            visited[start]=true;
            while (!bfs.isEmpty()) {
                int next = bfs.poll();
                for (int child : graph[next]) {
                    if (visited[child]) continue;
                    visited[child]=true;
                    distance[start][child] = distance[start][next] + 1;
                    bfs.add(child);
                }
            }
        }
        if (debug) {
            //for (int i=1;i<=N;i++) System.out.println(Arrays.toString(distance[i]));
            for (int r=0;r<=N;r++) {
                for (int c=0;c<=N;c++) {
                    System.out.print(distance[r][c]+"  ");
                }
                System.out.println();
            }
            System.out.println();
        }
        //dp
        int[] dp = new int[N];
        for (int end=0;end<N;end++) {
            Pair top = nodes[end];
            int best = 0;
            for (int start=0;start<=end;start++) {
                Pair bot = nodes[start];
                if (distance[top.node][bot.node]==-1) continue;
                best = Math.max(best, top.quality + dp[start] - distance[bot.node][top.node]*E);
            }
            dp[end]=best;
        }
        if (debug) System.out.println(Arrays.toString(dp));
        //get ans by searching dp
        int ans = 0;
        for (int i=0;i<N;i++) {
            ans = Math.max(dp[i],ans);
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Pair{
        int node;
        int quality;
        public Pair(int node, int quality){
            this.node=node;
            this.quality=quality;
        }
        public String toString(){
            return "["+node+", "+quality+"]";
        }
    }
}
