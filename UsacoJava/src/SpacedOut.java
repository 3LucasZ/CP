import java.io.*;
import java.util.StringTokenizer;

//USACO 2021 January Contest, Silver
//        Problem 3. Spaced Out
//Type: Bronze Very Hard Ad Hoc
public class SpacedOut {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int [][] beautyArr;

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            n = Integer.parseInt(f.readLine());
            beautyArr = new int[n][n];
            for (int i=0;i<n;i++) {
                StringTokenizer st = new StringTokenizer(f.readLine());
                for (int j=0;j<n;j++) {
                    beautyArr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        else {
            n = 4;
            beautyArr = new int[][]{{3, 3, 1, 1},
                                    {1, 1, 3, 1},
                                    {3, 3, 1, 1},
                                    {1, 1, 3, 3}};
        }
        //logic
        //print2DArr(beautyArr);
        int rowMaxSum = 0;
        for (int r=0;r<n;r++) {
            int oddSum = 0;
            int evenSum = 0;
            for (int c=0;c<n;c++) {
                if (c%2 == 0) evenSum += beautyArr[r][c];
                else oddSum += beautyArr[r][c];
            }
            rowMaxSum += Math.max(oddSum, evenSum);
        }
        int colMaxSum = 0;
        for (int c=0;c<n;c++) {
            int oddSum = 0;
            int evenSum = 0;
            for (int r=0;r<n;r++) {
                if (r%2 == 0) evenSum += beautyArr[r][c];
                else oddSum += beautyArr[r][c];
            }
            colMaxSum += Math.max(oddSum, evenSum);
        }
        //System.out.println(rowMaxSum);
        //System.out.println(colMaxSum);
        //print
        if (submission) {
            out.println(Math.max(rowMaxSum, colMaxSum));
            f.close();
            out.close();
        }
        else {
            //System.out.println(Math.max(rowMaxSum, colMaxSum));
        }
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
