import java.io.*;
import java.util.*;
/*
USACO 2015 January Contest, Silver
Problem 1. Stampede
USACO Silver Training
Thoughts:
Read solution :(
Interval processing
Plane sweep with points
each point denotes the time to process it and its priority
its mode (start vs end) is encoded by its sign (very smart technique)
sweeping through these points sorted by time
if point is a start -> add it to active
if point is a end -> delete interval from active
what is the smallest y value in active? add it to seen
very elegant and concise solution!
Another observation: only process "interesting points" (start and end points)
 */
public class Stampede {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("stampede.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        Point[] events = new Point[2*N];
        for (int i=0;i<2*N;i+=2){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = -1*Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            events[i]=new Point((x-1)*r,y);
            events[i+1]=new Point(x*r,-y);
        }
        //sort events by time
        Arrays.sort(events,(a,b)->{
            return a.x-b.x;
        });
        if (!submission) System.out.println(Arrays.toString(events));

        //plane sweep
        HashSet<Integer> seen = new HashSet<>();
        TreeSet<Integer> active = new TreeSet<>();

        for (int i=0;i<events.length-1;i++){
            //process
            if (events[i].y>0) active.add(events[i].y);
            else active.remove(-1*events[i].y);
            //interpret active
            if (!submission) System.out.println(active);
            //** mistake: remember to use the Math.abs(events[i].x)!=Math.abs(events[i+1].x)
            // in the if statement so that we process all t=x events before interpreting active
            if (!active.isEmpty() && Math.abs(events[i].x)!=Math.abs(events[i+1].x))seen.add(active.first());
        }
        out.println(seen.size());
        out.close();
    }
    private static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}
