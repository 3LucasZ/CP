package Solutions.USACOGuide.UsacoGuideSilver.PrefixSums;/*
USACO 2019 February Contest, Silver
Problem 2. Painting the Barn
USACO Silver Guide: More on Prefix Sums - Easy
Concepts: constructor 2d prefix sum
 */

import java.io.*;
import java.util.*;
public class PaintingTheBarn {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int n;
    static int k;
    static int[][] barn = new int[1001][1001];
    static int[][] sum = new int[1001][1001];
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
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            barn[x1][y1]++;
            barn[x1][y2]--;
            barn[x2][y1]--;
            barn[x2][y2]++;
        }
//        for (int i=0;i<10;i++) {
//            for (int j=0;j<10;j++) {
//                if (barn[i][j] < 0) out.print(barn[i][j]);
//                else out.print(" "+barn[i][j]);
//            }
//            out.println();
//        }
//        out.println();
        //logic
        int ans = 0;
        for (int r=0;r<=1000;r++) {
            for (int c=0;c<=1000;c++) {
                int num = barn[r][c];
                if (c!=0)num+=sum[r][c-1];
                if (r!=0)num+=sum[r-1][c];
                if (r!=0&&c!=0)num-=sum[r-1][c-1];
                sum[r][c] = num;
                if (num == k) ans++;
            }
        }
//        for (int i=0;i<10;i++) {
//            for (int j=0;j<10;j++) {
//                out.print(sum[i][j]);
//            }
//            out.println();
//        }
//        out.println();
        //turn in answer
        out.println(ans);
        out.close();
    }
}
