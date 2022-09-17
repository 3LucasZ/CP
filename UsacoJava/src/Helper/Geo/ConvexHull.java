package Helper.Geo;

import java.io.*;
import java.util.*;

public class ConvexHull {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static ArrayList<Point> points;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        while (true){
            N = Integer.parseInt(br.readLine());
            if (N==0) break;
            points = new ArrayList<>();
            for (int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points.add(new Point(x,y));
            }

            //hull
            ArrayList<Point> hull = convexHull(points,points.size());
            //ret
            out.println(hull.size()-1);
            for (int i=0;i<hull.size()-1;i++){
                out.println(hull.get(i).x+" "+hull.get(i).y);
            }
        }
        out.close();
    }

    //START
    static Point p0;
    public static ArrayList convexHull(ArrayList<Point> points, int N){
        //get point with least y,x
        p0 = points.get(0);
        for (Point p : points){
            if (p==null) continue;
            if (p.y < p0.y) p0=p;
            else if (p.y==p0.y && p.x < p0.y) p0=p;
        }
        Point.swap(points.get(0),p0);
        if (debug) System.out.println("Ref: "+p0);

        //sort with custom comparator
        //priority: smallest polar angle, smallest dist
        GrahamScanOrder pointComparator = new GrahamScanOrder();
        Collections.sort(points,pointComparator);

        //remove points with same angle, keep the farthest distance
        ArrayList<Point> points2 = new ArrayList<>();
        points2.add(p0);
        for (int i=0;i<N;i++){
            if (points.get(i).equals(p0)) continue;
            while (i<N-1&&Point.ccw(p0,points.get(i),points.get(i+1))==0)i++;
            points2.add(points.get(i));
        }

        //clean up
        points=points2;
        points.add(new Point(p0.x,p0.y));
        int M = points.size();
        if (debug) System.out.println("Sorted by polar: "+points);

        //return dummies
        if (M <= 4) return points;

        //graham scan
        Stack<Point> hull = new Stack<>();
        hull.add(points.get(0));
        hull.add(points.get(1));
        hull.add(points.get(2));
        for (int i=3;i<M;i++){
            Point c = points.get(i);
            while (hull.size()>2){
                Point b = hull.pop();
                Point a = hull.pop();
                //a,b,c work
                if (Point.ccw(a,b,c)>=0) {
                    hull.add(a);
                    hull.add(b);
                    break;
                }
                //not work
                hull.add(a);
            }
            hull.add(c);
        }

        //return the scan (with sentinel)
        ArrayList<Point> ret = new ArrayList<>(hull);
        return ret;
    }
    private static class Point {
        long x;
        long y;
        public Point(long x, long y){
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
    private static class GrahamScanOrder implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {

            int ccw = Point.ccw(p0,p1,p2);
            if (ccw==0){
                return Long.compare(p1.distSqr(p0),p2.distSqr(p0));
            }
            return -ccw;
        }
    }
    //END


















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
