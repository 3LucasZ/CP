package USACOGuide.UsacoGuideSilver.FunctionalGraphs;

import java.io.*;
import java.util.*;
/*
USACO 2017 December Contest, Silver
Problem 3. The Bovine Shuffle
USACO Silver Practice && USACO Silver Guide - Functional Graphs - Easy
 */
public class TheBovineShuffle {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int transpose[];
    static ArrayList<Integer>[] inverseTranspose;
    //intermediary
    static boolean[] visited;
    static boolean[] visited2;
    static int cyclesSum = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("shuffle.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        transpose = new int[N+1];
        inverseTranspose = new ArrayList[N+1];
        visited = new boolean[N+1];
        visited2 = new boolean[N+1];

        for (int i=1;i<=N;i++) {
            inverseTranspose[i] = new ArrayList<>();
        }
        for (int u=1;u<=N;u++) {
            int v = Integer.parseInt(st.nextToken());
            transpose[u] = v;
            inverseTranspose[v].add(u);
        }
        //out.println(Arrays.toString(transpose));
        //out.println(Arrays.toString(inverseTranspose));
        //logic
        for (int i=1;i<=N;i++) {
            if (!visited2[i]) {
                visited[i] = true;
                int j = transpose[i];
                while(!visited[j]) {
                    visited[j] = true;
                    j = transpose[j];
                }
                dfs(j);
                int cycleStart = transpose[j];
                int cycleSize = 1;
                while (cycleStart != j) {
                    cycleStart = transpose[cycleStart];
                    cycleSize++;
                }
                cyclesSum += cycleSize;
//                out.println("Curr sum: " + cyclesSum + " on i: " + i);
//                out.println(Arrays.toString(visited));
            }
        }
        //turn in answer
        out.println(cyclesSum);
        out.close();
    }
    static public void dfs(int n) {
        if (visited2[n]) return;
        visited2[n] = true;
        for (int child : inverseTranspose[n]) {
            dfs(child);
        }
    }
}
