package Platinum.EC.GEO5;

import java.io.*;
import java.util.*;

public class CowCurling {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static ArrayList<Point> red = new ArrayList<>();
    static ArrayList<Point> blue = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //parse
        setup("curling");
        N = Integer.parseInt(br.readLine());

        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            red.add(new Point(x,y));
        }
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            blue.add(new Point(x,y));
        }

        //calc blues in red
        ArrayList<Point> redHull = convexHull(red,red.size());
        if (debug) System.out.println(redHull);
        out.print(pointsInHull(redHull, blue));

        out.print(" ");

        //calc reds in blue
        ArrayList<Point> blueHull = convexHull(blue,blue.size());
        if (debug) System.out.println(blueHull);
        out.print(pointsInHull(blueHull,red));

        out.close();
    }
    public static int pointsInHull(ArrayList<Point> hull, ArrayList<Point> points){
        int ret = 0;

        //create hull query structure
        Point ref = hull.get(0);
        TreeSet<Point> hullQuery = new TreeSet<>((a, b)->{
            double cosa = cos(ref,a);
            double cosb = cos(ref,b);
            //equal -> least distance
            if (Math.abs(cosa-cosb)<epsilon) return Double.compare(a.distanceTo(ref),b.distanceTo(ref));
            //else
            return Double.compare(cosb,cosa);
        });
        for (int i=1;i<hull.size()-1;i++){
            hullQuery.add(hull.get(i));
        }
        if (debug) System.out.println(hullQuery);
        //check each point p
        for (Point p : points){
            //out of checking range
            if (p.y < ref.y || (p.y==ref.y && p.x<ref.x)) continue;
            //get lower,higher
            Point lower = hullQuery.lower(p); if (lower==null) lower=ref;
            Point higher = hullQuery.higher(p); if (higher==null) higher=ref;
            //make the query
            if (Point.ccw(lower,higher,p)>0) ret++;
            else if (Point.ccw(lower,higher,p)==0 && p.distanceTo(ref) < higher.distanceTo(ref)) ret++;
        }

        //ret
        return ret;
    }

    static final double epsilon = 0.00000000000001;
    public static ArrayList convexHull(ArrayList<Point> points, int N){
        //get point with least y,x
        Point ref = points.get(0);
        for (Point p : points){
            if (p==null) continue;
            if (p.y < ref.y) ref=p;
            else if (p.y==ref.y && p.x < ref.y) ref=p;
        }
        if (debug) System.out.println("Ref: "+ref);

        //sort by cos (monotonic with angle) and then distance
        //priority: largest cos, smallest dist
        Point finalRef = ref;
        Collections.sort(points,(a, b)->{
            double cosa = cos(finalRef,a);
            double cosb = cos(finalRef,b);
            //equal
            if (Math.abs(cosa-cosb)<epsilon) return Double.compare(a.distanceTo(finalRef),b.distanceTo(finalRef));
            //else
            return Double.compare(cosb,cosa);
        });

        //remove points with same angle, keep the farthest distance
        ArrayList<Point> points2 = new ArrayList<>();
        points2.add(ref);
        for (int i=1;i<N;i++){
            while (i<N-1&&Point.ccw(ref,points.get(i),points.get(i+1))==0)i++;
            points2.add(points.get(i));
        }

        points=points2;
        //sentinel
        points.add(new Point(ref.x,ref.y));
        //cleanup
        int M = points2.size();
        if (debug) System.out.println("Sorted by polar: "+points);

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
    public static double cos(Point ref, Point to){
        return (to.x-ref.x)/ref.distanceTo(to);
    }
    private static class Point {
        long x;
        long y;
        public Point(long x, long y){
            this.x=x;
            this.y=y;
        }
        public double distanceTo(Point o){
            return Math.hypot(this.x-o.x,this.y-o.y);
        }
        public static int ccw(Point a, Point b, Point c){
            long area = (long) (b.x - a.x) *(c.y-a.y) - (long) (b.y - a.y) *(c.x-a.x);
            if (area < 0) return -1;
            else if (area > 0) return 1;
            else return 0;
        }
        public static boolean collinear(Point a, Point b, Point c){
            return ccw(a,b,c)==0;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
        public boolean equals(Point o){
            return x==o.x && y==o.y;
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