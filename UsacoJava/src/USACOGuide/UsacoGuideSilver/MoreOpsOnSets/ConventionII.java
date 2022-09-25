package USACOGuide.UsacoGuideSilver.MoreOpsOnSets;

import java.util.*;
import java.io.*;

public class ConventionII {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static PriorityQueue<Cow> arrival = new PriorityQueue<>((a,b)->a.arrival-b.arrival);
    static PriorityQueue<Cow> waiting = new PriorityQueue<>((a,b)->a.seniority-b.seniority);
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("convention2.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            arrival.add(new Cow(i, a, t));
        }
        //logic
        int maxWait = 0;
        int runtime = 0;
        while (arrival.peek()!=null || waiting.peek()!=null) {
            while (arrival.peek()!=null && arrival.peek().arrival < runtime) {
                waiting.add(arrival.poll());
            }
            Cow next = waiting.poll();
            if (next == null) next = arrival.poll();
            int wait = Math.max(0, runtime-next.arrival);
            maxWait = Math.max(maxWait, wait);
            runtime = Math.max(runtime + next.time, next.arrival+next.time);

//            out.println("Processed cow: " + next);
//            out.println("runtime: " + runtime);
//            out.println("cow wait: " + wait);
        }
        //turn in answer
        out.println(maxWait);
        out.close();
    }
    private static class Cow {
        int seniority;
        int arrival;
        int time;
        public Cow(int s, int a, int t){
            seniority = s;
            arrival = a;
            time = t;
        }
        public String toString(){
            return "[seniority: " + seniority + ", arrival: " + arrival + ", time:" + time + "]";
        }
    }
}
