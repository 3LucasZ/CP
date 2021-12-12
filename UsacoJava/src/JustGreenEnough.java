import java.io.*;
import java.util.*;
/*
USACO 2021 February Contest, Silver
Problem 3. Just Green Enough
Solving Session - Normal
Concepts: Complementary counting, 2 pointers, prefix sums
Problem Statement: finding all rectangles of 2d array that contain only a certain character
 */
public class JustGreenEnough {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int[][] pasture;
    static int[][] sumsBelow;
    static int[][] sumsAtMost;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(f.readLine());
        pasture = new int[N][N];
        for (int r=0;r<N;r++) {
            StringTokenizer st =new StringTokenizer(f.readLine());
            for (int c=0;c<N;c++) {
                pasture[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        sumsBelow = new int[N][N+1];
        sumsAtMost = new int[N][N+1];
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                sumsBelow[r][c+1] = sumsBelow[r][c] + (pasture[r][c] < 100 ? 1:0);
                sumsAtMost[r][c+1] = sumsAtMost[r][c] + (pasture[r][c] <= 100 ? 1 : 0);
            }
        }
        long answer = 0;
        printArr(pasture);
        printArr(sumsBelow);
        printArr(sumsAtMost);
        for (int x1=0;x1<N;x1++) {
            for (int x2=x1+1;x2<=N;x2++) {
                int y1 = -1;
                int y2 = -1;
                for (int y0=0;y0<N;y0++) {
                    while (y1 < N && (y1 < y0 || sumsAtMost[y1][x2] - sumsAtMost[y1][x1] == 0)) {
                        y1++;
                    }
                    while (y2 < N && (y2 < y0 || sumsBelow[y2][x2] - sumsBelow[y2][x1] == 0)) {
                        y2++;
                    }
                    answer += y2 - y1;
                }
            }
        }
        //turn in answer
        out.println(answer);
        out.close();
        f.close();
    }
    public static void printArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            out.println(Arrays.toString(arr[i]));
        }
        out.println();
    }
}
