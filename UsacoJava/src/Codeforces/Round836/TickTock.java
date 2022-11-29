package Codeforces.Round836;

import java.io.*;
import java.util.*;
/*
PROB: TickTock
LANG: JAVA
*/
public class TickTock {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* TCS
        setup("TickTock");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve(i);
        }
        out.close();
    }

    static int R,C,H;
    static int[][] grid;
    static ArrayList<Edge>[] graph;

    static int[] dx;
    static boolean ok;

    static int components = 0;
    static int emptyCol = 0;

    static long MOD = (long)1e9+7;

    public static void solve(int T) throws IOException {
        if (debug) System.out.println("Debugging: "+T);
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken()); H = Integer.parseInt(st.nextToken());
        grid = new int[R][C];
        for (int r=0;r<R;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<C;c++){
                grid[r][c]=Integer.parseInt(st.nextToken());
            }
        }
        if (debug) print(grid);
        //* build graph
        graph = new ArrayList[R]; for (int i=0;i<R;i++) graph[i] = new ArrayList<Edge>();
        for (int c=0;c<C;c++){
            int last = -1;
            for (int r=0;r<R;r++){
                if (grid[r][c]!=-1) {
                    if (last!=-1) {
                        graph[last].add(new Edge(last,r,(grid[r][c]-grid[last][c]+H)%H));
                        graph[r].add(new Edge(r,last,(grid[last][c]-grid[r][c]+H)%H));
                    }
                    last=r;
                }
            }
        }
        if (debug) System.out.println(Arrays.toString(graph));
        //* DFS 1. make sure no discrepancy, 2. cnt components
        dx = new int[R]; for (int i=0;i<R;i++) dx[i]=-1;
        ok=true;
        components=0;
        for (int r=0;r<R;r++) {
            //DFS when found unvisited component
            if (dx[r]==-1) {
                components++;
                DFS(r, 0);
            }
        }
        if (debug) System.out.println("Components: "+components);
        //* Check for empty cols
        emptyCol=0;
        for (int c=0;c<C;c++){
            boolean empty = true;
            for (int r=0;r<R;r++) {
                if (grid[r][c]!=-1) empty=false;
            }
            if (empty) emptyCol++;
        }
        if (debug) System.out.println("emptyCols: "+emptyCol);
        //ret
        if (!ok){
            out.println(0);
        } else {
            long ans = 1;
            for (int i=0;i<emptyCol+components-1;i++) ans=(ans*H)%MOD;
            out.println(ans);
        }
    }

    public static void DFS(int node, int x){
        //new node, set dx
        dx[node]=x;
        //srch children
        for (Edge child : graph[node]){
            //new child
            if (dx[child.v]==-1){
                DFS(child.v,(x+child.cost)%H);
            }
            //alr visited child
            else {
                //discrepancy
                if (dx[child.v]!=(x+child.cost)%H) ok = false;
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        int cost;
        public Edge(int u, int v, int cost){
            this.u=u;
            this.v=v;
            this.cost=cost;
        }
        public String toString(){
            return "["+u+", "+v+": "+cost+"]";
        }
    }
    public static void print(int[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}