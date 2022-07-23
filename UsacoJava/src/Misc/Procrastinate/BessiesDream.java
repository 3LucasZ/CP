package Misc.Procrastinate;

import java.io.*;
import java.util.StringTokenizer;

/*
USACO 2015 December Contest, Gold
Problem 3. Bessie's Dream
USACO Gold Training
 */
public class BessiesDream {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static int[][] maze;
    //state: r c fish smell? int: how many rounds to reach
    static int[][][] visited;
    //deltas, careful placement so: dir is opp from 3-dir
    static int[] dr = {0,-1,1,0};
    static int[] dc = {1,0,0,-1};
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("dream.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        visited = new int[N][M][2];
        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<M;c++) {
                maze[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        //print2DArr(maze);
        //logic
        floodfill(0,0,0,1,0);
        //turn in answer
        int ans = Integer.MAX_VALUE;
        if (visited[N-1][M-1][0] != 0 && visited[N-1][M-1][0] < ans) ans = visited[N-1][M-1][0];
        if (visited[N-1][M-1][1] != 0 && visited[N-1][M-1][1] < ans) ans = visited[N-1][M-1][1];
        if (ans == Integer.MAX_VALUE) out.println(-1);
        else out.println(ans - 1);
        out.close();
    }

    public static void floodfill(int r, int c, int smell, int steps, int dir){
        if (!passable(r,c,smell)) return;
        //print3DArr(visited);
        //purple - slide to direction - complicated state
        if (maze[r][c] == 4) {
            smell = 0;
            if (!passable(r+dr[dir],c+dc[dir],smell)) {
                for (int i=0;i<4;i++) {
                    if (i != dir && i != 3-dir) floodfill(r+dr[i],c+dc[i],smell,steps+1,i);
                }
            }
            else floodfill(r+dr[dir],c+dc[dir],smell,steps+1,dir);
            return;
        }
        //visited with earlier steps
        if (visited[r][c][smell] > 0 && steps >= visited[r][c][smell]) return;
        visited[r][c][smell] = steps;

        if (maze[r][c] == 2) smell = 1;
        for (int i=0;i<4;i++) {
            floodfill(r+dr[i],c+dc[i],smell,steps+1, i);
        }
    }
    public static boolean passable(int r, int c, int smell){
        //out of bounds
        if (r < 0 || r >= N || c < 0 || c >= M) return false;
        //red - impassable
        if (maze[r][c] == 0) return false;
        //blue - must smell like oranges
        if (maze[r][c] == 3 && smell == 0) return false;
        return true;
    }
    public static void print2DArr(int[][] arr) {
        for (int r=0;r<arr.length;r++) {
            for (int c=0;c<arr[r].length;c++) {
                out.print(arr[r][c]+" ");
            }
            out.println();
        }
        out.println();
    }
    public static void print3DArr(int[][][] arr) {
        for (int r=0;r<arr.length;r++) {
            for (int c=0;c<arr[r].length;c++) {
                out.print(arr[r][c][0]+" ");
            }
            out.println();
        }
        out.println();
    }
}
