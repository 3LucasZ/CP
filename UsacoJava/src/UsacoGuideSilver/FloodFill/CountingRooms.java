package UsacoGuideSilver.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
CSES Problem Set
Counting Rooms
USACO Guide Silver Easy Floodfill
 */
public class CountingRooms {
    //const
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    //param
    static int height;
    static int width;
    static boolean[][] visited;
    static boolean[][] grid;
    static int rooms = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        grid = new boolean[height][width];
        visited = new boolean[height][width];
        for (int i=0;i<height;i++) {
            String str = f.readLine();
            for (int j=0;j<width;j++) {
                grid[i][j] = str.charAt(j) == '.';
            }
        }
        //logic
        for (int r=0;r<height;r++) {
            for (int c = 0;c<width;c++) {
                if (!visited[r][c] && grid[r][c]) {
                    rooms ++;
                    FloodFillAt(r, c);
                }
            }
        }
        //turn in answer
        out.println(rooms);
        out.close();
        f.close();
    }
    public static void FloodFillAt(int r, int c) {
        if (r < 0 || c < 0 || r >= height || c >= width || visited[r][c] || !grid[r][c]) {
            return;
        }
        visited[r][c] = true;
        for (int i=0;i<4;i++) {
            FloodFillAt(r + dy[i], c + dx[i]);
        }
    }
}
