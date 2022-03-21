package Procrastinate;

import java.io.*;
import java.util.StringTokenizer;

public class PaintingTheBarn {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static int[][] paintPreSum;
    static int[][] paint;
    static int[][] paintOver;
    static int[][] paintOverPreSum;
    //const
    static final int MAX = 200;
    static final int PRINT_MAX = 10;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("paintbarn.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        paintPreSum = new int[MAX+1][MAX+1];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x1=Integer.parseInt(st.nextToken());
            int y1=Integer.parseInt(st.nextToken());
            int x2=Integer.parseInt(st.nextToken());
            int y2=Integer.parseInt(st.nextToken());
            paintPreSum[x1][y1]++;
            paintPreSum[x2][y2]++;
            paintPreSum[x1][y2]--;
            paintPreSum[x2][y1]--;
        }
        if (!submission) printArr(paintPreSum, "paintPreSum");
        //reconstruct actual paint using paintPreSum
        paint = new int[MAX+1][MAX+1];
        for (int r=0;r<=MAX;r++) {
            for (int c=0;c<=MAX;c++) {
                if (r > 0) paint[r][c] += paint[r-1][c];
                if (c > 0) paint[r][c] += paint[r][c-1];
                if (r > 0 && c > 0) paint[r][c] -= paint[r-1][c-1];
                paint[r][c] += paintPreSum[r][c];
            }
        }
        if (!submission) printArr(paint, "paint");

        //create paintover array
        paintOver = new int[MAX+1][MAX+1];
        for (int r=0;r<=MAX;r++) {
            for (int c=0;c<=MAX;c++) {
                if (paint[r][c] == K) paintOver[r][c]=-1;
                else if (paint[r][c]==K-1) paintOver[r][c]=1;
            }
        }
        if (!submission) printArr(paintOver, "paintOver");

        //create paintover presum array so that summing column ranges cost O(1)
        paintOverPreSum = new int[MAX+1][MAX+1];
        for (int r=0;r<=MAX;r++) {
            for (int c=0;c<=MAX;c++) {
                if (c > 0) paintOverPreSum[r][c] = paintOverPreSum[r][c-1];
                paintOverPreSum[r][c] += paintOver[r][c];
            }
        }
        if (!submission) printArr(paintOverPreSum, "paintOverPreSum");

        //c, l/r
        int[][] bestRectangleC = new int[MAX+1][2];
        //r, t/d
        int[][] bestRectangleR = new int[MAX+1][2];
        //for every 2 column bounds, find the best rectangles and store them
        for (int l=0;l<=MAX;l++) {
            for (int r=l;r<=MAX;r++) {
                int runsum = 0;
                int t=0;
                for (int b=0;b<=MAX;b++) {
                    runsum += paintOverSum(l,r,b);
                    while (runsum < 0) {
                        runsum -= paintOverSum(l,r,t);
                        t++;
                    }
                    bestRectangleC[l][1] = Math.max(bestRectangleC[l][1], runsum);
                    bestRectangleC[r][0] = Math.max(bestRectangleC[r][0], runsum);
                    bestRectangleR[t][1] = Math.max(bestRectangleR[t][1], runsum);
                    bestRectangleR[b][0] = Math.max(bestRectangleR[b][0], runsum);
                }
            }
        }
        if (!submission) {
            printArr(bestRectangleR, "bestRectangleR");
            printArr(bestRectangleC, "bestRectangleC");
        }

        //c, l/r
        int[][] bestRectangleC2 = new int[MAX+1][2];
        bestRectangleC2[0][0] = bestRectangleC[0][0];
        bestRectangleC2[MAX][1] = bestRectangleC[MAX][1];
        for (int c=1;c<=MAX;c++) {
            bestRectangleC2[c][0] = Math.max(bestRectangleC2[c-1][0], bestRectangleC[c][0]);
            bestRectangleC2[MAX-c][1] = Math.max(bestRectangleC2[MAX-c+1][1], bestRectangleC[MAX-c][1]);
        }
        //r, t/d
        int[][] bestRectangleR2 = new int[MAX+1][2];
        bestRectangleR2[0][0] = bestRectangleR[0][0];
        bestRectangleR2[MAX][1] = bestRectangleR[MAX][1];
        for (int r=1;r<=MAX;r++) {
            bestRectangleR2[r][0] = Math.max(bestRectangleC2[r-1][0], bestRectangleC[r][0]);
            bestRectangleC2[MAX-r][1] = Math.max(bestRectangleC2[MAX-r+1][1], bestRectangleC[MAX-r][1]);
        }
        if (!submission) {
            printArr(bestRectangleR2, "bestRectangleR2");
            printArr(bestRectangleC2, "bestRectangleC2");
        }
        //turn in answer
        out.println();
        out.close();
    }
    public static int paintOverSum(int l, int r, int row){
        int ret = 0;
        if (l > 0) ret -= paintOverPreSum[row][l-1];
        ret += paintOverPreSum[row][r];
        return ret;
    }
    public static void printArr(int[][] arr, String label){
        System.out.println(label);
        for (int r=0;r<=Math.min(arr.length-1,PRINT_MAX);r++) {
            for (int c=0;c<=Math.min(arr[0].length-1,PRINT_MAX);c++) {
                if (arr[r][c] >= 0) System.out.print(" ");
                System.out.print(arr[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
