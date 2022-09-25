package Training.SilverTraining;

import java.io.*;
import java.util.*;

public class CowSteeplechase2 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static Point[] points;
    static Seg[] segments;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cowjump.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        segments = new Seg[N+1];
        points = new Point[2*N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x1 = Integer.parseInt(st.nextToken()); long y1 = Integer.parseInt(st.nextToken());
            long x2 = Integer.parseInt(st.nextToken()); long y2 = Integer.parseInt(st.nextToken());
            Seg S = new Seg(new Point(x1, y1, i+1), new Point(x2, y2, i+1), i+1);
            if (S.v.x < S.u.x) {
                Point tmp = S.u;
                S.u=S.v;
                S.v=tmp;
            }
            segments[i+1]=S;
            points[2*i]=S.u;
            points[2*i+1]=S.v;
        }

        //sort by X
        Arrays.sort(points, Comparator.comparingLong(a -> a.x));

        //logic
        Seg ans1 = new Seg();
        Seg ans2 = new Seg();
        TreeSet<Point> active = new TreeSet<>(Comparator.comparingLong(a->a.y));

        for (int i=0;i<2*N;i++){
            if (!submission) System.out.println(active);
            Point add = points[i];

            //this is endpoint, first point already added
            if (active.contains(segments[add.id].u)) active.remove(segments[add.id].u);
            else active.add(add);

            Seg cur = segments[add.id];

            if (active.lower(add)!=null) {
                Seg under = segments[active.lower(add).id];
                if (cur.intersect(under)) {
                    ans1=cur;
                    ans2=under;
                    break;
                }
            }

            if (active.higher(add)!=null){
                Seg upper = segments[active.higher(add).id];
                if (cur.intersect(upper)) {
                    ans1=cur;
                    ans2=upper;
                    break;
                }
            }
        }
        if (ans1.id==0){
            out.println(-1);
            out.close();
            return;
        }

        int intersections = 0;
        if (ans1.id < ans2.id) swap(ans1,ans2);
        for (int i=1;i<=N;i++){
            if (i==ans1.id) continue;
            if (ans1.intersect(segments[i])) intersections++;
        }

        //turn in answer
        if (intersections > 1) out.println(ans1.id);
        else out.println(ans2.id);
        out.close();
    }

//    public static void geometryTester(){
//        Seg a = new Seg(new Point(7, 11), new Point(3, 3));
//        //Seg b = new Seg(new Point(1, 7), new Point(8, 1));
//        Seg b = new Seg(new Point(1, 7), new Point(1, 11));
//        System.out.println("Intersection: "+a.intersect(b));
//        System.out.println("Intersection: "+b.intersect(a));
//    }

    public static void swap(Object a, Object b){
        Object tmp = a;
        a=b;
        b=tmp;
    }
    public static int direction(Seg segment, Point p) {
        Point p1 = segment.v.subtract(segment.u);
        Point p2 = p.subtract(segment.u);
        long cross = crossProduct(p1, p2);
        if (cross > 0) return 1;
        if (cross < 0) return -1;
        return 0;
    }

    public static long crossProduct(Point p1, Point p2){
        return p1.x*p2.y-p2.x*p1.y;
    }

    private static class Point {
        int id;
        long x;
        long y;
        public Point(){

        }
        public Point(long x, long y, int i) {
            this.x = x;
            this.y = y;
            this.id =i;
        }
        public Point subtract(Point other){
            return new Point(x-other.x,y-other.y,-1);
        }
        public String toString(){
            return "["+id+": "+x+", "+y+"]";
        }
    }

    private static class Seg {
        int id;
        Point u;
        Point v;
        public Seg() {

        }
        public Seg(Point u, Point v, int i){
            this.u=u;
            this.v=v;
            this.id =i;
        }

        public boolean intersect(Seg other){
            return direction(this, other.u)!=direction(this, other.v)
                    && direction(other, this.u) != direction(other, this.v);
        }
    }
}
