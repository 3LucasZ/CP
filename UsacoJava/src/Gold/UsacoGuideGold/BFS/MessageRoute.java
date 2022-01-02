package Gold.UsacoGuideGold.BFS;
/*
CSES Problem Set
Message Route
USACO Gold Guide - BFS - Easy Example
 */
import java.util.*;
import java.io.*;

public class MessageRoute {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int M;
    static int[] before;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        before = new int[N+1];
        for (int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        //First BFS implementation! :)
        Queue<Integer> bfs = new LinkedList<Integer>();
        bfs.add(1);
        before[1] = -1;
        while (!bfs.isEmpty()) {
            int next = bfs.poll();
            for (int child : graph[next]) {
                if (before[child] == 0) {
                    before[child] = next;
                    bfs.add(child);
                }
            }
        }
        int last = N;
        ArrayList<Integer> path = new ArrayList<>();
        int ans = 0;
        while (true){
            path.add(last);
            last = before[last];
            ans++;
            //not connected to 1
            if (last == 0) {
                out.println("IMPOSSIBLE");
                break;
            }
            //connected
            if (last == -1) {
                out.println(ans);
                for (int i=path.size()-1;i>=0;i--){
                    out.print(path.get(i)+" ");
                }
                break;
            }
        }
        out.close();
    }
}
