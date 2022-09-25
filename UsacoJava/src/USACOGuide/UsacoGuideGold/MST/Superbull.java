package USACOGuide.UsacoGuideGold.MST;

import java.io.*;
import java.util.*;
/*
USACO 2015 February Contest, Silver
Problem 3. Superbull
USACO Gold Guide - MST - Easy
Thoughts:
Extremely easy correspondence to MST and implementation
represent the games that are played as a tree
what is the best tree that can be formed for max pts?
 */
public class Superbull {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int[] id;
    static int[][] points;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("superbull.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        points = new int[N][N];
        id = new int[N];
        for (int i=0;i<N;i++){
            id[i] = Integer.parseInt(br.readLine());
        }

        //logic
        for (int u=0;u<N;u++){
            for (int v=0;v<N;v++){
                points[u][v] = id[u]^id[v];
            }
        }
        if (!submission) {
            System.out.println(Arrays.toString(id));
            System.out.println();
            for (int i=0;i<N;i++) System.out.println(Arrays.toString(points[i]));
        }
        //MST
        //init
        long sum=0;
        boolean[] visited = new boolean[N];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->b.weight-a.weight);
        pq.add(new Pair(0, 0));
        //log
        for (int i=0;i<N;i++){
            while (!pq.isEmpty() && visited[pq.peek().node]) pq.poll();
            Pair next = pq.poll();
            visited[next.node]=true;
            sum += next.weight;
            for (int other = 0;other<N;other++){
                if (visited[other]) continue;
                pq.add(new Pair(other, points[next.node][other]));
            }
        }

        //turn in answer
        out.println(sum);
        out.close();
    }

    private static class Pair {
        int node;
        int weight;
        public Pair(int n, int w){
            node=n;
            weight=w;
        }
    }
}
