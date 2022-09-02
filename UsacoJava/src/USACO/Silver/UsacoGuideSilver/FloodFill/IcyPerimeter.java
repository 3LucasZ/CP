package USACO.Silver.UsacoGuideSilver.FloodFill;

import java.io.*;

public class IcyPerimeter {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static boolean[][] icecream;
    //logik
    static boolean[][] found;
    static int largestArea = 0;
    static int blobArea;
    static int smallestPerim = 0;
    static int blobPerim;
    static int numBlob = 0;
    //temp
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("perimeter.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        icecream = new boolean[n][n];
        found = new boolean[n][n];
        for (int r=0;r<n;r++) {
            String str = f.readLine();
            for (int c=0;c<n;c++) {
                icecream[r][c] = str.charAt(c) == '#';
            }
        }
        //logic
        for (int r=0;r<n;r++) {
            for (int c=0;c<n;c++) {
                if (!found[r][c] && icecream[r][c]) {
                    blobArea = 0;
                    blobPerim = 0;
                    floodfill(r, c);
                    if (blobArea > largestArea) {
                        largestArea = blobArea;
                        smallestPerim = blobPerim;
                    } else if (blobArea == largestArea) {
                        smallestPerim = Math.min(smallestPerim, blobPerim);
                    }
                }
            }
        }
        //turn in answer
        out.println(largestArea + " " + smallestPerim);
        out.close();
        f.close();
    }
    public static void floodfill(int r, int c) {
        found[r][c] = true;
        blobArea ++;
        blobPerim += 4;
        for (int i=0;i<4;i++) {
            int r2 = r+dx[i];
            int c2 = c+dy[i];
            if (r2 < 0 || c2 < 0 || r2 >= n || c2 >= n) {
                //terrible
            } else if (icecream[r2][c2]) {
                blobPerim -= 1;
                if (!found[r2][c2]) {
                    floodfill(r + dx[i], c + dy[i]);
                }
            }
        }
    }
    public static void printArr(boolean[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += ((arr[i] ? 1 : 0) + ", ");
        }
        System.out.println(str);
    }
    public static void print2DArr(boolean[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
        System.out.println();
    }
}
