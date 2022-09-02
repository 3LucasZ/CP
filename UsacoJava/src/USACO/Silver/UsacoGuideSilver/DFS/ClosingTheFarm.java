package USACO.Silver.UsacoGuideSilver.DFS;/*
USACO 2016 US Open Contest, Silver
Problem 3. Closing the Farm
USACO Guide Silver - DFS - Easy
 */
import java.io.*;
import java.util.*;
public class ClosingTheFarm {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static int M;
    static ArrayList<Integer>[] barns;
    static int perm[];
    static boolean[] visited;
    static int visits;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("closing.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        barns = new ArrayList[N+1];
        for (int i=1;i<=N;i++) barns[i] = new ArrayList<>();
        perm = new int[N];
        for (int i=1;i<=M;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            barns[u].add(v);
            barns[v].add(u);
        }
        for (int i=0;i<N;i++) {
            perm[i] = Integer.parseInt(f.readLine());
        }
        //logic
        int central = perm[N-1];
        for (int i=0;i<N;i++) {
            Integer toRemove;
            if (i != 0) {
                 toRemove = perm[i - 1];
                for (int child : barns[toRemove]) barns[child].remove(toRemove);
            }
            visited = new boolean[N+1];
            visits = 0;
            dfs(central);
            if (visits < N-i) out.println("NO");
            else out.println("YES");
        }
        out.close();
        f.close();
    }
    public static void dfs(int node) {
        visited[node] = true;
        visits++;
        for (int child : barns[node]) {
            if (!visited[child]) dfs(child);
        }
    }
}
