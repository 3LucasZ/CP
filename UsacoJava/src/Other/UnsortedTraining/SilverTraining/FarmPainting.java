package Other.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2013 March Contest, Silver
Problem 2. Farm Painting
USACO Silver Training
- Had to read Solution
- Beautiful plane sweep algorithm
- shooting rays
 */
public class FarmPainting {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Point[] points;
    static TreeSet<Point> active = new TreeSet<Point>((a,b)->a.y-b.y);
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("painting.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("painting.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        points = new Point[2*N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            Point u = new Point(x1,y1,true);
            Point v = new Point(x2,y2,false);
            u.other = v;
            v.other = u;
            points[2*i] = u;
            points[2*i+1] = v;
        }
        //sort
        Arrays.sort(points, (a,b)->a.x-b.x);
        //logic
        int ans = 0;
        for (int i=0;i<2*N;i++) {
            if (points[i].isBottom) {
                if (active.ceiling(points[i])==null || active.ceiling(points[i]).isBottom) {
                    ans ++;
                    active.add(points[i]);
                    active.add(points[i].other);
                }
            }
            else {
                active.remove(points[i]);
                active.remove(points[i].other);
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Point {
        int x;
        int y;
        boolean isBottom;
        Point other;
        public Point(int x, int y, boolean isBottom){
            this.x=x;
            this.y=y;
            this.isBottom = isBottom;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}
