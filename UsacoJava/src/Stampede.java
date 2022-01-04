

import java.io.*;
import java.util.*;
/*
USACO 2015 January Contest, Silver
Problem 1. Stampede
USACO Silver Training
INCOMPLETE
 */
public class Stampede {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Cow[] cows;
    static Interval[] intervals;
    static TreeSet<Interval> intervals2 = new TreeSet<>((a,b)->a.t1-b.t1);
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
        cows = new Cow[N];
        intervals = new Interval[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(x,y,r);
        }
        //sort by priority
        Arrays.sort(cows, (a,b)->a.y-b.y);
        //out.println(Arrays.toString(cows));
        //calculate all intervals
        for (int i=0;i<N;i++) {
            int distance = -1 * cows[i].x;
            int t2 = distance * cows[i].r;
            int t1 = t2 - cows[i].r;
            Interval add = new Interval(t1, t2, i);
            intervals[i] = add;
        }
        //calculate all disjoint intervals
        Arrays.sort(intervals, (a,b)->{
            if (a.t1==b.t1) return a.priority-b.priority;
            return a.t1-b.t1;
        });
        PriorityQueue<Interval> active = new PriorityQueue<Interval>((a, b)->a.priority-b.priority);
        HashSet<Interval> seen = new HashSet<>();

        int ans = 1;
        active.add(intervals[0]);
        seen.add(intervals[0]);
        int i=1;
//        while (true){
//            active.add(intervals[i]);
//
//        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Interval{
        int t1;
        int t2;
        int priority;
        public Interval(int ti, int te, int p){
            t1=ti;
            t2=te;
            priority = p;
        }
        public String toString() {
            return "["+t1+", "+t2+"]";
        }
    }
    private static class Cow{
        int x;
        int y;
        int r;
        public Cow(int x1, int y1, int r1){
            x=x1;
            y=y1;
            r=r1;
        }
        public String toString(){
            return "["+x+", "+y+", "+r+"]";
        }
    }
}
