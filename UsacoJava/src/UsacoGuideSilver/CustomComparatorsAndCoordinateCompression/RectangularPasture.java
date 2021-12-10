package UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;/*
USACO 2020 December Contest, Silver
Problem 2. Rectangular Pasture
USACO Guide Silver - Hard Coordinate Compression
Concepts: Coordinate Compression, 2D Prefix Sums, Problem Solving
*/

import java.io.*;
import java.util.*;

public class RectangularPasture {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int[] xs;
    static int[] ys;
    static Integer[] cows;
    //logik
    static int[][] sums;
    public static void main(String[] args) throws IOException {
        //parse input
        n = Integer.parseInt(f.readLine());
        xs = new int[n];
        ys = new int[n];
        cows = new Integer[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            xs[i] = Integer.parseInt(st.nextToken());
            ys[i] = Integer.parseInt(st.nextToken());
            cows[i] = i;
        }
        //logik
        Arrays.sort(cows, (a, b) -> xs[a] - xs[b]);
        for (int i=1;i<=n;i++) {
            xs[cows[i-1]] = i;
        }
        Arrays.sort(cows, (a, b) -> ys[a] - ys[b]);
        for (int i=1;i<=n;i++) {
            ys[cows[i-1]] = i;
        }
        //out.println(Arrays.toString(xs));
        //out.println(Arrays.toString(ys));
        //out.println();
        sums = new int[n + 1][n + 1];
        for (int j = 0; j < n; j++) {
            sums[xs[j]][ys[j]]++;
        }
        //printArr(sums);
        //out.println();

        for (int r=1;r<=n;r++) {
            for (int c=1;c<=n;c++) {
                sums[r][c] = sums[r][c] + sums[r-1][c] + sums[r][c-1]  - sums[r-1][c-1];
            }
        }

        //printArr(sums);
        //out.println();

        //the rectangle around each singular cow + the empty set
        long answer = n + 1;
        for (int cow1=0;cow1<n;cow1++) {
            for (int cow2=cow1+1;cow2<n;cow2++) {
                    answer +=
                        getSum(Math.min(xs[cow1], xs[cow2]), 1,
                                Math.max(xs[cow1], xs[cow2]), Math.min(ys[cow1], ys[cow2])) *
                        getSum(Math.min(xs[cow1], xs[cow2]), Math.max(ys[cow1], ys[cow2]),
                                Math.max(xs[cow1], xs[cow2]), n);
            }
        }
        out.println(answer);
        out.close();
        f.close();
    }
    public static int getSum(int r1, int c1, int r2, int c2) {
        return sums[r2][c2] - sums[r2][c1-1] - sums[r1-1][c2] + sums[r1-1][c1-1];
    }
    public static void printArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            out.println(Arrays.toString(arr[i]));
        }
    }
}
