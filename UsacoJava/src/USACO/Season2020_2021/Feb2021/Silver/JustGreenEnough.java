package USACO.Season2020_2021.Feb2021.Silver;

import java.io.*;
import java.util.*;
/*
USACO 2021 February Contest, Silver
Problem 3. Just Green Enough
Solving Session - Normal
Concepts: Complementary counting, 2 pointers, prefix sums
The main problem: finding all rectangles of 2d array that contain only a certain character
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
                //0 for good: 100 and greater
                sumsBelow[r][c+1] = sumsBelow[r][c] + (pasture[r][c] < 100 ? 1:0);
                //0 for good: 101 and greater
                sumsAtMost[r][c+1] = sumsAtMost[r][c] + (pasture[r][c] <= 100 ? 1 : 0);
            }
        }
        long answer = 0;
//        printArr(pasture);
//        printArr(sumsBelow);
//        printArr(sumsAtMost);
        //logic
        for (int c1=0;c1<=N;c1++) {
            for (int c2=c1+1;c2<=N;c2++) {
                long belowRun = 0;
                long mostRun = 0;
                for (int r=0;r<N;r++) {
                    if (sumsBelow[r][c2]-sumsBelow[r][c1]==0) {
                        belowRun++;
                    }
                    else {
                        belowRun=0;
                    }
                    if (sumsAtMost[r][c2]-sumsAtMost[r][c1]==0) {
                        mostRun++;
                    }
                    else {
                        mostRun = 0;
                    }
                    answer += belowRun;
                    answer -= mostRun;
                }
            }
        }
        //turn in answer
        out.println(answer);
        out.close();
    }
    public static void printArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            out.println(Arrays.toString(arr[i]));
        }
        out.println();
    }
}
