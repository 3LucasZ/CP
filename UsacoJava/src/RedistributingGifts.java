import java.io.*;
import java.util.*;

public class RedistributingGifts {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;
    //param
    static int N;
    static int[][] favor;
    static ArrayList<Integer>[] canTrade;
    //dfs
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        //[cow][favor for gift i]
        favor = new int[N+1][N+1];
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int f=1;f<=N;f++){
                int g = Integer.parseInt(st.nextToken());
                favor[i][g]=f;
            }
        }
        if (debug) {
            for (int i=1;i<=N;i++) System.out.println(Arrays.toString(favor[i]));
        }
        //logic
        canTrade = new ArrayList[N+1];
        for (int i=1;i<=N;i++) canTrade[i] = new ArrayList<>();
        for (int cow=1;cow<=N;cow++){
            for (int g=1;g<=N;g++){
                if (favor[cow][cow] > favor[cow][g] && favor[g][g] > favor[g][cow]) {
                    canTrade[cow].add(g);
                }
            }
        }
        if (debug) System.out.println(Arrays.toString(canTrade));
        for (int i=1;i<=N;i++){
            visited = new boolean[N+1];
            dfs(i);
            int best = i;
            for (int j=1;j<=N;j++){
                if (visited[j]) {
                    if (favor[i][best] > favor[i][j]) best=j;
                }
            }
            out.println(best);
        }
        out.close();
    }

    public static void dfs(int node){
        if (visited[node]) return;
        visited[node] = true;
        for (int child : canTrade[node]){
            dfs(child);
        }
    }
}
/*
5
1 5 2 3 4
4 3 1 5 2
2 1 3 5 4
2 1 5 3 4
5 4 3 2 1
 */