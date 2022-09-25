package Training.SilverTraining;

import java.io.*;
import java.util.*;

/*
USACO 2014 December Contest, Silver
Problem 1. Piggyback
USACO Silver Training
Concepts: BFS - Easy
 */
public class Piggyback {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int B;
    static int E;
    static int P;
    static int N;
    static int M;
    static ArrayList<Integer>[] barns;
    static int[] SPfrom1;
    static int[] SPfrom2;
    static int[] SPtoN;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("piggyback.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        barns = new ArrayList[N+1];
        SPfrom1 = new int[N+1];
        SPfrom2 = new int[N+1];
        SPtoN = new int[N+1];
        for (int i=1;i<=N;i++) {
            barns[i] = new ArrayList<Integer>();
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            barns[u].add(v);
            barns[v].add(u);
        }
        //logic
        shortestPath(SPfrom1, 1);
        shortestPath(SPfrom2, 2);
        shortestPath(SPtoN, N);
        //out.println(Arrays.toString(SPfrom1));
        //i is testing meeting point
        long min_energy = Long.MAX_VALUE;
        for (int i=1;i<=N;i++) {
            min_energy = Math.min(min_energy, SPfrom1[i]*B + SPfrom2[i]*E + SPtoN[i]*P);
        }
        //turn in answer
        out.println(min_energy);
        out.close();
    }
    public static void shortestPath(int[] pathArr, int start){
        boolean[] visited = new boolean[N+1];

        Queue<Integer> bfs = new LinkedList<Integer>();
        bfs.add(start);
        visited[start] = true;
        while (!bfs.isEmpty()){
            int next = bfs.poll();
            for (int child : barns[next]) {
                if (!visited[child]) {
                    visited[child] = true;
                    pathArr[child] = pathArr[next]+1;
                    bfs.add(child);
                }
            }
        }
    }
}
