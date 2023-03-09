package Solutions.USACO.Season2019_2020.Feb2020.Bronze;

import java.util.*;
import java.io.*;
/*
USACO 2020 February Contest, Bronze
Problem 1. Silver.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression.Triangles
Speedrun
 */
public class Triangles {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static Point[] points;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("triangles.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        points = new Point[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        //logic
        int max = 0;
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++){
                for (int k=j+1;k<n;k++) {
                    int parX = 0;
                    int parY = 0;
                    if(parX == 0)
                    parX = (points[i].parX(points[j]));
                    if(parX == 0)
                    parX = (points[i].parX(points[k]));
                    if(parX == 0)
                    parX = (points[j].parX(points[k]));

                    if(parY == 0)
                    parY = (points[i].parY(points[j]));
                    if(parY == 0)
                    parY = (points[i].parY(points[k]));
                    if(parY == 0)
                    parY = (points[j].parY(points[k]));
                    if (parX > 0 && parY > 0) max = Math.max(max, parX * parY);
                    //out.println("parX: " + parX + " parY: " + parY);
                }
            }
        }
        //turn in answer
        out.println(max);
        out.close();
        f.close();
    }
    public static class Point {
        int x;
        int y;
        public Point(int x1, int y1) {
            x=x1;
            y=y1;
        }
        public int parY(Point o) {
            if(x==o.x)return Math.abs(y-o.y);
            else return 0;
        }
        public int parX(Point o) {
            if(y==o.y)return Math.abs(x-o.x);
            else return 0;
        }
    }
}
