package USACOGuide.UsacoGuideSilver.FloodFill;/*
USACO 2021 February Contest, Silver
Problem 1. Comfortable Cows
USACO Guide Silver - Floodfill - Normal
Concepts - expanding the grid (if necessary) and using the floodfill mentality on problems not exactly classic floodfill
 */

import java.io.*;
import java.util.*;

public class ComfortableCows {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int gridsize = 3000;
    static boolean[][] farm;
    static boolean[][] added;
    static int answer = 0;
    //dx dy
    static final int[] dx = {0, 0, 1, -1, 0};
    static final int[] dy = {1, -1, 0, 0, 0};
    public static void main(String[] args) throws IOException {
        //parse input
        n = Integer.parseInt(f.readLine());
        //add one to all values in these arrays
        farm = new boolean[gridsize][gridsize];
        added = new boolean[gridsize][gridsize];
        //logic
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int cowR = Integer.parseInt(st.nextToken()) + 1000;
            int cowC = Integer.parseInt(st.nextToken()) + 1000;
            if (added[cowR][cowC]) {
                added[cowR][cowC] = false;
                answer --;
            }
            else {
                placeCow(cowR, cowC);
            }
            out.println(answer);
        }
        out.close();
        f.close();
    }
    public static void placeCow(int r, int c) {
        farm[r][c] = true;
        for (int i=0;i<5;i++) {
            int checkR = r+dx[i];
            int checkC = c+dy[i];
            int[] res = isComfortable(checkR, checkC);
            if (res[0] == 3) {
                added[res[1]][res[2]] = true;
                answer ++;
                placeCow(res[1], res[2]);
            }
        }
    }
    public static int[] isComfortable(int r, int c) {
        int adj = 0;
        int addR = 0;
        int addC = 0;
        if (!farm[r][c]) return new int[]{0};
        for (int i=0;i<4;i++) {
            int adjR = r+dx[i];
            int adjC = c+dy[i];
            if (adjR < 0 || adjR >= gridsize || adjC < 0 || adjC >= gridsize) {
                continue;
            }
            if (farm[adjR][adjC]) {
                adj++;
            }
            else {
                addR = adjR;
                addC = adjC;
            }
        }
        return new int[]{adj, addR, addC};
    }
    public static void printArr(boolean[][] arr) {
        for (int r=0;r<arr.length;r++) {
            for (int c=0;c<arr.length;c++) {
                out.print(farm[r][c] ? 1 : 0);
                out.print("  ");
            }
            out.println();
        }
    }
}
