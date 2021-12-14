package Bronze.UsacoGuideBronze.adhoc;

import java.io.*;

public class ModernArt {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("art.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("art.out")));
            n = Integer.parseInt(f.readLine());
            board = new int[n][n];
            for (int i=0;i<n;i++) {
                String str = f.readLine();
                for (int j=0;j<n;j++) {
                    int num = Integer.parseInt(str.substring(j,j+1));
                    board[i][j] = num;
                }
            }
        }
        else {
            n = 4;
            board = new int[][]{{2, 2, 3, 0},
                                {2, 7, 3, 7},
                                {2, 7, 7, 7},
                                {0, 0, 0, 0}};
        }
        System.out.println(isOnTop(3, 3));
        int sol = 0;
        for (int i=1;i<=9;i++) {
            if (colorAppears(i)) {
                boolean possible = true;
                for (int j = 1; j <= 9; j++) {
                    //is i on top of j?
                    if (colorAppears(j) && isOnTop(i, j) && i != j) {
                        possible = false;
                    }
                }
                if (possible) {
                    sol++;
                }
            }
        }
        System.out.println(sol);
        if (submission) {
            out.println(sol);
            out.close();
        }

    }
    public static boolean isOnTop(int c2, int c1) {
        //find the minimal box for c1
        int minR = n;
        int minC = n;
        int maxR = 0;
        int maxC = 0;
        for (int r=0;r<n;r++) {
            for (int c=0;c<n;c++) {
                if (board[r][c] == c1) {
                    minR = Math.min(minR, r);
                    maxR = Math.max(maxR, r);
                    minC = Math.min(minC, c);
                    maxC = Math.max(maxC, c);
                }
            }
        }
        //System.out.println(minR + " " + maxR + " " + minC + " " + maxC);
        //System.out.println(c1 + ": " + (maxR - minR + 1) * (maxC - minC + 1));
        for (int r=minR;r<maxR+1;r++) {
            for (int c=minC;c<maxC+1;c++) {
                if (board[r][c] == c2) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean colorAppears(int color) {
        for (int r=0;r<n;r++) {
            for (int c=0;c<n;c++) {
                if (board[r][c] == color) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void printArr(int[] arr) {
        String result = "";
        for (int i=0;i<arr.length;i++) {
            result += (arr[i] + ", ");
        }
        System.out.println(result);
    }
    public static void print2DArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
    }
}
