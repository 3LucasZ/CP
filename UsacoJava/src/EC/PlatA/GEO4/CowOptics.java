package EC.PlatA.GEO4;

import java.io.*;
import java.util.*;

public class CowOptics {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static Point begin = new Point(0,0);
    static Point end;
    static Point[] points;

    static TreeSet<Point> pointsXY;
    static TreeSet<Point> pointsYX;
    static boolean[] directionVisited;

    public static void main(String[] args) throws IOException {
        //parse
        setup("optics");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        points = new Point[N+2];
        points[N]=begin;
        points[N+1]=end;
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char mirror = st.nextToken().charAt(0);
            points[i] = new Point(x,y,mirror);
        }

        //coordinate compress
        Point[] tmpValues = new Point[N+2]; for (int i = 0; i<N+2; i++) tmpValues[i]=new Point(1,1);

        Arrays.sort(points, (a, b)->a.x-b.x);
        for (int i=1;i<N+2;i++) {
            if (points[i].x == points[i-1].x) tmpValues[i].x=tmpValues[i-1].x;
            else tmpValues[i].x=tmpValues[i-1].x+1;
        } for (int i=0;i<N+2;i++) points[i].x=tmpValues[i].x;

        Arrays.sort(points, (a,b)->a.y-b.y);
        for (int i=1;i<N+2;i++) {
            if (points[i].y == points[i-1].y) tmpValues[i].y=tmpValues[i-1].y;
            else tmpValues[i].y=tmpValues[i-1].y+1;
        } for (int i=0;i<N+2;i++) points[i].y=tmpValues[i].y;

        if (debug) System.out.println(Arrays.toString(points));

        //xy points: sorted by x and then y
        pointsXY = new TreeSet<>((a,b)->{
            if (a.x==b.x) return a.y-b.y;
            return a.x-b.x;
        }); for (int i=0;i<N+2;i++) pointsXY.add(points[i]);

        //yx points: sorted by y and then x
        pointsYX = new TreeSet<>((a,b)->{
            if (a.y==b.y) return a.x-b.x;
            return a.y-b.y;
        }); for (int i=0;i<N+2;i++) pointsYX.add(points[i]);

        //RED path
        ArrayList<Segment> redHorizontal = new ArrayList<>();
        ArrayList<Segment> redVertical = new ArrayList<>();
        generatePath(begin, 0, redHorizontal, redVertical);

        //BLUE path
        ArrayList<Segment> blueHorizontal = new ArrayList<>();
        ArrayList<Segment> blueVertical = new ArrayList<>();
        directionVisited = new boolean[4];
        for (int i=0;i<4;i++){
            if (!directionVisited[i]) generatePath(end,i,blueHorizontal,blueVertical);
            if (debug) System.out.println(Arrays.toString(directionVisited));
        }

        if (debug){
            System.out.println(redHorizontal);
            System.out.println(redVertical);

            System.out.println(blueHorizontal);
            System.out.println(blueVertical);
        }

        //ret
        out.println(intersections(blueHorizontal,redVertical)+intersections(redHorizontal,blueVertical));
        out.close();
    }
    public static int intersections(ArrayList<Segment> horiz, ArrayList<Segment> vert){
        //count intersections with a BIT and sweepline
        //let vert be queries and horiz be events
        PriorityQueue<Event> events = new PriorityQueue<>((a,b)->a.x-b.x);
        //create events
        for (Segment h : horiz){
            events.add(new Event(h.a.x,h.a.y));
            events.add(new Event(h.b.x,h.b.y));
        }
        for (Segment v : vert){
            events.add(new Event(v.a.x,v.a.y,v.b.y));
        }
        if (debug) System.out.println("Events: "+events);
        //sweep
        BIT active = new BIT(N+5);
        int ret = 0;
        while (!events.isEmpty()) {
            Event next = events.poll();
            if (next.isHoriz){
                //remove event
                if (active.A[next.y]==1){
                    active.set(next.y,0);
                }
                //add event
                else {
                    active.set(next.y,1);
                }
            } else {
                //perform smart query
                if (next.y2-next.y>1){
                    ret += active.rangeSum(next.y+1,next.y2-1);
                }
            }
        }
        return ret;
    }
    public static void generatePath(Point start, int direction, ArrayList<Segment> horiz, ArrayList<Segment> vert){
        Point next = start;
        //direction 0: north, 1: east, 2: south, 3: west
        Point to;
        while (true){
            if (direction == 0){
                //find to
                to = pointsXY.higher(next);
                if (to == null || next.x!=to.x) to = null;

                //create new segment
                if (to == null) vert.add(new Segment(next,new Point(next.x,N+3)));
                else vert.add(new Segment(next,to));

                //break
                if (to==null||to==end) break;

                //change direction
                if (to.mirror=='/') direction=1;
                else direction=3;
            } else if (direction == 1){
                //find to
                to = pointsYX.higher(next);
                if (to == null || next.y!=to.y) to = null;

                //create new segment
                if (to==null) horiz.add(new Segment(next,new Point(N+3,next.y)));
                else horiz.add(new Segment(next,to));

                //break
                if (to==null||to==end) break;

                //change direction
                if (to.mirror=='/') direction=0;
                else direction=2;
            } else if (direction == 2){
                //find to
                to = pointsXY.lower(next);
                if (to == null || next.x!=to.x) to = null;

                //create new segment
                if (to==null) vert.add(new Segment(new Point(next.x, 0), next));
                else vert.add(new Segment(to,next));

                //break
                if (to==null||to==end) break;

                //change direction
                if (to.mirror=='/') direction=3;
                else direction=1;
            } else {
                //find to
                to = pointsYX.lower(next);
                if (to == null || next.y!=to.y) to = null;

                //create new segment
                if (to==null) horiz.add(new Segment(new Point(0, next.y),next));
                else horiz.add(new Segment(to,next));

                //break
                if (to==null||to==end) break;

                //change direction
                if (to.mirror=='/') direction=2;
                else direction=0;
            }
            next=to;
        }
        next=to;
        //next starts and ends at barn: don't overcount
        if (next==end) directionVisited[(direction+2)%4]=true;
    }

    private static class Event {
        boolean isHoriz;
        int x;
        int y;
        int y2;
        public Event(int x, int y){
            this.x=x;
            this.y=y;
            isHoriz = true;
        }
        public Event(int x,int y,int y2){
            this.x=x;
            this.y=y;
            this.y2=y2;
            isHoriz = false;
        }
        public String toString(){
            if (isHoriz) return "["+x+", "+y+"]";
            else return "["+x+": "+y+", "+y2+"]";
        }
    }
    private static class Segment {
        Point a;
        Point b;
        public Segment(Point a, Point b){
            this.a=a;
            this.b=b;
        }
        public String toString(){
            return "["+a+", "+b+"]";
        }
    }
    private static class Point {
        int id;
        int x;
        int y;
        char mirror;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public Point(int x, int y, char mirror){
            this.x=x;
            this.y=y;
            this.mirror=mirror;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
    private static class BIT {
        //must be 1 indexed
        //range sum is inclusive
        int K;
        int[] A;
        int[] bit;

        public BIT(int K){
            this.K=K;
            bit = new int[K+1];
            A = new int[K+1];
        }

        public void set(int i, int val){
            update(i, val-A[i]);
        }

        public void update(int i, int val){
            A[i]+=val;
            while (i <= K){
                bit[i]+=val;
                i += i&(-i);
                //System.out.println("STUCK IN UPDATE");
            }
        }

        public int preSum(int i){
            int sum=0;
            while (i > 0){
                sum += bit[i];
                i -= i&(-i);
                //System.out.println("STUCK IN PRESUM");
            }
            return sum;
        }

        public int rangeSum(int lo, int hi){
            return preSum(hi)-preSum(lo-1);
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
