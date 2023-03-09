package Solutions.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2015 January Contest, Silver
Problem 3. Meeting Time
USACO Silver Training
Concepts: Dynamic Programming
 */
public class MeetingTime {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static boolean[][] b_visited;
    static boolean[][] e_visited;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("meeting.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for (int i=1;i<=N;i++) {
            graph[i] = new ArrayList<Edge>();
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, b, e));
        }
        //get all final energies by dp
        b_visited = new boolean[N+1][100*100+1];
        e_visited = new boolean[N+1][100*100+1];
        b_visited[1][0] = true;
        e_visited[1][0] = true;
        for (int i=1;i<=N-1;i++) {
            DP(i);
        }
//        out.println("BESSIE:");
//        printDP(b_visited);
//        out.println();
//        out.println("ELSIE:");
//        printDP(e_visited);
//        out.println();
        //find the smallest energies that match
        for (int i=0;i<=100*100;i++){
            if (b_visited[N][i] && e_visited[N][i]) {
                out.println(i);
                out.close();
                return;
            }
        }
        out.println("IMPOSSIBLE");
        out.close();
    }
    public static void DP(int node){
        for (int i=0;i<=100*100;i++) {
            if (b_visited[node][i]) {
                for (Edge path : graph[node]){
                    b_visited[path.to][i + path.b] = true;
                }
            }
            if (e_visited[node][i]) {
                for (Edge path : graph[node]){
                    e_visited[path.to][i + path.e] = true;
                }
            }
        }
    }
    public static void printDP(boolean[][] arr){
        //row is node
        //column is energy spent
        for (int r=1;r<=N;r++) {
            out.print(r+": ");
            for (int c=0;c<=10;c++) {
                if (arr[r][c]) out.print(c+" ");
            }
            out.println();
        }
    }
    private static class Edge {
        int to;
        int b;
        int e;
        public Edge(int to1, int b1, int e1){
            to=to1;
            b=b1;
            e=e1;
        }
        public String toString(){
            return "["+to+": "+b+", "+e+"]";
        }
    }
}
