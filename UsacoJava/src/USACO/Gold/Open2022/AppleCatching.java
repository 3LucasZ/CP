package USACO.Gold.Open2022;

import java.io.*;
import java.util.*;
public class AppleCatching {

    //io
    static boolean submission = false;
    static boolean debug = false;

    //param
    static int N;
    static ArrayList<Event> events = new ArrayList<>();

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        setup("USACOJava/io/tmp");

        //parse input
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            Event e = new Event(q,t,x,n);
            events.add(e);
        }
        if (debug) out.println(events);

        //logic
        //integer 45 degree clockwise transformation
        for (Event e : events){
            int y = e.t;
            int x = e.x;
            e.x = x+y;
            e.t = y-x;
        }
        if (debug) out.println(events);

        //sort events by x,y
        Collections.sort(events, (a,b)->{
            if (a.x==b.x)return a.t-b.t;
            return a.x-b.x;
        });

        //keep track of running cows by y,x
        TreeSet<Event> set = new TreeSet<>((a,b)->{
            if (a.t==b.t)return a.x-b.x;
            return a.t-b.t;
        });

        //greedy line sweep
        for (Event e : events){
            //handle cow event
            if (e.type==1)set.add(e);
            //handle apple event
            else {
                if (debug) out.println("TreeSet: "+set);
                if (debug) out.println("Processing: "+e);
                while (e.n > 0){
                    Event lower = set.floor(e);
                    if (lower==null) break;
                    int dump = Math.min(e.n,lower.n);
                    lower.n-=dump;
                    e.n-=dump;
                    if (lower.n==0) set.remove(lower);
                    ans += dump;
                }
                if (debug) out.println("New ans: "+ans);
            }
        }


        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Event {
        int type;
        int n;
        int t;
        int x;
        public Event(int type, int t, int x, int n){
            this.type=type;
            this.t=t;
            this.x=x;
            this.n=n;
        }
        public String toString() {
            return "["+(type==1?"Cow":"Apple")+" at "+x+", "+t+" with "+n+"]";
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
