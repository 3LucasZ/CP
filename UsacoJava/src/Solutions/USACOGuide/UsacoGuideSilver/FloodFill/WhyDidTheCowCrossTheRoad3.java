package Solutions.USACOGuide.UsacoGuideSilver.FloodFill;

import java.io.*;
import java.util.StringTokenizer;
/*
USACO 2017 February Contest, Silver
Problem 3. Why Did the Cow Cross the Road III
USACO Guide - Flood fill - Normal
Concepts: Safety is first priority ;), extending the box
 */
public class WhyDidTheCowCrossTheRoad3 {
    //default
    static PrintWriter out;
    static BufferedReader f;
    static boolean submission = true;
    //param
    static int oldGridSize;
    static int newGridSize;
    static int numCows;
    static int numRoads;
    static int[][] grid;
    static boolean[][] visited;
    static Cow[] cows;
    //0 = road/fence
    //1 = farm
    //2 = farm and cow
    //const
    static final int[] dx = {0,0,1,-1};
    static final int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("countcross.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        oldGridSize = Integer.parseInt(st.nextToken());
        newGridSize = oldGridSize*2 - 1;
        numCows = Integer.parseInt(st.nextToken());
        cows = new Cow[numCows];
        numRoads = Integer.parseInt(st.nextToken());
        //logic
        createGrid();
        int answer = 0;
        for (Cow cow : cows) {
            answer += CheckCow(cow);
        }
        //turn in answer
        //out.println();
        //print2DArr(visited);
        out.println(answer/2);
        out.close();
        f.close();
    }
    static int cowsMet;
    public static int CheckCow(Cow cow) {
        visited = new boolean[newGridSize][newGridSize];
        //out.println("Checking cow " + cow);
        cowsMet = 0;
        floodfill(cow.r, cow.c);
        int cowsMissed = numCows - cowsMet;
        //out.println("This cow missed " + cowsMissed + " cows.");
        //out.println();
        return cowsMissed;
    }
    public static void floodfill(int r, int c) {
        if (r<0||r>=newGridSize||c<0||c>=newGridSize||grid[r][c]==1||grid[r][c]==3||visited[r][c]) {
            return;
        }
        if (grid[r][c] == 2) cowsMet ++;
        visited[r][c] = true;
        for (int i=0;i<4;i++) {
            floodfill(r+dx[i], c+dy[i]);
        }
    }
    public static void createGrid() throws IOException {
        grid = new int[newGridSize][newGridSize];
        for (int i=1;i<newGridSize;i+=2) {
            for (int j=1;j<newGridSize;j+=2) {
                grid[i][j] = 3;
            }
        }
        for (int i=0;i<numRoads;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x1 = 2*Integer.parseInt(st.nextToken())-2;
            int y1 = 2*Integer.parseInt(st.nextToken())-2;
            int x2 = 2*Integer.parseInt(st.nextToken())-2;
            int y2 = 2*Integer.parseInt(st.nextToken())-2;
            grid[(x1+x2)/2][(y1+y2)/2] = 1;
        }
        for (int i=0;i<numCows;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int r = 2*Integer.parseInt(st.nextToken())-2;
            int c = 2*Integer.parseInt(st.nextToken())-2;
            cows[i] = new Cow(r,c);
            grid[r][c] = 2;
        }
        //System.out.println();
        //print2DArr(grid);
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
    public static void print2DArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
    }
    public static void printArr(boolean[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] ? 1 : 0 ) + "  ";
        }
        out.println(str);
    }
    public static void print2DArr(boolean[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
        out.println();
    }
    private static class Cow {
        int r;
        int c;
        public Cow(int r1, int c1) {
            r = r1;
            c = c1;
        }
        public String toString() {
            return ("(" + r + ", " + c + ")");
        }
    }
}