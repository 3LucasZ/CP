package EC.PlatA1.GEO5;

import java.io.*;
import java.util.*;

public class BalanceBeam {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static ArrayList<Point> points = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //parse
        setup("balance");
        N = Integer.parseInt(br.readLine());
        points.add(new Point(0,0));
        for (int i=1;i<=N;i++){
            points.add(new Point(i,Integer.parseInt(br.readLine())));
        }
        points.add(new Point(N+1,0));

        //get hull (cw order)
        Stack<Point> hull = new Stack<>();
        hull.add(points.get(0));
        hull.add(points.get(1));
        for (int i=2;i<=N+1;i++){
            while (hull.size()>=2) {
                Point b = hull.pop();
                Point a = hull.pop();
                Point c = points.get(i);
                //good
                if (Point.ccw(a,b,c)<=0) {
                    hull.add(a);
                    hull.add(b);
                    break;
                }
                //bad
                else {
                    hull.add(a);
                }
            }
            hull.add(points.get(i));
        }
        ArrayList<Point> cHull = new ArrayList<>(hull);
        for (Point p : cHull) p.y *= 100000L;
        if (debug) System.out.println(cHull);

        //for each int x find its y on the hull
        Point[] l = new Point[N+2];
        Point[] r = new Point[N+2];
        for (int i=0;i<cHull.size()-1;i++){
            for (int m=cHull.get(i).x;m<=cHull.get(i+1).x;m++){
                l[m]=cHull.get(i);
                r[m]=cHull.get(i+1);
            }
        }
        for (int i=1;i<=N;i++){
            long ans;
            if (l[i].y==r[i].y) ans = l[i].y;
            else {
                long dy = r[i].y-l[i].y;
                long dx = r[i].x-l[i].x;
                long b = l[i].y;

                //positive sloping
                if (l[i].y<r[i].y){
                    ans = l[i].y+((r[i].y-l[i].y)*(i-l[i].x))/(r[i].x-l[i].x);
                }
                //negative sloping
                else {
                    ans = r[i].y+((l[i].y-r[i].y)*(r[i].x-i))/(r[i].x-l[i].x);
                }
            }
            out.println(ans);
        }

        out.close();
    }
    private static class Point {
        //ops
        int x;
        long y;
        public Point(int x, long y){
            this.x=x;
            this.y=y;
        }
        public long distSqr(Point o){
            return (this.x-o.x)*(this.x-o.x)+(this.y-o.y)*(this.y-o.y);
        }
        public double distanceTo(Point o){
            return Math.hypot(this.x-o.x,this.y-o.y);
        }
        public double cos(Point to){
            return (to.x-this.x)/this.distanceTo(to);
        }

        //static
        public static void swap(Point a, Point b){
            Point aCopy = a.copy();
            Point bCopy = b.copy();
            a=bCopy;
            b=aCopy;
        }
        public static int ccw(Point a, Point b, Point c){
            long area = (b.x - a.x) *(c.y-a.y) - (b.y - a.y) *(c.x-a.x);
            if (area < 0) return -1;
            else if (area > 0) return 1;
            else return 0;
        }

        //generic
        public String toString(){
            return "["+x+", "+y+"]";
        }
        public boolean equals(Point o){
            return x==o.x && y==o.y;
        }
        public Point copy() {
            return new Point(this.x,this.y);
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
