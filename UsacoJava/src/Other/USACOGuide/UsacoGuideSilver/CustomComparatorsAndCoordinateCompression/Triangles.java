package Other.USACOGuide.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;

import java.io.*;
import java.util.*;
/*
USACO 2020 February Contest, Silver
Problem 2. Silver.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression.Triangles
USACO Silver Guide - Sorting - Hard
Concepts: crafty counting, one to one correspondence, flip (x,y) of points to avoid repetition
 */
public class Triangles {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Point[] farm;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("triangles.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        farm = new Point[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            farm[i] = new Point(x,y);
        }
        //logic
        for (int k=0;k<2;k++) {
            //sort points based on x coordinate
            Arrays.sort(farm, (a, b) -> {
                if (a.x == b.x) return a.y - b.y;
                return a.x - b.x;
            });
            //check points with same x coordinates
            //calculate top sum
            int tcnt = 1;
            for (int i = 0; i < N; i++) {
                if (i == 0 || farm[i].x != farm[i - 1].x) {
                    tcnt = 1;
                } else {
                    if (k==0) farm[i].tSum = farm[i - 1].tSum + tcnt * (farm[i].y - farm[i - 1].y);
                    if (k==1) farm[i].lSum = farm[i - 1].lSum + tcnt * (farm[i].y - farm[i - 1].y);
                    tcnt++;
                }
            }
            //calculate bottom sum
            int bcnt = 1;
            for (int i = N - 1; i >= 0; i--) {
                if (i == N - 1 || farm[i].x != farm[i + 1].x) {
                    bcnt = 1;
                } else {
                    if (k==0) farm[i].bSum = farm[i + 1].bSum + bcnt * (farm[i + 1].y - farm[i].y);
                    if (k==1) farm[i].rSum = farm[i + 1].rSum + bcnt * (farm[i + 1].y - farm[i].y);
                    bcnt++;
                }
            }
            for (int j=0;j<N;j++) {
                int tmp = farm[j].x;
                farm[j].x=farm[j].y;
                farm[j].y=tmp;
            }
        }
//        for (int i=0;i<N;i++) {
//            out.println(farm[i]);
//        }
        //turn in answer
        long ans = 0;
        for (int i=0;i<N;i++) {
            ans = ((ans + (farm[i].lSum+farm[i].rSum)*(farm[i].tSum+farm[i].bSum)) % ((long)1e9+7));
        }
        out.println(ans);
        out.close();
    }
    private static class Point {
        int x;
        int y;
        long lSum = 0;
        long rSum = 0;
        long tSum = 0;
        long bSum = 0;
        public Point(int x1, int y1){
            x=x1;
            y=y1;
        }
        public String toString(){
            return "["+x+", "+y+"]: "+tSum+", "+bSum+", "+lSum+", "+rSum;
        }
    }
}
