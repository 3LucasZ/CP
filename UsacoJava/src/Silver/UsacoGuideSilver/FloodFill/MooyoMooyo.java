package Silver.UsacoGuideSilver.FloodFill;/*
USACO 2018 December Contest, Silver
Problem 3. Mooyo Mooyo
USACO Silver Guide - Floodfill - Normal
 */
import java.io.*;
import java.util.*;
public class MooyoMooyo {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static int state[][];
    static boolean visited[][];
    static ArrayList<Coord> toRemove;
    //mhm
    static int[] dr = new int[]{0,0,1,-1};
    static int[] dc = new int[]{1,-1,0,0};
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("mooyomooyo.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        state = new int[N][10];
        for (int r=0;r<N;r++) {
            String str = br.readLine();
            for (int c=0;c<10;c++) {
                state[r][c] = str.charAt(c)-48;
            }
        }
        //logic
        while (removeComponents()) gravity();
        printBoard();
        out.close();
    }
    public static boolean removeComponents(){
        visited = new boolean[N][10];
        boolean changed = false;
        for (int r=0;r<N;r++) {
            for (int c=0;c<10;c++) {
                if (state[r][c]!=0 && !visited[r][c]){
                    int color = state[r][c];
                    toRemove = new ArrayList<>();
                    floodfill(r,c,color);
                    if (toRemove.size()>=K){
                        for (Coord rem : toRemove){
                            state[rem.r][rem.c] = 0;
                        }
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }
    public static void floodfill(int r, int c, int color){
        if (r<0||r>=N||c<0||c>=10||visited[r][c]||state[r][c]!=color) return;
        visited[r][c] = true;
        toRemove.add(new Coord(r,c));
        for (int i=0;i<4;i++) {
            floodfill(r+dr[i],c+dc[i],color);
        }
    }
    public static void gravity(){
       for (int c=0;c<10;c++) {
           int floorlevel = N-1;
           for (int r=N-1;r>=0;r--) {
               if (state[r][c]!=0){
                   state[floorlevel][c] = state[r][c];
                   if (floorlevel!=r) state[r][c] = 0;
                   floorlevel--;
               }
           }
       }
    }
    public static void printBoard(){
        for (int r=0;r<N;r++) {
            for (int c=0;c<10;c++) {
                out.print(state[r][c]);
            }
            out.println();
        }
    }
    public static void printVisited(){
        for (int r=0;r<N;r++) {
            for (int c=0;c<10;c++) {
                out.print(visited[r][c]?1:0);
            }
            out.println();
        }
        out.println();
    }
    private static class Coord {
        int r;
        int c;
        public Coord(int r1, int c1){
            r=r1;
            c=c1;
        }
    }
}
