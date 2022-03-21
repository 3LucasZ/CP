package Gold.UsacoGuideGold.ShortestPath;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Shortest Routes II
USACO Gold Guide - Shortest Paths - Easy Example
Thoughts:
First every Floyd Warshall algorithm! quite nice.
logic: dp, for int med: for int u: for int v: relax
:)
 */
public class ShortestRoutes2FloydWarshall {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //param
    static int N;
    static int M;
    static int Q;

    //graph
    static long INF = (long)500e9;
    static long[][] shortestPath;

    public static void main(String[] args) throws IOException{
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        shortestPath = new long[N+1][N+1];
        for (int i=1;i<=N;i++) for (int j=1;j<=N;j++) shortestPath[i][j]=INF;
        for (int i=1;i<=N;i++) shortestPath[i][i] = 0;
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            shortestPath[u][v]=Math.min(shortestPath[u][v],c);
            shortestPath[v][u]=Math.min(shortestPath[u][v],c);
        }

        //floyd
        for (int mid=1;mid<=N;mid++){
            for (int u=1;u<=N;u++){
                for (int v=1;v<=N;v++){
                    //relax
                    shortestPath[u][v] = Math.min(shortestPath[u][v], shortestPath[u][mid]+shortestPath[mid][v]);
                }
            }
        }

        //ret
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (shortestPath[u][v]==INF) out.println(-1);
            else out.println(shortestPath[u][v]);
        }
        out.close();
    }
}
