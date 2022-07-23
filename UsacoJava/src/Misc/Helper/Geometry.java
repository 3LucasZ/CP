package Misc.Helper;

public class Geometry {
    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
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
    }
    private static class Line {
        Point p1;
        Point p2;
        public Line(Point p1, Point p2){
            this.p1=p1;
            this.p2=p2;
        }
        public static boolean intersect(Line l1, Line l2){
            int test1, test2;
            test1 = Point.ccw(l1.p1,l1.p2,l2.p1)
                    * Point.ccw(l1.p1,l1.p2,l2.p2);
            test2 = Point.ccw(l2.p1,l2.p2,l1.p1)
                    * Point.ccw(l2.p1,l2.p2,l1.p2);
            return test1<=0 && test2<=0;
        }
    }

}
