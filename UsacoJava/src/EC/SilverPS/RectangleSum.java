package EC.SilverPS;

import java.io.*;
import java.util.*;

public class RectangleSum {
    static BufferedReader br;
    static PrintWriter out;
    static int N;
    static int M;
    static int K;
    static int[][] rect;
    static long[][] preSum;
    static boolean submission = false;
    public static void main(String[] args) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader("rectanglesum.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("rectanglesum.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        rect = new int[N+1][M+1];
        preSum = new long[N+1][M+1];
        for (int r=1;r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=1;c<=M;c++) {
                rect[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int r=1;r<=N;r++) {
            for (int c=1;c<=M;c++) {
                preSum[r][c] = preSum[r-1][c]+preSum[r][c-1]-preSum[r-1][c-1]+rect[r][c];
            }
        }
//        print2DArr(rect);
//        print2DArr(preSum);
        for (int q=0;q<K;q++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1= Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            out.println(preSum[r2][c2]-preSum[r1-1][c2]-preSum[r2][c1-1]+preSum[r1-1][c1-1]);
        }
        out.close();
    }
    public static void print2DArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            out.println(Arrays.toString(arr[i]));
        }
        out.println();
    }
}
