package Misc.Procrastinate;

import java.io.*;
import java.util.*;
/*
USACO 2018 US Open Contest, Silver
Problem 3. Multiplayer Moo
USACO Silver Guide - Floodfill - Hard
Addiontal Concepts: DFS, linkage dynamic programming
 */

public class MultiplayerMoo {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<Component> components = new ArrayList<>();
    static Component[][] owner;
    //ans
    static int largestOne = 0;
    static int largestTwo = 0;
    static int sum;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("multimoo.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];
        owner = new Component[N][N];
        for (int r=0;r<N;r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        //logic
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                if (!visited[r][c]){
                    int color = board[r][c];
                    Component comp = new Component(color);
                    floodfill(r,c,comp);
                    largestOne = Math.max(largestOne, comp.size);
                    components.add(comp);
                }
            }
        }
//        for (int r=0;r<N;r++) {
//            for (int c=0;c<N;c++) {
//                out.print(owner[r][c]);
//            }
//            out.println();
//        }
//        out.println();
//
//        for (int r=0;r<N;r++) {
//            for (int c=0;c<N;c++) {
//                String toPrint = " "+owner[r][c].size;
//                if (owner[r][c].size < 10) toPrint = " "+toPrint;
//                out.print(toPrint);
//            }
//            out.println();
//        }
//        out.println();
        visited = new boolean[N][N];
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                if (!visited[r][c]){
                    Component comp = owner[r][c];
                    floodfill2(r,c,comp);
                }
            }
        }
//        for (Component comp : components){
//            out.println(comp + ": " + comp.connectedTo);
//        }
//        out.println();
        for (Component comp : components){
            findSize(comp);
        }
//        out.println(components);
//        out.println(components.size());
        //turn in answer
        out.println(largestOne);
        out.println(largestTwo);
        out.close();
    }
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    //find the largest component made of 2 numbers
    public static void findSize(Component comp){
        for (Component child : comp.connectedTo) {
            sum=0;
            dfs(comp, comp.color, child.color);
            largestTwo = Math.max(largestTwo, sum);
            //out.println(largestTwo);
        }
    }
    public static void dfs(Component comp, int color1, int color2){
        if (comp.color != color1 && comp.color != color2) return;
        if (comp.found) return;
        sum+=comp.size;
        comp.found = true;
        for (Component child : comp.connectedTo){
            dfs(child, color1, color2);
        }
        comp.found = false;
    }
    //to find components
    public static void floodfill(int r, int c, Component cur){
        if (r<0||r>=N||c<0||c>=N||visited[r][c]||board[r][c]!=cur.color) return;
        cur.size++;
        owner[r][c] = cur;
        visited[r][c] = true;
        for (int i=0;i<4;i++) {
            floodfill(r+dr[i],c+dc[i], cur);
        }
    }
    //to find components connected to each component
    public static void floodfill2(int r, int c, Component cur) {
        if (r<0||r>=N||c<0||c>=N) return;
        if (board[r][c] != cur.color) cur.connectedTo.add(owner[r][c]);
        if (visited[r][c]||board[r][c]!=cur.color) return;
        visited[r][c] = true;
        for (int i=0;i<4;i++) {
            floodfill2(r+dr[i],c+dc[i],cur);
        }
    }
    private static class Component {
        HashSet<Component> connectedTo = new HashSet<>();
        int size = 0;
        int color;
        boolean found = false;
        public Component(int c){color=c;}
        public String toString(){
            return ""+color;
        }
    }
}
