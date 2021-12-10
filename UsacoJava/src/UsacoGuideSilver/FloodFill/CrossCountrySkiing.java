package UsacoGuideSilver.FloodFill;/*
USACO 2014 January Contest, Silver
Problem 2. Cross Country Skiing
USACO Guide Silver Floodfill Easy
Concepts: Binary search, floodfill, global vars
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CrossCountrySkiing {
    static boolean submission = true;
    //param
    static int rows;
    static int cols;
    static int[][] elevation;
    static boolean[][] found;
    static final int[] dc = {0,0,1,-1};
    static final int[] dr = {1,-1,0,0};
    static ArrayList<Position> waypoints = new ArrayList<Position>();
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("ccski.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("ccski.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        elevation = new int[rows][cols];
        found = new boolean[rows][cols];
        for (int r=0;r<rows;r++) {
            st = new StringTokenizer(f.readLine());
            for (int c=0;c<cols;c++) {
                elevation[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int r=0;r<rows;r++) {
            st = new StringTokenizer(f.readLine());
            for (int c=0;c<cols;c++) {
                if (st.nextToken().equals("1")) {
                    waypoints.add(new Position(r, c));
                }
            }
        }
        //logic
        int left = 0, right = 1000000000;
        while (left < right) {
            int middle = (left + right) / 2;
            if (!floodfillWrapper(waypoints.get(0).r, waypoints.get(0).c, middle)) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        if (floodfillWrapper(waypoints.get(0).r, waypoints.get(0).c, left)) {
            out.println(left);
        }
        out.close();
        f.close();
    }
    public static boolean floodfillWrapper(int r, int c, int d) {
        found = new boolean[rows][cols];
        floodfill(r, c, d);
        for (Position p : waypoints) {
            if (!found[p.r][p.c]) return false;
        }
        return true;
    }
    public static void floodfill(int r, int c, int d) {
        found[r][c] = true;
        for (int i=0;i<4;i++) {
            if (    r+dr[i] < 0 ||
                    r+dr[i] >= rows ||
                    c+dc[i] < 0 ||
                    c+dc[i] >= cols ||
                    found[r+dr[i]][c+dc[i]] ||
                    Math.abs(elevation[r+dr[i]][c+dc[i]] - elevation[r][c]) > d) {
            }
            else {
                floodfill(r + dr[i], c + dc[i], d);
            }
        }
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        debugPrintln(str);
    }
    public static void debugPrintln(String str) {
        if (!submission) {
            System.out.println(str);
        }
    }
}
class Position {
    int r;
    int c;
    public Position(int rt, int ct) {
        r = rt;
        c = ct;
    }
}
