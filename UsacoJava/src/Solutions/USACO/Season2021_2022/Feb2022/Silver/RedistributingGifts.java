package Solutions.USACO.Season2021_2022.Feb2022.Silver;

import java.io.*;
import java.util.*;
/*
USACO 2022 February Contest, Silver
Problem 1. Redistributing Gifts
Thoughts:
Missed this problem in contest, but seems super easy...
THE GOLD: for edge i -> j, j can get gift i if and only if i and j are in a cycle
 */
public class RedistributingGifts {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int[][] favor;

    //SCC
    static boolean[] visited;
    static ArrayList<Integer>[] adjList;
    static boolean[][] canReach;

    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        //favor[cow][gift i] = favor_i
        favor = new int[N+1][N+1];
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int f=N;f>=1;f--){
                int g = Integer.parseInt(st.nextToken());
                favor[i][g]=f;
            }
        }
        if (debug) {
            for (int i=1;i<=N;i++) System.out.println(Arrays.toString(favor[i]));
        }

        //logic
        //adjlist creation
        adjList = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
        for (int cow=1;cow<=N;cow++){
            for (int other=1;other<=N;other++){
                if (favor[cow][other] > favor[cow][cow]) {
                    adjList[cow].add(other);
                }
            }
        }
        if (debug) System.out.println(Arrays.toString(adjList));

        //gen reachable
        canReach = new boolean[N+1][N+1];
        for (int cow=1;cow<=N;cow++){
            visited = new boolean[N+1];
            visited[cow]=true;
            dfs(cow);
            for (int i=1;i<=N;i++){
                if (visited[i]) canReach[cow][i]=true;
            }
        }

        //cycle detection
        for (int cow=1;cow<=N;cow++){
            int best = cow;
            for (int other : adjList[cow]){
                if (canReach[other][cow]){
                    if (favor[cow][other]>favor[cow][best]) best=other;
                }
            }
            out.println(best);
        }
        out.close();
    }

    public static void dfs(int node){
        for (int child : adjList[node]){
            if (visited[child]) continue;
            visited[child]=true;
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