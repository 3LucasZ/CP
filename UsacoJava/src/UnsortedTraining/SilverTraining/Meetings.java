package UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;

/*
USACO 2019 December Contest, Silver
Problem 2. Meetings
USACO Silver Training
USACO Custom Comparators + Coordinate Compression - VERY HARD (but I think it's hard, not very)
Notes:
Looked at solution (not code) multiple times until finally understood
each cow is a left or right vector, this vector changes weights as it intersects but it reaches the end at time Q
Diagram on Notability
 */
public class Meetings {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int L;
    static Cow[] cows;
    static int totWeight = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("meetings.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        cows = new Cow[N];
        int numLeft = 0;
        int numRight = 0;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (d == -1) numLeft++;
            else numRight++;
            totWeight += w;
            cows[i] = new Cow(w,x,d);
        }
        //sort by x
        Arrays.sort(cows, (a,b)->a.x-b.x);
        if (!submission) System.out.println(Arrays.toString(cows));
        //logic
        //find all events by time
        //first Z logic
        PriorityQueue<Event> events = new PriorityQueue<>((a,b)->a.t-b.t);
        int l; int r;
        r=0;
        for (l=0;l<numLeft;l++) {
            while (cows[r].d==1) r++;
            events.add(new Event(cows[l].w, cows[r].x));
            r++;
        }
        if (!submission) System.out.println(events);
        //last N-Z logic
        l=N-1;
        for (r=N-1;r>=numLeft;r--) {
            while (cows[l].d==-1)l--;
            events.add(new Event(cows[r].w, L-cows[l].x));
            l--;
        }
        if (!submission) System.out.println(events);
        //find T  (cows just finished moving)
        int netWeight = 0;
        int time = 0;
        while (netWeight < totWeight/2) {
            Event now = events.poll();
            netWeight += now.w;
            time = now.t;
        }
        if (!submission) System.out.println(time);
        //during T seconds how many meets?
        PriorityQueue<Cow> active = new PriorityQueue<>((a,b)->a.x-b.x);
        int ans = 0;
        for (int i=0;i<N;i++) {
            if (cows[i].d==1) active.add(cows[i]);
            else {
                while (!active.isEmpty() && cows[i].x-active.peek().x>2*time) active.poll();
                ans += active.size();
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Event {
        int w;
        int t;
        public Event (int w, int t){
            this.w=w;
            this.t=t;
        }
        public String toString(){
            return "["+t+": "+w+"]";
        }
    }
    private static class Cow {
        int w;
        int x;
        int d;
        public Cow(int w,int x,int d){
            this.w=w;
            this.x=x;
            this.d=d;
        }
        public String toString(){
            return "["+x+": "+w+", "+d+"]";
        }
    }
}
