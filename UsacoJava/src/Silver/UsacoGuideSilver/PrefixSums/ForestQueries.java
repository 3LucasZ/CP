package Silver.UsacoGuideSilver.PrefixSums;

import java.io.*;
import java.util.StringTokenizer;

/*
CSES Problem Set
Forest Queries
USACO Guide Silver More on Prefix Sums Easy
Concept: 2D array prefix sum :)
 */
public class ForestQueries {
    static BufferedReader f;
    static PrintWriter out;
    static int n;
    static int q;
    static int[][] prefixSum;
    public static void main(String args[]) throws IOException {
        f = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        prefixSum = new int[n+1][n+1];
        for (int r=0;r<n;r++) {
            String str = f.readLine();
            for (int c=0;c<n;c++) {
                prefixSum[r+1][c+1] = prefixSum[r][c+1] + prefixSum[r+1][c] - prefixSum[r][c];
                if (str.charAt(c) == '*') prefixSum[r+1][c+1]++;
            }
        }
        //print2DArr(prefixSum);
        for (int i=0;i<q;i++) {
            st = new StringTokenizer(f.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            out.println(prefixSum[r2][c2]-prefixSum[r1-1][c2]-prefixSum[r2][c1-1]+prefixSum[r1-1][c1-1]);
        }
        out.close();
        f.close();
    }
    public static void print2DArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
}
